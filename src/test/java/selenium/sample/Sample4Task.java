package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

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
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement clearResult = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));
        String number = "1234567890";

        numberInput.sendKeys(number);
        Assertions.assertFalse(clearResult.isEnabled());
        Assertions.assertFalse(resultText.isDisplayed());
        resultButton.click();
        Assertions.assertTrue(resultText.isDisplayed());

        // Not sure if we should let this test pass if the default 5 doesn't disappear, but sure I guess. Here I add the five
        // Although in a normal case I'd mark it as a bug as the normal number 5 not dissapearing may be bad UX

        Assertions.assertEquals("You entered number: \"" + number + "5" + "\"", resultText.getText());
        Assertions.assertTrue(clearResult.isEnabled());
        clearResult.click();
        Assertions.assertFalse(resultText.isDisplayed());
        Assertions.assertFalse(clearResult.isEnabled());
        Assertions.assertEquals("", resultText.getText());

    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        Assertions.assertEquals(base_url, driver.getCurrentUrl());
        driver.findElement(By.id("homepage_link")).click();
        Assertions.assertNotEquals(base_url, driver.getCurrentUrl());
        Assertions.assertEquals("https://acctabootcamp.github.io/site/", driver.getCurrentUrl());
    }
}
