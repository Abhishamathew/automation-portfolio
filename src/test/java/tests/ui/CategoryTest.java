package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.CategoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {

    @Test(description = "TC18 - Verify Women category products are displayed")
    public void verifyWomenCategoryProducts() {
        CategoryPage categoryPage = new CategoryPage(driver);

        driver.get("https://automationexercise.com");
        removeAds(); // ← add this

        categoryPage.clickWomenCategory();
        removeAds(); // ← add this after expanding
        categoryPage.clickWomenTops();

        Assert.assertTrue(categoryPage.isCategoryPageDisplayed("Tops"),
                "Women Tops category page not displayed!");
        Assert.assertTrue(categoryPage.areProductsDisplayed(),
                "No products displayed on Women Tops category page!");

        System.out.println("✅ TC18 Women category products test passed!");
    }

    @Test(description = "TC18 - Verify Men category products are displayed")
    public void verifyMenCategoryProducts() {
        CategoryPage categoryPage = new CategoryPage(driver);

        driver.get("https://automationexercise.com");
        removeAds(); // ← add this

        categoryPage.clickMenCategory();
        removeAds(); // ← add this after expanding
        categoryPage.clickMenTshirts();

        Assert.assertTrue(categoryPage.isCategoryPageDisplayed("Tshirts"),
                "Men Tshirts category page not displayed!");
        Assert.assertTrue(categoryPage.areProductsDisplayed(),
                "No products displayed on Men Tshirts category page!");

        System.out.println("✅ TC18 Men category products test passed!");
    }
}