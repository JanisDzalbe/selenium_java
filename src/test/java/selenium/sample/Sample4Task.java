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
        WebElement numberInput = driver.findElement(By.name("vfb-9"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearResultButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));

        String input = "10";

        assertEquals("5", numberInput.getDomProperty("value"));
//          enter a number under "Number"
        numberInput.clear();
        numberInput.sendKeys(input);
//          check that button is not clickable "Clear Result"
        assertFalse(clearResultButton.isEnabled());
//          check that text is not displayed
        assertFalse(resultText.isDisplayed());
//          click on "Result" button
        resultButton.click();
//          check that text is displayed
        assertTrue(resultText.isDisplayed());
//          check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals("You entered number: \"" + input + "\"", resultText.getText());
//          check that the button "Clear Result" is clickable now
        assertTrue(clearResultButton.isEnabled());
//          click on "Clear Result"
        clearResultButton.click();
//          check that the text is now (""), but it is not displayed
        assertEquals("", resultText.getText());
        assertFalse(resultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//          check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//          click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
//          check that current url is not base_url
        assertNotEquals(driver.getCurrentUrl(), base_url);
//          verify that current url is homepage
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}
