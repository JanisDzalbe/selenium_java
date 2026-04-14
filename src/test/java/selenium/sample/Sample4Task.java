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
        WebElement number = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearResultButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement showResultText = driver.findElement(By.id("result_number"));

        number.clear();
        number.sendKeys("5");

        assertFalse(clearResultButton.isEnabled());
        assertFalse(showResultText.isDisplayed());

        resultButton.click();

        assertTrue(showResultText.isDisplayed());
        assertEquals("You entered number: \"5\"", showResultText.getText());
        assertTrue(clearResultButton.isEnabled());

        clearResultButton.click();

        assertEquals("", showResultText.getText());
        assertFalse(showResultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
        String homePageUrl = "https://janisdzalbe.github.io/example-site/";
        assertEquals(base_url, driver.getCurrentUrl());
        driver.findElement(By.id("homepage_link")).click();
        assertNotEquals(driver.getCurrentUrl(), base_url);
        assertEquals(homePageUrl, driver.getCurrentUrl());
    }
}
