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
//         TODO:
//          enter a number under "Number"
        String number = "35";
        WebElement input = driver.findElement(By.id("number"));
        input.clear();
        input.sendKeys(number);
//          check that button is not clickable "Clear Result"
        WebElement buttonClear = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(buttonClear.isEnabled());
//          check that text is not displayed
        WebElement text = driver.findElement(By.id("result_number"));
        assertFalse(text.isDisplayed());
//          click on "Result" button
        WebElement buttonResult = driver.findElement(By.id("result_button_number"));
        buttonResult.click();
//          check that text is displayed
        assertTrue(text.isDisplayed());
//          check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertEquals("You entered number: \""+ number +"\"", text.getText() );
//          check that the button "Clear Result" is clickable now
        assertTrue(buttonClear.isEnabled());
//          click on "Clear Result"
        buttonClear.click();
//          check that the text is now (""), but it is not displayed
        assertEquals("", text.getText());
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//          check current url is base_url
            String currentUrl = driver.getCurrentUrl();
            assertEquals(base_url, currentUrl);
//          click on "This is a link to Homepage"
            WebElement toHome = driver.findElement(By.xpath("//a[@href='/example-site/']"));
            toHome.click();
//          check that current url is not base_url
            currentUrl = driver.getCurrentUrl();
            assertFalse(currentUrl.equals(base_url));
//          verify that current url is homepage
            assertTrue(currentUrl.equals("https://janisdzalbe.github.io/example-site/"));
    }
}
