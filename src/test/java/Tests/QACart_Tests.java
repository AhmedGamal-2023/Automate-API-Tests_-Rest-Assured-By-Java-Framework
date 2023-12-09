package Tests;

import Pojo.LoginPojo;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static Controller.QACartController.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class QACart_Tests extends BaseTest{
    @Test
    public void ValidateHeader1() {
        Response response = AddHeader1("name", "Selenium");
        response
        //Header class
                .then().spec(responseSpec);
    }
    @Test
    public void ValidateAddListOfHeaders(){

        Header nameHeader = new Header("name", "Selenium");
        Header languageHeader = new Header("language", "JAVA");
        Headers infoHeaders = new Headers(nameHeader, languageHeader);
        //Headers class
        Response response = AddListOfHeaders(infoHeaders);
        response
                .then().spec(responseSpec)
                .assertThat().body("count", equalTo(1));
    }

    @Test
    public void ValidateQueryParams() {

        HashMap<String, String> queries = new HashMap<>();
        queries.put("type", "PAID");
        queries.put("mode", "VIDEO");
        //Queries class
        Response response = AddQueryParams(queries);
        response
                .then().spec(responseSpec);
    }

    @Test
    public void requestBody() {
        //1
        File BodyRequest = new File("src/test/resources/login.json");
        Response response= AddRequestBody(BodyRequest);
        response
                .then().spec(responseSpec);
        //2
//        HashMap<String,String> BodyRequest =new HashMap<>();
//        BodyRequest.put("email","hatem@example.com");
//        BodyRequest.put("password","Test1234");
        //3
//        LoginPojo BodyRequest =new LoginPojo();
//        BodyRequest.setEmail("hatem@example.com");
//        BodyRequest.setPassword("Test1234");
        //4
////        LoginPojo BodyRequest = new LoginPojo("hatem@example.com", "Test1234");
    }
    @Test
    public void validateExtractTokenAuthorization(){
        LoginPojo BodyRequest = new LoginPojo("hatem@example.com", "Test1234");
        Response response = extractTokenAuthorization(BodyRequest);
        response
                .then().extract().response();
        String token = response.path("access_token");
        System.out.println(token);

        Response TokenResponse = passToken(token);
        TokenResponse
                .then().spec(responseSpec);
    }
}
