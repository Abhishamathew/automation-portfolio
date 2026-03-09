package com.abhisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SubscriptionPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By subscriptionHeading = By.xpath("//h2[text()='Subscription']");
    By subscribeEmailInput = By.id("susbscribe_email");
    By subscribeButton = By.id("subscribe");
    By successMessage = By.cssSelector("#success-subscribe .alert-success");

    public SubscriptionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void scrollToFooter() {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight)"
        );
        System.out.println("Scrolled to footer");
    }

    public boolean isSubscriptionHeadingVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionHeading));
            System.out.println("Subscription heading visible!");
            return true;
        } catch (Exception e) {
            System.out.println("Subscription heading not visible!");
            return false;
        }
    }

    public void enterSubscriptionEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeEmailInput))
                .sendKeys(email);
        System.out.println("Entered subscription email: " + email);
    }

    public void clickSubscribeButton() {
        driver.findElement(subscribeButton).click();
        System.out.println("Clicked subscribe button");
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            String text = driver.findElement(successMessage).getText();
            System.out.println("Subscription success message: " + text);
            return text.contains("You have been successfully subscribed!");
        } catch (Exception e) {
            System.out.println("Subscription success message not displayed!");
            return false;
        }
    }
}