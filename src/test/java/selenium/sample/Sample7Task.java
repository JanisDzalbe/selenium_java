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
        // Find Option 1 checkbox.
        WebElement option1 = driver.findElement(By.cssSelector("input[type='checkbox'][value='Option 1']"));

        // Find Option 2 checkbox.
        WebElement option2 = driver.findElement(By.cssSelector("input[type='checkbox'][value='Option 2']"));

        // Find Option 3 checkbox.
        WebElement option3 = driver.findElement(By.cssSelector("input[type='checkbox'][value='Option 3']"));


        // Check that none of the checkboxes are ticked.
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());


        // Tick Option 2.
        option2.click();


        // Check that Option 1 and Option 3 are not ticked, but Option 2 is ticked.
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());


        // Tick Option 3.
        option3.click();


        // Click Result.
        driver.findElement(By.xpath("//h2[text()='Checkbox']/following::button[text()='Result'][1]")).click();


        // Check that the correct result is displayed.
        assertTrue(driver.findElement(By.xpath("//*[contains(text(),'You selected value(s): Option 2, Option 3')]")).isDisplayed());
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


        // Find Option 1 radio button.
        WebElement option1 = driver.findElement(By.cssSelector("input[type='radio'][value='Option 1']"));

        // Find Option 2 radio button.
        WebElement option2 = driver.findElement(By.cssSelector("input[type='radio'][value='Option 2']"));

        // Find Option 3 radio button.
        WebElement option3 = driver.findElement(By.cssSelector("input[type='radio'][value='Option 3']"));


        // Check that none of the radio buttons are selected.
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        // Select Option 3
        option3.click();


        // Check that Option 1 and Option 2 are not selected, but Option 3 is selected.
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());


        // Select Option 1.
        option1.click();


        // Check that Option 2 and Option 3 are not selected, but Option 1 is selected.
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());


        // Click Result.
        driver.findElement(By.xpath("//h2[text()='Radio']/following::button[text()='Result'][1]")).click();


        // Check that the correct result is displayed.
        assertTrue(driver.findElement(By.xpath("//*[contains(text(),'You selected option: Option 1')]")).isDisplayed());;

        
        /*
          List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected()); // radio are NOT selected
            radioButton.click();
            assertTrue(radioButton.isSelected()); // radio are selected
        }

        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio'"));
        assertFalse(option2.isSelected());
        option2.click();
        assertTrue(option2.isSelected());
    }

         */
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
        // Find select dropdown option
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
        // Select Option 2 in Select.
        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        // Click Result button inside Select section.
        //*[@id='vfb-12'] — locate the Dropdown.
        // ancestor::div — move up to the parent block.
        // [1] — select the nearest matching block.
        //button — look for a button inside it.
       // driver.findElement(By.xpath("//*[@id='vfb-12']/ancestor::div[contains(@class,'w3-container')][1]//button")).click();

        // Click result in select
        driver.findElement(By.id("result_button_select")).click();
        // Check that the correct result is displayed.
        assertTrue(driver.findElement(By.xpath("//*[contains(text(),'You selected option: Option 2')]")).isDisplayed());


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
