package Tests;
import static Controller.PostsController.*;
import static  org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;



public class JSONPlaceholder extends BaseTest{

    @Test
    public void  ValidateListAllPosts() {
        Response response =  ListAllPosts();
        response
                .then()
                .log().body()
                .assertThat().statusCode(200);
    }

    @Test
    public void ValidateGetUserOneAndValidateTheTitle() {
        String TitleOfUserOne = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        Response response =  GetUserOneAndValidateTheTitle();
        response.then()
                .log().body()
                .assertThat().statusCode(200).assertThat().body("title", equalTo(TitleOfUserOne));
    }

    @Test
    public void ValidateAddPostAssertThatPostsAreAdded() {
//        AddUserPojo BodyRequest=new AddUserPojo();
//        BodyRequest.setUserId("1");
//        BodyRequest.setTitle("foo");
//        BodyRequest.setBody("bar");
        File BodyRequest = new File("src/test/resources/PostData.json");
        Response response =  AddPostAssertThatPostsAreAdded(BodyRequest);
        response.then()
                .log().body()
                .statusCode(201)
                .assertThat().body("id", is(equalTo(101)));
    }

    @Test
    public void ValidateDeleteUser(){
        Response response =  DeleteUser();
        response.then()
                .log().status().statusCode(200);
    }


}