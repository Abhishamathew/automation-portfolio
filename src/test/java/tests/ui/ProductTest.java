package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.ProductsPage;
import com.abhisha.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test(description = "Verify all products are displayed on products page")
    public void verifyAllProductsDisplayed() {
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.navigateToProducts();

        Assert.assertTrue(productsPage.areSearchResultsDisplayed(),
                "Products not displayed on products page!");

        System.out.println("✅ All products displayed test passed!");
    }

    @Test(description = "Verify user can search for a product")
    public void searchProduct() {
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.navigateToProducts();
        productsPage.searchProduct(ConfigReader.get("search.product"));

        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(),
                "Searched Products title not visible after search!");

        Assert.assertTrue(productsPage.areSearchResultsDisplayed(),
                "No search results found for: " + ConfigReader.get("search.product"));

        System.out.println("✅ Search product test passed!");
    }

    @Test(description = "Verify search results are related to search term")
    public void verifySearchResultsRelevance() {
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.navigateToProducts();
        productsPage.searchProduct(ConfigReader.get("search.product"));

        Assert.assertTrue(productsPage.isSearchedProductsTitleVisible(),
                "Searched Products title not visible!");

        Assert.assertTrue(productsPage.areAllResultsRelatedToSearch(
                        ConfigReader.get("search.product")),
                "Search results contain unrelated products!");

        System.out.println("✅ Search results relevance test passed!");
    }

    @Test(description = "Verify user can add product to cart from products page")
    public void addProductToCart() {
        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.navigateToProducts();
        productsPage.addFirstProductToCart();

        Assert.assertTrue(productsPage.isCartModalDisplayed(),
                "Cart modal not displayed after adding product!");

        productsPage.clickContinueShopping();

        System.out.println("✅ Add product to cart test passed!");
    }
}