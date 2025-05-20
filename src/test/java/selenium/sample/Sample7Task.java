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
import java.time.Year;
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
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        for(WebElement box : checkBoxes){
            assertFalse(box.isSelected());
        }
        checkBoxes.get(1).click();
        assertFalse(checkBoxes.get(0).isSelected());
        assertFalse(checkBoxes.get(2).isSelected());
        assertTrue(checkBoxes.get(1).isSelected());
        checkBoxes.get(2).click();
        driver.findElement(By.id("result_button_checkbox")).click();
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
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
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("[type=radio]"));
        for(WebElement button : radioButtons){
            assertFalse(button.isSelected());
        }
        radioButtons.get(2).click();
        assertFalse(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertTrue(radioButtons.get(2).isSelected());
        radioButtons.get(0).click();
        assertTrue(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertFalse(radioButtons.get(2).isSelected());
        driver.findElement(By.id("result_button_ratio")).click();
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
        Select dropDown = new Select(driver.findElement(By.id("vfb-12")));
        dropDown.selectByValue("value3");
        assertEquals("Option 3", dropDown.getFirstSelectedOption().getText());
        dropDown.selectByValue("value2");
        assertEquals("Option 2", dropDown.getFirstSelectedOption().getText());
        driver.findElement(By.id("result_button_select")).click();
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        LocalDate today = LocalDate.now();
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        for(int i=0; i< ((((today.getYear()-2000)*12)+today.getMonthValue())-((7*12)+7)); i++){
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
        dateWidget.findElement(By.xpath("//*[@class='ui-state-default' and text()='4']")).click();
        assertEquals("07/04/2007",dateBox.getAttribute("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
        driver.findElement(By.id("vfb-8")).sendKeys("05/02/1959");
        assertEquals("05/02/1959",driver.findElement(By.id("vfb-8")).getAttribute("value"));
    }
}
