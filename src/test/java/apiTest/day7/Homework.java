package apiTest.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Homework {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";

    }

    @Test
    public void saveAndVerify (){
        NewUserInfoForPost newUser= new NewUserInfoForPost();
        /*
  "email": "string",
  "password": "string",
  "name": "string",
  "google": "string",
  "facebook": "string",
  "github": "string"*/

        newUser.setEmail("fast&furious@gmail.com");
        newUser.setPassword("drivesafe");
        newUser.setName("Brian");
        newUser.setGoogle("Fast and Furious Google");
        newUser.setFacebook("Facebook Fast and furious");
        newUser.setGithub("F&F Github");
        Response response= given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(newUser)
                .post("/api/users");

    assertEquals(response.statusCode(),200);
        String token=response.path("token");

        String saveProfileBody="{\n" +
                "  \"company\": \"Universal\",\n" +
                "  \"website\": \"fastandfurious.com\",\n" +
                "  \"location\": \"USA\",\n" +
                "  \"status\": \"in vision\",\n" +
                "  \"skills\": \"Drive,Modify,Repair\",\n" +
                "  \"githubusername\": \"Fast Github\",\n" +
                "  \"youtube\": \"F&F youtube\",\n" +
                "  \"twitter\": \"F&F twitter\",\n" +
                "  \"facebook\": \"F&F Facebook\",\n" +
                "  \"linkedin\": \"F&F linkedin\",\n" +
                "  \"instagram\": \"F&F instagram\"\n" +
                "}";
response= given().accept(ContentType.JSON)
        .and().contentType(ContentType.JSON)
        .and()
        .header("x-auth-token",token)
        .body(saveProfileBody)
        .when()
        .post("api/profile");

      response.
              then().body("user.name", equalTo(newUser.getName()),
                        "user.email", equalTo(newUser.getEmail()),
                        "company", equalTo("Universal"));

assertEquals(response.statusCode(),200);
response.prettyPrint();








    }
}
