
package com.blazedemo.testcases;

import com.blazedemo.base.BaseTest;
import com.blazedemo.pages.*;
import com.blazedemo.utilities.CSVDataReader;
import com.blazedemo.utilities.ScreenshotUtil; // ✅ added import
import org.testng.Assert;
import org.testng.annotations.*;

public class FlightBookingTest extends BaseTest {

    @DataProvider(name = "csvData")
    public Object[][] getData() {
        return CSVDataReader.getData("flightData.csv");
    }

    @Test(dataProvider = "csvData")
    public void TC_FlightBooking(String tcid, String from, String to,
                                 String name, String address, String city,
                                 String state, String zip, String card,
                                 String month, String year) {

        System.out.println("Executing: " + tcid);

        HomePage home = new HomePage(driver);
        FlightResultsPage results = new FlightResultsPage(driver);
        PurchasePage purchase = new PurchasePage(driver);
        ConfirmationPage confirm = new ConfirmationPage(driver);

        // Step 1: Select cities
        home.selectDeparture(from);
        home.selectDestination(to);
        home.clickFindFlights();
        ScreenshotUtil.captureScreenshot(driver, "Cities_Selected_" + tcid);

        // Step 2: Choose flight and fill form
        results.selectFirstFlight();
        purchase.enterName(name);
        purchase.enterAddress(address);
        purchase.enterCity(city);
        purchase.enterState(state);
        purchase.enterZip(zip);
        purchase.enterCard(card);
        purchase.enterMonth(month);
        purchase.enterYear(year);
        purchase.enterNameOnCard(name);
        ScreenshotUtil.captureScreenshot(driver, "Form_Filled_" + tcid);

        // Step 3: Confirm booking
        purchase.clickPurchase();

        // Print the actual confirmation message
        String message = confirm.getConfirmationText();
        System.out.println("Confirmation Message: " + message);
        ScreenshotUtil.captureScreenshot(driver, "Confirmation_Page_" + tcid);

        // Assert that the message contains the expected text
        Assert.assertTrue(confirm.isSuccess(), "Purchase confirmation not found!");
    }
}







