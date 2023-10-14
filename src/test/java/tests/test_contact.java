import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class test_contact extends TestBase {
    @Test
    void validMessage(){
        // Create and open page
        ContactPage contactPage = new ContactPage(page);
        contactPage.openContactPage();
        // Generate test data
        String email = generateRandomString(7) + "@test.com";
        String name = generateRandomString(7);
        String message = generateRandomString(100);
        // Fill form
        contactPage.fillForm(email,name,message);
        // Assert that test data is filled in
        assertThat(contactPage.contactEmailTB).hasValue(email);
        assertThat(contactPage.contactNameTB).hasValue(name);
        assertThat(contactPage.contactMessageBox).hasValue(message);

        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            assertEquals("alert", dialog.type());
            assertTrue(dialog.message().contains("Thanks for the message!!"));
            dialog.accept();
        });

        //send form
        contactPage.sendForm();

    }
    @Test
    void BlankMessage(){
        // Create and open page
        ContactPage contactPage = new ContactPage(page);
        contactPage.openContactPage();


        // Assert that test data is filled in
        assertThat(contactPage.contactEmailTB).isEmpty();
        assertThat(contactPage.contactNameTB).isEmpty();
        assertThat(contactPage.contactMessageBox).isEmpty();

        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            assertEquals("alert", dialog.type());
            assertFalse(dialog.message().contains("Thanks for the message!!"));
            dialog.accept();
        });

        //send form
        contactPage.sendForm();

    }
}
