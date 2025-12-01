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

public class Sample7 {
    WebDriver driver;
    String base_url = "https://janisdzalbe.github.io/example-site/examples/actions";

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeDriver();

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

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
            checkBox.click();
            assertTrue(checkBox.isSelected()); // checkboxes are selected
            checkBox.click();
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }

        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        assertFalse(option3.isSelected());
        option3.click();
        assertTrue(option3.isSelected());
    }


    @Test
    public void selectRadioButton() throws Exception {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected()); // radio are NOT selected
            radioButton.click();
            assertTrue(radioButton.isSelected()); // radio are selected
        }

        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio'"));
        assertFalse(option2.isSelected());
        option2.click();
        assertTrue(option2.isSelected());
    }

    @Test
    public void selectOptionByText() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
    }

    @Test
    public void selectOptionByIndex() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(1);
        assertEquals("Option 1", dropdown.getFirstSelectedOption().getText());
    }

    @Test
    public void selectOptionByValue() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByValue("value3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
    }

    @Test
    public void chooseDateViaCalendar() throws Exception {
        // get today date
        Calendar cal = Calendar.getInstance();
        // go back 10 month
        cal.add(Calendar.MONTH, -10);
        String result = new SimpleDateFormat("MM/15/yyyy").format(cal.getTime());

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        // go back 10 month in calendar on page
        for (int i = 0; i < 10; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }
        // select date 15
        dateWidget.findElement(By.xpath("//a[text()='15']")).click();

        assertEquals(result, dateBox.getDomProperty("value"));
        dateBox.clear();
    }

    @Test
    public void chooseDateViaTextBox() throws Exception {
        String dateToEnter = "12/15/2014";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);
        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}
