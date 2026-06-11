package com.blazedemo.testcases;

import com.blazedemo.base.BaseTest;
import com.blazedemo.pages.*;
import com.blazedemo.utilities.CSVDataReader;
import com.blazedemo.utilities.ScreenshotUtil;
import com.blazedemo.utilities.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

        // Create Extent test instance for each row in CSV
        ExtentTest test = ExtentManager.getInstance().createTest("Flight Booking Test - " + tcid);

        System.out.println("Executing: " + tcid);

        HomePage home = new HomePage(driver);
        FlightResultsPage results = new FlightResultsPage(driver);
        PurchasePage purchase = new PurchasePage(driver);
        ConfirmationPage confirm = new ConfirmationPage(driver);

        // Step 1: Select cities
        home.selectDeparture(from);
        home.selectDestination(to);

        // ✅ Capture screenshot BEFORE clicking Find Flights
        String path1 = ScreenshotUtil.captureScreenshot(driver, "Cities_Selected_" + tcid);
        test.info("Cities selected: " + from + " → " + to).addScreenCaptureFromPath(path1);

        // Now click Find Flights
        home.clickFindFlights();

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

        String path2 = ScreenshotUtil.captureScreenshot(driver, "Form_Filled_" + tcid);
        test.info("Passenger form filled").addScreenCaptureFromPath(path2);

        // Step 3: Confirm booking
        purchase.clickPurchase();
        String message = confirm.getConfirmationText();
        System.out.println("Confirmation Message: " + message);

        String path3 = ScreenshotUtil.captureScreenshot(driver, "Confirmation_Page_" + tcid);
        test.pass("Booking confirmed: " + message).addScreenCaptureFromPath(path3);

        // Assert that the message contains the expected text
        Assert.assertTrue(confirm.isSuccess(), "Purchase confirmation not found!");
    }
}
