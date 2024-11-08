package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateEmployeeAPITest extends BaseAPI {

	
@Test
public void PostCreateEmployeeTest() { 
		

		 RestAssured.baseURI = "http://192.168.0.60:5000/api"; 
	     
	     String jsonBody = "[ { \"firstname\": \"chaithu\", \"lastname\": \"mokshreddy\", \"email\": \"chaithu@test.com\" } ]";
	     
	     Response response = RestAssured.given()
	    		 .header("accessToken", fetchAuthToken())
	             .contentType(ContentType.JSON)  
	             .body(jsonBody)                
	             .when()
	             .post("/employees") 
	             .then()
	             .statusCode(201)     
	             .extract().response();   

	     System.out.println("Register Response: " + response.getBody().asString());
	     
	     String responsevalue = response.jsonPath().getString("success");
		 String responsemessage = response.jsonPath().getString("message");
	  
		 Assert.assertEquals("true", responsevalue);
		 Assert.assertTrue(responsemessage.contains("id="), "Employee is not created ");
		      	     
	}


}
