package Tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;

public class RegisterUserAPITest extends BaseAPI{

	   
@Test
public void createUserTest() {
		
		RestAssured.baseURI = "http://192.168.0.60:5000/api"; 
		
	    Response registerResponse = given()
	            .multiPart("email", faker.internet().emailAddress())
	            .multiPart("password", password)
	            .multiPart("role", role)
	            .when()
	            .post("/register")
	            .then()
	            .statusCode(201)
	            .extract().response();

	    System.out.println("Register Response: " + registerResponse.getBody().asString());
	    
	    String responsevalue = registerResponse.jsonPath().getString("success");
	    String responsemessage = registerResponse.jsonPath().getString("message");
  
	    Assert.assertEquals("true", responsevalue);
	    Assert.assertEquals("created", responsemessage);
	        
	    }

}
