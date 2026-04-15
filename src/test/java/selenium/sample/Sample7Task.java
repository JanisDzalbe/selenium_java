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
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        WebElement resultButton = driver.findElement(By.id("result_button_checkbox"));
        String expectedResult = "You selected value(s): Option 2, Option 3";
        WebElement resultCheckbox = driver.findElement(By.id("result_checkbox"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        checkBoxes.get(1).click();

        assertFalse(checkBoxes.get(0).isSelected());
        assertTrue(checkBoxes.get(1).isSelected());
        assertFalse(checkBoxes.get(2).isSelected());

        checkBoxes.get(2).click();
        resultButton.click();

        assertTrue(resultCheckbox.isDisplayed());
        assertEquals(expectedResult, resultCheckbox.getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        WebElement resultButton = driver.findElement(By.id("result_button_ratio"));
        String expectedResult = "You selected option: Option 1";
        WebElement resultRadio = driver.findElement(By.id("result_radio"));

        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }

        radioButtons.get(2).click();

        assertFalse(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertTrue(radioButtons.get(2).isSelected());

        radioButtons.get(0).click();
        assertTrue(radioButtons.get(0).isSelected());
        assertFalse(radioButtons.get(1).isSelected());
        assertFalse(radioButtons.get(2).isSelected());

        resultButton.click();

        assertTrue(resultRadio.isDisplayed());
        assertEquals(expectedResult, resultRadio.getText());

    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        WebElement resultButton = driver.findElement(By.id("result_button_select"));
        String expectedResult = "You selected option: Option 2";
        WebElement resultSelect = driver.findElement(By.id("result_select"));


        dropdown.selectByValue("value3");
        assertTrue(dropdown.getFirstSelectedOption().isSelected());

        dropdown.selectByValue("value2");
        assertTrue(dropdown.getFirstSelectedOption().isSelected());

        resultButton.click();

        assertTrue(resultSelect.isDisplayed());
        assertEquals(expectedResult, resultSelect.getText());

    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        String targetDate = "07/04/2007";
        Calendar calTarget = Calendar.getInstance();
        calTarget.set(2007, Calendar.JULY, 4);

        Calendar calCurrent = Calendar.getInstance();
        int monthsBack = (calCurrent.get(Calendar.YEAR) - calTarget.get(Calendar.YEAR)) * 12
                + (calCurrent.get(Calendar.MONTH) - calTarget.get(Calendar.MONTH)); //

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        WebElement prevButton = dateWidget.findElement(By.className("ui-datepicker-prev"));

        for (int i = 0; i < monthsBack; i++) {
            prevButton.click();
            prevButton = dateWidget.findElement(By.className("ui-datepicker-prev"));
        }

        dateWidget.findElement(By.xpath("//a[text()='4']")).click();

        assertEquals(targetDate, dateBox.getDomProperty("value"));
        dateBox.clear();
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
