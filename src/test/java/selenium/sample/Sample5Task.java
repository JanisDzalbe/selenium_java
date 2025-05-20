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
//        switch to alert
//        click ok
//        switch to second alert
//        verify alert text
//        click ok on second alert
//        verify that the correct page is opened
        WebElement blueBtn = driver.findElement(By.className("w3-blue"));
        blueBtn.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        Thread.sleep(1000);
        assertEquals("Booooooooo!", alert.getText(), "Alert text should be same");
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        assertEquals("https://acctabootcamp.github.io/site/examples/alerted_page", driver.getCurrentUrl());
        Thread.sleep(1000);

    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
//        switch to alert
//        click cancel
//        verify the text on page
        WebElement blueBtn = driver.findElement(By.className("w3-blue"));
        blueBtn.click();
        Thread.sleep(1000);
        driver.switchTo().alert().dismiss();
        Thread.sleep(1000);
        WebElement txt = driver.findElement(By.id("textForAlerts"));
        assertEquals("So you desided to say? Good!", txt.getText());
        Thread.sleep(1000);
    }
}
