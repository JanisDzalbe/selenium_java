package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;
import static org.junit.jupiter.api.Assertions.*;


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
        driver.findElement(By.className("w3-blue")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        alert = driver.switchTo().alert();
        assertEquals("Booooooooo!", alert.getText());
        alert.accept();

        assertEquals(driver.getCurrentUrl(), "https://acctabootcamp.github.io/site/examples/alerted_page");
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        driver.findElement(By.className("w3-blue")).click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        assertEquals("So you desided to say? Good!", driver.findElement(By.id("textForAlerts")).getText());
    }
}
