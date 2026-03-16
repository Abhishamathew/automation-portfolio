package tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test(description = "API1 - Get All Products List returns 200")
    public void getAllProductsList() {
        Response response = given()
                .when()
                .get("/api/productsList")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString().substring(0, 200));

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("products"),
                "Response does not contain products!");

        System.out.println("✅ API1 Get All Products passed!");
    }

    @Test(description = "API2 - POST to Products List returns 405")
    public void postToProductsListNotAllowed() {
        Response response = given()
                .when()
                .post("/api/productsList")
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

        System.out.println("✅ API2 POST Products not supported passed!");
    }
}