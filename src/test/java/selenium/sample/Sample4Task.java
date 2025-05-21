package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://acctabootcamp.github.io/site/examples/actions";

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

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number"
//        check that button is not clickable "Clear Result"
//        check that text is not displayed
//        click on "Result" button
//        check that text is displayed
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//        check that the button "Clear Result" is clickable now
//        click on "Clear Result"
//        check that the text is now (""), but it is not displayed
        WebElement numInput = driver.findElement(By.id("number"));
        WebElement clearResult = driver.findElement(By.id("clear_result"));
        WebElement resultNum = driver.findElement(By.id("result_number"));
        WebElement resultButton = driver.findElement(By.id("result_button"));

        String testNum = "1";
        String expectedNum = testNum;
        System.out.println("You've entered " + expectedNum);

        numInput.clear();
        numInput.sendKeys(testNum);

        assertFalse(clearResult.isEnabled());
        assertFalse(resultNum.isDisplayed());

        resultButton.click();

        assertTrue(resultNum.isDisplayed());
        assertEquals(expectedNum, resultNum.getText());
        assertTrue(clearResult.isEnabled());

        clearResult.click();

        assertEquals("", resultNum.getText());
        assertFalse(resultNum.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        assertEquals(base_url, driver.getCurrentUrl());
        WebElement homeLink = driver.findElement(By.linkText("This is a link to Homepage"));
        homeLink.click();

        assertNotEquals(base_url, driver.getCurrentUrl());

        assertEquals(driver.getCurrentUrl(), "https://acctabootcamp.github.io/site/");
    }
}