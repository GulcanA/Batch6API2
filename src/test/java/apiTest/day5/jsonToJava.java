package apiTest.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
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
    @Test
    public void test2() {

        Response response = get(baseURI + "/api/profile");
        Assert.assertEquals(response.statusCode(),200);



        List<Map<String ,Object>> allBody = response.body().as(List.class);
        //  System.out.println("allBody = " + allBody);

        //print and verify third company is Siemens
        String expectedCompany= "Siemens";
        String actualCompany = (String) allBody.get(2).get("company");


        //allBody.get(2)  --> "id": 4,
        //        "company": "Siemens",
        //        "website": null,
        //        "year": 0,
        //        "location": "Ankara",
        //        "status": "Intern",
        //        "skills": [
        //            "Java",
        //            "Selenium",
        //            "TestNG",
        //            "Cucumber",
        //            "API"
        //        ],
        //        "bio": null,
        //        "githubusername": null,
        //        "social": {},
        //        "date": "2022-06-28T18:30:18.996Z",
        //        "userId": 17,
        //        "education": [],
        //        "experience": [],
        //        "user": {
        //            "id": 17,
        //            "name": "GOKHA N YILDIRIM",
        //            "email": "gokhanyildirim@gmail.com",
        //            "password": "$2a$10$KpOyVEezG6Eo.Mk2qhAJAO1808CtUQBKo68HO/koJNngPnv6sTn2a",
        //            "avatar": "//www.gravatar.com/avatar/b8eb1d988a52c2ab45e0548e32e9ca86?s=200&r=pg&d=mm",
        //            "date": "2022-06-28T18:29:44.970Z",
        //            "google": null,
        //            "github": null,
        //            "facebook": null,
        //            "profileId": 4
        //        }

        System.out.println("actualCompany = " + actualCompany);
        Assert.assertEquals(actualCompany,expectedCompany);

        Map<String ,Object> thirdUserInfo = allBody.get(2);   //we got all information related to third user
        System.out.println("thirdUserInfo = " + thirdUserInfo);

        Map<String ,Object> userInfo= (Map<String, Object>) thirdUserInfo.get("user"); //we got user information as map
        Object name = userInfo.get("name");
        System.out.println("name = " + name);
        Object email = userInfo.get("email");
        System.out.println("email = " + email);

        List<String> skills = (List<String>) thirdUserInfo.get("skills");
        System.out.println("skills = " + skills);

        //Everyone find their user
        //verify id, name , email
        //skills

    }
    @Test
    public void task(){
/* Get request /api/profile
verify seventh users company= Amazon
Email= sdet_blg@gmail.com
 */

        /*{
    "id": 34,
    "email": "sdet_blg@gmail.com",
    "name": "Blg",
    "company": "Amazon",
    "status": "Junior Developer",
    "profileId": 7
}*/
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("id", "34")
                .when().get(baseURI + "/api/profile/userQuery");
        assertEquals(response.statusCode(), 200);


        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);
        Object actualCompany = jsonDataMap.get("company");
        assertEquals(actualCompany, "Amazon");
        System.out.println("jsonDataMap.get(\"company\") = " + jsonDataMap.get("company"));
        Object actualEmail = jsonDataMap.get("email");

        assertEquals(actualEmail,"sdet_blg@gmail.com");

    }

    @Test
    public void testHomework(){

        Response response=given().accept(ContentType.JSON)
                .and().get(baseURI+"/api/profile");
        List<Map<String,Object>> allBody= response.body().as(List.class);

        Map<String, Object> user405Info = allBody.get(405);
        Map<String, Object> user405User=  (Map<String, Object>) user405Info.get("user");
        String actualName= (String) user405User.get("name");

        assertEquals(actualName,"Sigurd");
        System.out.println("user405Info = " + user405Info);
    }



}
