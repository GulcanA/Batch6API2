package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class practice2 {
    String devExBaseUrl = "http://eurotech.study";
    String petStoreBaseUrl = "https://petstore.swagger.io/v2";

    @Test
    public void getUserQuery(){
        Response response= RestAssured.given().accept(ContentType.JSON).
        and().queryParam("company", "Oracle").queryParam("name","Nihan").
                when().get(devExBaseUrl + "/api/profile/userQuery");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        assertEquals(response.path("name"),"Nihan");
        assertEquals(response.path("company"),"Oracle");
        assertEquals(response.path("status"),"Intern");
        assertEquals(response.path("email"),"nhntsc@gmail.com");

        int actualId=response.path("id");
     //   assertEquals(response.path("id"),909);


       assertEquals(actualId,909);

        assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
        assertTrue(response.headers().hasHeaderWithName("Date"));

    }

    @Test
    public void getUserPath(){
        // 913 ID user find
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userID", 913)
                .when().get(devExBaseUrl + "/api/profile/user/{userID}");

        response.prettyPrint();
        System.out.println("response.path(\"experience.title\") = " + response.path("experience.title"));


    }
}
