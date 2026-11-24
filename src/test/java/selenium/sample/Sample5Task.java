package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample5Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/alerts_popups";
    String secondAlertText = "Booooooooo!";
    String alertedPageUrl = "https://janisdzalbe.github.io/example-site/examples/alerted_page";
    String textAfterCancel = "So you desided to say? Good!";

    By alertedPageButton = By.xpath("//button[@onclick='goToAlertedPage()']");

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    private Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    @Test
    public void goToAlertedPageViaButton() throws Exception {
        // TODO Click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(alertedPageButton).click();

        //switch to alert
        Alert firstAlert = waitForAlert();

        //click ok
        firstAlert.accept();

        //switch to second alert
        Alert secondAlert = waitForAlert();

        //verify alert text
        assertEquals(secondAlertText, secondAlert.getText());

        //click ok on second alert
        secondAlert.accept();

        // verify that the correct page is opened
        assertEquals(alertedPageUrl, driver.getCurrentUrl());
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//          click on "To go to alerted page press Ok. Or stay here" button
        // Кликаем по кнопке перехода на страницу с алертом
        driver.findElement(alertedPageButton).click();

//          switch to alert
        Alert alert = waitForAlert();

//          click cancel
        alert.dismiss();

//          verify the text on page
        assertEquals(textAfterCancel, driver.findElement(By.id("textForAlerts")).getText());
    }
}