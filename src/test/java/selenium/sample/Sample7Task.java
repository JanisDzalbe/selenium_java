package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
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
//         TODO:
//          check that none of the checkboxes are ticked
//          tick  "Option 2"
//          check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//          tick  "Option 3"
//          click result
//          check that text 'You selected value(s): Option 2, Option 3' is being displayed
        List<WebElement> checkBoxes =
                driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement cb : checkBoxes) {
            assertFalse(cb.isSelected());
        }

        WebElement option1 = driver.findElement(
                By.cssSelector(".w3-check[type='checkbox'][value='Option 1']"));
        WebElement option2 = driver.findElement(
                By.cssSelector(".w3-check[type='checkbox'][value='Option 2']"));
        WebElement option3 = driver.findElement(
                By.cssSelector(".w3-check[type='checkbox'][value='Option 3']"));

        option2.click();
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        option3.click();
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertTrue(option3.isSelected());

        WebElement checkboxCard = option1.findElement(
                By.xpath("./ancestor::div[contains(@class,'w3-container')][1]"));

        WebElement resultButton = checkboxCard.findElement(
                By.xpath(".//button[contains(text(),'Result')]"));
        resultButton.click();

        WebElement resultText = checkboxCard.findElement(By.id("result_checkbox"));
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

        List<WebElement> radios =
                driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        for (WebElement rb : radios) {
            assertFalse(rb.isSelected());
        }

        WebElement option1 = driver.findElement(
                By.cssSelector(".w3-check[type='radio'][value='Option 1']"));
        WebElement option2 = driver.findElement(
                By.cssSelector(".w3-check[type='radio'][value='Option 2']"));
        WebElement option3 = driver.findElement(
                By.cssSelector(".w3-check[type='radio'][value='Option 3']"));

        option3.click();
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        option1.click();
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        WebElement radioCard = option1.findElement(
                By.xpath("./ancestor::div[contains(@class,'w3-container')][1]"));

        WebElement resultButton = radioCard.findElement(
                By.xpath(".//button[contains(text(),'Result')]"));
        resultButton.click();

        WebElement resultText = radioCard.findElement(By.id("result_radio"));
        assertEquals("You selected option: Option 1", resultText.getText());
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
        WebElement selectElement = driver.findElement(By.id("vfb-12"));
        Select dropdown = new Select(selectElement);

        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        WebElement selectCard = selectElement.findElement(
                By.xpath("./ancestor::div[contains(@class,'w3-container')][1]"));

        WebElement resultButton = selectCard.findElement(
                By.xpath(".//button[contains(text(),'Result')]"));
        resultButton.click();

        WebElement resultText = selectCard.findElement(By.id("result_select"));
        assertEquals("You selected option: Option 2", resultText.getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
//          check that correct date is added
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.click();
        WebElement widget = driver.findElement(By.id("ui-datepicker-div"));

        String expected = "07/04/2007";

        int yearsBack =  Calendar.getInstance().get(Calendar.YEAR) - 2007;
        int monthsBack = yearsBack * 12 + (Calendar.getInstance().get(Calendar.MONTH) - 6); // July = 6 (0-based)

        for (int i = 0; i < monthsBack; i++) {
            widget.findElement(By.className("ui-datepicker-prev")).click();
        }

        widget.findElement(By.xpath("//a[text()='4']")).click();

        assertEquals(expected, dateBox.getDomProperty("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
        String dateToEnter = "05/02/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);

        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}
