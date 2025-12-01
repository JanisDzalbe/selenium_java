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
        String number = "42";

        WebElement numberInput = driver.findElement(By.id("inputNumber")); // FIXED ID
        numberInput.sendKeys(number);

        WebElement resultButton = driver.findElement(By.id("resultButton"));
        WebElement clearButton = driver.findElement(By.id("clearResult"));
        WebElement resultText = driver.findElement(By.id("result"));


        assertFalse(clearButton.isEnabled());


        assertEquals("", resultText.getText());


        resultButton.click();


        assertEquals("You entered number: " + number, resultText.getText());


        assertTrue(clearButton.isEnabled());


        clearButton.click();


        assertEquals("", resultText.getText());
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

