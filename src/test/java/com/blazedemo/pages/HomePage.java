package com.blazedemo.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By fromPort = By.name("fromPort");   // Departure dropdown
    By toPort = By.name("toPort");       // Destination dropdown
    By findFlightsBtn = By.xpath("//input[@value='Find Flights']");

    // Select departure city (force re-selection if default)
    public void selectDeparture(String from) {
        wait.until(ExpectedConditions.elementToBeClickable(fromPort));
        WebElement dropdown = driver.findElement(fromPort);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

        Select select = new Select(dropdown);

        // If already selected, change temporarily then re-select
        if (select.getFirstSelectedOption().getText().equals(from.trim())) {
            select.selectByIndex(1);
        }
        select.selectByVisibleText(from.trim());
        System.out.println("Selected FROM: " + from);
    }

    // Select destination city (force re-selection if default)
    public void selectDestination(String to) {
        wait.until(ExpectedConditions.elementToBeClickable(toPort));
        WebElement dropdown = driver.findElement(toPort);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);

        Select select = new Select(dropdown);

        if (select.getFirstSelectedOption().getText().equals(to.trim())) {
            select.selectByIndex(1);
        }
        select.selectByVisibleText(to.trim());
        System.out.println("Selected TO: " + to);
    }

    // Click Find Flights
    public void clickFindFlights() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(findFlightsBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        System.out.println("Find Flights clicked successfully");
    }
}
