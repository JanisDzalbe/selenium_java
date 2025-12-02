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

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("input.w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {        //loop where we check if its ticked or not
            assertFalse(checkBox.isSelected(), "A checkbox is ticked");
        }

        WebElement option2 = driver.findElement(By.id("vfb-6-1"));
        option2.click();

        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        WebElement option3 = driver.findElement(By.id("vfb-6-2"));
        assertFalse(option1.isSelected(), "Option 1 should not be selected");
        assertTrue(option2.isSelected(), "Option 2 should be selected");
        assertFalse(option3.isSelected(), "Option 3 should not be selected");

        option3.click();

        WebElement resultButton = driver.findElement(By.cssSelector("#result_button_checkbox"));
        resultButton.click();

        WebElement resultText = driver.findElement(By.id("result_checkbox"));
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
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input.w3-check[type='radio']"));

        for (WebElement radioButton : radioButtons) {           //check that radios aint ticked
            assertFalse(radioButton.isSelected(), "Radio button/s are ticked");
        }

        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
        option3.click();

        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));
        assertFalse(option1.isSelected(), "Option 1 should not be selected");
        assertFalse(option2.isSelected(), "Option 2 should not be selected");
        assertTrue(option3.isSelected(), "Option 3 should be selected");

        option1.click();

        assertFalse(option2.isSelected(), "Option 2 should not be selected");
        assertFalse(option3.isSelected(), "Option 3 should not be selected");
        assertTrue(option1.isSelected(), "Option 1 should be selected");

        WebElement resultButton = driver.findElement(By.cssSelector("#result_button_ratio"));;
        resultButton.click();

        WebElement resultText = driver.findElement(By.id("result_radio"));
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
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByVisibleText("Option 3");

        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("Option 2");

        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        WebElement resultButton = driver.findElement(By.cssSelector("#result_button_select"));
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
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
    }
}
