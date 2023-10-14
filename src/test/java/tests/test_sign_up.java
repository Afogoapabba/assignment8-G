package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.SignUpPage;
import testbase.TestBase;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class test_sign_up extends TestBase {
    /**
     * Test will create a new login.
     */
    @Test
    void newSignUp() {
        Faker faker = new Faker();
        // Create and open page
        SignUpPage signUpPage = new SignUpPage(page);
        signUpPage.openSignUpPage();
        // Generate test data
        String username = faker.name().username();
        String password = faker.internet().password();

        // Fill form
        signUpPage.fillForm(username,password);
        // Assert that test data is filled in
        assertThat(signUpPage.getTbUsername()).hasValue(username);
        assertThat(signUpPage.getTbPassword()).hasValue(password);
        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            assertEquals("alert", dialog.type());
            assertTrue(dialog.message().contains("Sign up successful."));
            dialog.accept();
        });
        signUpPage.signUp();
        // Sign in




    }
}
