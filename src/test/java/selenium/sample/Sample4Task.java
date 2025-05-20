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
        driver.findElement(By.id("number")).clear();
        driver.findElement(By.id("number")).sendKeys("8");
//        check that button is not clickable "Clear Result"
        driver.findElement(By.id("clear_result_button_number")).click();
        assertFalse(driver.findElement(By.id("clear_result_button_number")).isEnabled());
//        check that text is not displayed
       driver.findElement(By.id("number")).clear();
       assertFalse(driver.findElement(By.id("result_number")).isDisplayed());
//        click on "Result" button
        driver.findElement(By.id("result_button_number")).click();

//        check that text is displayed
        assertTrue(driver.findElement(By.id("number")).isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        //driver.findElement(By.id("result_number"));
        driver.findElement(By.id("number")).sendKeys("5");
        driver.findElement(By.id("result_button_number")).click();
        assertEquals("You entered number: \"5\"",driver.findElement(By.id("result_number")).getText());

//        check that the button "Clear Result" is clickable now
        assertTrue(driver.findElement(By.id("clear_result_button_number")).isEnabled());

//        click on "Clear Result"
        driver.findElement(By.id("clear_result_button_number")).click();

//        check that the text is now (""), but it is not displayed
        assertEquals("",driver.findElement(By.id("result_number")).getText());

    }


    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
//        check that current url is not base_url
        assertNotEquals("https://acctabootcamp.github.io/site/examples/actions", driver.getCurrentUrl());
//        verify that current url is homepage
        assertEquals("https://acctabootcamp.github.io/site/", driver.getCurrentUrl());

    }
}
