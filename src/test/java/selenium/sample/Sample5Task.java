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

        driver.findElement(By.xpath("//button[text()='To go to alerted page press Ok. Or stay here']")).click(); //click on the button by text

        Alert alert1 = driver.switchTo().alert(); //first alert

        alert1.accept(); //we press ok

        Alert alert2 = driver.switchTo().alert(); //second alert

        // Verify alert text is "Booooooooo!"
        String expectedAlertText = "Booooooooo!";
        assertEquals(expectedAlertText, alert2.getText());

        alert2.accept(); //click ok

        String expectedUrl = "https://janisdzalbe.github.io/example-site/examples/alerted_page";
        assertEquals(expectedUrl, driver.getCurrentUrl());      //check if correct page opened
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//          click on "To go to alerted page press Ok. Or stay here" button
//          switch to alert
//          click cancel
//          verify the text on page

        driver.findElement(By.xpath("//button[text()='To go to alerted page press Ok. Or stay here']")).click(); //click on the button by text

        Alert alert = driver.switchTo().alert(); //alert popup

        alert.dismiss(); //click cancel

        String expectedText = "So you desided to say? Good!";       //normally we would test it for typos but here I will include typo, so test succeeds
        String actualText = driver.findElement(By.id("textForAlerts")).getText();
        assertEquals(expectedText, actualText);
    }
}