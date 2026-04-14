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
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void enterNumber() throws Exception {
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));

        String number = "5";

        //clear existing value first
        numberInput.clear();

        //then enter new value
        numberInput.sendKeys(number);

        //checks that button is not clickable "Clear Result"
        assertFalse(clearButton.isEnabled());

        //checks that text is not displayed
        assertFalse(resultText.isDisplayed());

        //click on "Result" button
        resultButton.click();

        Thread.sleep(1000);

        //checks that text is displayed
        assertTrue(resultText.isDisplayed());

        //checks if the correct Text appears
        String expectedText = "You entered number: \"" + number + "\"";
        assertEquals(expectedText, resultText.getText());

        assertTrue(clearButton.isEnabled());
    }

    @Test
    public void clickOnLink() throws Exception {
        //checks current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());

        //clicks on "This is a link to Homepage"
        driver.findElement(By.id("link1")).click();

        //checks that current url is not base_url
        assertNotEquals(base_url, driver.getCurrentUrl());

        //verify that current url is homepage
        assertEquals("https://janisdzalbe.github.io/example-site/examples/link1", driver.getCurrentUrl());
    }
}
