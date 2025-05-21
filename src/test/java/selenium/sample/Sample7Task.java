package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class Sample7Task {
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
    public void selectCheckBox() throws Exception {
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3']"));

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        option2.click();

        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        option3.click();
        driver.findElement(By.id("result_button_checkbox")).click();


        WebElement resultText = driver.findElement(By.id("result_checkbox"));

        assertEquals("You selected value(s): Option 2, Option 3", resultText.getText());
//         TODO:
//        check that none of the checkboxes are ticked
//        tick  "Option 2"
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//        tick  "Option 3"
//        click result
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
    }


    @Test
    public void selectRadioButton() throws Exception {
        WebElement option1 = driver.findElement(By.cssSelector("input[type='radio'][value='Option 1']"));
        WebElement option2 = driver.findElement(By.cssSelector("input[type='radio'][value='Option 2']"));
        WebElement option3 = driver.findElement(By.cssSelector("input[type='radio'][value='Option 3']"));

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        option3.click();
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        option1.click();
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        driver.findElement(By.id("result_button_ratio")).click();

        WebElement resultText = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result_radio")));
        assertEquals("You selected option: Option 1", resultText.getText());
//         TODO:
//        check that none of the radio are selected
//        select  "Option 3"
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//        select  "Option 1"
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//        click result
//        check that 'You selected option: Option 1' text is being displayed
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));

        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        driver.findElement(By.id("result_button_select")).click();

        WebElement resultText = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result_select")));
        assertEquals("You selected option: Option 2", resultText.getText());
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click();

        Calendar target = Calendar.getInstance();
        target.set(2007, Calendar.JULY, 4);
        Calendar current = Calendar.getInstance();

        int diff = (current.get(Calendar.YEAR) - 2007) * 12 + (current.get(Calendar.MONTH) - Calendar.JULY);
        for (int i = 0; i < diff; i++) {
            driver.findElement(By.className("ui-datepicker-prev")).click();
            Thread.sleep(200);
        }

        driver.findElement(By.xpath("//a[text()='4']")).click();

        String expected = new SimpleDateFormat("MM/dd/yyyy").format(target.getTime());
        assertEquals(expected, dateBox.getAttribute("value"));
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        String dateToEnter = "05/02/1959";

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);

        assertEquals(dateToEnter, dateBox.getAttribute("value"));
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
    }
}
