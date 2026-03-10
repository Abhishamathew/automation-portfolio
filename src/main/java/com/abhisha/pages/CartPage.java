package com.abhisha.pages;

import com.abhisha.utils.JavascriptUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptUtils js;

    // Locators
    By cartProducts = By.cssSelector("#cart_info_table tbody tr[id^='product-']");
    By cartProductNames = By.cssSelector(".cart_description h4 a");
    By deleteButtons = By.cssSelector(".cart_quantity_delete");
    By emptyCartMessage = By.cssSelector("#empty_cart");
    By proceedToCheckoutButton = By.cssSelector(".check_out");
    By checkoutModal = By.cssSelector("#checkoutModal");
    By checkoutModalTitle = By.cssSelector(".modal-title");
    By continueOnCartButton = By.cssSelector(".close-checkout-modal");
    By cartQuantityButton = By.cssSelector(".cart_quantity button");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = new JavascriptUtils(driver);
    }

    public void navigateToCart() {
        driver.get("https://automationexercise.com/view_cart");
        System.out.println("Navigated to cart page");
    }

    public boolean isCartEmpty() {
        try {
            List<WebElement> products = driver.findElements(cartProducts);
            boolean empty = products.size() == 0;
            System.out.println("Cart is empty: " + empty);
            return empty;
        } catch (Exception e) {
            return true;
        }
    }

    public int getCartProductCount() {
        List<WebElement> products = driver.findElements(cartProducts);
        System.out.println("Products in cart: " + products.size());
        return products.size();
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> names = driver.findElements(cartProductNames);
        for (WebElement name : names) {
            if (name.getText().toLowerCase().contains(productName.toLowerCase())) {
                System.out.println("Product found in cart: " + name.getText());
                return true;
            }
        }
        System.out.println("Product NOT found in cart: " + productName);
        return false;
    }

    public void removeFirstProduct() {
        List<WebElement> deleteBtn = driver.findElements(deleteButtons);
        if (deleteBtn.size() > 0) {
            js.click(deleteBtn.get(0));
            System.out.println("Removed first product from cart");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
        System.out.println("Clicked Proceed to Checkout");
    }

    public boolean isCheckoutModalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutModal));
            System.out.println("Checkout modal displayed!");
            return true;
        } catch (Exception e) {
            System.out.println("Checkout modal not displayed!");
            return false;
        }
    }

    public boolean isOnCheckoutPage() {
        try {
            wait.until(ExpectedConditions.urlContains("/checkout"));
            System.out.println("On checkout page: " + driver.getCurrentUrl());
            return true;
        } catch (Exception e) {
            System.out.println("Not on checkout page. URL: " + driver.getCurrentUrl());
            return false;
        }
    }

    public void clickContinueOnCart() {
        wait.until(ExpectedConditions.elementToBeClickable(continueOnCartButton)).click();
        System.out.println("Clicked Continue On Cart");
    }

    public int getProductQuantityInCart() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartQuantityButton));
            String qty = driver.findElement(cartQuantityButton).getText();
            System.out.println("Product quantity in cart: " + qty);
            return Integer.parseInt(qty.trim());
        } catch (Exception e) {
            System.out.println("Could not get quantity from cart!");
            return 0;
        }
    }
}