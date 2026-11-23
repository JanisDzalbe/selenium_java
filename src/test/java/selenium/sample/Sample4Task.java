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
        WebElement numInput = driver.findElement(By.id("number"));
        WebElement numResultButton = driver.findElement(By.id("result_button_number"));
        WebElement numClearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement numResults = driver.findElement(By.id("result_number"));

        numInput.clear();
        numInput.sendKeys("6");
        assertFalse(numClearButton.isEnabled());
        assertFalse(numResults.isDisplayed());

        numResultButton.click();
        assertTrue(numResults.isDisplayed());
        assertEquals("You entered number: \"6\"", numResults.getText());
        assertTrue(numClearButton.isEnabled());

        numClearButton.click();
        assertFalse(numResults.isDisplayed());
        assertEquals("", numResults.getText());
    }

    @Test
    public void clickOnLink() throws Exception {
        assertEquals(base_url, driver.getCurrentUrl());
        driver.findElement(By.id("homepage_link")).click();
        assertNotEquals(base_url, driver.getCurrentUrl());
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}