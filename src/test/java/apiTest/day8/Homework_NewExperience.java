package apiTest.day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Homework_NewExperience {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";

    }

    @Test
    public void postNewExperience() {
/*
{
  "title": "string",
  "company": "string",
  "location": "string",
  "from": "YYYY-MM-DD",
  "to": "YYYY-MM-DD",
  "current": false,
  "description": "string"
}
 */
        String requestBody=" {\n" +
                "  \"title\": \"Developer\",\n" +
                "  \"company\": \"Apple\",\n" +
                "  \"location\": \"India\",\n" +
                "  \"from\": \"2020-05-04\",\n" +
                "  \"to\": \"2021-03-02\",\n" +
                "  \"current\": false,\n" +
                "  \"description\": Super Job\"\n" +
                "}";
        given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .headers(getTokenMap("gulcan@gmail.com","newjob2023"))
                .body(requestBody)
                .when()
                .post("api/profile/experience")
                .then().log().all()
                .assertThat().statusCode(201)
                .body("experience[0].company",equalTo("Apple"),
                        "experience[0].title",equalTo("Developer"),
                        "experience[0].location",equalTo("India"));
        /*given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .headers(getTokenMap("nhntsc@gmail.com", "N12345!"))
                .body(requestBody)
                .when()
                .post("/api/profile/experience")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .assertThat()
                .body("experience[0].company", equalTo(company),
                        "experience[0].title", equalTo(title),
                        "experience[0].location", equalTo(location));*/

    }
      public static Map<String,Object> getTokenMap(String email, String password){
        Map<String,Object> loginBody= new HashMap<>();
        loginBody.put("email",email);
        loginBody.put("password",password);

        Response response= given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("api/auth");

        String tokenStr = response.path("token");
        Map<String,Object> token = new HashMap<>();
        token.put("x-auth-token",tokenStr);

        return token;




    }
}
