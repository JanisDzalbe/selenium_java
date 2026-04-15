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
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement cb : checkBoxes) {
            assertFalse(cb.isSelected());
        }

//          tick  "Option 2"
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        option2.click();

//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));

        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

//          tick  "Option 3"
        option3.click();

//          click result
        driver.findElement(By.xpath("//h2[text()='Checkbox']/following::button[1]")).click();

//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        String result = driver.findElement(By.xpath("//h2[text()='Checkbox']/following::p[1]")).getText();
        assertEquals("You selected value(s): Option 2, Option 3", result);
    }


    @Test
    public void selectRadioButton() throws Exception {

    // check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement rb : radioButtons) {
            assertFalse(rb.isSelected());
        }

    // select "Option 3"
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
        option3.click();

    // check states
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

    // select "Option 1"
        option1.click();

    // check states again
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

    // click result
        driver.findElement(By.xpath("//h2[text()='Radio']/following::button[1]")).click();

    // check result text
        String result = driver.findElement(By.xpath("//h2[text()='Radio']/following::p[1]")).getText();
        assertEquals("You selected option: Option 1", result);
    }

    @Test
    public void selectOption() throws Exception {

    // create Select object
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));

    // select "Option 3"
        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

    // select "Option 2"
        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

    // click result
        driver.findElement(By.id("result_button_select")).click();

    // check result text
        String result = driver.findElement(By.id("result_select")).getText();
        assertEquals("You selected option: Option 2", result);
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {

    // expected result
        String expectedDate = "07/04/2007";

    // find input
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

    // open calendar
        dateBox.click();
        WebElement calendar = driver.findElement(By.id("ui-datepicker-div"));

    // go back until July 2007
        while (true) {
            String monthYear = calendar.findElement(By.className("ui-datepicker-title")).getText();

            if (monthYear.equals("July 2007")) {
                break;
            }

            calendar.findElement(By.className("ui-datepicker-prev")).click();
        }

    // select day 4
        calendar.findElement(By.xpath("//a[text()='4']")).click();

    // verify
        assertEquals(expectedDate, dateBox.getDomProperty("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {

    // expected date
        String dateToEnter = "05/02/1959";

    // find input
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

    // enter date
        dateBox.clear();
        dateBox.sendKeys(dateToEnter);

    // verify
        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}
