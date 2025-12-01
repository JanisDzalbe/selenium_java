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

        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));

        String numberToEnter = "10"; //number we will enter

        numberInput.clear(); //clears any previous existing value in the box (default 5)

        numberInput.sendKeys(numberToEnter); //entering the earlier defined 10

        assertFalse(clearButton.isEnabled()); //check that button "Clear Result" is not clickable

        assertFalse(resultText.isDisplayed()); // check that text is not displayed

        resultButton.click(); //click on "Result" button

        assertTrue(resultText.isDisplayed()); // check that text is displayed

        String expectedText = "You entered number: \"" + numberToEnter + "\"";
        assertEquals(expectedText, resultText.getText());

        assertTrue(clearButton.isEnabled()); //check that the button "Clear Result" is clickable now

        clearButton.click(); // clicking on "Clear Result"

        assertEquals("", resultText.getText()); // Check that the text is now empty (""), but it is not displayed
        assertFalse(resultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//          check current url is base_url
//          click on "This is a link to Homepage"
//          check that current url is not base_url
//          verify that current url is homepage

        assertEquals(base_url, driver.getCurrentUrl()); //check current url is base_url

        driver.findElement(By.linkText("This is a link to Homepage")).click(); // click on homepage

        assertNotEquals(base_url, driver.getCurrentUrl()); //check that current url is not base_url

        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl()); //verify that current url is homepage
    }
}