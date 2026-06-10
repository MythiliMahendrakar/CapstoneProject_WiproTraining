package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage {

    WebDriver driver;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    By name = By.id("inputName");
    By address = By.id("address");
    By city = By.id("city");
    By state = By.id("state");
    By zip = By.id("zipCode");
    By card = By.id("creditCardNumber");
    By month = By.id("creditCardMonth");
    By year = By.id("creditCardYear");
    By nameOnCard = By.id("nameOnCard");
    By purchaseBtn = By.cssSelector("input[type='submit']");

    public void enterName(String val) { driver.findElement(name).sendKeys(val); }
    public void enterAddress(String val) { driver.findElement(address).sendKeys(val); }
    public void enterCity(String val) { driver.findElement(city).sendKeys(val); }
    public void enterState(String val) { driver.findElement(state).sendKeys(val); }
    public void enterZip(String val) { driver.findElement(zip).sendKeys(val); }
    public void enterCard(String val) { driver.findElement(card).sendKeys(val); }
    public void enterMonth(String val) { driver.findElement(month).sendKeys(val); }
    public void enterYear(String val) { driver.findElement(year).sendKeys(val); }
    public void enterNameOnCard(String val) { driver.findElement(nameOnCard).sendKeys(val); }

    public void clickPurchase() {
        driver.findElement(purchaseBtn).click();
        System.out.println("Purchase clicked successfully");
    }
}
