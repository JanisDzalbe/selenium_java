package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

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
//         TODO:
//          check that none of the checkboxes are ticked
//          tick  "Option 2"
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//          tick  "Option 3"
//          click result
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement opt1 = driver.findElement(By.id("vfb-6-0"));
        WebElement opt2 = driver.findElement(By.id("vfb-6-1"));
        WebElement opt3 = driver.findElement(By.id("vfb-6-2"));

        // none selected
        assertFalse(opt1.isSelected());
        assertFalse(opt2.isSelected());
        assertFalse(opt3.isSelected());

        // tick Option 2
        opt2.click();
        assertFalse(opt1.isSelected());
        assertTrue(opt2.isSelected());
        assertFalse(opt3.isSelected());

        // tick Option 3
        opt3.click();
        assertTrue(opt2.isSelected());
        assertTrue(opt3.isSelected());

        // click result
        driver.findElement(By.id("result_button_checkbox")).click();

        // verify result text
        WebElement result = driver.findElement(By.id("result_checkbox"));
        assertEquals("You selected value(s): Option 2, Option 3", result.getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//          check that none of the radio are selected
//          select  "Option 3"
//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//          select  "Option 1"
//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//          click result
//          check that 'You selected option: Option 1' text is being displayed
        WebElement r1 = driver.findElement(By.id("vfb-7-1"));
        WebElement r2 = driver.findElement(By.id("vfb-7-2"));
        WebElement r3 = driver.findElement(By.id("vfb-7-3"));

        // none selected
        assertFalse(r1.isSelected());
        assertFalse(r2.isSelected());
        assertFalse(r3.isSelected());

        // select Option 3
        r3.click();
        assertFalse(r1.isSelected());
        assertFalse(r2.isSelected());
        assertTrue(r3.isSelected());

        // select Option 1
        r1.click();
        assertTrue(r1.isSelected());
        assertFalse(r2.isSelected());
        assertFalse(r3.isSelected());

        // click result
        driver.findElement(By.id("result_button_ratio")).click();

        // verify result text
        WebElement result = driver.findElement(By.id("result_radio"));
        assertEquals("You selected option: Option 1", result.getText());
    }

    @Test
    public void selectOption() throws Exception {
//         TODO:
//          select "Option 3" in Select
//          check that selected option is "Option 3"
//          select "Option 2" in Select
//          check that selected option is "Option 2"
//          click result
//          check that 'You selected option: Option 2' text is being displayed
        Select select = new Select(driver.findElement(By.id("vfb-12")));

        // select Option 3
        select.selectByVisibleText("Option 3");
        assertEquals("Option 3", select.getFirstSelectedOption().getText());

        // select Option 2
        select.selectByVisibleText("Option 2");
        assertEquals("Option 2", select.getFirstSelectedOption().getText());

        // click result
        driver.findElement(By.id("result_button_select")).click();

        // verify result
        WebElement result = driver.findElement(By.id("result_select"));
        assertEquals("You selected option: Option 2", result.getText());
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
//          check that correct date is added
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click(); // open calendar

        // *** STEP 1: Go to year 2007 ***
        // calendar starts at current year → we must click prev button until year = 2007

        while (!driver.findElement(By.className("ui-datepicker-year")).getText().equals("2007")) {
            driver.findElement(By.className("ui-datepicker-prev")).click();
        }

        // *** STEP 2: Select month July ***
        while (!driver.findElement(By.className("ui-datepicker-month")).getText().equals("July")) {
            driver.findElement(By.className("ui-datepicker-prev")).click();
        }

        // *** STEP 3: Select day 4 ***
        driver.findElement(By.xpath("//a[text()='4']")).click();

        // *** VERIFY RESULT ***
        assertEquals("07/04/2007", dateBox.getAttribute("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
        WebElement dateBox = driver.findElement(By.id("vfb-8"));

        // enter text manually
        dateBox.sendKeys("05/02/1959");

        assertEquals("05/02/1959", dateBox.getAttribute("value"));
    }
}
