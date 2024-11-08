package Utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.util.Map;

public class UtilityHelper {

    private String baseUri;
    private String authToken;

    public UtilityHelper(String baseUri, String authToken) {
        this.baseUri = baseUri;
        this.authToken = authToken;
        RestAssured.baseURI = baseUri;
    }

  
    public Response sendMultipartRequest(String endpoint, Map<String, String> formParams) {
        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.MULTIPART);

   
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            request.formParam(entry.getKey(), entry.getValue());
        }

        return request.when().post(endpoint);
    }
}
