package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import testbase.TestBase;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * this class works like a smoke-test to test the navigation bar
 * and also make sure all pages are accessible
 */
public class test_navigation extends TestBase {
    //Navigate to a URL
    @Test
    void navigateToPage() {

        assertEquals("https://www.demoblaze.com/", page.url());
    }
    @Test
    void navigateToHome() {
        Locator navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
        navigationLink.click();
        assertEquals("https://www.demoblaze.com/index.html", page.url());
    }
    @Test
    void navigateToContact() {
        Locator navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact"));
        navigationLink.click();
        assertThat(page.locator("#exampleModalLabel")).hasText("New message");
    }
    @Test
    void navigateToAboutUs() {
        Locator navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About us"));
        navigationLink.click();
        assertThat(page.locator("#videoModalLabel")).hasText("About us");

    }
    @Test
    void navigateToCart() {
        Locator navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart"));
        navigationLink.click();
        assertEquals("https://www.demoblaze.com/cart.html", page.url());
    }
    @Test
    void navigateToLogin() {
        Locator navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in"));
        navigationLink.click();
        assertThat(page.locator("#logInModalLabel")).hasText("Log in");
    }
    @Test
    void navigateToSignUp() {
        Locator navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign up"));
        navigationLink.click();
        assertThat(page.locator("#signInModalLabel")).hasText("Sign up");
    }

}


