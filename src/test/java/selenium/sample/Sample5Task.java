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

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/alerts_popups");
    }

    @AfterEach
    public void endingTests() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        //clicks button
        driver.findElement(By.xpath("//button[contains(text(),'To go to alerted page press Ok. Or stay here')]")).click();

        //switch to first alert
        Alert alert = driver.switchTo().alert();

        //clicks ok
        alert.accept();

        //switch to second alert
        Alert secondAlert = driver.switchTo().alert();

        //verify alert text
        assertEquals("Booooooooo!", secondAlert.getText());

        //clicks  ok on second alert
        secondAlert.accept();

        //verify that correct page is opened
        assertEquals("This page is alerted", driver.findElement(By.id("heading")).getText());
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerted_page", driver.getCurrentUrl());    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        //clicks on button
        driver.findElement(By.xpath("//button[contains(text(),'To go to alerted page press Ok. Or stay here')]")).click();
        //switch to alert
        Alert alert = driver.switchTo().alert();

        //clicks cancel
        alert.dismiss();

        //verify text on page
        assertEquals("So you desided to say? Good!", driver.findElement(By.id("textForAlerts")).getText());
    }
}
