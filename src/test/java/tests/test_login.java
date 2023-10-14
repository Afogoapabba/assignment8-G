package tests;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import pages.LogInPage;
import testbase.TestBase;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class test_login extends TestBase {
    /**
     * Test will try to log in with a user
     * not registered
     */
    @Test
    void invalidLogin() {
        Faker faker = new Faker();
        // Create and open page
        LogInPage logInPage = new LogInPage(page);
        logInPage.openLogInPage();
        // Generate test data
        String username = faker.name().username();
        String password = faker.internet().password();
        // Fill form
        logInPage.fillForm(username,password);
        // Assert that test data is filled in
        assertThat(logInPage.getTbUsername()).hasValue(username);
        assertThat(logInPage.getTbPassword()).hasValue(password);
        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            assertEquals("alert", dialog.type());
            assertTrue(dialog.message().contains("User does not exist."));
            dialog.accept();
        });

        // Log in
        logInPage.getLoginButton().click();

    }

    /**
     * Test will try to log in with an already
     * registered user.(set in TestBase)
     */
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
        assertThat(logInPage.getTbUsername()).hasValue(username);
        assertThat(logInPage.getTbPassword()).hasValue(password);

        // Log in
        logInPage.getLoginButton().click();

        // Asser correct user is logged in
        Locator usernameLink = page.locator("#nameofuser");
        assertThat(usernameLink).containsText(staticUser);


    }
}
