import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class test_home extends TestBase {
    @Test
    void verifyProductListCount(){
        // Create and open page
        HomePage homePage = new HomePage(page);
        homePage.openHomePage();

        //check laptop count
        homePage.laptopLink.click();
        assertEquals(homePage.countProducts(),6);

        //check Phone count
        homePage.phoneLink.click();
        assertEquals(homePage.countProducts(),7);

        //check Monitor count
        homePage.monitorLink.click();
        assertEquals(homePage.countProducts(),2);


    }
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
        page.locator("//*[@id=\"tbodyid\"]/div[1]").click();
        // New Product Page
        ProductPage productPage = new ProductPage(page);
        productPage.addToCartButton.click();



    }

}
