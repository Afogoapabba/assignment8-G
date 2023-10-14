package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage {

    //Most "pages" on this site isn't actually pages, but the ones who have urls
    // I thought of setting the Url as a property
    final public String cartURL = "https://www.demoblaze.com/cart.html";

    public Locator getPlaceOrderButton() {
        return placeOrderButton;
    }


    public Locator getOrderName() {
        return orderName;
    }

    public Locator getOrderCountry() {
        return orderCountry;
    }

    public Locator getOrderCity() {
        return orderCity;
    }

    public Locator getOrderCreditCard() {
        return orderCreditCard;
    }

    public Locator getOrderMonth() {
        return orderMonth;
    }

    public Locator getOrderYear() {
        return orderYear;
    }

    public Locator getOrderPurchaseButton() {
        return orderPurchaseButton;
    }

    public Locator getPriceTotal() {
        return priceTotal;
    }

    Locator priceTotal;
    Locator placeOrderButton;

    //Place order form locators
    Locator orderName;
    Locator orderCountry;
    Locator orderCity;
    Locator orderCreditCard;
    Locator orderMonth;
    Locator orderYear;
    Locator orderPurchaseButton;

    public CartPage(Page page) {
        this.priceTotal = page.locator("#totalp");
        this.placeOrderButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Place Order"));
        this.orderName = page.locator("#name");
        this.orderCountry = page.locator("#country");
        this.orderCity = page.locator("#city");
        this.orderCreditCard = page.locator("#card");
        this.orderMonth = page.locator("#month");
        this.orderYear = page.locator("#year");
        this.orderPurchaseButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Purchase"));

    }

}
