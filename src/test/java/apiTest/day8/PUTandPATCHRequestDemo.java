package apiTest.day8;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PUTandPATCHRequestDemo {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";

    }

    @Test
    public void postCreateNewExperience() {
        /*{
  "title": "string",
  "company": "string",
  "location": "string",
  "from": "YYYY-MM-DD",
  "to": "YYYY-MM-DD",
  "current": false,
  "description": "string"
}*/
        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("title", "Tester");
        experienceBody.put("company", "Apple");
        experienceBody.put("location", "duisburg");
        experienceBody.put("from", "2021-09-09");
        experienceBody.put("to", "2022-09-12");
        experienceBody.put("current", false);
        experienceBody.put("description", "All is well");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .header("x-auth-token", Authorization.getToken())
                .body(experienceBody)
                .when()
                .post("api/profile/experience")
                .then().log().all()
                .assertThat().statusCode(201);

        //homework verfiy body information

    }

    @Test
    public void updateExperienceWithPUTRequest() {
        /*
        {
  "title": "Test Engineer",
  "company": "{{$randomCompanyName}}",
  "location": "USA",
  "from": "2020-05-03",
  "to": "2021-04-06",
  "current": false,
  "description": "Ok"
}
         */
        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("title", "Instructor");
        experienceBody.put("company", "Siemens");
        experienceBody.put("location", "Duisburg");
        experienceBody.put("from", "2021-05-09");
        experienceBody.put("to", "2022-09-11");
        experienceBody.put("current", false);
        experienceBody.put("description", "Perfect!");

        given().log().all()
                .and().contentType(ContentType.JSON)
                .headers(Authorization.getAccessToken("gulcan@gmail.com", "newjob2023"))
                .and()
                .body(experienceBody)
                .when()
                .put("/api/profile/experience/66")
                .then().log().all()
                .assertThat().statusCode(204);
    }

    @Test
    public void updateWithPutWithPathParam() {

        //User update experience id is 646
        int id = 712;

        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("title", "Product Owner");
        experienceBody.put("company", "Samsung");
        experienceBody.put("location", "Ankara");
        experienceBody.put("from", "2015-10-10");
        experienceBody.put("to", "2016-10-10");
        experienceBody.put("current", false);
        experienceBody.put("description", "All Good");

        Response response = given().log().all()
                .and().contentType(ContentType.JSON)
                .pathParam("experienceID", id)
                .and().headers(Authorization.getAccessToken("eurotech@gmail.com", "Test12345!"))
                .body(experienceBody)
                .when().put("api/profile/experience/{experienceID}");
        assertEquals(response.statusCode(), 204);

        Response response2 = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .header("x-auth-token", Authorization.getToken())
                .pathParam("id", 712)
                .get("/api/profile/experience/{id}");

        assertEquals(response2.statusCode(),200);
        System.out.println(" ********************");
        response2.prettyPrint();
    assertEquals(response2.path("title"), "Product Owner");


        //homework
        //verify body has updated
        //
    }

    //homework
    // verify body
    @Test
    public void uprdateExperienceWithPatch() {
        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("title", "Engineer");
        experienceBody.put("company", "Tesla");
        int experienceId = 679;
        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .header("x-auth-token", Authorization.getToken())
                .and()
                .pathParam("id", experienceId)
                .body(experienceBody)
                .when()
                .patch("api/profile/experience/{id}")
                .then().log().all()
                .assertThat().statusCode(204);

    }

    @Test
    public void testPosts() {
        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .header("x-auth-token", Authorization.getToken())
                .when()
                .get("api/posts")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("title[0]", Matchers.equalTo("UI Test"));

    }

    @Test
    public void testPostsResponse() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .header("x-auth-token", Authorization.getToken())
                .when()
                .get("api/posts");
     assertEquals(response.statusCode(), 200);
      assertEquals(response.path("title[0]"), "UI Test");

    }

    @Test
    public void testPatch() {
        /* update one of post with Patch
        verify that body informationhas updated
        and stsatus code 200
         */
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("title", "API TESTING IS FINISHED!");
        postBody.put("text", "IT was nice to learn API Testing");
        int postId = 142;
        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .header("x-auth-token", Authorization.getToken())
                .and()
                .pathParam("id", postId)
                .body(postBody)
                .when()
                .patch("api/posts/{id}")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("title", Matchers.equalTo("API TESTING IS FINISHED!"),
                        "text", Matchers.equalTo("IT was nice to learn API Testing"))
                .log().all();


    }
}
