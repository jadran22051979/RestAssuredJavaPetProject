package tests;

import content.PetContent;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Pet;
import models.PetNegative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import payload.PetPayload;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;


public class NegativePetTests extends BaseTests{
    public static RequestSpecification requestNegative;
    private static  Pet petDtoNegative = PetPayload.petDtoNegative();
    @BeforeEach
    void setRequest() {
        requestNegative = given().config(RestAssured.config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .header(PetContent.API_KEY, PetContent.API_KEY_VALUE)
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
    @DisplayName("Add new pet")
    @Description("Adding new Pet and check of response status code and body")
    public void postAddNewPetNegative_200() {
        requestNegative
                .body(petDtoNegative)
                .when()
                .post("/pet/")
                .then()
                .statusCode(PetContent.STATUS_CODE_404)
                .log().all()
                .header(PetContent.HEADER_CONTENT_TYPE, PetContent.HEADER_JSON)
                .header(PetContent.HEADER_ALLOW_METHODS,PetContent.HEADER_ALLOW_METHODS_TYPES);



    }

}
