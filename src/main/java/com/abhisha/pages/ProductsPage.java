package com.abhisha.pages;

import com.abhisha.utils.JavascriptUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductsPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptUtils js;

    // Locators
    By searchInput = By.id("search_product");
    By searchButton = By.id("submit_search");
    By searchedProductsTitle = By.xpath("//h2[text()='Searched Products']");
    By allProductsTitle = By.xpath("//h2[text()='All Products']");
    By productNames = By.cssSelector(".productinfo p");
    By addToCartButtons = By.cssSelector(".productinfo .add-to-cart");
    By cartModalTitle = By.cssSelector(".modal-title");
    By continueShoppingButton = By.cssSelector(".close-modal");
    // Product detail page locators
    By quantityInput = By.id("quantity");
    By addToCartButtonDetail = By.cssSelector("button.cart");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = new JavascriptUtils(driver);
    }

    public void navigateToProducts() {
        driver.get("https://automationexercise.com/products");
        wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsTitle));
        System.out.println("On products page");
    }

    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput))
                .clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
        System.out.println("Searched for: " + productName);
    }

    public boolean isSearchedProductsTitleVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchedProductsTitle));
            System.out.println("Searched Products title visible!");
            return true;
        } catch (Exception e) {
            System.out.println("Searched Products title not found!");
            return false;
        }
    }

    public boolean areSearchResultsDisplayed() {
        try {
            List<WebElement> products = driver.findElements(productNames);
            System.out.println("Search results found: " + products.size() + " products");
            return products.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAllResultsRelatedToSearch(String searchTerm) {
        List<WebElement> products = driver.findElements(productNames);

        // Handle variations like "Tshirt" vs "T-Shirt" vs "T Shirt"
        String normalizedSearch = searchTerm.toLowerCase()
                .replace("-", "")
                .replace(" ", "");

        for (WebElement product : products) {
            String name = product.getText().toLowerCase()
                    .replace("-", "")
                    .replace(" ", "");
            if (!name.contains(normalizedSearch)) {
                System.out.println("Unrelated product found: " + product.getText());
                return false;
            }
        }
        System.out.println("All " + products.size() + " results are related to: " + searchTerm);
        return true;
    }

    public void addFirstProductToCart() {
        List<WebElement> addToCartBtns = driver.findElements(addToCartButtons);
        js.scrollIntoView(addToCartBtns.get(0));
        js.click(addToCartBtns.get(0));
        System.out.println("Clicked Add to Cart for first product");
    }

    public boolean isCartModalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartModalTitle));
            System.out.println("Cart modal displayed: " + driver.findElement(cartModalTitle).getText());
            return true;
        } catch (Exception e) {
            System.out.println("Cart modal not displayed!");
            return false;
        }
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
        System.out.println("Clicked Continue Shopping");
    }

    public void navigateToProductDetail(int productId) {
        driver.get("https://automationexercise.com/product_details/" + productId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        System.out.println("On product detail page for product: " + productId);
    }

    public void setQuantity(int quantity) {
        WebElement qtyInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(quantityInput)
        );
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(quantity));
        System.out.println("Set quantity to: " + quantity);
    }

    public void clickAddToCartOnDetailPage() {
        driver.findElement(addToCartButtonDetail).click();
        System.out.println("Clicked Add to Cart on detail page");
    }
}