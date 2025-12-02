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
        WebElement option1 = driver.findElement(By.xpath("//input[@id='vfb-6-0' and @value='Option 1']"));
        WebElement option2 = driver.findElement(By.xpath("//input[@id='vfb-6-1' and @value='Option 2']"));
        WebElement option3 = driver.findElement(By.xpath("//input[@id='vfb-6-2' and @value='Option 3']"));

//         TODO:
//          check that none of the checkboxes are ticked
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());
//          tick  "Option 2"
       option2.click();
       Thread.sleep(10000);
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());
//          tick  "Option 3"
        option3.click();
//          click result
       driver.findElement(By.id("result_button_checkbox")).click();
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());

    }

    @Test
    public void selectRadioButton() throws Exception {
        WebElement option1 = driver.findElement(By.xpath("//input[@id='vfb-7-1' and @value='Option 1']"));
        WebElement option2 = driver.findElement(By.xpath("//input[@id='vfb-7-2' and @value='Option 2']"));
        WebElement option3 = driver.findElement(By.xpath("//input[@id='vfb-7-3' and @value='Option 3']"));

//         TODO:
//          check that none of the radio are selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());
//          select  "Option 3"
        option3.click();
//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());
//          select  "Option 1"
        option1.click();
//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());
//          click result
        driver.findElement(By.id("result_button_ratio")).click();
//          check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropDown = new Select(driver.findElement(By.tagName("select")));
//         TODO:
//          select "Option 3" in Select
        dropDown.selectByVisibleText("Option 3");
//          check that selected option is "Option 3"
        assertEquals("Option 3", dropDown.getFirstSelectedOption().getText());
//          select "Option 2" in Select
        dropDown.selectByVisibleText("Option 2");
//          check that selected option is "Option 2"
        assertEquals("Option 2", dropDown.getFirstSelectedOption().getText());
//          click result
        driver.findElement(By.id("result_button_select")).click();
//          check that 'You selected option: Option 2' text is being displayed
        WebElement dropDownResult = driver.findElement(By.id("result_select"));
        assertTrue(dropDownResult.isDisplayed());
        assertEquals("You selected option: Option 2", dropDownResult.getText());
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
//          check that correct date is added
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
    }
}
