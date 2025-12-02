package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.Select;
public class Sample7Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeDriver();

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

        WebElement option1 = driver.findElement(
                By.xpath("//h2[.='Checkbox']/following::input[@type='checkbox'][1]"));
        WebElement option2 = driver.findElement(
                By.xpath("//h2[.='Checkbox']/following::input[@type='checkbox'][2]"));
        WebElement option3 = driver.findElement(
                By.xpath("//h2[.='Checkbox']/following::input[@type='checkbox'][3]"));

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        option2.click();

        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        option3.click();

        WebElement resultButton = driver.findElement(
                By.xpath("//h2[.='Checkbox']/following::button[contains(.,'Result')][1]"));
        resultButton.click();

        WebElement resultText = driver.findElement(
                By.xpath("//h2[.='Checkbox']/following::*[contains(text(),'You selected value(s):')][1]"));
        assertEquals("You selected value(s): Option 2, Option 3", resultText.getText());
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
        WebElement option1 = driver.findElement(
                By.xpath("//h2[.='Radio']/following::input[@type='radio'][1]"));
        WebElement option2 = driver.findElement(
                By.xpath("//h2[.='Radio']/following::input[@type='radio'][2]"));
        WebElement option3 = driver.findElement(
                By.xpath("//h2[.='Radio']/following::input[@type='radio'][3]"));

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        option3.click();

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        option1.click();

        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        WebElement resultButton = driver.findElement(
                By.xpath("//h2[.='Radio']/following::button[contains(.,'Result')][1]"));
        resultButton.click();

        WebElement resultText = driver.findElement(
                By.xpath("//h2[.='Radio']/following::*[contains(text(),'You selected option:')][1]"));
        assertEquals("You selected option: Option 1", resultText.getText());
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
        // сам <select> под заголовком "Select"
        WebElement selectElement = driver.findElement(By.id("vfb-12"));
        Select select = new Select(selectElement);

        select.selectByVisibleText("Option 3");

        assertEquals("Option 3", select.getFirstSelectedOption().getText());

        select.selectByVisibleText("Option 2");

        assertEquals("Option 2", select.getFirstSelectedOption().getText());

        WebElement resultButton = driver.findElement(By.id("result_button_select"));
        resultButton.click();

        WebElement resultText = driver.findElement(By.id("result_select"));
        assertEquals("You selected option: Option 2", resultText.getText());

    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
//          check that correct date is added
        WebElement dateInput = driver.findElement(
                By.xpath("//h2[.='Date']/following::input[1]"));

        String dateValue = "20070704";
        dateInput.click();
        dateInput.clear();
        dateInput.sendKeys(dateValue);

        assertEquals(dateValue, dateInput.getAttribute("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
        WebElement dateInput = driver.findElement(
                By.xpath("//h2[.='Date']/following::input[1]"));

        String dateValue = "19590502";
        dateInput.clear();
        dateInput.sendKeys(dateValue);

        assertEquals(dateValue, dateInput.getAttribute("value"));
    }
}
