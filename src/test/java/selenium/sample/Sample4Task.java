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
        int number = 190;
        WebElement inputFieldForNumber = driver.findElement(By.id("number"));
        WebElement clearResultButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement enterNumberButton = driver.findElement(By.id("result_button_number"));
        WebElement resultElement = driver.findElement(By.id("result_number"));

        inputFieldForNumber.clear();
        inputFieldForNumber.sendKeys(Integer.toString(number));
        assertFalse(clearResultButton.isEnabled());
        assertFalse(resultElement.isDisplayed());
        enterNumberButton.click();
        assertTrue(resultElement.isDisplayed());
        assertEquals("You entered number: \"" + number + "\"", resultElement.getText());
        assertTrue(clearResultButton.isEnabled());
        clearResultButton.click();
        assertEquals("", resultElement.getText());
        assertFalse(resultElement.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
        assertEquals(base_url, driver.getCurrentUrl());
        WebElement link = driver.findElement(By.linkText("This is a link to Homepage"));
        link.click();
        assertNotEquals(base_url, driver.getCurrentUrl());
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}
