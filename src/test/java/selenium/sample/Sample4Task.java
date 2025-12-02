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


        WebElement numberInput = driver.findElement(By.id("number")); // FIXED ID


        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));

        numberInput.clear();
        numberInput.sendKeys("7");


        assertFalse(clearButton.isEnabled());
        assertFalse(resultText.isDisplayed());

        resultButton.click();

        assertTrue(resultText.isDisplayed());

        resultButton.click();

        assertTrue(resultText.isDisplayed());
        assertEquals("You entered number: \"7\"",resultText.getText());
        assertTrue(clearButton.isEnabled());

        clearButton.click();
        assertEquals("",resultText.getText());
        assertFalse(resultText.isDisplayed());

    }


    @Test
    public void clickOnLink() throws Exception {

        assertEquals(base_url, driver.getCurrentUrl(), "Current URL should be the base URL");

        WebElement link = driver.findElement(By.linkText("This is a link to Homepage"));
        link.click();

        assertNotEquals(base_url, driver.getCurrentUrl(), "Current URL should have changed");

        String expectedHome = "https://janisdzalbe.github.io/example-site/";
        assertEquals(expectedHome, driver.getCurrentUrl(), "Current URL should be homepage");
    }
}

