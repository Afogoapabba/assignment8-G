import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class test_sign_up extends TestBase{
    @Test
    void newSignUp() {
        // Create and open page
        SignUpPage signUpPage = new SignUpPage(page);
        signUpPage.openSignUpPage();
        // Generate test data
        String username = generateRandomString(15);
        String password = generateRandomString(25);

        // Fill form
        signUpPage.fillForm(username,password);
        // Assert that test data is filled in
        assertThat(signUpPage.tbUsername).hasValue(username);
        assertThat(signUpPage.tbPassword).hasValue(password);
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
