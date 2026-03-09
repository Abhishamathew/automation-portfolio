package com.abhisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By nameInput = By.cssSelector("[data-qa='name']");
    By emailInput = By.cssSelector("[data-qa='email']");
    By subjectInput = By.cssSelector("[data-qa='subject']");
    By messageInput = By.cssSelector("[data-qa='message']");
    By submitButton = By.cssSelector("[data-qa='submit-button']");
    By successMessage = By.cssSelector(".status.alert.alert-success");

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToContactPage() {
        driver.get("https://automationexercise.com/contact_us");
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        System.out.println("On Contact Us page");
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(subjectInput).sendKeys(subject);
        driver.findElement(messageInput).sendKeys(message);
        System.out.println("Contact form filled");
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
        // Handle the alert popup
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        System.out.println("Form submitted and alert accepted");
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            String text = driver.findElement(successMessage).getText();
            System.out.println("Success message: " + text);
            return text.contains("Success");
        } catch (Exception e) {
            System.out.println("Success message not displayed!");
            return false;
        }
    }
}