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

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeDriver();

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
//         TODO:
//          click on "To go to alerted page press Ok. Or stay here" button
//          switch to alert
//          click ok
//          switch to second alert
//          verify alert text
//          click ok on second alert
//          verify that the correct page is opened

        WebElement button = driver.findElement(By.cssSelector("button.w3-btn.w3-blue"));
        button.click();
        Alert alert1 = driver.switchTo().alert();
        alert1.accept();
        Alert alert2 = driver.switchTo().alert();
        String expectedText = "Booooooooo!";
        assertEquals(expectedText, alert2.getText());
        alert2.accept();
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerted_page", actualUrl);
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//          click on "To go to alerted page press Ok. Or stay here" button
//          switch to alert
//          click cancel
//          verify the text on page
        WebElement button = driver.findElement(By.cssSelector("button.w3-btn.w3-blue"));
        button.click();

        Alert alert1 = driver.switchTo().alert();


        alert1.dismiss();

        WebElement heading = driver.findElement(By.cssSelector("ul.w3-navbar"));

    }
}
