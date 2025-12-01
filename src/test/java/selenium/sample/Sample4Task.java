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
        driver = BootcampUtils.initializeChromeDriver();
        driver.get(base_url);
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void enterNumber() throws Exception {
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));

        assertFalse(resultText.isDisplayed());
        assertTrue(resultButton.isEnabled());
        assertFalse(clearButton.isEnabled());

        numberInput.sendKeys("123");
        resultButton.click();

        assertTrue(resultText.isDisplayed());
        assertEquals("You entered number: \"123\"", resultText.getText());
        assertTrue(clearButton.isEnabled());

        clearButton.click();

        assertEquals("", resultText.getText());
        assertFalse(resultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
        assertEquals(base_url, driver.getCurrentUrl());

        WebElement homeLink = driver.findElement(By.linkText("Home"));
        homeLink.click();

        assertNotEquals(base_url, driver.getCurrentUrl());
        assertEquals("https://janisdzalbe.github.io/example-site/", driver.getCurrentUrl());
    }
}