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
        driver = BootcampUtils.initializeEdgeDriver();

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
        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        WebElement option2 = driver.findElement(By.id("vfb-6-1"));
        WebElement option3 = driver.findElement(By.id("vfb-6-2"));

        //Check that none of the checkboxes are ticked
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        //Tick "Option 2"
        option2.click();

        //Check that Option 1 and Option 3 are not ticked, but Option 2 is ticked
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        // Tick "Option 3"
        option3.click();

        // Click result
        driver.findElement(By.id("result_button_checkbox")).click();

        // Check result text
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

        WebElement option1 = driver.findElement(By.id("vfb-7-1"));
        WebElement option2 = driver.findElement(By.id("vfb-7-2"));
        WebElement option3 = driver.findElement(By.id("vfb-7-3"));

        // Check that none of the radio buttons are selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        //Select "Option 3"
        option3.click();

        //Check that Option 1 and Option 2 are not selected, but Option 3 is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        //Select "Option 1"
        option1.click();

        //Check that Option 2 and Option 3 are not selected, but Option 1 is selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        //Click result
        driver.findElement(By.id("result_button_ratio")).click();

        //Check result text
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

        //Select "Option 3"
        select.selectByVisibleText("Option 3");

        //Check that selected option is "Option 3"
        assertEquals("Option 3", select.getFirstSelectedOption().getText());

        //Select "Option 2"
        select.selectByVisibleText("Option 2");

        //Check that selected option is "Option 2"
        assertEquals("Option 2", select.getFirstSelectedOption().getText());

        //Click result and text
        driver.findElement(By.id("result_button_select")).click();
        WebElement result = driver.findElement(By.id("result_select"));
        assertEquals("You selected option: Option 2", result.getText());
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
//          check that correct date is added
        WebElement dateInput = driver.findElement(By.id("vfb-8"));

        dateInput.clear();

        dateInput.sendKeys("05/02/1959");

        dateInput.sendKeys(org.openqa.selenium.Keys.ESCAPE);

        driver.findElement(By.id("result_button_date")).click();

        assertEquals("05/02/1959", dateInput.getAttribute("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
        WebElement dateInput = driver.findElement(By.id("vfb-8"));

        // Enters date 2 of May 1959 and clicks result
        dateInput.sendKeys("05/02/1959");
        dateInput.sendKeys(org.openqa.selenium.Keys.ESCAPE);
        driver.findElement(By.id("result_button_date")).click();

        // Checks that correct date is added
        assertEquals("05/02/1959", dateInput.getAttribute("value"));
    }
}
