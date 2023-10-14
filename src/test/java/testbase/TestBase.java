package testbase;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {
    final public String staticUser = "Admin4ff86e74872b";

    final public String staticPassword = "Password";

    public String getStaticUser() {
        return staticUser;
    }

    public String getStaticPassword() {
        return staticPassword;
    }


    // Shared between all tests in this class and subclasses.
    protected static Playwright playwright;
    protected static Browser browser;

    // New instance for each test method.
    protected BrowserContext context;
    protected Page page;


    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://www.demoblaze.com/");
    }

    @AfterEach
    void closeContext() {
        context.close();
    }


}
