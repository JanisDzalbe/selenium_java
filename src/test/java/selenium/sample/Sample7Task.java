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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Activity 7: Practicing checkboxes, radio buttons, Select dropdowns,
 * and date selection (calendar widget and text input).
 */
public class Sample7Task {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";

    /**
     * Initializes ChromeDriver and opens the Actions example page before each test.
     *
     * @throws Exception if driver initialization fails
     */
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // open page:
        driver.get(base_url);
    }

    /**
     * Closes the browser after each test.
     *
     * @throws Exception if browser teardown fails
     */
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    /**
     * Verifies checkbox selection states, clicks Result, and checks the displayed output text.
     *
     * @throws Exception if any checkbox or result element cannot be found
     */
    @Test
    public void selectCheckBox() throws Exception {
//         TODO:
//          check that none of the checkboxes are ticked
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        WebElement option2 = driver.findElement(By.id("vfb-6-1"));
        WebElement option3 = driver.findElement(By.id("vfb-6-2"));

//          tick  "Option 2"
        option2.click();

//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

//          tick  "Option 3"
        option3.click();

//          click result
        driver.findElement(By.id("result_button_checkbox")).click();

//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement resultText = driver.findElement(By.id("result_checkbox"));
        assertTrue(resultText.isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", resultText.getText());
    }

    /**
     * Verifies radio button exclusivity, clicks Result, and checks the displayed output text.
     *
     * @throws Exception if any radio button or result element cannot be found
     */
    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//          check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }

        WebElement option1 = driver.findElement(By.id("vfb-7-1"));
        WebElement option2 = driver.findElement(By.id("vfb-7-2"));
        WebElement option3 = driver.findElement(By.id("vfb-7-3"));

//          select  "Option 3"
        option3.click();

//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

//          select  "Option 1"
        option1.click();

//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

//          click result
        driver.findElement(By.id("result_button_ratio")).click();

//          check that 'You selected option: Option 1' text is being displayed
        WebElement resultText = driver.findElement(By.id("result_radio"));
        assertTrue(resultText.isDisplayed());
        assertEquals("You selected option: Option 1", resultText.getText());
    }

    /**
     * Selects options from the dropdown using Selenium's Select class, clicks Result,
     * and checks the displayed output text.
     *
     * @throws Exception if the dropdown or result element cannot be found
     */
    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));

//         TODO:
//          select "Option 3" in Select
        dropdown.selectByVisibleText("Option 3");

//          check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

//          select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");

//          check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

//          click result
        driver.findElement(By.id("result_button_select")).click();

//          check that 'You selected option: Option 2' text is being displayed
        WebElement resultText = driver.findElement(By.id("result_select"));
        assertTrue(resultText.isDisplayed());
        assertEquals("You selected option: Option 2", resultText.getText());
    }

    // ** Bonus tasks **

    /**
     * Navigates the jQuery calendar widget back to July 2007, picks day 4,
     * and verifies the formatted date in the input box.
     *
     * @throws Exception if the date box or calendar widget elements cannot be found
     */
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        while (true) {
            String displayedMonth = dateWidget.findElement(By.className("ui-datepicker-month")).getText();
            String displayedYear = dateWidget.findElement(By.className("ui-datepicker-year")).getText();
            if ("July".equals(displayedMonth) && "2007".equals(displayedYear)) {
                break;
            }
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        dateWidget.findElement(By.xpath(".//a[text()='4']")).click();

//          check that correct date is added
        assertEquals("07/04/2007", dateBox.getDomProperty("value"));
    }

    /**
     * Enters May 2, 1959 directly into the date input box and verifies the entered value.
     *
     * @throws Exception if the date input box cannot be found
     */
    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
        String dateToEnter = "05/02/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);

//          check that correct date is added
        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}