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
        //check that none of the checkboxes are ticked
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=\"checkbox\"]"));
        assertFalse(checkboxes.get(0).isSelected());
        assertFalse(checkboxes.get(1).isSelected());
        assertFalse(checkboxes.get(2).isSelected());
        //tick  "Option 2"
        checkboxes.get(1).click();
        assertFalse(checkboxes.get(0).isSelected());
        assertTrue(checkboxes.get(1).isSelected());
        assertFalse(checkboxes.get(2).isSelected());
        //check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        driver.findElement(By.cssSelector("[type=\"checkbox\"][value=\"Option 3\"]")).click();
        driver.findElement(By.id("result_button_checkbox")).click();

        assertTrue(driver.findElement(By.id("result_checkbox")).isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", driver.findElement(By.id("result_checkbox")).getText());
    }


    @Test
    public void selectRadioButton() throws Exception {
        //check that none of the radio are selected
        List<WebElement> radios = driver.findElements(By.cssSelector("[type='radio']"));
        assertFalse(radios.get(0).isSelected());
        assertFalse(radios.get(1).isSelected());
        assertFalse(radios.get(2).isSelected());
        //select  "Option 3"
        driver.findElement(By.cssSelector("[type='radio'][value='Option 3']")).click();
        //check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radios.get(0).isSelected());
        assertFalse(radios.get(1).isSelected());
        assertTrue(radios.get(2).isSelected());
        //select  "Option 1"
        driver.findElement(By.cssSelector("[type='radio'][value='Option 1']")).click();
        //check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertTrue(radios.get(0).isSelected());
        assertFalse(radios.get(1).isSelected());
        assertFalse(radios.get(2).isSelected());
        //click result
        driver.findElement(By.id("result_button_ratio")).click();
        //check that 'You selected option: Option 1' text is being displayed
        WebElement result = driver.findElement(By.id("result_radio"));
        assertTrue(result.isDisplayed());
        assertEquals("You selected option: Option 1", result.getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select selectbox = new Select(driver.findElement(By.id("vfb-12")));
        //select "Option 3" in Select
        selectbox.selectByVisibleText("Option 3");
        //check that selected option is "Option 3"
        assertEquals("Option 3", selectbox.getFirstSelectedOption().getText());
        //select "Option 2" in Select
        selectbox.selectByVisibleText("Option 2");
        //check that selected option is "Option 2"
        assertEquals("Option 2", selectbox.getFirstSelectedOption().getText());
        //click result
        driver.findElement(By.id("result_button_select")).click();
        //check that 'You selected option: Option 2' text is being displayed
        WebElement result = driver.findElement(By.id("result_select"));
        assertTrue(result.isDisplayed());
        assertEquals("You selected option: Option 2", result.getText());
    }

// ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));
        dateBox.click();
        String expectedDate = "07/04/2007";
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        //enter date '4 of July 2007' using calendar widget
        int Year = 2007;
        int Month = 7;
        while (true) {
            String headerText = dateWidget.findElement(By.className("ui-datepicker-title")).getText();
            if (headerText.equals("July 2007")) {
                break;
            }
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
            Thread.sleep(150);
        }
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
        assertEquals(expectedDate, dateBox.getDomProperty("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        //enter date '2 of May 1959' using text
        String expectedDate = "05/02/1959";
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));
        dateBox.clear();
        dateBox.sendKeys(expectedDate);
        //check that correct date is added
        assertEquals(expectedDate, dateBox.getDomProperty("value"));
    }
}
