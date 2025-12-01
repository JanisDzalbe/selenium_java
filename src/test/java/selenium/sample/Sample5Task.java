package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample5Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/alerts_popups");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        //TODO:
        //click on "To go to alerted page press Ok. Or stay here" button
        WebElement button = driver.findElement(By.cssSelector(".w3-blue"));
        button.click();
        //switch to alert
        Alert firstAlert = driver.switchTo().alert();
        //click ok
        firstAlert.accept();
        //switch to second alert
        Alert secondAlert = driver.switchTo().alert();
        //verify alert text
        assertEquals( "Booooooooo!", secondAlert.getText());
        //click ok on second alert
        secondAlert.accept();
        //verify that the correct page is opened
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerted_page", driver.getCurrentUrl());
        //Verify this text is displayed "This page is alerted" to make sure that the correct page is opened
        WebElement textHeader = driver.findElement(By.id("heading"));
        assertTrue(textHeader.isDisplayed());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        //TODO:
        //click on "To go to alerted page press Ok. Or stay here" button
        WebElement button = driver.findElement(By.cssSelector(".w3-blue"));
        button.click();
        //switch to alert
        Alert alert = driver.switchTo().alert();
        //click cancel
        alert.dismiss();
        //verify the text on page
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerts_popups", driver.getCurrentUrl());
        assertTrue(button.isDisplayed());
    }
}
