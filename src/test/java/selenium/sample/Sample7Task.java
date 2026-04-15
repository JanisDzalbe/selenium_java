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

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
            checkBox.click();
            assertTrue(checkBox.isSelected()); // checkboxes are selected
            checkBox.click();
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        assertFalse(option2.isSelected());
        option2.click();
        assertTrue(option2.isSelected());


        WebElement option1 = driver.findElement(By.cssSelector("input[value='Option 1']"));
        WebElement Option2 = driver.findElement(By.cssSelector("input[value='Option 2']"));
        WebElement option3 = driver.findElement(By.cssSelector("input[value='Option 3']"));
        assertFalse(option1.isSelected());
        assertTrue(Option2.isSelected());
        assertFalse(option3.isSelected());
        option3.click();
        assertTrue(option3.isSelected());

        WebElement resultBtn = driver.findElement(By.id("result_button_checkbox"));
        resultBtn.click();

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



        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));

        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
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

        assertEquals("You selected option: Option 1",
                driver.findElement(By.id("result_radio")).getText());
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

        select.selectByVisibleText("Option 3");
        assertEquals("Option 3", select.getFirstSelectedOption().getText());

        select.selectByVisibleText("Option 2");
        assertEquals("Option 2", select.getFirstSelectedOption().getText());

        driver.findElement(By.id("result_button_select")).click();

        assertEquals("You selected option: Option 2",
                driver.findElement(By.id("result_select")).getText());
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
