package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteEmployeeAPITest  extends BaseAPI{
	
@Test
public void DeleteEmployeeTest(){ 
		
	RestAssured.baseURI = "http://192.168.0.60:5000/api"; 
	     
	Response deleteresponse = RestAssured.given()
	    		 .header("accessToken", fetchAuthToken())
	             .multiPart("id", employeeId)  
	             .when()
	             .delete("/employees")
	             .then()
	             .statusCode(202)  
	             .extract().response();

	         System.out.println("Response: " + deleteresponse.asString());
	         
	         String responsevalue = deleteresponse.jsonPath().getString("success");
		 	 String responsemessage = deleteresponse.jsonPath().getString("message");
	         
	         Assert.assertEquals("true", responsevalue);
		 	 Assert.assertEquals("deleted", responsemessage);
		 	 
	Response employeedetails = RestAssured.given()
		    		 .header("accessToken", fetchAuthToken())
		    		 .param("id", employeeId)
		             .when()
		             .get("/employees/id")
		             .then()
		             .statusCode(404)  
		             .extract().response();
	
	   System.out.println("Response: " + employeedetails.asString());
	   
	   String value = employeedetails.jsonPath().getString("success");
	   String message = employeedetails.jsonPath().getString("message");
       
       Assert.assertEquals("false", value);
	   Assert.assertEquals("employee not found", message);
       
	     
	   }

}
