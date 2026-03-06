package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.CartPage;
import com.abhisha.pages.LoginPage;
import com.abhisha.pages.ProductsPage;
import com.abhisha.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(description = "Verify product is added to cart successfully")
    public void verifyProductAddedToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Add product to cart
        productsPage.navigateToProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        // Navigate to cart and verify
        cartPage.navigateToCart();

        Assert.assertFalse(cartPage.isCartEmpty(),
                "Cart is empty! Product was not added successfully.");

        System.out.println("✅ Product added to cart test passed!");
    }

    @Test(description = "Verify product can be removed from cart")
    public void verifyProductRemovedFromCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Add product to cart first
        productsPage.navigateToProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        // Navigate to cart
        cartPage.navigateToCart();
        int countBefore = cartPage.getCartProductCount();
        System.out.println("Products before removal: " + countBefore);

        // Remove first product
        cartPage.removeFirstProduct();

        int countAfter = cartPage.getCartProductCount();
        System.out.println("Products after removal: " + countAfter);

        Assert.assertTrue(countAfter < countBefore,
                "Product was not removed from cart!");

        System.out.println("✅ Product removed from cart test passed!");
    }

    @Test(description = "Verify checkout modal appears for guest user")
    public void verifyCheckoutModalForGuestUser() {
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Add product to cart as guest (not logged in)
        productsPage.navigateToProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        // Navigate to cart and click checkout
        cartPage.navigateToCart();
        cartPage.clickProceedToCheckout();

        // Assert modal appears asking to login
        Assert.assertTrue(cartPage.isCheckoutModalDisplayed(),
                "Checkout modal not displayed for guest user!");

        cartPage.clickContinueOnCart();

        System.out.println("✅ Guest checkout modal test passed!");
    }

    @Test(description = "Verify logged in user can proceed to checkout page")
    public void verifyLoggedInUserCanCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Login first
        loginPage.login(
                ConfigReader.get("login.email"),
                ConfigReader.get("login.password")
        );
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed! Cannot proceed with checkout test.");

        // Add product to cart
        productsPage.navigateToProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        // Navigate to cart and checkout
        cartPage.navigateToCart();
        cartPage.clickProceedToCheckout();

        // Assert redirected to checkout page
        Assert.assertTrue(cartPage.isOnCheckoutPage(),
                "Logged in user not redirected to checkout page!");

        System.out.println("✅ Logged in user checkout test passed!");
    }
}