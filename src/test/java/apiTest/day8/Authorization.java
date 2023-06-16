package apiTest.day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Authorization {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";

    }

    @Test
    public void test1() {
        String jsonBody="{\n" +
                "  \"email\": \"gulcan@gmail.com\",\n" +
                "  \"password\": \"newjob2023\"\n" +
                "}";
        Response response=given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("api/auth");

        response.prettyPrint();
        String token=response.path("token");


    }

    @Test
    public static String getToken() {
        Map<String,Object> body= new HashMap<>();
        body.put("email","gulcan@gmail.com");
        body.put("password","newjob2023");

        Response response=given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("api/auth");

        response.prettyPrint();
        String token=response.path("token");

        return token;

    }
    public static Map<String,Object> getAccessToken(String email,String password){

        Map<String,Object> getTokenMap = new HashMap<>();
        getTokenMap.put("email",email);
        getTokenMap.put("password",password);

        Response response = given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .body(getTokenMap)
                .when()
                .post("api/auth");

        String token = response.path("token");
        Map<String,Object> authorization = new HashMap<>();
        authorization.put("x-auth-token",token);

        return authorization;
    }
}
