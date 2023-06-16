package apiTest.day6;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static org.testng.AssertJUnit.assertEquals;

public class Pojo_Test {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";


    }
    @Test
    public void testPojo(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id",909)
                .when().get("/api/profile/userQuery");

        EurotechUser oneUser= response.body().as(EurotechUser.class);

        System.out.println("oneUser.getName() = " + oneUser.getName());
        System.out.println("oneUser.getCompany() = " + oneUser.getCompany());
        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
        System.out.println("oneUser.getId() = " + oneUser.getId());
        System.out.println("oneUser.getStatus() = " + oneUser.getStatus());
        System.out.println("oneUser.getProfileId() = " + oneUser.getProfileId());

        assertEquals(oneUser.getId(),909.0);
        assertEquals(oneUser.getName(),"Nihan");
        assertEquals(oneUser.getCompany(),"Oracle");
        assertEquals(oneUser.getProfileId(),517.0);
        assertEquals(oneUser.getStatus(),"Intern");
        assertEquals(oneUser.getEmail(),"nhntsc@gmail.com");

    }
}
