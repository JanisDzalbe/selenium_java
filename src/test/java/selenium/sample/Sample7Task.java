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
        for (int i = 0; i < checkBoxes.size(); i++) {
            assertFalse(checkBoxes.get(i).isSelected());
        }
//          tick  "Option 2"
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
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
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }




    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//          check that none of the radio are selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (int i = 0; i < radioButtons.size(); i++) {
            assertFalse(radioButtons.get(i).isSelected());
        }
//          select  "Option 3"
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
        option3.click();

//          check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

//          select  "Option 1"
        // select "Option 1"
        option1.click();

//          check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

//          click result
        driver.findElement(By.id("result_button_ratio")).click();

//          check that 'You selected option: Option 1' text is being displayed
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());

    }

    @Test
    public void selectOption() throws Exception {

//         TODO:
//          select "Option 3" in Select
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
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
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//          enter date '4 of July 2007' using calendar widget
// enter date '4 of July 2007' using calendar widget
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateBox);
        dateBox.click();

        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        while (!dateWidget.findElement(By.className("ui-datepicker-year")).getText().equals("2007")
                || !dateWidget.findElement(By.className("ui-datepicker-month")).getText().equals("July")) {
            WebElement prevButton = dateWidget.findElement(By.className("ui-datepicker-prev"));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", prevButton);
        }
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();

        // check that correct date is added
        assertEquals("07/04/2007", dateBox.getDomProperty("value"));

    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//          enter date '2 of May 1959' using text
//          check that correct date is added
    }
}
