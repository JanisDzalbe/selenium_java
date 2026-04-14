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
        //TODO: enter a number under "Number"
        //TODO: check that button is not clickable "Clear Result"
        //TODO: check that text is not displayed
        //TODO: check that text is not displayed
        //TODO: click on "Result" button
        //TODO: check that text is displayed
        //TODO: check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        //TODO: check that the button "Clear Result" is clickable now
        //TODO: click on "Clear Result"
        //TODO: check that the text is now (""), but it is not displayed

        WebElement numInput = driver.findElement(By.id("number"));
        WebElement numClearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement numResultButton = driver.findElement(By.id("result_button_number"));
        WebElement numResultText = driver.findElement(By.id("result_number"));

        numInput.clear();
        numInput.sendKeys("6");
        assertFalse(numClearButton.isEnabled());
        assertFalse(numResultText.isDisplayed());

        numResultButton.click();
        assertTrue(numResultText.isDisplayed());
        assertEquals("You entered number: \"6\"", numResultText.getText());
        assertTrue(numClearButton.isEnabled());

        numClearButton.click();
        assertEquals("", numResultText.getText());
        assertFalse(numResultText.isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());

//          TODO: click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();

//          TODO: check that current url is not base_url
        assertNotEquals(driver.getCurrentUrl(), base_url);

//          verify that current url is homepage
        String homepageUrl = "https://janisdzalbe.github.io/example-site/";
        assertEquals(homepageUrl, driver.getCurrentUrl());
    }
}
