package com.blazedemo.testcases;

import com.blazedemo.base.BaseTest;
import com.blazedemo.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class GUIElementsTests extends BaseTest {

    @Test
    public void validateDepartureDropdown() {
        driver.get("https://blazedemo.com");
        HomePage home = new HomePage(driver);
        home.selectDeparture("Boston");
        Assert.assertEquals(home.getSelectedDeparture(), "Boston", "Departure dropdown not working correctly");
    }

    @Test
    public void validateDestinationDropdown() {
        driver.get("https://blazedemo.com");
        HomePage home = new HomePage(driver);
        home.selectDestination("London");
        Assert.assertEquals(home.getSelectedDestination(), "London", "Destination dropdown not working correctly");
    }

    @Test
    public void validateFindFlightsButton() {
        driver.get("https://blazedemo.com");
        HomePage home = new HomePage(driver);
        home.selectDeparture("Boston");
        home.selectDestination("London");
        home.clickFindFlights();
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("reserve"), "Find Flights button did not navigate correctly");
    }

    @Test
    public void validateChooseFlightButton() {
        driver.get("https://blazedemo.com");
        HomePage home = new HomePage(driver);
        home.selectDeparture("Boston");
        home.selectDestination("London");
        home.clickFindFlights();

        // ✅ Fix: Use first "Choose This Flight" button explicitly
        WebElement chooseBtn = driver.findElement(By.xpath("(//input[@value='Choose This Flight'])[1]"));
        chooseBtn.click();

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("purchase"), "Choose Flight button did not open purchase page");
    }

    @Test
    public void validateConfirmationText() {
        driver.get("https://blazedemo.com");
        HomePage home = new HomePage(driver);
        home.selectDeparture("Boston");
        home.selectDestination("London");
        home.clickFindFlights();

        // Click a flight to reach purchase page
        WebElement chooseBtn = driver.findElement(By.xpath("(//input[@value='Choose This Flight'])[1]"));
        chooseBtn.click();

        // Fill minimal purchase form (otherwise confirmation won’t appear)
        driver.findElement(By.id("inputName")).sendKeys("Test User");
        driver.findElement(By.id("address")).sendKeys("123 Street");
        driver.findElement(By.id("city")).sendKeys("Boston");
        driver.findElement(By.id("state")).sendKeys("MA");
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("creditCardNumber")).sendKeys("4111111111111111");
        driver.findElement(By.id("creditCardMonth")).sendKeys("12");
        driver.findElement(By.id("creditCardYear")).sendKeys("2026");
        driver.findElement(By.id("nameOnCard")).sendKeys("Test User");
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();

        // Fix: Wait for confirmation text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmation = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Thank you')]"))
        );

        Assert.assertTrue(confirmation.isDisplayed(), "Confirmation text missing");
    }
}
