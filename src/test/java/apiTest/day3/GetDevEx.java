package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class GetDevEx {
    String devExBaseUrl = "http://eurotech.study";
    String petStoreBaseUrl = "https://petstore.swagger.io/v2";

    @Test
    public void test1() {

        /*
        Given accept type is Json
        When user send GETS request to /api/profile end point
        Then verify that response status code is 200
        and verify that body contains "Microsoft"
         */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(devExBaseUrl + "/api/profile");

        //verify status code
        assertEquals(response.statusCode(), 200);

        //verify content type (body format)
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        //verify body contains "Microsoft"
        assertTrue(response.body().asString().contains("Microsoft"));

    }

    @Test
    public void headersTest() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(devExBaseUrl + "/api/profile");

        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));
        System.out.println("response.header(\"ETag\") = " + response.header("ETag"));
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));

        assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");

        //  assertEquals(response.header("Date"),"Wed, 24 May 2023 16:50:38 GMT");

        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertTrue(response.headers().hasHeaderWithName("ETag"));
    }
}
