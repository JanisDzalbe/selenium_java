package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";

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
//          enter a number under "Number"
//          check that button is not clickable "Clear Result"
//          check that text is not displayed
//          click on "Result" button
//          check that text is displayed
//          check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//          check that the button "Clear Result" is clickable now
//          click on "Clear Result"
//          check that the text is now (""), but it is not displayed
        WebElement numberInput = driver.findElement(
                By.xpath("//h2[normalize-space()='Number']/following::input[1]")
        );

        WebElement resultButton = driver.findElement(
                By.xpath("//h2[normalize-space()='Number']/following::button[normalize-space()='Result'][1]")
        );

        WebElement clearButton = driver.findElement(
                By.xpath("//h2[normalize-space()='Number']/following::button[normalize-space()='Clear Result'][1]")
        );

        // Result text (Number bölməsində çıxan mətni götürürük)
        WebElement resultText = driver.findElement(
                By.xpath("//h2[normalize-space()='Number']/following::p[1]")
        );

        String numberToEnter = "123";

        numberInput.clear();
        numberInput.sendKeys(numberToEnter);

        // check that button is not clickable "Clear Result"
        assertFalse(clearButton.isEnabled());

        // check that text is not displayed
        assertFalse(resultText.isDisplayed());

        // click on "Result" button
        resultButton.click();

        // check that text is displayed
        assertTrue(resultText.isDisplayed());

        // check that the correct Text appears ("You entered number: \"NUMBER YOU ENTERED\"")
        String expectedText = "You entered number: " + numberToEnter;
        assertEquals(expectedText, resultText.getText());

        // check that the button "Clear Result" is clickable now
        assertTrue(clearButton.isEnabled());

        // click on "Clear Result"
        clearButton.click();

        // check that the text is now (""), but it is not displayed
        assertFalse(resultText.isDisplayed());
        assertEquals("", resultText.getText());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//          check current url is base_url
//          click on "This is a link to Homepage"
//          check that current url is not base_url
//          verify that current url is homepage
        // check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());

        // click on "This is a link to Homepage"
        driver.findElement(By.linkText("This is a link to Homepage")).click();

        // check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));

        // verify that current url is homepage
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}
