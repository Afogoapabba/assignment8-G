package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import testbase.TestBase;

import java.util.function.BooleanSupplier;

public class test_cart extends TestBase {
    /**
     * Test will combine multiple pages.
     * it will start at the homepage and add a product.
     * then return and add another
     * finally att cart page it will validate all input and
     * verify the sum of the products is correct.
     *
     * this test often timeout because demoblaze often runs really slow.
     */
    @Test
    void placeOrder() {
        Faker faker = new Faker();
        HomePage homePage = new HomePage(page);

        //Add a product
        homePage.getExampleProduct1().click();
        ProductPage productPage1 = new ProductPage(page);
        //without this wait headless will be too quick. and navigate from product page before its Hydrated
        page.waitForURL("**/prod**");
        productPage1.getAddToCartButton().click();

        //Go back
        page.navigate("https://www.demoblaze.com/index.html");

        // Add a second one
        homePage.getExampleProduct2().click();
        ProductPage productPage2 = new ProductPage(page);
        page.waitForURL("**/prod**");
        productPage2.getAddToCartButton().click();

        //Go to cart
        page.navigate("https://www.demoblaze.com/cart.html");
        CartPage cartPage = new CartPage(page);
        cartPage.getPlaceOrderButton().click();

        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            Assertions.assertEquals("alert", dialog.type());
            Assertions.assertTrue(dialog.message().contains("Thank you for your purchase!"));
            dialog.accept();
        });
        //Generate some mock-data
        String name = faker.name().firstName();
        String country = faker.country().name();
        String city = faker.country().capital();
        String card = faker.finance().creditCard();
        String month = String.valueOf(faker.random().nextInt(1, 12));
        String year = String.valueOf(faker.number().numberBetween(2022, 2023));

        cartPage.getOrderName().fill(name);
        cartPage.getOrderCountry().fill(country);
        cartPage.getOrderCity().fill(city);
        cartPage.getOrderCreditCard().fill(card);
        cartPage.getOrderMonth().fill(month);
        cartPage.getOrderYear().fill(year);

        String test = cartPage.getOrderName().inputValue();
        Assertions.assertTrue(cartPage.getOrderName().inputValue().contains(name));
        Assertions.assertTrue(cartPage.getOrderCountry().inputValue().contains(country));
        Assertions.assertTrue(cartPage.getOrderCity().inputValue().contains(city));
        Assertions.assertTrue(cartPage.getOrderCreditCard().inputValue().contains(card));
        Assertions.assertTrue(cartPage.getOrderMonth().inputValue().contains(month));
        Assertions.assertTrue(cartPage.getOrderYear().inputValue().contains(year));

        float price1 = Float.parseFloat(page.locator("//*[@id=\"tbodyid\"]/tr[1]/td[3]").innerText());
        float price2 = Float.parseFloat(page.locator("//*[@id=\"tbodyid\"]/tr[2]/td[3]").innerText());
        float priceTot = Float.parseFloat(cartPage.getPriceTotal().innerText());
        // another speed-bump for PW so below assertion has time to finish.
        BooleanSupplier condition = () -> {
            String text = cartPage.getPriceTotal().innerText();
            return text != null && !text.isEmpty();
        };

        page.waitForCondition(condition);
        // Assert sum of product price corresponds to total
        Assertions.assertEquals((price1+price2),priceTot);

        cartPage.getOrderPurchaseButton().click();


    }

    @Test
    void emptyOrder() {
        Faker faker = new Faker();
        CartPage cartPage = new CartPage(page);
        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            Assertions.assertEquals("alert", dialog.type());
            Assertions.assertFalse(dialog.message().contains("Thank you for your purchase!"));
            dialog.accept();
        });
        page.navigate(cartPage.cartURL);
        cartPage.getPlaceOrderButton().click();
        cartPage.getOrderPurchaseButton().click();

    }


}
