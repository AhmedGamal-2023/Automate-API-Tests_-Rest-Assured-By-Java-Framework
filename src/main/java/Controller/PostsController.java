package Controller;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PostsController extends BaseController{
    static RequestSpecification basePosts = baseRequestUrl("https://jsonplaceholder.typicode.com/");

    public static Response ListAllPosts() {
       return given()
                .spec(basePosts)
                .when().get("posts");
    }
    public static Response GetUserOneAndValidateTheTitle(){
        return   given()
                .spec(basePosts)
                .when().get("posts/1");
    }
    public static Response AddPostAssertThatPostsAreAdded(File BodyRequest){
        return  given()
                .spec(basePosts)
                .body(BodyRequest)
                .when().post("posts");
    }
    public static Response DeleteUser (){
        return   given()
                .spec(basePosts)
                .when().delete("posts/50");
    }

}
