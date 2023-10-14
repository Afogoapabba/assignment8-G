package tests;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import testbase.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_home extends TestBase {
    /**
     * Test will cycle through the product categories on the homepage
     * and verify that the listed count matches the database (mocked)
     */
    @Test
    void verifyProductListCount(){
        // Create and open page
        HomePage homePage = new HomePage(page);
        homePage.openHomePage();
/*      These hardcoded values could have been fetched from a database
        in a real scenario to verify that product table in db matched gui*/
        int expectedLaptopCount = 6;
        int expectedPhoneCount = 7 ;
        int expectedMonitorCount = 2;

        //check laptop count
        homePage.getLaptopLink().click();

        /*this wait is needed else headless mode is too fast.
        this makes the following assert seem a little redundant, but it
        still reports an assert status.
        */
        page.waitForCondition(() -> homePage.getProducts().count() == expectedLaptopCount);
        assertEquals(homePage.getProducts().count(),expectedLaptopCount);

        //check Phone count
        homePage.getPhoneLink().click();
        page.waitForCondition(() -> homePage.getProducts().count() == expectedPhoneCount);
        assertEquals(homePage.countProducts(),expectedPhoneCount);

        //check Monitor count
        homePage.getMonitorLink().click();
        page.waitForCondition(() -> homePage.getProducts().count() == expectedMonitorCount);
        assertEquals(homePage.countProducts(),expectedMonitorCount);


    }


}
