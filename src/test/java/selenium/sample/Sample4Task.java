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
    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeDriver();

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
        numberInput.clear();
        numberInput.sendKeys("42");
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(clearButton.isEnabled(), "Clear Result should NOT be clickable yet");
        WebElement resultText = driver.findElement(By.id("result_number"));
        assertFalse(resultText.isDisplayed(), "Result text must NOT be visible before clicking");
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        resultButton.click();
        assertTrue(resultText.isDisplayed(), "Result text should be visible after clicking Result");
        String expected = "You entered number: \"42\"";
        String actual = resultText.getText();
        assertEquals(expected, actual);
        assertTrue(clearButton.isEnabled(), "Clear Result should be clickable after showing result");
        clearButton.click();
        String afterClear = resultText.getText();
        assertEquals("", afterClear);
        assertFalse(resultText.isDisplayed(), "Result text must not be visible after clearing");
    }


    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//          check current url is base_url
//          click on "This is a link to Homepage"
//          check that current url is not base_url
//          verify that current url is homepage
        assertEquals(base_url, driver.getCurrentUrl());
        WebElement link = driver.findElement(By.id("homepage_link"));
        link.click();
        String newUrl = driver.getCurrentUrl();
        assertFalse(newUrl.equals(base_url));
        assertEquals("https://janisdzalbe.github.io/example-site/", newUrl);
    }
}
