package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

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
        assertFalse(driver.findElement(By.id("vfb-6-0")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-2")).isSelected());

        driver.findElement(By.id("vfb-6-1")).click();

        assertFalse(driver.findElement(By.id("vfb-6-0")).isSelected());
        assertTrue(driver.findElement(By.id("vfb-6-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-2")).isSelected());

        driver.findElement(By.id("vfb-6-2")).click();

        driver.findElement(By.id("result_button_checkbox")).click();

        assertTrue(driver.findElement(By.id("result_checkbox")).isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
        assertFalse(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-3")).isSelected());
//        select  "Option 3"
        driver.findElement(By.id("vfb-7-3")).click();;
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());
        assertTrue(driver.findElement(By.id("vfb-7-3")).isSelected());
//        select  "Option 1"
        driver.findElement(By.id("vfb-7-1")).click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-3")).isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
        Select dElement = new Select(driver.findElement(By.id("vfb-12")));
//        check that selected option is "Option 3"
        dElement.selectByVisibleText("Option 3");
        assertEquals("Option 3", dElement.getFirstSelectedOption().getText());
//        select "Option 2" in Select
        dElement.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals("Option 2", dElement.getFirstSelectedOption().getText());
//        click result
        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2",driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget

//        check that correct date is added
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
        driver.findElement(By.id("vfb-8")).sendKeys("05/02/1959");
//        check that correct date is added
        assertEquals("05/02/1959", driver.findElement(By.id("vfb-8")).getAttribute("value"));
    }
}
