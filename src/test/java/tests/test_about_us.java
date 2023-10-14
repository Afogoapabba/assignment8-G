package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import testbase.TestBase;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class test_about_us extends TestBase {
    /**
     * Test will open the about us modal and verify its title
     * and check if the play button is visible
     */
    @Test
    void checkTitle(){

        //Go to page
        Locator navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About us"));
        navigationLink.click();
        // Create Locators locally since about us is just a modal
        Locator aboutUsHeading = page.locator("#videoModalLabel");
        Locator aboutsUsPlayButton = page.locator("\"button.vjs-big-play-button[title='Play Video']\"");
        Locator closeButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));

        // Assertion
        assertThat(aboutUsHeading).hasText("About us");

        /*
        this will fail since the video fails to load.
        I could not make out how to get the video to run so i stopped here.
        my idea was to test the video in more detail.
        * */
        assertThat(aboutsUsPlayButton).isVisible();

    }
}
