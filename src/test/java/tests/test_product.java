package tests;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductPage;
import testbase.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class test_product extends TestBase {
    /**
     * This test will start at the homepage and click
     * a product and verify that It's added to cart.
     */
    @Test
    void addProductToCart(){
        // Create  page
        HomePage homePage = new HomePage(page);

        // Create an onDialog handler to validate the dialog before accepting
        page.onDialog(dialog -> {
            assertEquals("alert", dialog.type());
            assertTrue(dialog.message().contains("Product added"));
            dialog.accept();
        });
        // Click on a Product
        homePage.getExampleProduct1().click();
        // New Product Page
        ProductPage productPage = new ProductPage(page);
        productPage.getAddToCartButton().click();



    }
}
