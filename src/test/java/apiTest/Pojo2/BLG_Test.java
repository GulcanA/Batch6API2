package apiTest.Pojo2;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class BLG_Test {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";

    }

    @Test
    public void testPojo() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 256)
                .when().get(baseURI + "/api/profile/userQuery");


        Blg blg= response.body().as(Blg.class);

        System.out.println("blg.getCompany() = " + blg.getCompany()); //Amazon
        System.out.println("blg.getName() = " + blg.getName()); //BLG
    }
}