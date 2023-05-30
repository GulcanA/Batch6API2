package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;

public class DevExTestWithParameters {
    String devExBaseUrl = "http://eurotech.study";
    String petStoreBaseUrl = "https://petstore.swagger.io/v2";

    @Test
    public void pathParamPetStore1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreBaseUrl + "/pet/10");

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void pathParamPetStore2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get(petStoreBaseUrl + "/pet/{id}");
        response.prettyPrint();
    }

    @Test
    public void pathParamDevEx() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userId", 11)
                .when().get(devExBaseUrl + "/api/profile/user/{userId}");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void pathParamDevEx2() {
        //11
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userId", 11)
                .when().get(devExBaseUrl + "/api/profile/user/{userId}");
        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("Study"));
        assertTrue(response.body().asString().contains ("euroTechStudy" ));


    }
}

