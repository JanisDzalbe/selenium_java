package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        System.out.println(checkBoxes);
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());// checkboxes are NOT selected
            Thread.sleep(2000);
        }
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        checkBoxes.get(1).click();
        assertFalse(checkBoxes.get(0).isSelected());
        assertFalse(checkBoxes.get(2).isSelected());
        assertTrue(checkBoxes.get(1).isSelected());
        Thread.sleep(2000);

        checkBoxes.get(2).click();
        driver.findElement(By.id("result_button_checkbox")).click();
        assertEquals(driver.findElement(By.id("result_checkbox")).getText(), "You selected value(s): Option 2, Option 3");
        Thread.sleep(2000);
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
        List<WebElement> radios = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        System.out.println(radios);
        for (WebElement radio : radios) {
            assertFalse(radio.isSelected());// checkboxes are NOT selected
            Thread.sleep(2000);
        }
        radios.get(2).click();
        assertFalse(radios.get(0).isSelected());
        assertFalse(radios.get(1).isSelected());

        Thread.sleep(2000);

        radios.get(0).click();
        assertFalse(radios.get(2).isSelected());
        assertFalse(radios.get(1).isSelected());

        driver.findElement(By.id("result_button_ratio")).click();
        assertEquals(driver.findElement(By.id("result_radio")).getText(), "You selected option: Option 1");
        Thread.sleep(2000);


    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed
        WebElement select = driver.findElement(By.id("vfb-12"));
        select.click();
        List<WebElement> options = select.findElements(By.tagName("option"));
        options.get(3).click();
        assertTrue(options.get(3).isSelected());
        Thread.sleep(2000);

        options.get(2).click();
        assertTrue(options.get(2).isSelected());
        Thread.sleep(2000);

        driver.findElement(By.id("result_button_select")).click();
        assertEquals(driver.findElement(By.id("result_select")).getText(), "You selected option: Option 2");
        Thread.sleep(2000);
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added
    }
}
