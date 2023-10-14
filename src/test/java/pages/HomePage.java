package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
public class HomePage {
    public Locator getProducts() {
        return products;
    }

    public Locator getCategoryList() {
        return categoryList;
    }

    public Locator getNavBarLink() {
        return navBarLink;
    }

    public Locator getProductList() {
        return productList;
    }

    public Locator getPhoneLink() {
        return phoneLink;
    }

    public Locator getLaptopLink() {
        return laptopLink;
    }

    public Locator getMonitorLink() {
        return monitorLink;
    }
    public Locator getExampleProduct1() {
        return exampleProduct1;
    }

    public Locator getExampleProduct2() {
        return exampleProduct2;
    }
    Locator products;
    Locator categoryList;
    Locator navBarLink;
    Locator productList;
    Locator phoneLink;
    Locator laptopLink;
    Locator monitorLink;


    //A few example product so I don't have to iterate products
    Locator exampleProduct1;
    Locator exampleProduct2;





    public HomePage(Page page){
        this.navBarLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
        this.categoryList = page.locator("#cat");
        this.productList = page.locator("#tbodyid");
        this.phoneLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Phones"));
        this.laptopLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Laptops"));
        this.monitorLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Monitors"));
        this.products = page.locator("//*[@id=\"tbodyid\"]/div");
        this.exampleProduct1 = page.locator("//*[@id=\"tbodyid\"]/div[1]");
        this.exampleProduct2 = page.locator("//*[@id=\"tbodyid\"]/div[2]");
    }

    public void openHomePage(){
        navBarLink.click();
    }

    public int countProducts(){
        return products.count();
    }








}
