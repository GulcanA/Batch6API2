package apiTest.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class simpleGETrequest {
    String devExBaseUrl="http://eurotech.study";
    String petStoreBaseUrl="https://petstore.swagger.io/v2";

    @Test
    public void test1(){
       Response response=  RestAssured.get(devExBaseUrl+"/api/profile");
       //print status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print body
        response.prettyPrint();

    }

    @Test
    public void test2() {
        Response response=  RestAssured.get(petStoreBaseUrl+"/pet/findByStatus?status=available");
        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();

    }

    @Test
    public void testBody() {
        /* Given accept type is Json
            When user get request all profiles
            Then verify that status code is 200
            And body is json format
    */
Response response= RestAssured.given().accept(ContentType.JSON)
        .when().get(devExBaseUrl+"/api/profile");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }

    @Test
    public void test5() {
        //verify using RestAssure
        RestAssured.given().accept(ContentType.JSON)
                .when().get(devExBaseUrl+"/api/profile")
                .then()
                .assertThat().statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8");
    }

    @Test
    public void test6() {
        //verify that body has Anderson
        Response response= RestAssured.given().accept(ContentType.JSON)
                .when().get(devExBaseUrl+"/api/profile");
        Assert.assertEquals(response.statusCode(),200);

        Assert.assertTrue(response.body().asString().contains("Anderson"));

    }
}
