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
        driver = BootcampUtils.initializeEdgeDriver();

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

        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));

        String number = "5";
        String expectedText = "You entered number: \"" + number + "\"";

        // initial state checks
        assertFalse(clearButton.isEnabled());
        assertFalse(resultText.isDisplayed());

        // enter number
        numberInput.clear();
        numberInput.sendKeys(number);

        // result still not shown yet
        assertFalse(resultText.isDisplayed());

        // click result
        resultButton.click();

        // verify text appears
        assertTrue(resultText.isDisplayed());
        assertEquals(expectedText, resultText.getText());

        // clear button becomes enabled
        assertTrue(clearButton.isEnabled());

        // click clear
        clearButton.click();

        // verify reset state
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
        String homepage = "https://janisdzalbe.github.io/example-site/";

        assertEquals(base_url, driver.getCurrentUrl());

        WebElement link = driver.findElement(By.id("homepage_link"));
        link.click();

        assertNotEquals(base_url, driver.getCurrentUrl());
        assertEquals(homepage, driver.getCurrentUrl());
    }
}
