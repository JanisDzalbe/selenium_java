package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample5Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://acctabootcamp.github.io/site/examples/alerts_popups");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();
//        switch to alert
//        click ok
        driver.switchTo().alert().accept();
//        switch to second alert
//        verify alert text
        Alert alert = driver.switchTo().alert();
        assertEquals("Booooooooo!", alert.getText());
//        click ok on second alert
        alert.accept();
//        verify that the correct page is opened
        assertEquals("https://acctabootcamp.github.io/site/examples/alerted_page",driver.getCurrentUrl());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue") ).click();
//        switch to alert
        driver.switchTo().alert().dismiss();
//        click cancel
//        verify the text on page
        assertEquals("So you desided to say? Good!", driver.findElement(By.id("textForAlerts")).getText());
    }
}
