package tests.ui;

import com.abhisha.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReviewTest extends BaseTest {

    @Test(description = "TC21 - Add review on product")
    public void addReviewOnProduct() {
        driver.get("https://automationexercise.com/product_details/1");
        removeAds();

        // Fill in review form
        WebElement nameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        nameField.clear();
        nameField.sendKeys("Abhisha Test");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.clear();
        emailField.sendKeys("abhishaauto2026@yopmail.com");

        WebElement reviewField = driver.findElement(By.id("review"));
        reviewField.clear();
        reviewField.sendKeys("Great product! Very good quality and fast delivery.");

        removeAds();

        // Submit review
        driver.findElement(By.id("button-review")).click();

        // Verify success message
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("review-section")));

        Assert.assertTrue(successMessage.isDisplayed(),
                "Thank you message not displayed after review submission!");
        Assert.assertTrue(successMessage.getText().contains("Thank you for your review"),
                "Success message text mismatch!");

        System.out.println("✅ TC21 Add review on product passed!");
    }
}