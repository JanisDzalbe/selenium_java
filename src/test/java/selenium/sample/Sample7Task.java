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
    String base_url = "https://acctabootcamp.github.io/site/examples/actions";

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
//        check that none of the checkboxes are ticked
//        tick  "Option 2"
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//        tick  "Option 3"
//        click result
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed

        // Attempt 1

//        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
//
//        for (WebElement checkBox : checkBoxes) {
//            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
//        }
//        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
//        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
//        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
//        WebElement resultButton = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
//
//        option2.click();
//        assertFalse(option1.isSelected());
//        assertFalse(option3.isSelected());
//
//        option3.click();

        /* LECTURE CODE */
        assertFalse(driver.findElement(By.id("vfb-6-0")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-2")).isSelected());

        driver.findElement(By.id("vfb-6-1")).click();

        assertFalse(driver.findElement(By.id("vfb-6-0")).isSelected());
        assertTrue(driver.findElement(By.id("vfb-6-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-6-2")).isSelected());

        driver.findElement(By.id("vfb-6-2")).click();

        driver.findElement(By.id("result_button_checkbox")).click();

        assertTrue(driver.findElement(By.id("result_checkbox")).isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
//        select  "Option 3"
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//        select  "Option 1"
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//        click result
//        check that 'You selected option: Option 1' text is being displayed
        List<WebElement> radioList = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement radioButton : radioList) {
            assertFalse(radioButton.isSelected());
        }

        radioList.get(2).click();

        assertFalse(radioList.get(0).isSelected());
        assertFalse(radioList.get(1).isSelected());
        assertTrue(radioList.get(2).isSelected());

        radioList.get(0).click();

        assertTrue(radioList.get(0).isSelected());
        assertFalse(radioList.get(1).isSelected());
        assertFalse(radioList.get(2).isSelected());

        driver.findElement(By.id("result_button_ratio")).click();

        assertTrue(driver.findElement(By.id("result_radio")).isDisplayed());
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
        Select selectElement = new Select(driver.findElement(By.id("vfb-12")));

        selectElement.selectByVisibleText("Option 3");
        assertEquals("Option 3", selectElement.getFirstSelectedOption().getText());

        selectElement.selectByVisibleText("Option 2");
        assertEquals("Option 2", selectElement.getFirstSelectedOption().getText());

        driver.findElement(By.id("result_button_select")).click();

        assertTrue(driver.findElement(By.id("result_select")).isDisplayed());
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        String dateToEnter = "07/04/2007";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getAttribute("value"));

        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        while (true) {
            String currentMonthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
            if (currentMonthYear.contains("July") && currentMonthYear.contains("2007")) {
                break;
            }
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
        assertEquals(dateToEnter, dateBox.getAttribute("value"));

        dateBox.clear();
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
        String dateToEnter = "05/02/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getAttribute("value"));

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);
        assertEquals(dateToEnter, dateBox.getAttribute("value"));
    }
}
