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
    String baseUrl = "https://janisdzalbe.github.io/example-site/examples/alerts_popups";
    String alertedPageUrl = "https://janisdzalbe.github.io/example-site/examples/alerted_page";

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get(baseUrl);
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        // verify starting page
        assertEquals(baseUrl, driver.getCurrentUrl());

        // click button "To go to alerted page press Ok. Or stay here"
        WebElement goToAlertedPageButton =
                driver.findElement(By.xpath("//button[contains(@onclick,'goToAlertedPage')]"));
        goToAlertedPageButton.click();

        // first alert
        Alert firstAlert = driver.switchTo().alert();
        assertEquals("Want to see an alerted page?!", firstAlert.getText());
        firstAlert.accept();

        // second alert
        Alert secondAlert = driver.switchTo().alert();
        assertEquals("Booooooooo!", secondAlert.getText());
        secondAlert.accept();

        // verify alerted page
        assertEquals("This page is alerted", driver.findElement(By.id("heading")).getText());
        assertEquals(alertedPageUrl, driver.getCurrentUrl());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        // still on alerts_popups page
        assertEquals(baseUrl, driver.getCurrentUrl());

        // click "To go to alerted page press Ok. Or stay here" button
        WebElement goToAlertedPageButton =
                driver.findElement(By.xpath("//button[contains(@onclick,'goToAlertedPage')]"));
        goToAlertedPageButton.click();

        // alert: ask if we want alerted page
        Alert alert = driver.switchTo().alert();
        assertEquals("Want to see an alerted page?!", alert.getText());

        // stay on the same page
        alert.dismiss();

        // URL must remain alerts_popups
        assertEquals(baseUrl, driver.getCurrentUrl());

        // check message text (без привязки к скрытым пробелам/опечаткам)
        String message = driver.findElement(By.id("textForAlerts")).getText().trim();
        assertTrue(message.startsWith("So you"));
        assertTrue(message.endsWith("Good!"));
    }
}