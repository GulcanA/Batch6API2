package apiTest.day6;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class gsonExample {
    @Test
    public void jsonToMap(){
        Gson gson=new Gson();
        String jsonBody="{\n" +
                "    \"id\": 256,\n" +
                "    \"email\": \"Sdet_BLG_2@gmail.com\",\n" +
                "    \"name\": \"BLG\",\n" +
                "    \"company\": \"Amazon\",\n" +
                "    \"status\": \"Developer\",\n" +
                "    \"profileId\": 178\n" +
                "}";
        // De-serialisation
 // convert Json body to Map
        Map<String,Object> dataMap=gson.fromJson(jsonBody,Map.class);
        System.out.println("dataMap = " + dataMap);

        //convert jason to object class
        EurotechUser eurotechUser=gson.fromJson(jsonBody,EurotechUser.class);
        System.out.println("eurotechUser.getEmail() = " + eurotechUser.getEmail());
        System.out.println("eurotechUser.getCompany() = " + eurotechUser.getCompany());

        // Serialisation
        EurotechUser eurotechUser1=new EurotechUser(11,"pojo123@gmail.com","Tom","Motorola","Retired",111);
        String jsonUser = gson.toJson(eurotechUser1);
        System.out.println("jsonUser = " + jsonUser);


    }

}
