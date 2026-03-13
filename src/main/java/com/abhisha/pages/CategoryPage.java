package com.abhisha.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CategoryPage {

    WebDriver driver;
    WebDriverWait wait;

    // Category locators
    By womenCategory = By.cssSelector("a[href='#Women']");
    By menCategory = By.cssSelector("a[href='#Men']");
    By kidsCategory = By.cssSelector("a[href='#Kids']");

    // Subcategory locators
    By womenDress = By.cssSelector("a[href='/category_products/1']");
    By womenTops = By.cssSelector("a[href='/category_products/2']");
    By womenSaree = By.cssSelector("a[href='/category_products/7']");
    By menTshirts = By.cssSelector("a[href='/category_products/3']");
    By menJeans = By.cssSelector("a[href='/category_products/6']");

    // Results locators
    By categoryTitle = By.cssSelector(".features_items h2.title");
    By productList = By.cssSelector(".features_items .productinfo");

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Remove ads on page load
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "var iframes = document.querySelectorAll('iframe');" +
                        "iframes.forEach(function(el) { el.remove(); });" +
                        "var ads = document.querySelectorAll('ins, .adsbygoogle, .google-auto-placed');" +
                        "ads.forEach(function(el) { el.remove(); });"
        );
    }

    public void clickWomenCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(womenCategory)).click();
        System.out.println("Clicked Women category");
        // Wait for panel to expand
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public void clickMenCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(menCategory)).click();
        System.out.println("Clicked Men category");
        // Wait for panel to expand
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public void clickWomenTops() {
        // Wait for subcategory to be visible after panel expands
        wait.until(ExpectedConditions.visibilityOfElementLocated(womenTops));
        wait.until(ExpectedConditions.elementToBeClickable(womenTops)).click();
        System.out.println("Clicked Women > Tops");
    }

    public void clickMenTshirts() {
        // Wait for subcategory to be visible after panel expands
        wait.until(ExpectedConditions.visibilityOfElementLocated(menTshirts));
        wait.until(ExpectedConditions.elementToBeClickable(menTshirts)).click();
        System.out.println("Clicked Men > Tshirts");
    }

    public boolean isCategoryPageDisplayed(String expectedTitle) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTitle));
            String title = driver.findElement(categoryTitle).getText();
            System.out.println("Category page title: " + title);
            return title.toUpperCase().contains(expectedTitle.toUpperCase());
        } catch (Exception e) {
            System.out.println("Category title not found!");
            return false;
        }
    }

    public boolean areProductsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productList));
            int count = driver.findElements(productList).size();
            System.out.println("Products displayed: " + count);
            return count > 0;
        } catch (Exception e) {
            System.out.println("No products displayed!");
            return false;
        }
    }
}