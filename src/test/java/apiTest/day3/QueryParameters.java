package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class QueryParameters {

    String devExBaseUrl = "http://eurotech.study";
    String petStoreBaseUrl = "https://petstore.swagger.io/v2";

    @Test
    public void queryParamPets(){
         /*
        Given accept type is JSON
        and query param is status sold
        When user sends GET request to pet/findByStatus
        Then response status code is 200
        And content type is application/json
        And "cats" should be in payload/body

         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("status", "sold")
                .when().get(petStoreBaseUrl + "/pet/findByStatus");

        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("cats"));
        assertEquals(response.header("Content-Type"),"application/json");

    }
    @Test
    public void queryParamDevEx(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                and().queryParam("id", "914").
                when().get(devExBaseUrl + "/api/profile/userQuery");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
        assertTrue(response.body().asString().contains("IBM"));
        assertTrue(response.body().asString().contains("Gülcan"));


    }
    @Test
    public void homework(){
    /*
        Homework
        get request query param company = Al-Hilal
        verify company, name, status, email

         */
        Response response = RestAssured.given().accept(ContentType.JSON).
                and().queryParam("company", "Al-Hilal").
                when().get(devExBaseUrl + "/api/profile/userQuery");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.path("company"),"Al-Hilal");
        assertEquals(response.path("name"),"Anderson Talisca");
        assertEquals(response.path("status"),"Manager");
        assertEquals(response.path("email"),"talisca@gmail.com");


    }

    @Test
    public void queryParamWithMap(){
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("status","pending");

        Response response = RestAssured.given().accept(ContentType.JSON).
                and().queryParams(queryMap)
                .when().get(petStoreBaseUrl + "/pet/findByStatus");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);

    }
    @Test
    public void queryParams(){
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("id",909);
        queryMap.put("company","Oracle");


        Response response = RestAssured.given().accept(ContentType.JSON).
                and().queryParams(queryMap)
                .when().get(devExBaseUrl + "/api/profile/userQuery");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
    }


}
