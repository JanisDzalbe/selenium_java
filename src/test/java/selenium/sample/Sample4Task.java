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
        numberInput.clear();
        numberInput.sendKeys("8");


        WebElement clearResultButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(clearResultButton.isEnabled());

        WebElement resultText = driver.findElement(By.id("result_number"));
        assertFalse(resultText.isDisplayed());


        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        resultButton.click();
        assertTrue(resultText.isDisplayed());
        assertEquals(resultText.getText(), "You entered number: " + "\"8\"");

        assertTrue(clearResultButton.isEnabled());
        clearResultButton.click();
        assertEquals(resultText.getText(),"");


    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage
        assertEquals(driver.getCurrentUrl(), base_url);
        driver.findElement(By.id("homepage_link")).click();
        assertFalse(driver.getCurrentUrl().equals(base_url));
        assertEquals(driver.getCurrentUrl(), "https://acctabootcamp.github.io/site/");
    }
}
