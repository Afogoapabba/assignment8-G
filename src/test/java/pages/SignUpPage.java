package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SignUpPage {
    public Locator getTbUsername() {
        return tbUsername;
    }

    public Locator getTbPassword() {
        return tbPassword;
    }

    public Locator getSignUpButton() {
        return signUpButton;
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
    Locator signUpButton;
    Locator closeButton;
    Locator navigationLink;
    public SignUpPage(Page page){
        this.tbUsername = page.locator("#sign-username");
        this.tbPassword = page.locator("#sign-password");
        this.signUpButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign up"));
        this.closeButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
        this.navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign up"));
    }
    public void fillForm(String username,String password){
        tbUsername.fill(username);
        tbPassword.fill(password);
    }
    public void signUp(){
        signUpButton.click();
    }
    public void openSignUpPage(){navigationLink.click();}
}
