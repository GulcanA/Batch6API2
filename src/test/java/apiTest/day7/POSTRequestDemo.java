package apiTest.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class POSTRequestDemo {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";

    }

    @Test
    public void postNewUser() {
       String jsonBody= "{\n" +
               "  \"email\": \"scherlockholmes@gmail.com\",\n" +
               "  \"password\": \"123456\",\n" +
               "  \"name\": \"Scherlock\",\n" +
               "  \"google\": \"Holmes google\",\n" +
               "  \"facebook\": \"Face Holmes\",\n" +
               "  \"github\": \"Holmes github\"\n" +
               "}";

       Response response= given().accept(ContentType.JSON)
               .and()
               .contentType(ContentType.JSON)
               .and()
               .body(jsonBody)
               .when()
               .post("api/users");
response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("token"));


    }

    @Test
    public void postNewUser2() {
        Map<String,Object> requestBody= new HashMap<>();

        requestBody.put("email","jamesbond@gmail.com");
        requestBody.put("password","998877");
        requestBody.put("name","James Bond");
        requestBody.put("google","james google");
        requestBody.put("facebook","jbond facebook");
        requestBody.put("github","jamesbond github");

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("api/users");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
        Assert.assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser3() {
        NewUserInfoForPost newUser= new NewUserInfoForPost();
        newUser.setEmail("johnwick@gmail.com");
        newUser.setPassword("123456");
        newUser.setName("JohnWick");
        newUser.setFacebook("John Wick Facebook");
        newUser.setGithub("JohnWick Github");

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(newUser)                     // serialisitaion
                .when()
                .post("api/users");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
        Assert.assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser4() {
        NewUserInfoForPost newUser=new NewUserInfoForPost("blackjoe@gmail.com","123456","joe","joe google","joe facebook","joe github");

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(newUser)                     // serialisitaion
                .when()
                .post("api/users");

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
        Assert.assertTrue(response.body().asString().contains("token"));
    }
}
