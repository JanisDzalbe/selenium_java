package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        //  check that none of the checkboxes are ticked
        List<WebElement> checkBoxes = driver.findElements(
                By.cssSelector(".w3-check[type='checkbox']")
        );
        for (WebElement cb : checkBoxes) {
            assertFalse(cb.isSelected());
        }

        //  locate options
        WebElement option1 = driver.findElement(
                By.cssSelector(".w3-check[type='checkbox'][value='Option 1']")
        );
        WebElement option2 = driver.findElement(
                By.cssSelector(".w3-check[type='checkbox'][value='Option 2']")
        );
        WebElement option3 = driver.findElement(
                By.cssSelector(".w3-check[type='checkbox'][value='Option 3']")
        );

        //  tick  "Option 2"
        option2.click();

        //  check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        //  tick  "Option 3"
        option3.click();

        //  click result (Result button in Checkbox section)
        WebElement checkboxResultButton = driver.findElement(
                By.xpath("//h2[normalize-space()='Checkbox']/following::button[normalize-space()='Result'][1]")
        );
        checkboxResultButton.click();

        //  check that text 'You selected value(s): Option 2, Option 3' is being displayed
        WebElement checkboxResultText = driver.findElement(
                By.xpath("//*[text()='You selected value(s): Option 2, Option 3']")
        );
        assertTrue(checkboxResultText.isDisplayed());
    }


    @Test
    public void selectRadioButton() throws Exception {
        //  check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(
                By.cssSelector(".w3-check[type='radio']")
        );
        for (WebElement rb : radioButtons) {
            assertFalse(rb.isSelected());
        }

        WebElement option1 = driver.findElement(
                By.cssSelector(".w3-check[type='radio'][value='Option 1']")
        );
        WebElement option2 = driver.findElement(
                By.cssSelector(".w3-check[type='radio'][value='Option 2']")
        );
        WebElement option3 = driver.findElement(
                By.cssSelector(".w3-check[type='radio'][value='Option 3']")
        );

        //  select  "Option 3"
        option3.click();

        //  check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        //  select  "Option 1"
        option1.click();

        //  check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        //  click result (Result button in Radio section)
        WebElement radioResultButton = driver.findElement(
                By.xpath("//h2[normalize-space()='Radio']/following::button[normalize-space()='Result'][1]")
        );
        radioResultButton.click();

        //  check that 'You selected option: Option 1' text is being displayed
        WebElement radioResultText = driver.findElement(
                By.xpath("//*[text()='You selected option: Option 1']")
        );
        assertTrue(radioResultText.isDisplayed());
    }

    @Test
    public void selectOption() throws Exception {
        //  select "Option 3" in Select
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByVisibleText("Option 3");

        //  check that selected option is "Option 3"
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        //  select "Option 2" in Select
        dropdown.selectByVisibleText("Option 2");

        //  check that selected option is "Option 2"
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        //  click result (Result button in Select section)
        WebElement selectResultButton = driver.findElement(
                By.xpath("//h2[normalize-space()='Select']/following::button[normalize-space()='Result'][1]")
        );
        selectResultButton.click();

        //  check that 'You selected option: Option 2' text is being displayed
        WebElement selectResultText = driver.findElement(
                By.xpath("//*[text()='You selected option: Option 2']")
        );
        assertTrue(selectResultText.isDisplayed());
    }

    // ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        //  enter date '4 of July 2007' using calendar widget
        //  and check that correct date is added (format MM/dd/yyyy → 07/04/2007)

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        // target date
        Calendar target = Calendar.getInstance();
        target.set(2007, Calendar.JULY, 4);

        // current month/year shown in calendar (we assume it opens on "today")
        Calendar now = Calendar.getInstance();

        int monthsDiff =
                (now.get(Calendar.YEAR) - target.get(Calendar.YEAR)) * 12 +
                        (now.get(Calendar.MONTH) - target.get(Calendar.MONTH));

        // go back required number of months
        for (int i = 0; i < monthsDiff; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        // select day 4
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();

        String expected = new SimpleDateFormat("MM/dd/yyyy").format(target.getTime());
        assertEquals(expected, dateBox.getDomProperty("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        //  enter date '2 of May 1959' using text → 05/02/1959
        String dateToEnter = "05/02/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);

        //  check that correct date is added
        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}
