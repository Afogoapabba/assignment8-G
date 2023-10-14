import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
public class test_login extends TestBase {
    @Test
    void invalidLogin() {
        // Create and open page
        LogInPage logInPage = new LogInPage(page);
        logInPage.openLogInPage();
        // Generate test data
        String username = generateRandomString(15);
        String password = generateRandomString(25);
        // Fill form
        logInPage.fillForm(username,password);
        // Assert that test data is filled in
        assertThat(logInPage.tbUsername).hasValue(username);
        assertThat(logInPage.tbPassword).hasValue(password);
        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            assertEquals("alert", dialog.type());
            assertTrue(dialog.message().contains("User does not exist."));
            dialog.accept();
        });

        // Log in
        logInPage.loginButton.click();

    }
    @Test
    void validLogin() {

        // Create and open page
        LogInPage logInPage = new LogInPage(page);
        logInPage.openLogInPage();
        // Get static test-data
        String username = getStaticUser();
        String password = getStaticPassword();
        // Fill form
        logInPage.fillForm(username,password);
        // Assert that test data is filled in
        assertThat(logInPage.tbUsername).hasValue(username);
        assertThat(logInPage.tbPassword).hasValue(password);
        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            assertEquals("alert", dialog.type());
            assertTrue(dialog.message().contains("User does not exist."));
            dialog.accept();
        });

        // Log in
        logInPage.loginButton.click();

        // Asser correct user is logged in
        Locator usernameLink = page.locator("#nameofuser");
        assertThat(usernameLink).containsText(staticUser);


    }
}
