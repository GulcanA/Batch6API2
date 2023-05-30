package apiTest.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class DevExTestHamcrestMatchers {


    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";


    }

    @Test
    public void test() {
            /* Given accept type is json
            And query param user id is
            When user sends Get request to /api/profile/userQuery
            Then status code is 200
            And content type is application/json
            and verify body
       {
                "id": 200,
                    "email": "france@gmail.com",
                    "name": "tanju",
                    "company": "TCS",
                    "status": "Automation Test Engineer",
                    "profileId": 134
            }
            */
        given().accept(ContentType.JSON)
                .queryParam("id", 200)
                .get(baseURI + "/api/profile/userQuery")
                .then()
                .statusCode(200)
                .assertThat()
                .contentType("application/json");

    }

    @Test
    public void verifyWithHamcrestMatcher() {
        given().accept(ContentType.JSON)
                .queryParam("id", 200)
                .get(baseURI + "/api/profile/userQuery").
                then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat()
                .body("id", equalTo(200),
                        "email", equalTo("france@gmail.com"),
                        "name", equalTo("tanju"),
                        "company", equalTo("TCS"),
                        "status", equalTo("Automation Test Engineer"),
                        "profileId", equalTo(134));

    }

    @Test
    public void hamcrestTest2() {
        given().accept(ContentType.JSON)
                .queryParam("id", 528)
                .when()
                .log().all()
                .get(baseURI + "/api/profile/userQuery")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json; charset=utf-8"))
                .header("Content-Length", equalTo("121"))
                .header("Connection", equalTo("keep-alive"))
                .header("Date", notNullValue())//if date has value , it will pass
                .body("id", equalTo(528),
                        "name", equalTo("Teacher"),
                        "email", equalTo("eurotech@gmail.com"),
                        "company", equalTo("Eurotech Study"),
                        "status", equalTo("Instructor")).log().all();


    }

    @Test
    public void test4() {
        given().accept(ContentType.JSON)
                .when().log().all()
                .get(baseURI + "/api/profile")
                .then().log().all()
                .assertThat().statusCode(200)
                .contentType("application/json")
                .body("user.name", hasItem("ibrahim"));
    }

    @Test
    public void test5() {
        given().accept(ContentType.JSON)
                .when().log().all()
                .get(baseURI + "/api/profile")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("company[1]", equalTo("Eurotech"),
                        "skills[1][4]", equalTo("API"));

    }
}
