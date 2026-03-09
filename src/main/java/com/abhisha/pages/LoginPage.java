package com.abhisha.pages;

import com.abhisha.utils.JavascriptUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptUtils js;

    // Locators
    By emailInput = By.cssSelector("[data-qa='login-email']");
    By passwordInput = By.cssSelector("[data-qa='login-password']");
    By loginButton = By.cssSelector("[data-qa='login-button']");
    By errorMessage = By.xpath("//*[contains(text(),'Your email or password is incorrect!')]");
    By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");
    By logoutButton = By.cssSelector("a[href='/logout']");
    By loggedInAsText = By.xpath("//a[contains(text(),'Logged in as')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = new JavascriptUtils(driver);
    }

    public void login(String email, String password) {
        driver.get("https://automationexercise.com/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput))
                .sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        System.out.println("Login attempted with: " + email);
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAs));
            System.out.println("Login successful!");
            return true;
        } catch (Exception e) {
            System.out.println("Login failed!");
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            System.out.println("Error message displayed: Your email or password is incorrect!");
            return true;
        } catch (Exception e) {
            System.out.println("Error message not found!");
            return false;
        }
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        System.out.println("Clicked Logout");
    }

    public boolean isLoggedOut() {
        try {
            wait.until(ExpectedConditions.urlContains("/login"));
            System.out.println("Redirected to login page after logout!");
            return true;
        } catch (Exception e) {
            System.out.println("Not redirected to login page!");
            return false;
        }
    }
}