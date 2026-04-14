package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;
import java.util.Objects;

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

        WebElement numInput = driver.findElement(By.id("number"));
        WebElement numResultButton = driver.findElement(By.id("result_button_number"));
        WebElement numClearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement numResultText = driver.findElement(By.id("result_number"));

        numInput.clear();
        numInput.sendKeys("10");

        assertFalse(numClearButton.isEnabled());
        assertFalse(numResultText.isDisplayed());

        numResultButton.click();

        assertTrue(numResultText.isDisplayed());
        assertEquals("You entered number: \"10\"", numResultText.getText());
        assertTrue(numClearButton.isEnabled());

        numClearButton.click();

        assertEquals("", numResultText.getText());
        assertFalse(numResultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
        assertEquals(base_url, driver.getCurrentUrl());
        List<WebElement> links = driver.findElements(By.tagName("a"));
        assertFalse(links.isEmpty(), "No links found");
        links.getFirst().click();

        assertNotEquals(base_url, driver.getCurrentUrl());
        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("example-site"));
    }
}