package tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserApiTest {

    private static final String EMAIL = "abhisha.api.test@yopmail.com";
    private static final String PASSWORD = "Test@1234";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test(description = "API11 - Create User Account", priority = 1)
    public void createUserAccount() {
        Response response = given()
                .formParam("name", "Abhisha API Test")
                .formParam("email", EMAIL)
                .formParam("password", PASSWORD)
                .formParam("title", "Mrs")
                .formParam("birth_date", "15")
                .formParam("birth_month", "6")
                .formParam("birth_year", "1995")
                .formParam("firstname", "Abhisha")
                .formParam("lastname", "Test")
                .formParam("company", "Test Co")
                .formParam("address1", "123 Test Street")
                .formParam("address2", "Apt 1")
                .formParam("country", "Canada")
                .formParam("zipcode", "M1M1M1")
                .formParam("state", "Ontario")
                .formParam("city", "Toronto")
                .formParam("mobile_number", "6471234567")
                .when()
                .post("/api/createAccount")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("201"),
                "Response should contain 201 created code!");
        Assert.assertTrue(response.getBody().asString().contains("User created!"),
                "User not created!");

        System.out.println("✅ API11 Create User passed!");
    }

    @Test(description = "API14 - Get User Detail by Email", priority = 2)
    public void getUserDetailByEmail() {
        Response response = given()
                .queryParam("email", EMAIL)
                .when()
                .get("/api/getUserDetailByEmail")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("Abhisha"),
                "User detail not found in response!");

        System.out.println("✅ API14 Get User Detail passed!");
    }

    @Test(description = "API7 - Verify Login with valid credentials", priority = 3)
    public void verifyLoginValid() {
        Response response = given()
                .formParam("email", EMAIL)
                .formParam("password", PASSWORD)
                .when()
                .post("/api/verifyLogin")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("User exists!"),
                "User should exist!");

        System.out.println("✅ API7 Verify Login valid passed!");
    }

    @Test(description = "API8 - Verify Login without email returns 400", priority = 4)
    public void verifyLoginWithoutEmail() {
        Response response = given()
                .formParam("password", PASSWORD)
                .when()
                .post("/api/verifyLogin")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("400"),
                "Response should contain 400 code!");

        System.out.println("✅ API8 Verify Login without email passed!");
    }

    @Test(description = "API9 - DELETE to Verify Login returns 405", priority = 5)
    public void deleteVerifyLoginNotAllowed() {
        Response response = given()
                .when()
                .delete("/api/verifyLogin")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertTrue(response.getBody().asString().contains("405"),
                "Response should contain 405 code!");

        System.out.println("✅ API9 DELETE Login not supported passed!");
    }

    @Test(description = "API10 - Verify Login with invalid credentials returns 404", priority = 6)
    public void verifyLoginInvalid() {
        Response response = given()
                .formParam("email", "wrong@gmail.com")
                .formParam("password", "wrongpassword")
                .when()
                .post("/api/verifyLogin")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("404"),
                "Response should contain 404 code!");
        Assert.assertTrue(response.getBody().asString().contains("User not found!"),
                "Message should say User not found!");

        System.out.println("✅ API10 Verify Login invalid passed!");
    }

    @Test(description = "API13 - Update User Account", priority = 7)
    public void updateUserAccount() {
        Response response = given()
                .formParam("name", "Abhisha Updated")
                .formParam("email", EMAIL)
                .formParam("password", PASSWORD)
                .formParam("title", "Mrs")
                .formParam("birth_date", "15")
                .formParam("birth_month", "6")
                .formParam("birth_year", "1995")
                .formParam("firstname", "Abhisha")
                .formParam("lastname", "Updated")
                .formParam("company", "Updated Co")
                .formParam("address1", "456 Updated Street")
                .formParam("address2", "Apt 2")
                .formParam("country", "Canada")
                .formParam("zipcode", "M1M1M1")
                .formParam("state", "Ontario")
                .formParam("city", "Toronto")
                .formParam("mobile_number", "6471234567")
                .when()
                .put("/api/updateAccount")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("User updated!"),
                "User not updated!");

        System.out.println("✅ API13 Update User passed!");
    }

    @Test(description = "API12 - Delete User Account", priority = 8)
    public void deleteUserAccount() {
        Response response = given()
                .formParam("email", EMAIL)
                .formParam("password", PASSWORD)
                .when()
                .delete("/api/deleteAccount")
                .then()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200!");
        Assert.assertTrue(response.getBody().asString().contains("Account deleted!"),
                "Account not deleted!");

        System.out.println("✅ API12 Delete User passed!");
    }
}