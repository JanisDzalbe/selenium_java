package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
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
//         TODO:
//        check that none of the checkboxes are ticked
//        tick  "Option 2"
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//        tick  "Option 3"
//        click result
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement box : checkboxes){
           assertFalse(box.isSelected());
        }

        checkboxes.get(1).click();
        assertTrue(checkboxes.get(1).isSelected());
        assertFalse(checkboxes.get(0).isSelected());
        assertFalse(checkboxes.get(2).isSelected());

        checkboxes.get(2).click();
        driver.findElement(By.id("result_button_checkbox")).click();

        //would better practice here be using contains Option2 and Option3?
        assertTrue(driver.findElement(By.id("result_checkbox")).isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3" ,driver.findElement(By.id("result_checkbox")).getText());

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
        List<WebElement> radios = driver.findElements(By.cssSelector("input[type='radio']"));
        for (WebElement radio : radios){
            assertFalse(radio.isSelected());
        }

        radios.get(2).click();
        assertTrue(radios.get(2).isSelected());
        assertFalse(radios.get(0).isSelected());
        assertFalse(radios.get(1).isSelected());

        radios.get(0).click();
        assertTrue(radios.get(0).isSelected());
        assertFalse(radios.get(1).isSelected());
        assertFalse(radios.get(2).isSelected());

        driver.findElement(By.id("result_button_ratio")).click();
        assertTrue(driver.findElement(By.id("result_radio")).isDisplayed());
        assertEquals("You selected option: Option 1" ,driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
        Select dropdownMenu = new Select(driver.findElement(By.id("vfb-12")));
        dropdownMenu.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdownMenu.getFirstSelectedOption().getText());
        dropdownMenu.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdownMenu.getFirstSelectedOption().getText());
        driver.findElement(By.id("result_button_select")).click();
        assertTrue(driver.findElement(By.id("result_select")).isDisplayed());
        assertEquals("You selected option: Option 2" ,driver.findElement(By.id("result_select")).getText());

    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        Calendar calendar = Calendar.getInstance();
        calendar.set(2007, Calendar.JULY, 4);
        String date = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime());

        driver.findElement(By.id("vfb-8")).click();

        // How many times to click the back button lol
        LocalDate startDate = LocalDate.now();
        // may be bad practice getting the date twice lol. Should probably convert date to this in a normal scenario or find a cleaner way of doing this
        LocalDate endDate = LocalDate.of(2007, Month.JULY, 4);
        int diffMonths = (int) Period.between(endDate, startDate).toTotalMonths();

        WebElement dateBox = driver.findElement(By.id("ui-datepicker-div"));
//        WebElement previousButton = dateBox.findElement(By.className("ui-datepicker-prev"));
        for(int i=0; i<diffMonths; i++){
            dateBox.findElement(By.className("ui-datepicker-prev")).click();
        }
        dateBox.findElement(By.xpath("//a[text() = '4']")).click();

        //weirdly this doesnt work with getDomAttribute
        assertEquals(date, driver.findElement(By.id("vfb-8")).getAttribute("value"));

    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added

        Calendar calendar = Calendar.getInstance();
        calendar.set(1959, Calendar.MAY, 2);
        String date = new SimpleDateFormat("MM/dd/yyyy").format(calendar.getTime());

        WebElement dateInput = driver.findElement(By.id("vfb-8"));
        dateInput.clear();
        assertEquals("", dateInput.getAttribute("value"));

        dateInput.sendKeys(date);

        assertEquals(date, driver.findElement(By.id("vfb-8")).getAttribute("value"));
    }
}
