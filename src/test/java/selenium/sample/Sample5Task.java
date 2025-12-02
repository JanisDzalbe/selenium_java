package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;


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

        driver.findElement(By.xpath("//button[contains(text(), 'To go to alerted page')]")).click();

        // First alert
        Alert alert1 = driver.switchTo().alert();
        alert1.accept();

        // Second alert - EXPECTED TEXT IS "Booooooooo!"
        Alert alert2 = driver.switchTo().alert();
        String text = alert2.getText();
        Assertions.assertEquals("Booooooooo!", text);
        alert2.accept();

        // Verify redirected page
        Assertions.assertTrue(driver.getCurrentUrl().contains("alerted_page"));
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {

        // Click the button
        driver.findElement(By.xpath("//button[contains(text(), 'To go to alerted page')]")).click();

        // Cancel the alert
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        // Verify text on page
        String result = driver.findElement(By.id("textForAlerts")).getText();
        Assertions.assertEquals("So you desided to say? Good!", result);
    }
}
