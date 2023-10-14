import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
public class HomePage {

    Locator products;
    Locator categoryList;
    //Create Locators
    Locator navBarLink;
    Locator productList;

    //Categories links
    Locator phoneLink;
    Locator laptopLink;
    Locator monitorLink;

    Locator product;


    public HomePage(Page page){
        this.navBarLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
        this.categoryList = page.locator("#cat");
        this.productList = page.locator("#tbodyid");
        this.phoneLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Phones"));
        this.laptopLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Laptops"));
        this.monitorLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Monitors"));
        this.products=page.locator("//*[@id=\"tbodyid\"]/div");


    }

    public void openHomePage(){
        navBarLink.click();

    }

    public int countProducts(){
        return products.count();

    }








}
