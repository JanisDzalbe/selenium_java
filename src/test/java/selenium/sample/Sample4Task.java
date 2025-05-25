package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

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
        //driver.quit();
    }

    @Test
    public void enterNumber() throws Exception {
//         TODO:
        WebElement numberArea = driver.findElement(By.id("number"));
        String originalNumber = "5";
        assertEquals(numberArea.getAttribute("value"), originalNumber);
        numberArea.clear();

//        enter a number under "Number"
        String numberNew = "2";
        numberArea.sendKeys(numberNew);

//        check that button is not clickable "Clear Result"
        WebElement clearResultButtonNumber = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(clearResultButtonNumber.isEnabled());

//        check that text is not displayed
        WebElement textResultNumber = driver.findElement(By.id("result_number"));
        assertFalse(textResultNumber.isDisplayed());

//        click on "Result" button
        WebElement resultButtonNumber = driver.findElement(By.id("result_button_number"));
        assertTrue(resultButtonNumber.isEnabled());
        resultButtonNumber.click();

//        check that text is displayed
        assertTrue(textResultNumber.isDisplayed());

//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        String expectedText = ("You entered number: \"" + numberNew + "\"");
        assertEquals(expectedText, textResultNumber.getText());
        assertTrue(textResultNumber.isDisplayed());

//        check that the button "Clear Result" is clickable now
        assertTrue(clearResultButtonNumber.isEnabled());

//        click on "Clear Result"
        clearResultButtonNumber.click();

//        check that the text is now (""), but it is not displayed
        assertEquals("", textResultNumber.getText());
        assertFalse(textResultNumber.isDisplayed());

    }


    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());

//        click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
        assertEquals("This is a home page", driver.findElement(By.id("h1")).getText());

//        check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));

//        verify that current url is homepage
        assertEquals("https://acctabootcamp.github.io/site/", driver.getCurrentUrl());
    }
}
