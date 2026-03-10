package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.CartPage;
import com.abhisha.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuantityTest extends BaseTest {

    @Test(description = "TC13 - Verify product quantity in cart")
    public void verifyProductQuantityInCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Navigate to product detail page
        productsPage.navigateToProductDetail(1);

        // Set quantity to 4
        productsPage.setQuantity(4);

        // Add to cart
        productsPage.clickAddToCartOnDetailPage();

        // Handle cart modal
        productsPage.isCartModalDisplayed();
        productsPage.clickContinueShopping();

        // Navigate to cart
        cartPage.navigateToCart();

        // Verify quantity is 4
        int actualQty = cartPage.getProductQuantityInCart();
        Assert.assertEquals(actualQty, 4,
                "Expected quantity 4 but found: " + actualQty);

        System.out.println("✅ TC13 Product quantity in cart test passed!");
    }
}