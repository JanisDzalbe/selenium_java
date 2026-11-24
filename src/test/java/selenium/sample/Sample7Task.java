
package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

public class Sample7Task {

    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";

    // Method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {

        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // Open page
        driver.get(base_url);
    }

    // Method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void selectCheckBox() throws Exception {

        // Find all checkboxes
        WebElement option1 = driver.findElement(
                By.cssSelector("input[type='checkbox'][value='Option 1']"));

        WebElement option2 = driver.findElement(
                By.cssSelector("input[type='checkbox'][value='Option 2']"));

        WebElement option3 = driver.findElement(
                By.cssSelector("input[type='checkbox'][value='Option 3']"));

        // Check that none of the checkboxes are selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        // Select Option 2
        option2.click();

        // Verify checkbox selection
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        // Select Option 3
        option3.click();

        // Click Result button in Checkbox section
        driver.findElement(By.xpath(
                "//h2[normalize-space()='Checkbox']/following::button[normalize-space()='Result'][1]"
        )).click();

        // Verify result message
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("You selected value(s): Option 2, Option 3"));
    }

    @Test
    public void selectRadioButton() throws Exception {

        // Find all radio buttons
        WebElement option1 = driver.findElement(
                By.cssSelector("input[type='radio'][value='Option 1']"));

        WebElement option2 = driver.findElement(
                By.cssSelector("input[type='radio'][value='Option 2']"));

        WebElement option3 = driver.findElement(
                By.cssSelector("input[type='radio'][value='Option 3']"));

        // Check that none of the radio buttons are selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        // Select Option 3
        option3.click();

        // Verify Option 3 is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        // Select Option 1
        option1.click();

        // Verify Option 1 is selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        // Click Result button in Radio section
        driver.findElement(By.xpath(
                "//h2[normalize-space()='Radio']/following::button[normalize-space()='Result'][1]"
        )).click();

        // Verify result message
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("You selected option: Option 1"));
    }

    @Test
    public void selectOption() throws Exception {

        // Find dropdown
        Select dropdown = new Select(
                driver.findElement(By.id("vfb-12")));

        // Verify default selected option
        assertEquals("Choose your option",
                dropdown.getFirstSelectedOption().getText());

        // Select Option 3
        dropdown.selectByVisibleText("Option 3");

        // Verify Option 3 is selected
        assertEquals("Option 3",
                dropdown.getFirstSelectedOption().getText());

        // Select Option 2
        dropdown.selectByVisibleText("Option 2");

        // Verify Option 2 is selected
        assertEquals("Option 2",
                dropdown.getFirstSelectedOption().getText());

        // Click Result button in Select section
        driver.findElement(By.xpath(
                "//h2[normalize-space()='Select']/following::button[normalize-space()='Result'][1]"
        )).click();

        // Verify result message
        assertTrue(driver.findElement(By.tagName("body"))
                .getText().contains("You selected option: Option 2"));
    }

    // Bonus task 1: Choose date using calendar

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {

        // Find date input
        WebElement dateBox = driver.findElement(By.id("vfb-8"));

        // Check that date field is empty
        assertEquals("", dateBox.getDomProperty("value"));

        // Open calendar
        dateBox.click();

        // Find calendar widget
        WebElement dateWidget = driver.findElement(
                By.id("ui-datepicker-div"));

        // Navigate backwards until July 2007
        int attempts = 0;

        while (!dateWidget.findElement(
                        By.className("ui-datepicker-title"))
                .getText().equals("July 2007")) {

            dateWidget.findElement(
                    By.className("ui-datepicker-prev")).click();

            attempts++;

            assertTrue(attempts <= 300,
                    "Could not navigate to July 2007");
        }

        // Select day 4
        dateWidget.findElement(
                By.xpath(".//a[normalize-space()='4']")).click();

        // Verify selected date
        assertEquals("07/04/2007",
                dateBox.getDomProperty("value"));
    }

    // Bonus task 2: Choose date using text

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {

        // Find date input
        WebElement dateBox = driver.findElement(By.id("vfb-8"));

        // Check that date field is empty
        assertEquals("", dateBox.getDomProperty("value"));

        // Enter date: 2 May 1959
        String dateToEnter = "05/02/1959";

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);

        // Verify entered date
        assertEquals(dateToEnter,
                dateBox.getDomProperty("value"));
    }
}
