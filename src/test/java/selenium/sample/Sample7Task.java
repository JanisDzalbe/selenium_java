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

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get(base_url);
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void selectCheckBox() throws Exception {
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement cb : checkBoxes) {
            assertFalse(cb.isSelected(), "Checkbox should not be selected initially");
        }

        WebElement opt2 = driver.findElement(By.cssSelector("input[value='Option 2']"));
        opt2.click();

        assertTrue(opt2.isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[value='Option 1']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[value='Option 3']")).isSelected());

        driver.findElement(By.cssSelector("input[value='Option 3']")).click();
        driver.findElement(By.id("result_button_checkbox")).click();
        String resultText = driver.findElement(By.id("result_checkbox")).getText();
        assertEquals("You selected value(s): Option 2, Option 3", resultText);
    }

    @Test
    public void selectRadioButton() throws Exception {
        List<WebElement> radios = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        for (WebElement rb : radios) {
            assertFalse(rb.isSelected());
        }

        WebElement opt3 = driver.findElement(By.id("vfb-7-3"));
        opt3.click();
        assertTrue(opt3.isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-1")).isSelected());
        assertFalse(driver.findElement(By.id("vfb-7-2")).isSelected());

        WebElement opt1 = driver.findElement(By.id("vfb-7-1"));
        opt1.click();
        assertTrue(opt1.isSelected());
        assertFalse(opt3.isSelected());
        driver.findElement(By.id("result_button_ratio")).click();
        assertEquals("You selected option: Option 1", driver.findElement(By.id("result_radio")).getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByValue("value2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
        driver.findElement(By.id("result_button_select")).click();
        assertEquals("You selected option: Option 2", driver.findElement(By.id("result_select")).getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click();

        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        while (!driver.findElement(By.className("ui-datepicker-year")).getText().equals("2007")) {
            driver.findElement(By.className("ui-datepicker-prev")).click();
        }
        while (!driver.findElement(By.className("ui-datepicker-month")).getText().equals("July")) {
            driver.findElement(By.className("ui-datepicker-prev")).click();
        }

        driver.findElement(By.xpath("//a[text()='4']")).click();
        assertEquals("07/04/2007", dateBox.getDomProperty("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        String targetDate = "05/02/1959";
        WebElement dateBox = driver.findElement(By.id("vfb-8"));

        dateBox.clear();
        dateBox.sendKeys(targetDate);

        assertEquals(targetDate, dateBox.getDomProperty("value"));
    }
}