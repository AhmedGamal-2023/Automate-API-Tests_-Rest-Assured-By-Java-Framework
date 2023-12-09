package Tests;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static Controller.MockAPIController.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class MockAPI_Tests extends BaseTest{

    @Test
    public void ValidateTest1_equalTo_hasItem_hasItems_not() {
        Response response = get_Users();
        // Apply logging configuration separately
        response.then().spec(responseSpec)
                .assertThat().body("[0].name", is(equalTo("Miss Lindsay Bednar")),
                        "name", is(hasItem("Miss Lindsay Bednar")),
                        "name", is(hasItems("Miss Lindsay Bednar", "Warren Veum")),
                        "name", not(hasItem("Ahmed Gamal")));
    }

    @Test
    public void test2_contains() {

        Response response = get_Users();
        response.then().spec(responseSpec);
//                .assertThat().body("name", contains("Miss Lindsay Bednar", "Warren Veum"))
//                .assertThat().body("name", containsInAnyOrder("Warren Veum", "Miss Lindsay Bednar"));
    }
    @Test
    public void test3_empty_hasSize_everyItemStartsWith_hasKey() {
           Response response =get_Users();
        response.then().spec(responseSpec)
                .assertThat().body("name", not(empty()))
//                .assertThat().body("name", hasSize(2))
//                .assertThat().body("name.size()", equalTo(2))
                .assertThat().body("createdAt", everyItem(startsWith("2023")))
                .assertThat().body("[0]", hasKey("name")
                        , "[0]", hasValue("Lesotho")
                        , "[0]", hasEntry("name", "Miss Lindsay Bednar"));
    }

    @Test
    public void test4_ExtractResponse() {
        Response response=get_Users();
                response
                        .then()
                        .extract().response();
        String CountryNameWithAnotherMethod1 = response.jsonPath().getString("[0].Country");
        System.out.println(CountryNameWithAnotherMethod1);

        //extract().response().path >> extract specific item from response Using extract().response().path
        Response response2=get_Users();
        response2
                .then().extract().response();
        String CountryNameWithAnotherMethod2= response2.path("[0].Country");
        System.out.println(CountryNameWithAnotherMethod2);
    }

    @Test
    public void test5_Logging() {
        Response response = get_Users();
                //to print status
        response
                .then()
                .log().status()
                .log().ifValidationFails()
                .assertThat().body("[1].id", equalTo("2"))
                .log().ifError();
    }

}
