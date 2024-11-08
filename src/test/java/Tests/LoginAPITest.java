package Tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginAPITest extends BaseAPI {
	
	
@Test
public void PostLoginTest() { 
		
	 RestAssured.baseURI = "http://192.168.0.60:5000/api"; 
	     
	 Response loginResponse = given()
	             .auth()
	             .preemptive()
	             .basic(username,password)
	             .header("Accept", "application/json")
	             .contentType(ContentType.JSON)
	             .when()
	             .post("/login")
	             .then()
	             .statusCode(201) 
	             .extract().response();
	     
	     String accessToken = loginResponse.jsonPath().getString("token");
	     System.out.println("Access Token: " + accessToken);
	     
	     Response invalidloginResponse = given()
	             .auth()
	             .preemptive()
	             .basic(invalidusername,invalidpassword)
	             .header("Accept", "application/json")
	             .contentType(ContentType.JSON)
	             .when()
	             .post("/login")
	             .then()
	             .statusCode(201) 
	             .extract().response();
	     
	     String nullaccessToken = invalidloginResponse.jsonPath().getString("token");
	     System.out.println("Access Token: " + nullaccessToken);
	     Assert.assertEquals(nullaccessToken, "");
	     	     
	}  

}
