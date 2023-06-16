package apiTest.day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Practice {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }

    @Test
    public void testPatch() {
         /*
        Update one of posts with PATCH method
        Verify that body information has updated
        And status code is 200
         */
        Map<String,Object> patchBody= new HashMap<>();
        patchBody.put("title","Hello again everybody");
        patchBody.put("text","this is not so funny");

        Response response= given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",142)
                .body(patchBody)
                .header("x-auth-token",Authorization.getToken())
                .patch("api/posts/{id}");
              Assert.assertEquals(response.statusCode(),200);
               Assert.assertEquals(response.path("title"), "Hello again everybody");




    }
}
