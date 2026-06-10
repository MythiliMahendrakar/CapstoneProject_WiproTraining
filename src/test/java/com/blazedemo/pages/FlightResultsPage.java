package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightResultsPage {

    WebDriver driver;

    public FlightResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    By chooseFlightBtn = By.cssSelector("input[type='submit']");

    public void selectFirstFlight() {
        driver.findElement(chooseFlightBtn).click();
        System.out.println("First flight selected");
    }
}
