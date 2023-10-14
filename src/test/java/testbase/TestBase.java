
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import java.util.Random;



public class TestBase {
    final String staticUser = "Admin4ff86e74872b";

    final String staticPassword = "Password";

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

    //Random string generator
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }


    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
//        browser = playwright.chromium().launch();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
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
