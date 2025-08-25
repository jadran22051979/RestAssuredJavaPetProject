package tests;

import content.PetContent;
import enums.PetStatus;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Pet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import payload.PetPayload;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


@Feature("Pet tests")
@Epic("Pets Controller")
public class PetTests extends BaseTests {
    //Get from env variables API_KEY
    Dotenv dotenv = Dotenv.load();
    String apiKey = dotenv.get("API_KEY");
    String apiKeyValue = dotenv.get("API_KEY_VALUE");


    private static final Logger logger = LogManager.getLogger(PetTests.class);

    public static RequestSpecification request;
    private static final Pet petDto = PetPayload.petDto(PetStatus.available);
    static Integer petDtoId = petDto.getId();


    @AfterAll
    //Delete created Pet from Test (Good practice).Problems also on https://petstore.swagger.io/ sometimes
    @DisplayName("Delete pet")
    public static void deletePet() {
        logger.debug("Deleting Pet created in Test");
        request
                .param("petId", petDtoId)
                .when()
                .delete("/pet/" + petDtoId)
                .then().assertThat().statusCode(PetContent.STATUS_CODE_200)
                .log().all();


    }


    @BeforeEach
    void setRequest() {
        //Set API key in header
        request = given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .header(apiKey, apiKeyValue)
                .contentType(ContentType.JSON);


    }


    @Test
    @Order(1)
    @DisplayName("Add new pet")
    @Description("Adding new Pet and check of response status code and body")
    public void postAddNewPet_200() {
        request
                .body(petDto)
                .when()
                .post("/pet/")
                .then()
                .statusCode(PetContent.STATUS_CODE_200)
                .log().all()
                .header(PetContent.HEADER_CONTENT_TYPE, PetContent.HEADER_JSON)
                .header(PetContent.HEADER_ALLOW_METHODS, PetContent.HEADER_ALLOW_METHODS_TYPES)
                .body(PetContent.BODY_ID, equalTo(petDto.getId()),
                        PetContent.BODY_CATEGORY_ID, equalTo(petDto.getCategory().getId()),
                        PetContent.BODY_CATEGORY_NAME, equalTo(petDto.getCategory().getName()),
                        PetContent.BODY_NAME, equalTo(petDto.getName()),
                        PetContent.BODY_STATUS, equalTo(PetContent.STATUS_AVAILABLE));


    }

    @Test
    @Order(2)
    @DisplayName("Update pet")
    public void updatePet() {
        //Change name in Fake data
        petDto.setName(PetContent.BODY_CHANGED_NAME);
        request
                .body(petDto)
                .when()
                .put("/pet/")
                .then()
                .assertThat().statusCode(PetContent.STATUS_CODE_200)
                .header(PetContent.HEADER_CONTENT_TYPE, PetContent.HEADER_JSON)
                .body(PetContent.BODY_ID, equalTo(petDto.getId()),
                        PetContent.BODY_CATEGORY_ID, equalTo(petDto.getCategory().getId()),
                        PetContent.BODY_CATEGORY_NAME, equalTo(petDto.getCategory().getName()),
                        PetContent.BODY_NAME, equalTo(petDto.getName()),
                        PetContent.BODY_STATUS, equalTo(PetContent.STATUS_AVAILABLE))
                .log().all();


    }

    @Test
    @Order(3)
    @DisplayName("Get Pet by ID")
    @Description("Get Pet by ID created before in Test wth changed name")
    public void findPet_ByID() {
        //Problems found here when try to GET by ID also on https://petstore.swagger.io/ .Probably server issue
        request
                .param("petId", petDtoId)
                .log().all()
                .when()
                .get("/pet/" + petDtoId)
                .then()
                .statusCode(PetContent.STATUS_CODE_200)
                .log().all()
                .header(PetContent.HEADER_CONTENT_TYPE, PetContent.HEADER_JSON)
                .header(PetContent.HEADER_ALLOW_METHODS, PetContent.HEADER_ALLOW_METHODS_TYPES)
                .body(PetContent.BODY_ID, equalTo(petDto.getId()),
                        PetContent.BODY_CATEGORY_ID, equalTo(petDto.getCategory().getId()),
                        PetContent.BODY_CATEGORY_NAME, equalTo(petDto.getCategory().getName()),
                        PetContent.BODY_NAME, equalTo(petDto.getName()),
                        PetContent.BODY_STATUS, equalTo(PetContent.STATUS_AVAILABLE));


    }


}
