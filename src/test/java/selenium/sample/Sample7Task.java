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
        // TODO Check that none of the checkboxes are ticked
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        WebElement option1 = driver.findElement(By.id("vfb-6-0"));
        WebElement option2 = driver.findElement(By.id("vfb-6-1"));
        WebElement option3 = driver.findElement(By.id("vfb-6-2"));

        // tick "Option 2"
        option2.click();

        // check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        // tick "Option 3"
        option3.click();
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertTrue(option3.isSelected());

        // click result
        driver.findElement(By.id("result_button_checkbox")).click();

        // check that text 'You selected value(s): Option 2, Option 3' is being displayed
        String result = driver.findElement(By.id("result_checkbox")).getText();
        assertEquals("You selected value(s): Option 2, Option 3", result);
    }

    @Test
    public void selectRadioButton() throws Exception {
        // TODO:
        // check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }

        WebElement option1 = driver.findElement(By.id("vfb-7-1"));
        WebElement option2 = driver.findElement(By.id("vfb-7-2"));
        WebElement option3 = driver.findElement(By.id("vfb-7-3"));

        // select "Option 3"
        option3.click();

        // check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        // select "Option 1"
        option1.click();

        // check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());
        assertTrue(option1.isSelected());

        // click result
        driver.findElement(By.id("result_button_ratio")).click();

        // check that 'You selected option: Option 1' text is being displayed
        String result = driver.findElement(By.id("result_radio")).getText();
        assertEquals("You selected option: Option 1", result);
    }

    @Test
    public void selectOption() throws Exception {
        // TODO:
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));

        // select "Option 3" in Select
        dropdown.selectByVisibleText("Option 3");

        // check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        // select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");

        // check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        // click result
        // Нажимаем кнопку результата
        driver.findElement(By.id("result_button_select")).click();

        // check that 'You selected option: Option 2' text is being displayed
        String result = driver.findElement(By.id("result_select")).getText();
        assertEquals("You selected option: Option 2", result);
    }

    // ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        // TODO Enter date '4 of July 2007' using calendar widget
        WebElement dateInput = driver.findElement(By.id("vfb-8"));
        dateInput.click();

        while (true) {
            String monthText = driver.findElement(By.className("ui-datepicker-month")).getText();
            String yearText = driver.findElement(By.className("ui-datepicker-year")).getText();

            if ("July".equals(monthText) && "2007".equals(yearText)) {
                break;
            }
            driver.findElement(By.cssSelector(".ui-datepicker-prev")).click();
        }

        List<WebElement> days = driver.findElements(By.cssSelector("#ui-datepicker-div td[data-handler='selectDay'] a"));
        for (WebElement day : days) {
            if ("4".equals(day.getText().trim())) {
                day.click();
                break;
            }
        }

        // check that correct date is added
        assertEquals("07/04/2007", dateInput.getDomProperty("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        // TODO Enter date '2 of May 1959' using text
        String expectedDate = "05/02/1959";
        WebElement dateBox = driver.findElement(By.id("vfb-8"));

        assertEquals("", dateBox.getDomProperty("value"));
        dateBox.clear();
        dateBox.sendKeys(expectedDate);

        // check that correct date is added
        assertEquals(expectedDate, dateBox.getDomProperty("value"));
    }
}