package com.abhisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    // Signup form locators (on login page)
    By signupName = By.cssSelector("[data-qa='signup-name']");
    By signupEmail = By.cssSelector("[data-qa='signup-email']");
    By signupButton = By.cssSelector("[data-qa='signup-button']");

    // Registration form locators (on signup page)
    By passwordInput = By.cssSelector("[data-qa='password']");
    By daySelect = By.cssSelector("[data-qa='days']");
    By monthSelect = By.cssSelector("[data-qa='months']");
    By yearSelect = By.cssSelector("[data-qa='years']");
    By firstNameInput = By.cssSelector("[data-qa='first_name']");
    By lastNameInput = By.cssSelector("[data-qa='last_name']");
    By addressInput = By.cssSelector("[data-qa='address']");
    By countrySelect = By.cssSelector("[data-qa='country']");
    By stateInput = By.cssSelector("[data-qa='state']");
    By cityInput = By.cssSelector("[data-qa='city']");
    By zipcodeInput = By.cssSelector("[data-qa='zipcode']");
    By mobileInput = By.cssSelector("[data-qa='mobile_number']");
    By createAccountButton = By.cssSelector("[data-qa='create-account']");

    // Success locator
    By accountCreatedText = By.xpath("//b[text()='Account Created!']");
    By emailExistsError = By.xpath(
            "//*[contains(text(),'Email Address already exist!')]"
    );

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterSignupDetails(String name, String email) {
        driver.get("https://automationexercise.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupName))
                .sendKeys(name);
        driver.findElement(signupEmail).sendKeys(email);
        driver.findElement(signupButton).click();
        System.out.println("Signup details entered for: " + email);
    }

    public void fillRegistrationForm(String password, String firstName,
                                     String lastName, String address, String country,
                                     String state, String city, String zipcode, String mobile) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput))
                .sendKeys(password);

        new Select(driver.findElement(daySelect)).selectByValue("1");
        new Select(driver.findElement(monthSelect)).selectByValue("1");
        new Select(driver.findElement(yearSelect)).selectByValue("2000");

        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        new Select(driver.findElement(countrySelect)).selectByVisibleText(country);
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipcodeInput).sendKeys(zipcode);
        driver.findElement(mobileInput).sendKeys(mobile);

        driver.findElement(createAccountButton).click();
        System.out.println("Registration form submitted");
    }

    public boolean isAccountCreated() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedText));
            System.out.println("Account created successfully!");
            return true;
        } catch (Exception e) {
            System.out.println("Account creation failed!");
            return false;
        }
    }

    public boolean isEmailAlreadyExists() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailExistsError));
            System.out.println("Email already exists error shown!");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}