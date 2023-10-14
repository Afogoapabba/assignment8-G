package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
public class LogInPage {
    public Locator getTbUsername() {
        return tbUsername;
    }

    public Locator getTbPassword() {
        return tbPassword;
    }

    public Locator getLoginButton() {
        return loginButton;
    }

    public Locator getCloseButton() {
        return closeButton;
    }

    public Locator getNavigationLink() {
        return navigationLink;
    }

    //create locators
    Locator tbUsername;
    Locator tbPassword;
    Locator loginButton;
    Locator closeButton;
    Locator navigationLink;

    public LogInPage(Page page){
        this.tbUsername = page.locator("#loginusername");
        this.tbPassword = page.locator("#loginpassword");
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in"));
        this.closeButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
        this.navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in"));
    }

    public void openLogInPage(){navigationLink.click();}
    public void fillForm(String username, String password){
        tbUsername.fill(username);
        tbPassword.fill(password);
    }

}
