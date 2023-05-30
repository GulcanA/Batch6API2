package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class DevExBodyTest {
    String devExBaseUrl = "http://eurotech.study";
    String petStoreBaseUrl = "https://petstore.swagger.io/v2";

    @Test
    public void verifyWithPathMethod() {
        /*
              "id": 109,
              "email": "angelina@mynet.com",
              "name": "Angelina",
              "company": "Apple",
              "status": "Tester",
              "profileId": 52
         */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", "109")
                .when().get(devExBaseUrl + "/api/profile/userQuery");

        assertEquals(response.statusCode(), 200);

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"company\") = " + response.path("company"));
        System.out.println("response.path(\"status\") = " + response.path("status"));

        int actualUserId = response.path("id");
        String actualEmail = response.path("email");
        String actualName = response.path("name");
        String actualCompany = response.path("company");
        String actualStatus = response.path("status");

        assertEquals(actualUserId, 109);
        assertEquals(actualEmail, "angelina@mynet.com");
        assertEquals(actualName, "Angelina");
        assertEquals(actualCompany, "Apple");
        assertEquals(actualStatus, "Tester");
    }
    @Test
    public void verifyBodyPetStore(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get(petStoreBaseUrl + "/pet/{id}");

        response.prettyPrint();

        int actualId = response.path("id");
        System.out.println("actualId = " + actualId);

        System.out.println("response.path(\"name\") = " + response.path("name"));

        //print string
        System.out.println("response.path(\"category.name\") = " + response.path("category.name"));

        //status
        System.out.println("response.path(\"status\") = " + response.path("status"));
    }

}
