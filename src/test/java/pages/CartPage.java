import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage {
    Locator priceTotal;
    Locator orderButton;

    public  CartPage(Page page){
        this.priceTotal = page.locator("#totalp");
        this.orderButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Place Order"));
    }



}
