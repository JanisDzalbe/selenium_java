package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import selenium.utility.BootcampUtils;

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
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        WebElement resultButton = driver.findElement(By.id("result_button_checkbox"));
        WebElement resultText = driver.findElement(By.id("result_checkbox"));

        //checkss that none of the checkboxes are ticked
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        option2.click();

        //checks states
        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        option3.click();

        resultButton.click();

        //checks result text
        assertTrue(resultText.isDisplayed());
        assertEquals("You selected value(s): Option 2, Option 3", resultText.getText());
    }

    @Test
    public void selectRadioButton() throws Exception {
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
        WebElement resultButton = driver.findElement(By.id("result_button_ratio"));
        WebElement resultText = driver.findElement(By.id("result_radio"));

        //checks that none of the radio are selected
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        option3.click();

        //checks states
        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        option1.click();

        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        resultButton.click();

        //checks result text
        assertTrue(resultText.isDisplayed());
        assertEquals("You selected option: Option 1", resultText.getText());
    }

    @Test
    public void selectOption() throws Exception {
        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        WebElement resultButton = driver.findElement(By.id("result_button_select"));
        WebElement resultText = driver.findElement(By.id("result_select"));

        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByVisibleText("Option 2");
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        resultButton.click();

        //check result text
        assertTrue(resultText.isDisplayed());
        assertEquals("You selected option: Option 2", resultText.getText());
    }

    // ** Bonus tasks **
    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click();

        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));

        while (true) {
            String currentMonth = dateWidget.findElement(By.className("ui-datepicker-month")).getText();
            String currentYear = dateWidget.findElement(By.className("ui-datepicker-year")).getText();

            if (currentMonth.equals("July") && currentYear.equals("2007")) {
                break;
            }
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        //selecst day 4
        dateWidget.findElement(By.xpath("//a[text()='4']")).click();

        //checks correct date is added
        assertEquals("07/04/2007", dateBox.getDomProperty("value"));
    }

    @Test
    public void chooseDateViaTextBoxBonus() {

        WebElement dateBox = driver.findElement(By.id("vfb-8"));

        //clear existing value
        dateBox.clear();

        //enters date using text
        String date = "05/02/1959";
        dateBox.sendKeys(date);

        //verify value
        assertEquals(date, dateBox.getDomProperty("value"));
    }
}
