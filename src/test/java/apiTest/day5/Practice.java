package apiTest.day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Practice {
    String devExBaseUrl = "http://eurotech.study";

    @Test
            public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("id", 29)
                .when().get(devExBaseUrl+"/api/profile/userQuery");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("id"),29);
        Assert.assertEquals(jsonPath.getString("email"),"oyku@gmail.com");
        Assert.assertEquals(jsonPath.getString("name"),"oyku");
        Assert.assertEquals(jsonPath.getString("company"),"Microsoft");
        Assert.assertEquals(jsonPath.getString("status"),"Student or Learning");
        Assert.assertEquals(jsonPath.getString("profileId"),"11");
    }

    @Test
    public void test4(){

//      given().accept(ContentType.JSON)
//                .when().get(devExBaseUrl+"/api/profile")
//                        .then().assertThat()
//                .body("experience[6].company", Matchers.equalTo("Amazon"));

        Response response = given().accept(ContentType.JSON)
                .when().get(devExBaseUrl + "/api/profile");
        JsonPath jsonPath=response.jsonPath();
        String company = jsonPath.getString("experience[5][0].company");
        System.out.println("company = " + company);


    }


    @Test
    public void test5() {
        //Hamcrest Matchers
        given().accept(ContentType.JSON)
                .and().get(devExBaseUrl+"/api/profile")
                .then().statusCode(200)
                .assertThat().contentType("application/json")
                .body("user.name",Matchers.hasItem("ibrahim"));
    }
    @Test
    public void test6() {
        //Hamcrest Matchers
        given().accept(ContentType.JSON)
                .and().get(devExBaseUrl + "/api/profile")
                .then().statusCode(200)
                .assertThat().contentType("application/json")
                .body("user.name", Matchers.hasItems("ibrahim", "Gülcan", "Nihan"));
    }
}
