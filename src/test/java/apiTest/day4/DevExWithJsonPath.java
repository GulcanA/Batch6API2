package apiTest.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class DevExWithJsonPath {
    String devExBaseUrl = "http://eurotech.study";
    String petStoreBaseUrl = "https://petstore.swagger.io/v2";

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                and().queryParam("id", "909")
                .when().get(devExBaseUrl + "/api/profile/userQuery");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        Object email= response.path("email");
        System.out.println("email = " + email);
        System.out.println("********************************************");

        JsonPath jsonPath=response.jsonPath();
        int id=jsonPath.getInt("id");
        System.out.println("id = "+id);

        String actualEmail1=jsonPath.getString("email");
        String actualName=jsonPath.getString("name");
        String actualCompany=jsonPath.getString("company");
        String actualStatus=jsonPath.getString("status");
        int actualProfileId=jsonPath.getInt("profileId");

        System.out.println(actualName+actualProfileId+actualEmail1+actualCompany+actualStatus);

        assertEquals(actualProfileId,517);
        assertEquals(actualCompany,"Oracle");
        assertEquals(actualStatus,"Intern");
        assertEquals(actualEmail1,"nhntsc@gmail.com");
        assertEquals(actualName,"Nihan");

    }
    @Test
    public void task(){
        /*
                    Given accept type is Json
                    And query param id is 29
                    Verify Status code is 200
                    verify content type application/json
                    Verify all body information with using Jsonpath
                    {
                    "id": 29,
                    "email": "oyku@gmail.com",
                    "name": "oyku",
                    "company": "Microsoft",
                    "status": "Student or Learning",
                     "profileId": 11
                    }
         */
        Response response=RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", "29")
                .when().get(devExBaseUrl + "/api/profile/userQuery");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");

        JsonPath jsonPath=response.jsonPath();

        String actualEmail=jsonPath.getString("email");
        String actualName=jsonPath.getString("name");
        String actualCompany=jsonPath.getString("company");
        String actualStatus=jsonPath.getString("status");
        int actualId=jsonPath.getInt("id");
        int actualprofileId=jsonPath.getInt("profileId");

        assertEquals(actualId,29);
        assertEquals(actualEmail,"oyku@gmail.com");
        assertEquals(actualName,"oyku");
        assertEquals(actualCompany,"Microsoft");
        assertEquals(actualprofileId,11);

    }
    @Test
    public void test2(){
        Response response= RestAssured.get(devExBaseUrl+"/api/profile");
        JsonPath jsonPath=response.jsonPath();

        int secondId=jsonPath.getInt("id[1]");
        System.out.println("secondId = " + secondId);

        //get all company
        List<Object> allCompanies = jsonPath.getList("company");
        System.out.println("allCompanies.size() = " + allCompanies.size());
        System.out.println("allCompanies = " + allCompanies);

        //get all info users Anderson
        Map<Object, Object> anderson = jsonPath.getMap("user[3]");
        System.out.println("anderson = " + anderson);

        //print andersons skills
        List<Object> list = jsonPath.getList("skills[3]");
        System.out.println("list = " + list);

        String php=jsonPath.getString("skills[6][3]");
        System.out.println("php = " + php);

        //get all username which has github is null
        List<Object> allNames = jsonPath.getList("user.findAll{it.github==null}.name");
        System.out.println("allNames = " + allNames);

        //find all names who has Id's bigger than 100
        List<Object> names = jsonPath.getList("user.findAll{it.id>300}.name");
        System.out.println("names.size() = " + names.size());
        System.out.println("names = " + names);



    }
}
