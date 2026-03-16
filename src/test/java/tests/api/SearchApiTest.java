package tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test(description = "API5 - Search Product with valid parameter")
    public void searchProductWithValidParam() {
        Response response = given()
                .formParam("search_product", "tshirt")
                .when()
                .post("/api/searchProduct")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString().substring(0, 200));

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("products"),
                "Response does not contain products!");

        System.out.println("✅ API5 Search Product passed!");
    }

    @Test(description = "API6 - Search Product without parameter returns 400")
    public void searchProductWithoutParam() {
        Response response = given()
                .when()
                .post("/api/searchProduct")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("400"),
                "Response should contain 400 response code!");
        Assert.assertTrue(response.getBody().asString()
                        .contains("search_product parameter is missing"),
                "Response message mismatch!");

        System.out.println("✅ API6 Search without param passed!");
    }
}