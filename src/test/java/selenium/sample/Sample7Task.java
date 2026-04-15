package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

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
        WebElement opt1 = driver.findElement(By.xpath("//input[@type='checkbox' and @value='Option 1']"));
        WebElement opt2 = driver.findElement(By.xpath("//input[@type='checkbox' and @value='Option 2']"));
        WebElement opt3 = driver.findElement(By.xpath("//input[@type='checkbox' and @value='Option 3']"));
        WebElement resultButton = driver.findElement(By.id("result_button_checkbox"));
        WebElement resultText = driver.findElement(By.id("result_checkbox"));

        assertFalse(opt1.isSelected());
        assertFalse(opt2.isSelected());
        assertFalse(opt3.isSelected());

        opt2.click();
        assertFalse(opt1.isSelected());
        assertTrue(opt2.isSelected());
        assertFalse(opt3.isSelected());

        opt3.click();
        resultButton.click();
        assertEquals("You selected value(s): Option 2, Option 3", resultText.getText());
    }

    @Test
    public void selectRadioButton() throws Exception {
        WebElement opt1 = driver.findElement(By.xpath("//input[@type='radio' and @value='Option 1']"));
        WebElement opt2 = driver.findElement(By.xpath("//input[@type='radio' and @value='Option 2']"));
        WebElement opt3 = driver.findElement(By.xpath("//input[@type='radio' and @value='Option 3']"));
        WebElement resultButton = driver.findElement(By.id("result_button_ratio"));
        WebElement resultText = driver.findElement(By.id("result_radio"));

        assertFalse(opt1.isSelected());
        assertFalse(opt2.isSelected());
        assertFalse(opt3.isSelected());

        opt3.click();
        assertFalse(opt1.isSelected());
        assertFalse(opt2.isSelected());
        assertTrue(opt3.isSelected());

        opt1.click();
        assertTrue(opt1.isSelected());
        assertFalse(opt2.isSelected());
        assertFalse(opt3.isSelected());

        resultButton.click();
        assertEquals("You selected option: Option 1", resultText.getText());
    }

    @Test
    public void selectOption() throws Exception {
        WebElement selectElement = driver.findElement(By.tagName("select"));
        WebElement resultButton = driver.findElement(By.id("result_button_select"));
        WebElement resultText = driver.findElement(By.id("result_select"));

        Select select = new Select(selectElement);

        select.selectByVisibleText("Option 3");
        assertEquals("Option 3", select.getFirstSelectedOption().getText());

        select.selectByVisibleText("Option 2");
        assertEquals("Option 2", select.getFirstSelectedOption().getText());

        resultButton.click();
        assertEquals("You selected option: Option 2", resultText.getText());
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        // 4 July 2007
        String dateToEnter = "07/04/2007";
        String expectedResult = "You entered date: 07/04/2007";

        WebElement dateInput = driver.findElement(By.id("vfb-8"));
        dateInput.click();
        dateInput.sendKeys(dateToEnter);
        dateInput.sendKeys(Keys.ENTER);

        assertEquals(dateToEnter, dateInput.getAttribute("value"));

        driver.findElement(By.id("result_button_date")).click();

        WebElement resultText = driver.findElement(By.id("result_date"));
        assertEquals(expectedResult, resultText.getText());
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        // 2 May 1959
        String dateToEnter = "05/02/1959";
        String expectedResult = "You entered date: 05/02/1959";

        WebElement dateInput = driver.findElement(By.id("vfb-8"));
        dateInput.clear();
        dateInput.sendKeys(dateToEnter);
        dateInput.sendKeys(Keys.ENTER);

        assertEquals(dateToEnter, dateInput.getAttribute("value"));

        driver.findElement(By.id("result_button_date")).click();

        WebElement resultText = driver.findElement(By.id("result_date"));
        assertEquals(expectedResult, resultText.getText());
    }
}