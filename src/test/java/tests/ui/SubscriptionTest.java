package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.SubscriptionPage;
import com.abhisha.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubscriptionTest extends BaseTest {

    @Test(description = "TC10 - Verify subscription on home page")
    public void verifySubscriptionOnHomePage() {
        SubscriptionPage subscriptionPage = new SubscriptionPage(driver);

        // Navigate to home page
        driver.get("https://automationexercise.com");

        // Scroll to footer
        subscriptionPage.scrollToFooter();

        // Verify subscription heading
        Assert.assertTrue(subscriptionPage.isSubscriptionHeadingVisible(),
                "Subscription heading not visible!");

        // Enter email and subscribe
        subscriptionPage.enterSubscriptionEmail(ConfigReader.get("subscription.email"));
        subscriptionPage.clickSubscribeButton();

        // Verify success message
        Assert.assertTrue(subscriptionPage.isSuccessMessageDisplayed(),
                "Subscription success message not displayed!");

        System.out.println("✅ TC10 Subscription on home page test passed!");
    }

    @Test(description = "TC11 - Verify subscription on cart page")
    public void verifySubscriptionOnCartPage() {
        SubscriptionPage subscriptionPage = new SubscriptionPage(driver);

        // Navigate to cart page
        driver.get("https://automationexercise.com/view_cart");

        // Scroll to footer
        subscriptionPage.scrollToFooter();

        // Verify subscription heading
        Assert.assertTrue(subscriptionPage.isSubscriptionHeadingVisible(),
                "Subscription heading not visible on cart page!");

        // Enter email and subscribe
        subscriptionPage.enterSubscriptionEmail(ConfigReader.get("subscription.email"));
        subscriptionPage.clickSubscribeButton();

        // Verify success message
        Assert.assertTrue(subscriptionPage.isSuccessMessageDisplayed(),
                "Subscription success message not displayed on cart page!");

        System.out.println("✅ TC11 Subscription on cart page test passed!");
    }
}