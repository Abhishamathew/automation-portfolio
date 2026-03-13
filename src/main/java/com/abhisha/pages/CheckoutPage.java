package com.abhisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // Checkout page locators
    By commentTextarea = By.cssSelector("#ordermsg textarea");
    By placeOrderButton = By.cssSelector(".check_out[href='/payment']");

    // Payment page locators
    By nameOnCard = By.cssSelector("[data-qa='name-on-card']");
    By cardNumber = By.cssSelector("[data-qa='card-number']");
    By cvc = By.cssSelector("[data-qa='cvc']");
    By expiryMonth = By.cssSelector("[data-qa='expiry-month']");
    By expiryYear = By.cssSelector("[data-qa='expiry-year']");
    By payButton = By.cssSelector("[data-qa='pay-button']");

    // Success locators
    By orderPlacedMessage = By.cssSelector("[data-qa='order-placed']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentTextarea))
                .sendKeys(comment);
        System.out.println("Entered comment: " + comment);
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
        System.out.println("Clicked Place Order");
    }

    public void enterPaymentDetails(String name, String number,
                                    String cvcValue, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameOnCard))
                .sendKeys(name);
        driver.findElement(cardNumber).sendKeys(number);
        driver.findElement(cvc).sendKeys(cvcValue);
        driver.findElement(expiryMonth).sendKeys(month);
        driver.findElement(expiryYear).sendKeys(year);
        System.out.println("Payment details entered");
    }

    public void clickPayAndConfirm() {
        driver.findElement(payButton).click();
        System.out.println("Clicked Pay and Confirm Order");
    }

    public boolean isOrderPlacedSuccessfully() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderPlacedMessage));
            String text = driver.findElement(orderPlacedMessage).getText();
            System.out.println("Order status: " + text);
            return text.contains("Order Placed!");
        } catch (Exception e) {
            System.out.println("Order placed message not found!");
            return false;
        }
    }
}