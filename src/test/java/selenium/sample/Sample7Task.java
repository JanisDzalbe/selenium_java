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
//        TODO:
//            check that none of the checkboxes are ticked
//            tick  "Option 2"
//            check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//            tick  "Option 3"
//            click result
//            check that text 'You selected value(s): Option 2, Option 3' is being displayed
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[name=\"vfb-6[]\"]"));
        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }

        checkboxes.get(1).click();
        assertFalse(checkboxes.get(0).isSelected());
        assertTrue(checkboxes.get(1).isSelected());
        assertFalse(checkboxes.get(2).isSelected());

        checkboxes.get(2).click();
        driver.findElement(By.id("result_button_checkbox")).click();

        WebElement resultText = driver.findElement(By.id("result_checkbox"));
        assertTrue(resultText.isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", resultText.getText());




    }


    @Test
    public void selectRadioButton() throws Exception {
//        TODO:
//            check that none of the radio are selected
//            select  "Option 3"
//            check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//            select  "Option 1"
//            check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//            click result
//            check that 'You selected option: Option 1' text is being displayed
        WebElement radioOption1 = driver.findElement(By.xpath("//input[@type='radio' and @value='Option 1']"));
        WebElement radioOption2 = driver.findElement(By.xpath("//input[@type='radio' and @value='Option 2']"));
        WebElement radioOption3 = driver.findElement(By.xpath("//input[@type='radio' and @value='Option 3']"));

        assertFalse(radioOption1.isSelected());
        assertFalse(radioOption2.isSelected());
        assertFalse(radioOption3.isSelected());

        radioOption3.click();
        assertFalse(radioOption1.isSelected());
        assertFalse(radioOption2.isSelected());
        assertTrue(radioOption3.isSelected());

        radioOption1.click();
        assertTrue(radioOption1.isSelected());
        assertFalse(radioOption2.isSelected());
        assertFalse(radioOption3.isSelected());

        driver.findElement(By.id("result_button_ratio")).click();
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }
    @Test
    public void selectOption() throws Exception {
//        TODO:
//            select "Option 3" in Select
//            check that selected option is "Option 3"
//            select "Option 2" in Select
//            check that selected option is "Option 2"
//            click result
//            check that 'You selected option: Option 2' text is being displayed
        Select dropdown = new Select(driver.findElement(By.cssSelector("#vfb-12")));

        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        driver.findElement(By.id("result_button_select")).click();
        WebElement resultText = driver.findElement(By.id("result_select"));
        assertTrue(resultText.isDisplayed());
        assertEquals("You selected option: Option 2", resultText.getText());
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
