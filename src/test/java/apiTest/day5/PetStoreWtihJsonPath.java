package apiTest.day5;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PetStoreWtihJsonPath {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://petstore.swagger.io/v2";

    }
    @Test
    public void getPet() {

        // GET request path param id 14
        Response response = given().get(baseURI + "/pet/10");
        response.prettyPrint();

        //print category ID
        JsonPath jsonPath = response.jsonPath();
        int categoryID = jsonPath.getInt("category.id");
        System.out.println("categoryID = " + categoryID);

        assertEquals(categoryID,10);

        //print doggie
        String name = jsonPath.getString("name");
        System.out.println("name = " + name);

        //print string
        String name2 = jsonPath.getString("tags[0].name");
        System.out.println("name2 = " + name2);
    }

    @Test
    public void getAllPets() {

        Response response = given().and().queryParam("status", "available")
                .when().get(baseURI + "/pet/findByStatus");
        assertEquals(response.statusCode(),200);

        response.prettyPrint();

        //print all category names
        JsonPath jsonPath = response.jsonPath();
        List<Object> allCategoryNames = jsonPath.getList("category.name");
        System.out.println("allCategoryNames.size() = " + allCategoryNames.size());
        System.out.println("allCategoryNames = " + allCategoryNames);

        //print  Aurelia
        String  aurelia = jsonPath.getString("category[4].name");
        System.out.println("aurelia = " + aurelia);

    }
}
