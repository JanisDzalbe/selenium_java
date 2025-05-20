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
        WebElement number_area = driver.findElement(By.id("number"));
        String num = "10";

        number_area.clear();
        number_area.sendKeys(num);

        WebElement ClearResultButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(ClearResultButton.isEnabled());

        WebElement ResultText = driver.findElement(By.id("result_number"));
        assertFalse(ResultText.isDisplayed());

        WebElement ResultButton = driver.findElement(By.id("result_button_number"));
        ResultButton.click();

        assertTrue(ResultText.isDisplayed());

        assertEquals(ResultText.getText(), String.format("You entered number: \"%s\"", num));

        assertTrue(ClearResultButton.isEnabled());

        ClearResultButton.click();

        assertFalse(ResultText.isDisplayed());
        //Thread.sleep(5000);

    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage

        String current_url = driver.getCurrentUrl();
        String homepage_url = "https://acctabootcamp.github.io/site/";

        assertEquals(base_url, current_url);

        WebElement link = driver.findElement(By.id("homepage_link"));
        link.click();

        current_url = driver.getCurrentUrl();
        assertFalse(base_url.equals(current_url));

        assertEquals(current_url, homepage_url);
    }
}
