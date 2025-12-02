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

        List<WebElement> checkBoxes =
                driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement cb : checkBoxes) {
            assertFalse(cb.isSelected());
        }

        WebElement option2 =
                driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        option2.click();

        WebElement option1 =
                driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option3 =
                driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));

        assertFalse(option1.isSelected());
        assertTrue(option2.isSelected());
        assertFalse(option3.isSelected());

        option3.click();

        driver.findElement(By.id("result_button_checkbox")).click();

        WebElement result = driver.findElement(By.id("result_checkbox"));
        assertEquals("You selected value(s): Option 2, Option 3", result.getText());
    }


    @Test
    public void selectRadioButton() throws Exception {

        List<WebElement> radios =
                driver.findElements(By.cssSelector(".w3-check[type='radio']"));

        for (WebElement r : radios) {
            assertFalse(r.isSelected());
        }

        WebElement option3 =
                driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio']"));
        option3.click();

        WebElement option1 =
                driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio']"));
        WebElement option2 =
                driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio']"));

        assertFalse(option1.isSelected());
        assertFalse(option2.isSelected());
        assertTrue(option3.isSelected());

        option1.click();

        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
        assertFalse(option3.isSelected());

        WebElement resultButton = driver.findElement(
                By.xpath("//h2[text()='Radio']/following::button[1]"));
        resultButton.click();

        WebElement result = driver.findElement(
                By.xpath("//h2[text()='Radio']/following::p[1]"));
        assertEquals("You selected option: Option 1", result.getText());
    }
}



//@Test
//public void selectOption() throws Exception {
//    Select dropdown = new Select(driver.findElement(By.tagName("select")));
//
//    dropdown.selectByVisibleText("Option 3");
//    assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//
//    dropdown.selectByValue("value2");
//    assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//
//    driver.findElement(By.id("result_button_select")).click();
//
//    WebElement dropdownResult = driver.findElement(By.id("result_select"));
//    assertTrue(dropdownResult.isDisplayed());
//    assertEquals("You selected option: Option 2", dropdownResult.getText());
//}














// ** Bonus tasks **
//    @Test
//    public void chooseDateViaCalendarBonus() throws Exception {
////         TODO:
////          enter date '4 of July 2007' using calendar widget
////          check that correct date is added
//    }
//
//    @Test
//    public void chooseDateViaTextBoxBonus() throws Exception {
////         TODO:
////          enter date '2 of May 1959' using text
////          check that correct date is added
//    }


