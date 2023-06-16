package apiTest.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class POSTSaveProfile {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";

    }

    @Test
    public void postNewUser() {
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("email", "jamesbond123@gmail.com");
        requestBody.put("password", "9985877");
        requestBody.put("name", "James Bond07");
        requestBody.put("google", "james07 google");
        requestBody.put("facebook", "jbond07 facebook");
        requestBody.put("github", "jamesbond07 github");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("api/users");

        Assert.assertEquals(response.statusCode(), 200);
        String token = response.path("token");

        String saveProfileBody = "{\n" +
                "  \"company\": \"Century\",\n" +
                "  \"website\": \"jamesbond.de\",\n" +
                "  \"location\": \"USA\",\n" +
                "  \"status\": \"Legend\",\n" +
                "  \"skills\": \"HTML,CSS,Javascript\",\n" +
                "  \"githubusername\": \"jamesjames\",\n" +
                "  \"youtube\": \"string\",\n" +
                "  \"twitter\": \"string\",\n" +
                "  \"facebook\": \"string\",\n" +
                "  \"linkedin\": \"james\",\n" +
                "  \"instagram\": \"string\"\n" +
                "}";
        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and()
                .header("x-auth-token", token)
                .body(saveProfileBody)
                .when()
                .post("api/profile");

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void postNewUserAndVerify() {
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("email", "madagaskar@gmail.com");
        requestBody.put("password", "9985877");
        requestBody.put("name", "Alex");
        requestBody.put("google", "Marty google");
        requestBody.put("facebook", "Melman facebook");
        requestBody.put("github", "Gloria github");
        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when().post("api/users");

        Assert.assertEquals(response.statusCode(), 200);
        String token = response.path("token");

        String saveProfileBody = "{\n" +
                "  \"company\": \"Disney\",\n" +
                "  \"website\": \"madagaskar.de\",\n" +
                "  \"location\": \"USA\",\n" +
                "  \"status\": \"Animation\",\n" +
                "  \"skills\": \"HTML,CSS,Javascript\",\n" +
                "  \"githubusername\": \"madagaskar\",\n" +
                "  \"youtube\": \"string\",\n" +
                "  \"twitter\": \"string\",\n" +
                "  \"facebook\": \"string\",\n" +
                "  \"linkedin\": \"james\",\n" +
                "  \"instagram\": \"string\"\n" +
                "}";
        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .header("x-auth-token",token)
                .body(saveProfileBody)
                .when()
                .post("api/profile");
        Assert.assertEquals(response.statusCode(),200);
        String actualCompany= response.path("company");
        String actualName=response.path("user.name");
        String actualEmail=response.path("user.email");

        Assert.assertEquals(actualCompany,"Disney");
        Assert.assertEquals(actualName,"Alex");
        Assert.assertEquals(actualEmail,"madagaskar@gmail.com");


    }

    @Test
    public void postAndSave(){
    String email="zootropolis123@gmail.com";
    String password="Test12345";
    String name="Hobbs";
    String google="Hobbs google";
            String facebook="Hoobs facebook";
            String github="Zootropolis github";

            Map<String,Object> requestBody= new HashMap<>();
            requestBody.put("email",email);
        requestBody.put("password",password);
        requestBody.put("name",name);
        requestBody.put("google",google);
        requestBody.put("facebook",facebook);
        requestBody.put("github",github);

        //verfiy that name email, google and facebook
        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestBody)
                .when().post("api/users");

        Assert.assertEquals(response.statusCode(),200);
               String token=response.path("token");
        System.out.println("token = " + token);

        Map<String,Object> saveProfileBody=new HashMap<>();

        saveProfileBody.put("company","Toyota");
        saveProfileBody.put("website","www.toyota.com");
        saveProfileBody.put("location","Tokyo");
        saveProfileBody.put("status","Car Factory");
        saveProfileBody.put("skills","Car");
        saveProfileBody.put("githubusername","Git Toyota");
        saveProfileBody.put("youtube","Youtube Toyota");
        saveProfileBody.put("twitter","Toyo");
        saveProfileBody.put("facebook","face");
        saveProfileBody.put("linkedin","Link");
        saveProfileBody.put("instagram","Toyota");
//        saveProfileBody.put("company","disney");
//        saveProfileBody.put("website","zootopia");
//        saveProfileBody.put("location","Zooztopia");
//        saveProfileBody.put("status","Animal Country");
//        saveProfileBody.put("githubusername","agentHobs");
//        saveProfileBody.put("youtube","Zootopia Fragman");
//        saveProfileBody.put("twitter","zoo twitter");
//        saveProfileBody.put("facebook","Zootopia Animals");
//        saveProfileBody.put("linkedin","Linkedin Zootopia");
//        saveProfileBody.put("instagram","zootopia insta");

        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and()
                .header("x-auth-token", token)
                .body(saveProfileBody)
                .when()
                .post("api/profile");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.path("user.name"),name);
        Assert.assertEquals(response.path("user.email"),email);
        Assert.assertEquals(response.path("user.google"),google);
        Assert.assertEquals(response.path("user.facebook"),facebook);




    }
}
