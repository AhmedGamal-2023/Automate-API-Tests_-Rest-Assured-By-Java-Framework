package Tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import Listeners.ITestListener_Implement;
import Listeners.IInvokedMethod_Implement;

import static org.hamcrest.Matchers.lessThan;

@Listeners({ITestListener_Implement.class, IInvokedMethod_Implement.class})
public class BaseTest {
    protected  static ResponseSpecification responseSpec;


    @BeforeMethod
    public void Setup(){
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
//                .log(LogDetail.BODY)  // Add this line to log the response body
                .build();
    }
}
