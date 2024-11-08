package Tests;
	
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
	 
public class BaseAPI {
	
   Faker faker = new Faker();
	
   String email = "thejdeep.reddy@bolt.com";
   String username ="thejdeep.reddy@bolt.com";
   String password = "admin";
   String role = "write";
   String employeeId ="5";
   
   String invalidusername ="test123@gmail.com";
   String invalidpassword = "invalidadmin";
   


public String fetchAuthToken() {
    // Simulating the process of getting an authentication token
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

    return accessToken;
}

}
	
