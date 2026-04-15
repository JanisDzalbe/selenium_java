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
//          tick  "Option 2"
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//          tick  "Option 3"
//          click result
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        WebElement option2 = driver.findElement(By.id("vfb-6-1"));
        WebElement option3 = driver.findElement(By.id("vfb-6-2"));
        WebElement result = driver.findElement(By.id("result_button_checkbox"));
        WebElement text = driver.findElement(By.id("result_checkbox"));
        List<WebElement> checkbox = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement cb : checkbox){
            assertFalse(cb.isSelected());
        }
        option2.click();
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());
        option3.click();
        result.click();
        assertEquals("You selected value(s): Option 2, Option 3", text.getText());
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
        WebElement result = driver.findElement(By.id("result_button_ratio"));
        WebElement text = driver.findElement(By.id("result_radio"));
        List<WebElement> ratio = driver.findElements(By.cssSelector("input[type='ratio']"));
        for (WebElement r : ratio){
            assertFalse(r.isSelected());
        }
        option3.click();
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());
        option1.click();
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());
        assertTrue(option1.isSelected());
        result.click();
        assertEquals("You selected option: Option 1", text.getText());
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
        WebElement result = driver.findElement(By.id("result_button_select"));
        WebElement text = driver.findElement(By.id("result_select"));
        select.selectByValue("value3");
        assertEquals("Option 3", select.getFirstSelectedOption().getText());
        select.selectByValue("value2");
        assertEquals("Option 2", select.getFirstSelectedOption().getText());
        result.click();
        assertEquals("You selected option: Option 2", text.getText());
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
//          check that correct date is added
        driver.findElement(By.id("vfb-8")).click();
        while (!driver.findElement(By.className("ui-datepicker-title")).getText().equals("July 2007")){
            driver.findElement(By.className("ui-datepicker-prev")).click();
        }
        driver.findElement(By.linkText("4")).click();
        driver.findElement(By.tagName("h2")).click();
        driver.findElement(By.id("result_button_date")).click();
        assertEquals("You entered date: 07/04/2007", driver.findElement(By.id("result_date")).getText());
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
            driver.findElement(By.id("vfb-8")).sendKeys("05/02/1959");
            driver.findElement(By.tagName("h2")).click();
            driver.findElement(By.id("result_button_date")).click();
            assertEquals("You entered date: 05/02/1959", driver.findElement(By.id("result_date")).getText());
    }
}
