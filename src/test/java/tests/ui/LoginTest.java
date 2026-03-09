package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.LoginPage;
import com.abhisha.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify user can login with valid credentials")
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.get("login.email"),
                ConfigReader.get("login.password")
        );

        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed! Valid credentials should allow login.");

        System.out.println("✅ Valid login test passed!");
    }

    @Test(description = "Verify error message for invalid credentials")
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.get("invalid.email"),
                ConfigReader.get("invalid.password")
        );

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message not shown for invalid credentials!");

        Assert.assertFalse(loginPage.isLoginSuccessful(),
                "User should NOT be logged in with invalid credentials!");

        System.out.println("✅ Invalid login test passed!");
    }

    @Test(description = "Verify user can logout successfully")
    public void logoutUser() {
        LoginPage loginPage = new LoginPage(driver);

        // Login first
        loginPage.login(
                ConfigReader.get("login.email"),
                ConfigReader.get("login.password")
        );

        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed! Cannot test logout.");

        // Logout
        loginPage.clickLogout();

        // Assert redirected to login page
        Assert.assertTrue(loginPage.isLoggedOut(),
                "User was not redirected to login page after logout!");

        System.out.println("✅ Logout test passed!");
    }
}
