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
        List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }

        WebElement checkboxOption1 = driver.findElement(By.xpath("//*[@type='checkbox' and @value='Option 1']"));
        WebElement checkboxOption2 = driver.findElement(By.xpath("//*[@type='checkbox' and @value='Option 2']"));
        WebElement checkboxOption3 = driver.findElement(By.xpath("//*[@type='checkbox' and @value='Option 3']"));
        WebElement resultButton = driver.findElement(By.id("result_button_checkbox"));
        WebElement textElement = driver.findElement(By.id("result_checkbox"));

        checkboxOption2.click();
        assertFalse(checkboxOption1.isSelected());
        assertFalse(checkboxOption3.isSelected());
        assertTrue(checkboxOption2.isSelected());

        checkboxOption3.click();
        resultButton.click();
        assertEquals("You selected value(s): Option 2, Option 3", textElement.getText());

//         TODO:
//          check that none of the checkboxes are ticked
//          tick  "Option 2"
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//          tick  "Option 3"
//          click result
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
    }


    @Test
    public void selectRadioButton() throws Exception {
        WebElement option1 = driver.findElement(By.xpath("//*[@type='radio' and @value='Option 1']"));
        WebElement option2 = driver.findElement(By.xpath("//*[@type='radio' and @value='Option 2']"));
        WebElement option3 = driver.findElement(By.xpath("//*[@type='radio' and @value='Option 3']"));
        WebElement button = driver.findElement(By.id("result_button_ratio"));
        WebElement text = driver.findElement(By.id("result_radio"));

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

        button.click();
        assertEquals("You selected option: Option 1", text.getText());

//         TODO:
//          check that none of the radio are selected
//          select  "Option 3"
//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//          select  "Option 1"
//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//          click result
//          check that 'You selected option: Option 1' text is being displayed
    }

    @Test
    public void selectOption() throws Exception {
        WebElement option1 = driver.findElement(By.xpath("//option[@value='value1']"));
        WebElement option2 = driver.findElement(By.xpath("//option[@value='value2']"));
        WebElement option3 = driver.findElement(By.xpath("//option[@value='value3']"));
        WebElement button = driver.findElement(By.id("result_button_select"));
        WebElement text = driver.findElement(By.id("result_select"));
        Select select = new Select(driver.findElement(By.xpath("//select[@name='vfb-12']")));

        option3.click();
        assertEquals("Option 3", select.getFirstSelectedOption().getText());

        option2.click();
        assertEquals("Option 2", select.getFirstSelectedOption().getText());

        button.click();
        assertEquals("You selected option: Option 2", text.getText());

//         TODO:
//          select "Option 3" in Select
//          check that selected option is "Option 3"
//          select "Option 2" in Select
//          check that selected option is "Option 2"
//          click result
//          check that 'You selected option: Option 2' text is being displayed
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
