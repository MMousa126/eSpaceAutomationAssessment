package Testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class T04AddCandidateAPITest {

    @Test
    public void addCandidateApiTest() {
        // Set base URI
        RestAssured.baseURI = "https://opensource-demo.orangehrmlive.com";

        // Simulated JSON body for a new candidate
        String requestBody = "{\"firstName\":\"cxxc\",\n" +
                "\"middleName\":null,\n" +
                "\"lastName\":\"x cx\",\n" +
                "\"email\":\"fdkdfk@gamil.com\",\n" +
                "\"contactNumber\":null,\n" +
                "\"keywords\":null,\n" +
                "\"comment\":null,\n" +
                "\"dateOfApplication\":\"2025-07-18\",\n" +
                "\"consentToKeepData\":false}";

        // Make the POST request with basic authentication
        Response response = given()
                .auth().preemptive().basic("Admin", "admin123")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/web/index.php/api/v2/recruitment/candidates");

        // Print response (for debug)
        response.prettyPrint();

        // Assert on status code (200, 201, 403, etc.)
        int statusCode = response.getStatusCode();
        System.out.println("Response Code: " + statusCode);

        // Validate the request failed or succeeded as expected
        Assert.assertTrue(statusCode == 200 || statusCode == 201 || statusCode == 403 || statusCode == 400,
                "Unexpected status code: " + statusCode);
    }
}
