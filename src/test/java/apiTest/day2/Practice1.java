package apiTest.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Practice1 {

        String devExUrl="http://eurotech.study";
        String petStoreBaseUrl="https://petstore.swagger.io/v2";

        @Test
        public void Test1(){
            String endPoint=devExUrl+"/api/profile/user/37";
            Response response= RestAssured.given().accept(ContentType.JSON)
                    .when().get(endPoint);

            response .then()
                    .assertThat().statusCode(200)
                    .and().assertThat().contentType("application/json; charset=utf-8");

           Assert.assertTrue(response.body().toString().contains("37"));



    }
}
