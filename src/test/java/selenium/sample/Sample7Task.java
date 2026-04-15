package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;
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

    // Get result text after clicking Result button, waiting up to 5 seconds
    private String getResultText(String section) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement resultDiv = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("result_" + section))
            );
            return resultDiv.getText().trim();
        } catch (Exception e1) {
            try {
                return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//*[contains(@id,'result') and contains(text(),'You selected')]")
                        )
                ).getText().trim();
            } catch (Exception e2) {
                return driver.findElement(
                        By.xpath("//*[contains(@class,'w3-pale') or contains(@class,'result')]")
                ).getText().trim();
            }
        }
    }

    @Test
    public void selectCheckBox() throws Exception {
        // Get all checkboxes
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        // Check that none of the checkboxes are ticked
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        // Locate individual checkboxes by value
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));

        // Tick "Option 2"
        option2.click();

        // Check Option 1 and Option 3 are NOT ticked, but Option 2 IS ticked
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        // Tick "Option 3"
        option3.click();

        // Click the Result button
        driver.findElement(By.id("result_button_checkbox")).click();

        // Check result text
        String result = getResultText("checkbox");
        assertEquals("You selected value(s): Option 2, Option 3", result);
    }

    @Test
    public void selectRadioButton() throws Exception {
        // Get all radio buttons
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        // Check that none of the radio buttons are selected
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
        }

        // Locate individual radio buttons by value
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));

        // Select "Option 3"
        option3.click();

        // Check Option 1 and Option 2 are NOT selected, Option 3 IS selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        // Select "Option 1"
        option1.click();

        // Check Option 2 and Option 3 are NOT selected, Option 1 IS selected
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());
        assertTrue(option1.isSelected());

        // Click the Result button - NOTE: page has a typo "ratio" instead of "radio"
        driver.findElement(By.id("result_button_ratio")).click();

        // Check result text
        String result = getResultText("ratio");
        assertEquals("You selected option: Option 1", result);
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));

        // Select "Option 3" and verify
        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        // Select "Option 2" and verify
        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        // Click the Result button - please verify the exact id on the page
        driver.findElement(By.id("result_button_select")).click();

        // Check result text
        String result = getResultText("select");
        assertEquals("You selected option: Option 2", result);
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        // Target date: July 4, 2007 → "07/04/2007"
        String expectedDate = "07/04/2007";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        // Open the calendar widget
        dateBox.click();
        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        // Navigate from April 2026 back to July 2007 = 225 months
        int monthsBack = (2026 - 2007) * 12 + (4 - 7); // = 225
        for (int i = 0; i < monthsBack; i++) {
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        // Select day 4
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();

        // Verify the date
        assertEquals(expectedDate, dateBox.getDomProperty("value"));
        dateBox.clear();
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
        // Target date: May 2, 1959 → "05/02/1959"
        String dateToEnter = "05/02/1959";

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        assertEquals("", dateBox.getDomProperty("value"));

        dateBox.clear();
        dateBox.sendKeys(dateToEnter);

        // Verify the date
        assertEquals(dateToEnter, dateBox.getDomProperty("value"));
    }
}