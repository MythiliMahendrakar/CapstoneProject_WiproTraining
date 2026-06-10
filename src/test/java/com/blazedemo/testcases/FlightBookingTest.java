
package com.blazedemo.testcases;

import com.blazedemo.base.BaseTest;
import com.blazedemo.pages.*;
import com.blazedemo.utilities.CSVDataReader;
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

        home.selectDeparture(from);
        home.selectDestination(to);
        home.clickFindFlights();

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

        purchase.clickPurchase();

        // Print the actual confirmation message
        String message = confirm.getConfirmationText();
        System.out.println("Confirmation Message: " + message);

        // Assert that the message contains the expected text
        Assert.assertTrue(confirm.isSuccess(), "Purchase confirmation not found!");
    }
}






