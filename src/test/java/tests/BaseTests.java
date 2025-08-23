package tests;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Logger;


import static io.restassured.RestAssured.baseURI;
@NoArgsConstructor
public class BaseTests {
    static {
        baseURI = "https://petstore.swagger.io/v2";

    }
}
