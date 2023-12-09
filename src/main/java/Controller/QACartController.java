package Controller;

import Pojo.LoginPojo;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class QACartController extends BaseController{
    static RequestSpecification baseQACartAPI = baseRequestUrl("https://todo.qacart.com/api/v1/");

    public static Response AddHeader1(String key,String value) {
        //Header class
//        Header headerName = new Header("name", "Selenium");
        return   given()
                .spec(baseQACartAPI).header(key,value)
                .when().get("info/courses");
    }
    public static Response AddListOfHeaders(Headers infoHeaders){
        return given()
                .spec(baseQACartAPI)
                .headers(infoHeaders)
                .when().get("info/courses");
    }
    public static Response AddQueryParams(HashMap queries) {
       return given()
               .spec(baseQACartAPI).queryParams(queries)
                .when().get("info/lectures");
    }
    public static Response AddRequestBody(File BodyRequest) {
       return given()
               .spec(baseQACartAPI).body(BodyRequest)
                .when().post("Students/login");
    }
    public static Response  extractTokenAuthorization(LoginPojo BodyRequest) {
        return
                given().spec(baseQACartAPI)
                        .body(BodyRequest)
                        .when().post("Students/login");

    }
    public static Response passToken(String token){
        return
                given().spec(baseQACartAPI)
                        .auth().oauth2(token)
                        .when().get("courses");
    }
}
