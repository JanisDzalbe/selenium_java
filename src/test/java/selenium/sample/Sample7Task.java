package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

        String expectedResult = "You selected value(s): Option 2, Option 3";

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox: checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        WebElement checkBox1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement checkBox2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        WebElement checkBox3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));

        checkBox2.click();

        assertFalse(checkBox1.isSelected());
        assertFalse(checkBox3.isSelected());

        checkBox3.click();

        driver.findElement(By.id("result_button_checkbox")).click();

        assertEquals(expectedResult, driver.findElement(By.id("result_checkbox")).getText());


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

        String expectedResult = "You selected option: Option 1";

        assertFalse(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-3")).isSelected());

        driver.findElement(By.id("vfb-7-3")).click();

        assertFalse(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());
        assertTrue(driver.findElement(By.id("vfb-7-3")).isSelected());

        driver.findElement(By.id("vfb-7-1")).click();

        assertTrue(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-3")).isSelected());

        driver.findElement(By.id("result_button_ratio")).click();

        assertEquals(expectedResult, driver.findElement(By.id("result_radio")).getText());


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

        String expectedResult = "You selected option: Option 2";

        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByValue("value3");

        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByValue("value2");

        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        driver.findElement(By.id("result_button_select")).click();

        assertEquals(expectedResult, driver.findElement(By.id("result_select")).getText());


//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {

        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        LocalDate currentDate = LocalDate.now();// Текущая дата
        LocalDate checkingDate = LocalDate.of(2007, 7, 4); // Дата для вычитания

        long monthsBetween = ChronoUnit.MONTHS.between(
                LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1),
                LocalDate.of(checkingDate.getYear(), checkingDate.getMonth(), 1)
        );

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedCheckingDate = checkingDate.format(dateFormatter);

        driver.findElement(By.id("vfb-8")).click();

        for (int i = 0 ; i < Math.abs(monthsBetween); i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
        driver.findElement(By.id("result_button_date")).click();

        assertEquals("You entered date: " + formattedCheckingDate, driver.findElement(By.id("result_date")).getText());
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        LocalDate checkingDate = LocalDate.of(1959, 5, 2); // Дата для вычитания

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedCheckingDate = checkingDate.format(dateFormatter);

        dateBox.sendKeys(formattedCheckingDate);
        dateBox.sendKeys(Keys.RETURN);

        driver.findElement(By.id("result_button_date")).click();
        assertEquals("You entered date: " + formattedCheckingDate, driver.findElement(By.id("result_date")).getText());
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
    }
}
