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
        driver.get("https://janisdzalbe.github.io/example-site/examples/alerts_popups");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        // click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.id("textForAlerts")).click();

        // first alert
        Alert firstAlert = driver.switchTo().alert();
        // (you can assert text if needed)
        firstAlert.accept();  // click OK

        // second alert
        Alert secondAlert = driver.switchTo().alert();
        String secondText = secondAlert.getText();
        // verify alert text (expected text may differ slightly, adjust if needed)
        assertEquals("You will be redirected to alerted page", secondText);
        secondAlert.accept(); // click OK

        // verify that the correct page is opened
        assertEquals(
                "https://janisdzalbe.github.io/example-site/examples/alerted_page",
                driver.getCurrentUrl()
        );
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
        // click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.id("textForAlerts")).click();

        // first alert
        Alert alert = driver.switchTo().alert();
        alert.dismiss();  // click Cancel

        // verify we stayed on the same page
        assertEquals(
                "https://janisdzalbe.github.io/example-site/examples/alerts_popups",
                driver.getCurrentUrl()
        );

        // optionally check some text on the page (heading)
        String heading = driver.findElement(By.id("h1")).getText();
        assertEquals("Alert and pop-ups", heading);
    }
}
