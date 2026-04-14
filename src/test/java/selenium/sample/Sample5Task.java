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
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        driver.findElement(By.className("w3-blue")).click();

        Alert firstAlert = driver.switchTo().alert();
        firstAlert.accept();
        Alert secondAlert = driver.switchTo().alert();

        assertEquals("Booooooooo!", secondAlert.getText());
        secondAlert.accept();
        assertEquals("This page is alerted", driver.findElement(By.id("heading")).getText());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        driver.findElement(By.className("w3-blue")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        assertEquals("So you chose to stay here!", driver.findElement(By.id("textForAlerts")).getText());
    }
}