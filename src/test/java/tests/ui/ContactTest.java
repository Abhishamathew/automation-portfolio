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
}