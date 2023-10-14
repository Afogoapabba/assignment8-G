import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
public class ProductPage {
    Locator addToCartButton;
    Locator productTitle;

    Locator productPrice;

    public  ProductPage(Page page){
        this.addToCartButton = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Add to Cart"));
    }

}
