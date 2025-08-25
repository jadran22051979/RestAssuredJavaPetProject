package tests;

import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.baseURI;

@NoArgsConstructor
public class BaseTests {
    static {
        baseURI = "https://petstore.swagger.io/v2";

    }
}
