package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import javax.sql.rowset.JdbcRowSet;
import java.util.List;
import java.util.Set;

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
        assertFalse(checkBoxes.get(0).isSelected());
        assertFalse(checkBoxes.get(1).isSelected());
        assertFalse(checkBoxes.get(2).isSelected());

        checkBoxes.get(1).click();
        assertFalse(checkBoxes.get(0).isSelected());
        assertTrue(checkBoxes.get(1).isSelected());
        assertFalse(checkBoxes.get(2).isSelected());

        driver.findElement(By.cssSelector("[type=\"checkbox\"][value=\"Option 3\"]")).click();
        driver.findElement(By.id("result_button_checkbox")).click();

        assertTrue(driver.findElement(By.id("result_checkbox")).isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());


//        for (WebElement checkBox : checkBoxes) {
//            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
//        }

//        assertFalse(checkBoxes.get(0).isSelected());
//        assertFalse(checkBoxes.get(1).isSelected());
//        assertFalse(checkBoxes.get(2).isSelected());

//        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
//        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
//        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
//        assertFalse(option1.isSelected());
//        option2.click();
//        assertTrue(option2.isSelected());
//        assertFalse(option3.isSelected());


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

        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));


        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio'"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio'"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio'"));

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

        driver.findElement(By.id("result_button_ratio")).click();

        WebElement radioResult = driver.findElement(By.id("result_radio"));
        assertTrue(radioResult.isDisplayed());
        assertEquals("You selected option: Option 1", radioResult.getText());



//        for (WebElement radioButton : radioButtons) {
//            assertFalse(radioButton.isSelected()); // radio are NOT selected
//        }

//        option3.click();
//        option1.click();
//        assertTrue(option1.isSelected());
//        assertFalse(option2.isSelected());
//        assertFalse(option3.isSelected());
//
//        WebElement option1Text = driver.findElement(By.id("result_checkbox"));
//
//        assertEquals("You selected value(s): Option 1", option1Text.getText());


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

        Select dropdown = new Select(driver.findElement(By.tagName("select")));

        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByValue("value2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        driver.findElement(By.id("result_button_select")).click();

        WebElement dropdownResult = driver.findElement(By.id("result_select"));
        assertTrue(dropdownResult.isDisplayed());
        assertEquals("You selected option: Option 2", dropdownResult.getText());


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
