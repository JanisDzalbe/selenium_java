package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        driver.findElement(By.id("number")).clear();
        driver.findElement(By.id("number")).sendKeys("228");
        assertFalse(driver.findElement(By.id("clear_result_button_number")).isEnabled());
        assertFalse(driver.findElement(By.id("result_number")).isDisplayed());
        driver.findElement(By.id("result_button_number")).click();
        assertTrue(driver.findElement(By.id("result_number")).isDisplayed());
        assertEquals("You entered number: \""+driver.findElement(By.id("number")).getAttribute("value")+"\"", driver.findElement(By.id("result_number")).getText());
        assertTrue(driver.findElement(By.id("clear_result_button_number")).isEnabled());
        driver.findElement(By.id("clear_result_button_number")).click();
        assertEquals("", driver.findElement(By.id("result_number")).getText());
        assertFalse(driver.findElement(By.id("result_number")).isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        assertEquals(base_url, driver.getCurrentUrl());
        driver.findElement(By.id("homepage_link")).click();
        assertNotEquals(base_url, driver.getCurrentUrl());
        assertEquals("https://acctabootcamp.github.io/site/", driver.getCurrentUrl());
    }
}
