package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.CartPage;
import com.abhisha.pages.CheckoutPage;
import com.abhisha.pages.LoginPage;
import com.abhisha.pages.ProductsPage;
import com.abhisha.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    @Test(description = "TC16 - Place Order: Login before Checkout")
    public void placeOrderLoginBeforeCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Step 1 - Login
        loginPage.login(
                ConfigReader.get("login.email"),
                ConfigReader.get("login.password")
        );
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed!");
        System.out.println("Step 1 - Logged in successfully");

        // Step 2 - Add product to cart
        productsPage.navigateToProducts();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();
        System.out.println("Step 2 - Product added to cart");

        // Step 3 - Go to cart and checkout
        cartPage.navigateToCart();
        cartPage.clickProceedToCheckout();
        Assert.assertTrue(cartPage.isOnCheckoutPage(),
                "Not on checkout page!");
        System.out.println("Step 3 - On checkout page");

        // Step 4 - Enter comment and place order
        checkoutPage.enterComment("Test order comment");
        checkoutPage.clickPlaceOrder();
        System.out.println("Step 4 - Place order clicked");

        // Step 5 - Enter payment details
        checkoutPage.enterPaymentDetails(
                ConfigReader.get("card.name"),
                ConfigReader.get("card.number"),
                ConfigReader.get("card.cvc"),
                ConfigReader.get("card.month"),
                ConfigReader.get("card.year")
        );

        // Step 6 - Pay and confirm
        checkoutPage.clickPayAndConfirm();
        System.out.println("Step 5 - Payment submitted");

        // Step 7 - Verify order placed
        Assert.assertTrue(checkoutPage.isOrderPlacedSuccessfully(),
                "Order was not placed successfully!");

        System.out.println("✅ TC16 Place Order Login before Checkout test passed!");
    }
}