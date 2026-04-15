package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.html.HTMLInputElement;
import selenium.utility.BootcampUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
        WebElement option1 = driver.findElement((By.cssSelector(".w3-check[value='Option 1'][type='checkbox']")));
        WebElement option2 = driver.findElement((By.cssSelector(".w3-check[value='Option 2'][type='checkbox']")));
        WebElement option3 = driver.findElement((By.cssSelector(".w3-check[value='Option 3'][type='checkbox']")));
        List<WebElement> checkBoxes = Arrays.asList(option1, option2, option3);
        WebElement resultButton = driver.findElement((By.id("result_button_checkbox")));
        WebElement resultText = driver.findElement((By.id("result_checkbox")));
//          check that none of the checkboxes are ticked
        for(WebElement cb : checkBoxes){
            assertFalse(cb.isSelected());
        }
//          tick  "Option 2"
        option2.click();
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(option1.isSelected() && option3.isSelected());
        assertTrue(option2.isSelected());
//          tick  "Option 3"
        option3.click();
//          click result
        resultButton.click();
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed

//      filter the list of checkBoxes so that only the selected checkBoxes remain
        Stream<WebElement> selectedCheckBoxes = checkBoxes.stream().filter(c -> c.isSelected());
//      map each checkbox to it's `value` attribute
        List<String> checkBoxStrings = selectedCheckBoxes.map(c -> c.getDomProperty("value")).toList();
//      create the expected string like on the website
        assertEquals("You selected value(s): "+ String.join(", ", checkBoxStrings), resultText.getText());
    }

    @Test
    public void selectRadioButton() throws Exception {
        WebElement option1 = driver.findElement((By.cssSelector(".w3-check[value='Option 1'][type='radio']")));
        WebElement option2 = driver.findElement((By.cssSelector(".w3-check[value='Option 2'][type='radio']")));
        WebElement option3 = driver.findElement((By.cssSelector(".w3-check[value='Option 3'][type='radio']")));
        List<WebElement> radioButtons = Arrays.asList(option1, option2, option3);
        WebElement resultButton = driver.findElement((By.id("result_button_radio")));
        WebElement resultText = driver.findElement((By.id("result_radio")));
//          check that none of the radio are selected
        for(WebElement rb : radioButtons){
            assertFalse(rb.isSelected());
        }
//          select  "Option 3"
        option3.click();
//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(option1.isSelected() && option2.isSelected());
        assertTrue(option3.isSelected());
//          select  "Option 1"
        option1.click();
//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(option2.isSelected() && option3.isSelected());
        assertTrue(option1.isSelected());
//          click result
        resultButton.click();
//          check that 'You selected option: Option 1' text is being displayed
//      filter radioButtons so that the selected one remains and save it to a variable
        WebElement selectedOption = radioButtons.stream().filter(c -> c.isSelected()).toList().getFirst();
//      create the expected string like on the website
        assertEquals("You selected value(s): "+ selectedOption.getDomProperty("value"), resultText.getText());

    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        WebElement resultButton = driver.findElement(By.id("result_button_select"));
        WebElement resultText = driver.findElement(By.id("result_select"));
//          select "Option 3" in Select
        dropdown.selectByVisibleText("Option 3");
//          check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//          select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");
//          check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//          click result
        resultButton.click();
//          check that 'You selected option: Option 2' text is being displayed
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
