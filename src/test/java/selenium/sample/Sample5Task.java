package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

public class Sample5Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/alerts_popups";

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get(base_url);
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        // click on "To go to alerted page press Ok. Or stay here" button
        WebElement alertButton = driver.findElement(
                By.xpath("//button[text()='To go to alerted page press Ok. Or stay here']")
        );
        alertButton.click();

        // switch to first alert and click OK (accept)
        Alert firstAlert = driver.switchTo().alert();
        System.out.println("First alert text: " + firstAlert.getText());
        firstAlert.accept();

        // switch to second alert
        Alert secondAlert = driver.switchTo().alert();

        // verify alert text
        String alertText = secondAlert.getText();
        System.out.println("Second alert text: " + alertText);
        assertEquals("Booooooooo!", alertText);

        // click OK on second alert
        secondAlert.accept();

        // verify that the correct page is opened
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        assertNotEquals(base_url, currentUrl);
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        // click on "To go to alerted page press Ok. Or stay here" button
        WebElement alertButton = driver.findElement(
                By.xpath("//button[text()='To go to alerted page press Ok. Or stay here']")
        );
        alertButton.click();

        // switch to alert and click Cancel (dismiss)
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        // verify we stayed on the same page
        assertEquals(base_url, driver.getCurrentUrl());

        // verify the text on page
        WebElement textForAlerts = driver.findElement(By.id("textForAlerts"));
        String text = textForAlerts.getText();
        System.out.println("textForAlerts text: " + text);
        assertEquals("So you desided to say? Good!", text);
    }
}