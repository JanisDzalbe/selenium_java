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
//         TODO:
//          click on "To go to alerted page press Ok. Or stay here" button
//          switch to alert
//          click ok
//          switch to second alert
//          verify alert text
//          click ok on second alert
//          verify that the correct page is opened
        WebElement blueButton = driver.findElement(By.className("w3-blue"));
        blueButton.click();

        driver.switchTo().alert().accept();

        Alert secondAlert = driver.switchTo().alert();
        assertEquals("Booooooooo!", secondAlert.getText());
        secondAlert.accept();

        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerted_page", driver.getCurrentUrl());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {

        driver.findElement(By.className("w3-blue")).click();
        driver.switchTo().alert().dismiss();
        assertEquals("https://janisdzalbe.github.io/example-site/examples/alerts_popups", driver.getCurrentUrl());

//         TODO:
//          click on "To go to alerted page press Ok. Or stay here" button
//          switch to alert
//          click cancel
//          verify the text on page
    }
}
