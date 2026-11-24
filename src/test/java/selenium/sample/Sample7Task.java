package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample7Task {
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
    public void selectCheckBox() throws Exception {
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));

        // check that none of the checkboxes are ticked
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        // tick "Option 2"
        option2.click();

        // check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        // tick "Option 3"
        option3.click();

        // click result
        driver.findElement(By.id("result_button_checkbox")).click();

        // check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement result = driver.findElement(By.id("result_checkbox"));
        assertTrue(result.isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", result.getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));

        // check that none of the radio are selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        // select "Option 3"
        option3.click();

        // check that "Option 1" and "Option 2" are not selected, but "Option 3" is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        // select "Option 1"
        option1.click();

        // check that "Option 2" and "Option 3" are not selected, but "Option 1" is selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        // click result
        driver.findElement(By.id("result_button_ratio")).click();

        // check that 'You selected option: Option 1' text is being displayed
        WebElement result = driver.findElement(By.id("result_radio"));
        assertTrue(result.isDisplayed());
        assertEquals("You selected option: Option 1", result.getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));

        // select "Option 3" in Select
        dropdown.selectByVisibleText("Option 3");

        // check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        // select "Option 2" in Select
        dropdown.selectByValue("value2");

        // check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        // click result
        driver.findElement(By.id("result_button_select")).click();

        // check that 'You selected option: Option 2' text is being displayed
        WebElement result = driver.findElement(By.id("result_select"));
        assertTrue(result.isDisplayed());
        assertEquals("You selected option: Option 2", result.getText());
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        // enter date '4 of July 2007' using calendar widget
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));
        dateBox.click();

        // go back from the current month to July 2007
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        long monthsBack = ChronoUnit.MONTHS.between(YearMonth.of(2007, 7), YearMonth.now());
        for (int i = 0; i < monthsBack; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
        dateWidget.findElement(By.xpath(".//a[text()='4']")).click();

        // check that correct date is added
        assertEquals("07/04/2007", dateBox.getDomProperty("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        // enter date '2 of May 1959' using text
        String dateToEnter = "05/02/1959";
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));
        dateBox.sendKeys(dateToEnter);

        // check that correct date is added
        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}
