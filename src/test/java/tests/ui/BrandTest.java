package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.CategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class BrandTest extends BaseTest {

    @Test(description = "TC19 - Verify brand products page is displayed for Polo")
    public void verifyPoloBrandProducts() {
        driver.get("https://automationexercise.com/products");
        removeAds();

        // Click Polo brand
        By poloBrand = By.cssSelector("a[href='/brand_products/Polo']");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .elementToBeClickable(poloBrand)).click();
        System.out.println("Clicked Polo brand");

        removeAds();

        // Verify URL
        Assert.assertTrue(driver.getCurrentUrl().contains("brand_products/Polo"),
                "Not on Polo brand page!");

        // Verify title
        String title = driver.findElement(
                By.cssSelector(".features_items h2.title")).getText();
        System.out.println("Brand page title: " + title);
        Assert.assertTrue(title.toUpperCase().contains("POLO"),
                "Brand title does not contain Polo!");

        // Verify products displayed
        int productCount = driver.findElements(
                By.cssSelector(".features_items .productinfo")).size();
        System.out.println("Polo products displayed: " + productCount);
        Assert.assertTrue(productCount > 0,
                "No products displayed for Polo brand!");

        System.out.println("✅ TC19 Polo brand products test passed!");
    }

    @Test(description = "TC19 - Verify brand products page is displayed for H&M")
    public void verifyHMBrandProducts() {
        driver.get("https://automationexercise.com/products");
        removeAds();

        // Click H&M brand
        By hmBrand = By.cssSelector("a[href='/brand_products/H&M']");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .elementToBeClickable(hmBrand)).click();
        System.out.println("Clicked H&M brand");

        removeAds();

        // Verify URL
        Assert.assertTrue(driver.getCurrentUrl().contains("brand_products"),
                "Not on H&M brand page!");

        // Verify products displayed
        int productCount = driver.findElements(
                By.cssSelector(".features_items .productinfo")).size();
        System.out.println("H&M products displayed: " + productCount);
        Assert.assertTrue(productCount > 0,
                "No products displayed for H&M brand!");

        System.out.println("✅ TC19 H&M brand products test passed!");
    }
}