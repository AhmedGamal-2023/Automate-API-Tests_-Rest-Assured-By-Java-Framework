package Controller;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MockAPIController extends BaseController {
    static RequestSpecification baseMockAPI = baseRequestUrl("https://653ba1f12e42fd0d54d56f29.mockapi.io/");

    public static Response get_Users() {
        return given().spec(baseMockAPI)
                .when().get("Users");
    }
}