package tests.ui;

import com.abhisha.base.BaseTest;
import com.abhisha.pages.ContactPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTest extends BaseTest {

    @Test(description = "TC6 - Verify user can submit contact us form successfully")
    public void submitContactUsForm() {
        ContactPage contactPage = new ContactPage(driver);

        contactPage.navigateToContactPage();

        contactPage.fillContactForm(
                "Abhisha Test",
                "abhishaauto2026@yopmail.com",
                "Test Subject",
                "This is a test message from automation."
        );

        contactPage.submitForm();

        Assert.assertTrue(contactPage.isSuccessMessageDisplayed(),
                "Success message not displayed after form submission!");

        System.out.println("✅ Contact Us form test passed!");
    }

    @Test(description = "TC7 - Verify Test Cases page is accessible and displayed")
    public void verifyTestCasesPage() {
        driver.get("https://automationexercise.com/test_cases");

        // Verify URL
        Assert.assertTrue(driver.getCurrentUrl().contains("/test_cases"),
                "Not on Test Cases page!");

        // Verify page title
        Assert.assertTrue(driver.getTitle().contains("Test Cases"),
                "Page title mismatch!");

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());
        System.out.println("✅ Test Cases page verified!");
    }
}