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
import java.util.Objects;

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
//          check that none of the checkboxes are ticked
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }
//          tick  "Option 2"
        WebElement option2 = driver.findElement(By.cssSelector("[value='Option 2'][type='checkbox']"));
        option2.click();
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        for (WebElement checkBox : checkBoxes) {
            if(Objects.equals(checkBox.getDomAttribute("id"), option2.getDomAttribute("id"))){
                assertTrue(checkBox.isSelected());
                continue;
            }
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }
//          tick  "Option 3"
        driver.findElement(By.cssSelector("[value='Option 3'][type='checkbox']")).click();
//          click result
        driver.findElement(By.cssSelector("#result_button_checkbox")).click();
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.cssSelector("#result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//          check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("[type='radio']"));
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected()); // radio are NOT selected
        }
//          select  "Option 3"
        radioButtons.get(2).click();
//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertTrue(radioButtons.get(2).isSelected());
//          select  "Option 1"
        radioButtons.get(0).click();
//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertFalse(radioButtons.get(2).isSelected());
//          click result
        driver.findElement(By.id("result_button_ratio")).click();
//          check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.cssSelector("#result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
//          select "Option 3" in Select
        dropdown.selectByVisibleText("Option 3");
//          check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//          select "Option 2" in Select
        dropdown.selectByValue("value2");
//          check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//          click result
        driver.findElement(By.id("result_button_select")).click();
//          check that 'You selected option: Option 2' text is being displayed
        assertEquals("You selected option: Option 2", driver.findElement(By.cssSelector("#result_select")).getText());
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
//          enter date '2 of May 1959' using text
        String dateToEnter = "05/02/1959";
//          check that correct date is added
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));
        dateBox.clear();
        dateBox.sendKeys(dateToEnter);
        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}
