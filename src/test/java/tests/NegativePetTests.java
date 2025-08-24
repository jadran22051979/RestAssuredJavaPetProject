package tests;

import content.PetContent;
import enums.PetStatus;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import payload.PetPayload;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;


public class NegativePetTests extends BaseTests{
    Dotenv dotenv = Dotenv.load();
    String apiKey = dotenv.get("API_KEY");
    String apiKeyValue = dotenv.get("API_KEY_VALUE");
    public static RequestSpecification requestNegative;
    private static final Pet petDtoNegative = PetPayload.petDto(PetStatus.negative);
    @BeforeEach
    void setRequest() {
        requestNegative = given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .header(apiKey, apiKeyValue)
                .contentType(ContentType.JSON);


    }

    @Test
    @Order(1)
    @DisplayName("Get Pet by ID-Negative Test")
    @Description("Get Pet by ID Negative Test and check of status code 404")
    public void findPet_ByID_Negative() {
        requestNegative
                .param("petId", PetContent.petDtoNegativeId)
                .log().all()
                .when()
                .get("/pet/" + PetContent.petDtoNegativeId)
                .then()
                .header(PetContent.HEADER_CONTENT_TYPE, PetContent.HEADER_JSON)
                .header(PetContent.HEADER_ALLOW_METHODS,PetContent.HEADER_ALLOW_METHODS_TYPES)
                .statusCode(PetContent.STATUS_CODE_404)
                .log().all();


    }

    @Test
    @Order(2)
    @DisplayName("Update Pet-Negative Test")
    @Description("Update Pet if resource is not found with PUT method")
    public void updatePetNegative() {
        //Behaviour is same as on https://petstore.swagger.io/
        requestNegative
                .body(petDtoNegative)
                .when()
                .put("/pet/")
                .then()
                .assertThat().statusCode(200)
                .header(PetContent.HEADER_CONTENT_TYPE, PetContent.HEADER_JSON)
                .log().all();


    }

    /*@Test
    @Order(3)
    @DisplayName("Post Pet-Negative Test")
    @Description("")
    public void postPetNegative() {
        petDtoNegative.setId();
        requestNegative
                .body(petDtoNegative)
                .when()
                .post("/pet/")
                .then()
                .assertThat().statusCode(200)
                .header(PetContent.HEADER_CONTENT_TYPE, PetContent.HEADER_JSON)
                .log().all();


    }*/

}
