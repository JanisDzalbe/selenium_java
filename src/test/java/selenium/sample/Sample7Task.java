package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

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
//         TODO:
//        check that none of the checkboxes are ticked
//        tick  "Option 2"
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//        tick  "Option 3"
//        click result
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement checkBox1 = driver.findElement(By.id("vfb-6-0"));
        WebElement checkBox2 = driver.findElement(By.id("vfb-6-1"));
        WebElement checkBox3 = driver.findElement(By.id("vfb-6-2"));

        assertFalse(checkBox1.isSelected());
        assertFalse(checkBox2.isSelected());
        assertFalse(checkBox3.isSelected());

        checkBox2.click();

        assertFalse(checkBox1.isSelected());
        assertTrue(checkBox2.isSelected());
        assertFalse(checkBox3.isSelected());

        checkBox3.click();

        driver.findElement(By.id("result_button_checkbox")).click();
        String actualText = driver.findElement(By.id("result_checkbox")).getText();
        assertEquals("You selected value(s): Option 2, Option 3", actualText);
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
//        select  "Option 3"
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//        select  "Option 1"
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//        click result
//        check that 'You selected option: Option 1' text is being displayed

        WebElement radioButton1 = driver.findElement(By.id("vfb-7-1"));
        WebElement radioButton2 = driver.findElement(By.id("vfb-7-2"));
        WebElement radioButton3 = driver.findElement(By.id("vfb-7-3"));

        assertFalse(radioButton1.isSelected());
        assertFalse(radioButton2.isSelected());
        assertFalse(radioButton3.isSelected());

        radioButton3.click();

        assertFalse(radioButton1.isSelected());
        assertFalse(radioButton2.isSelected());
        assertTrue(radioButton3.isSelected());

        radioButton1.click();

        assertTrue(radioButton1.isSelected());
        assertFalse(radioButton2.isSelected());
        assertFalse(radioButton3.isSelected());

        driver.findElement(By.id("result_button_ratio")).click();

        String actualText = driver.findElement(By.id("result_radio")).getText();
        assertEquals("You selected option: Option 1", actualText);

    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(2);

        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
        driver.findElement(By.id("result_button_select")).click();
        String actualText = driver.findElement(By.id("result_select")).getText();
        assertEquals("You selected option: Option 2", actualText);

        WebElement dropdownResultButton = driver.findElement(By.id("dropdownResult"));
        dropdownResultButton.click();

        String expectedResult = "Option 2";
        assertEquals(expectedResult, driver.findElement(By.id("result_select")).getText());

    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        WebElement dateInput = driver.findElement(By.id("datePickerInput"));
        dateInput.click();

        LocalDate targetDate = LocalDate.of(2007, 7, 4);

        String expectedDateString = targetDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        assertEquals(expectedDateString, dateInput.getAttribute("value"),
                "Expected date " + expectedDateString);
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added

        LocalDate targetDate = LocalDate.of(1959, 5, 2);
        String dateString = targetDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        WebElement dateInput = driver.findElement(By.id("datePickerInput"));
        dateInput.clear();

        assertEquals(dateString, dateInput.getAttribute("value"),
                "Expected date " + dateString);
    }
}
