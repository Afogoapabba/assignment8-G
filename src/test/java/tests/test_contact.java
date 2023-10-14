package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ContactPage;
import testbase.TestBase;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class test_contact extends TestBase {
    /**
     * Test will open the contact modal and fill it
     * with valid data.
     */
    @Test
    void sendValidMessage(){
        Faker faker = new Faker();
        // Create and open page
        ContactPage contactPage = new ContactPage(page);
        contactPage.openContactPage();
        // Generate test data
        String email = faker.internet().emailAddress();
        String name = faker.name().fullName();
        String message = faker.lorem().sentence(40);
        // Fill form
        contactPage.fillForm(email,name,message);
        // Assert that test data is filled in
        assertThat(contactPage.getContactEmailTB()).hasValue(email);
        assertThat(contactPage.getContactNameTB()).hasValue(name);
        assertThat(contactPage.getContactMessageBox()).hasValue(message);

        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            Assertions.assertEquals("alert", dialog.type());
            Assertions.assertTrue(dialog.message().contains("Thanks for the message!!"));
            dialog.accept();
        });

        //send form
        contactPage.sendForm();

    }
    /**
     * Test will open the contact modal and attempt to
     * send without and data.
     *
     * Page has no validation for this so test will fail.
     */
    @Test
    void sendInvalidMessage(){
        // Create and open page
        ContactPage contactPage = new ContactPage(page);
        contactPage.openContactPage();

        // Assert that test data is filled in
        assertThat(contactPage.getContactEmailTB()).isEmpty();
        assertThat(contactPage.getContactNameTB()).isEmpty();
        assertThat(contactPage.getContactMessageBox()).isEmpty();

        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            Assertions.assertEquals("alert", dialog.type());
            assertFalse(dialog.message().contains("Thanks for the message!!"));
            dialog.accept();
        });

        //send form this will fail always since the page has a bug
        contactPage.sendForm();

    }
}
