package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import java.util.List;

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
        List<WebElement> checkboxes = driver.findElements(By.name("vfb-6[]"));
        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }
//          tick  "Option 2"
        checkboxes.get(1).click();
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(checkboxes.get(0).isSelected());
        assertFalse(checkboxes.get(2).isSelected());
        assertTrue(checkboxes.get(1).isSelected());
//          tick  "Option 3"
        checkboxes.get(2).click();
//          click result
        driver.findElement(By.id("result_button_checkbox")).click();
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        String corRes = "You selected value(s): Option 2, Option 3";
        assertEquals(corRes, driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//          check that none of the radio are selected
        List<WebElement> radios = driver.findElements(By.name("vfb-7"));
//          select  "Option 3"
        radios.get(2).click();
//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radios.get(0).isSelected());
        assertFalse(radios.get(1).isSelected());
        assertTrue(radios.get(2).isSelected());
//          select  "Option 1"
        radios.get(0).click();
//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radios.get(0).isSelected());
        assertFalse(radios.get(1).isSelected());
        assertFalse(radios.get(2).isSelected());
//          click result
        driver.findElement(By.id("result_button_ratio")).click();
//          check that 'You selected option: Option 1' text is being displayed
        String correctRes = "You selected option: Option 1";
        assertEquals(correctRes, driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//         TODO:
//          select "Option 3" in Select
        Select dropdown = new Select(driver.findElement(By.name("vfb-12")));
        dropdown.selectByVisibleText("Option 3");
//          check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//          select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");
//          check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//          click result
        driver.findElement(By.id("result_button_select")).click();
//          check that 'You selected option: Option 2' text is being displayed
        String corRes = "You selected option: Option 2";
        assertEquals(corRes, driver.findElement(By.id("result_select")).getText());
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        while (!(dateWidget.findElement(By.className("ui-datepicker-month")).getText().equals("July")
                && dateWidget.findElement(By.className("ui-datepicker-year")).getText().equals("2007"))) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        dateWidget.findElement(By.xpath(".//a[text()='4']")).click();


        assertEquals("07/04/2007", dateBox.getDomProperty("value"));

    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
        String dateToEnter = "05/09/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));
        dateBox.clear();
        dateBox.sendKeys(dateToEnter);
        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}
