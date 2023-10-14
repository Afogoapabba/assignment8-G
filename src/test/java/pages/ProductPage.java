package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
public class ProductPage {
    public Locator getAddToCartButton() {
        return addToCartButton;
    }

    Locator addToCartButton;

    public Locator getProductTitle() {
        return productTitle;
    }

    public Locator getProductPrice() {
        return productPrice;
    }

    Locator productTitle;

    Locator productPrice;

    public  ProductPage(Page page){
        this.addToCartButton = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Add to Cart"));
    }

}
