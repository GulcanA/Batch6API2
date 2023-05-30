package apiTest.day4;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ReviewWithPathParam {
    String devExBaseUrl = "http://eurotech.study";
    String petStoreBaseUrl = "https://petstore.swagger.io/v2";

    @Test
    public void getAllProfiles(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().get(devExBaseUrl + "/api/profile");
      //  response.prettyPrint();
        int secondId=response.path("id[1]");
        System.out.println("secondId = " + secondId);

        int lastId=response.path("id[-1]");
        System.out.println("lastId = " + lastId);

        System.out.println("response.path(\"company[1]\") = " + response.path("company[1]"));

        List<String> secondSkills= response.path("skills[1]");
        System.out.println("secondSkills.size() = " + secondSkills.size());
        System.out.println("secondSkills = " + secondSkills);
        System.out.println("secondSkills.get(1) = " + secondSkills.get(1));

        System.out.println("response.path(\"skills[1][1]\") = " + response.path("skills[1][1]"));

        Map<String,Object> firstUser=response.path("user[0]");
        System.out.println("firstUser = " + firstUser);

        for (String user : firstUser.keySet()) {
            System.out.println(user+" : "+firstUser.get(user));

        }

        //print user 1 id(25)
        Object firstUserId=response.path("user[0].id");
        System.out.println("firstUserId = " + firstUserId);

       //get all users ID
        List<String> allUsersId= response.path("user.id");
        System.out.println("allUsersId = " + allUsersId);

        //get all users name
        List<String> allUsersName=response.path("user.name");
        System.out.println("user.name " +allUsersName);

        List<Integer> allIds=response.path("id");
        System.out.println("allIds = " + allIds);


    }

}
