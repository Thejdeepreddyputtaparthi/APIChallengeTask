package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateEmployeeAPITest extends BaseAPI {

	
@Test
public void PostUpdateEmployeeTest() { 
	     
		 RestAssured.baseURI = "http://192.168.0.60:5000/api"; 
		
	     Response updatedresponse = RestAssured.given()
	    		 .header("accessToken", fetchAuthToken())
	    		 .multiPart("firstname","Thejreddy")
	    		 .multiPart("lastname","mokshtha")
	    		 .multiPart("email","thejderedy@test.com")
	             .multiPart("id", "1")  
	             .when()
	             .patch("/employees") 
	             .then()
	             .statusCode(201) 
	             .extract().response();

	         System.out.println("Response: " + updatedresponse.asString());
	         
	        String responsevalue = updatedresponse.jsonPath().getString("success");
	 	    String responsemessage = updatedresponse.jsonPath().getString("message");
	   
	 	    Assert.assertEquals("true", responsevalue);
	 	    Assert.assertEquals("updated", responsemessage);
	     
	}
	    
}
