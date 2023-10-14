import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;


public class ContactPage {

    //Create Locators
    Locator contactEmailTB;
    Locator contactNameTB;
    Locator contactMessageBox;
    Locator sendMsgButton;
    Locator closeButton;
    Locator navigationLink;

    public ContactPage(Page page) {
        this.contactEmailTB = page.locator("#recipient-email");
        this.contactNameTB = page.locator("#recipient-name");
        this.contactMessageBox = page.locator("#message-text");
        this.sendMsgButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Send message"));
        this.closeButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
        this.navigationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact"));

    }
    public void openContactPage() {
        navigationLink.click();
    }
    public void fillForm(String email,String name,String message){
        contactEmailTB.fill(email);
        contactNameTB.fill(name);
        contactMessageBox.fill(message);
    }
    public void sendForm(){
        sendMsgButton.click();
    }

}
