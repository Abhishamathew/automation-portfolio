package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.RegisterPage;
import com.abhisha.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test(description = "Verify new user can register successfully")
    public void registerNewUser() {
        RegisterPage registerPage = new RegisterPage(driver);

        // Step 1 - Enter name and email on login page
        registerPage.enterSignupDetails(
                ConfigReader.get("register.name"),
                ConfigReader.get("register.email")
        );

        // Step 2 - Fill registration form
        registerPage.fillRegistrationForm(
                ConfigReader.get("register.password"),
                ConfigReader.get("register.firstname"),
                ConfigReader.get("register.lastname"),
                ConfigReader.get("register.address"),
                ConfigReader.get("register.country"),
                ConfigReader.get("register.state"),
                ConfigReader.get("register.city"),
                ConfigReader.get("register.zipcode"),
                ConfigReader.get("register.mobile")
        );

        // Step 3 - Assert account created
        Assert.assertTrue(registerPage.isAccountCreated(),
                "Account creation failed! Expected 'Account Created!' message.");

        System.out.println("✅ Register new user test passed!");
    }

    @Test(description = "Verify error shown for already registered email")
    public void registerWithExistingEmail() {
        RegisterPage registerPage = new RegisterPage(driver);

        // Use already registered email
        registerPage.enterSignupDetails(
                ConfigReader.get("register.name"),
                ConfigReader.get("login.email")
        );

        // Assert error message appears
        Assert.assertTrue(registerPage.isEmailAlreadyExists(),
                "Expected 'Email Address already exist!' error but it was not shown!");

        System.out.println("✅ Existing email register test passed!");
    }
}