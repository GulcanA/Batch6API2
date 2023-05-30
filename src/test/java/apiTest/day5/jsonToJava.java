package apiTest.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class jsonToJava {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";

    }

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("id", "922")
                .when().get(baseURI + "/api/profile/userQuery");
        assertEquals(response.statusCode(), 200);
        //  response.prettyPrint();

        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);
        Object actualEmail = jsonDataMap.get("email");
        assertEquals(actualEmail, "seyit@gmail.com");
    }
}
