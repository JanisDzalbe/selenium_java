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
        driver.get("https://janisdzalbe.github.io/example-site/examples/alerts_popups");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        WebElement blueButton = driver.findElement(By.className("w3-blue"));
        blueButton.click();
        Alert firstAlert = driver.switchTo().alert();
        firstAlert.accept();
        Alert secondAlert = driver.switchTo().alert();
        assertEquals("Booooooooo!", secondAlert.getText());
        secondAlert.accept();
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerted_page", driver.getCurrentUrl());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        WebElement blueButton = driver.findElement(By.className("w3-blue"));
        blueButton.click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        WebElement textElement = driver.findElement(By.id("textForAlerts"));
        assertEquals("So you desided to say? Good!", textElement.getText());
    }
}
