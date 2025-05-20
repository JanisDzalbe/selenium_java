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
        WebElement numInput = driver.findElement(By.name("vfb-9"));
        WebElement clearBtn = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement((By.id("result_number")));
        WebElement resultBtn = driver.findElement((By.id("result_button_number")));

        numInput.clear();
        String num = "123";
        numInput.sendKeys(num);
        assertFalse(clearBtn.isEnabled());
        assertFalse(resultText.isDisplayed());
        Thread.sleep(1000);
        resultBtn.click();
        assertTrue(resultText.isDisplayed());
        assertEquals("You entered number: \"123\"", resultText.getText());
        assertTrue(clearBtn.isEnabled());
        Thread.sleep(1000);
        clearBtn.click();
        assertEquals("", resultText.getText());
        assertFalse(resultText.isDisplayed());
        Thread.sleep(1000);
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        // Check that current URL is base URL
        assertEquals(base_url, driver.getCurrentUrl());
        WebElement homeLink = driver.findElement(By.id("homepage_link"));
        homeLink.click();
        Thread.sleep(2000);
        assertNotEquals(base_url, driver.getCurrentUrl());
        assertEquals("https://acctabootcamp.github.io/site/", driver.getCurrentUrl());
        Thread.sleep(3000);
    }
}
