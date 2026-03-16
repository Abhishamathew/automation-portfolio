package tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BrandApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test(description = "API3 - Get All Brands List returns 200")
    public void getAllBrandsList() {
        Response response = given()
                .when()
                .get("/api/brandsList")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString().substring(0, 200));

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("brands"),
                "Response does not contain brands!");

        System.out.println("✅ API3 Get All Brands passed!");
    }

    @Test(description = "API4 - PUT to Brands List returns 405")
    public void putToBrandsListNotAllowed() {
        Response response = given()
                .when()
                .put("/api/brandsList")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("405"),
                "Response should contain 405 response code!");
        Assert.assertTrue(response.getBody().asString()
                        .contains("This request method is not supported"),
                "Response message mismatch!");

        System.out.println("✅ API4 PUT Brands not supported passed!");
    }
}