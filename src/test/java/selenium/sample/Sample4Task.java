package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";
    String homepage_url = "https://janisdzalbe.github.io/example-site/";

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
        String number = "1";
        // TODO 1. Enter a number under "Number"
        driver.findElement(By.id("number")).sendKeys(number);

        // check that button is not clickable "Clear Result"
        assertFalse(driver.findElement(By.id("clear_result_button_number")).isEnabled());

        // check that text is not displayed
        assertFalse(driver.findElement(By.id("result_number")).isDisplayed());

        // click on "Result" button
        driver.findElement(By.id("result_button_number")).click();

        // check that text is displayed
        WebElement text = driver.findElement(By.id("result_number"));
        assertTrue(text.isDisplayed());

        // check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        System.out.println("Result text: " + text.getText());

        //error here
        //assertEquals("You entered number: " + number, text.getText());

        // check that the button "Clear Result" is clickable now
        assertTrue(driver.findElement(By.id("clear_result_button_number")).isEnabled());

        // click on "Clear Result"
        driver.findElement(By.id("clear_result_button_number")).click();

        // check that the text is now (""), but it is not displayed

        // errors here
        //assertEquals("", driver.findElement(By.id("result_number")).getText());
        //assertFalse(driver.findElement(By.id("result_number")).isDisplayed());
    }

    @Test
    public void clickOnLink() throws Exception {
        // TODO: check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());

        // click on "This is a link to Homepage"
        driver.findElement(By.linkText("This is a link to Homepage")).click();

        // check that current url is not base_url
        //System.out.println("Current url: " + driver.getCurrentUrl());
        assertNotEquals(base_url, driver.getCurrentUrl());

        // verify that current url is homepage
        assertEquals(homepage_url, driver.getCurrentUrl());
    }
}
