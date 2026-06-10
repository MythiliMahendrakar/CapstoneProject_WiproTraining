/*package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSuccess() {
        return driver.getPageSource().contains("Thank you");
    }
}
*/

package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    private WebDriver driver;

    // Locator for the confirmation message
    private By confirmationMessage = By.xpath("//h1[contains(text(),'Thank you')]");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Return the actual text of the confirmation message
    public String getConfirmationText() {
        try {
            return driver.findElement(confirmationMessage).getText();
        } catch (Exception e) {
            return "Confirmation message not found!";
        }
    }

    // Boolean check for success
    public boolean isSuccess() {
        return getConfirmationText().contains("Thank you for your purchase today!");
    }
}
