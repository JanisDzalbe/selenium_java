package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sample8 {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/randomized_list");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void dynamicLocators() throws Exception {
        String elementTitle = "Second Element";
        String elementIndex = "2";
        WebElement xpathElement = driver.findElement(By.xpath("//h3[contains(text(),'" + elementTitle + "')]"));
        WebElement cssElement = driver.findElement(By.cssSelector("input[id*='" + elementIndex + "']"));
        assertEquals("Second Element", xpathElement.getText());
        assertEquals("yellow", cssElement.getDomProperty("value"));
    }

    @Test
    public void conditionalLocators() throws Exception {
        WebElement firstElem = driver.findElement(By.xpath("//div[@id='element' and .//label[@for='input_element_1']]"));
        WebElement secondElem = driver.findElement(By.cssSelector("div#element:has(label[for='input_element_2'])"));
        assertTrue(firstElem.getText().contains("First Element"));
        assertTrue(secondElem.getText().contains("Second Element"));
    }

    @Test
    public void elementChaining() throws Exception {
        String thirdElemTitle = "Third Element";
        String fourthElemTitle = "Fourth Element";
        WebElement thirdElem = driver.findElement(By.xpath("//div[@id='element' and .//*[text()='" + thirdElemTitle + "']]"));
        WebElement fourthElem = driver.findElement(By.xpath("//div[@id='element' and .//*[text()='" + fourthElemTitle + "']]"));

        assertEquals("Element number three has its own unique characteristics and testing purposes.",
                thirdElem.findElement(By.tagName("p")).getText());
        assertEquals("This is the fourth element, positioned in the middle of our collection.",
                fourthElem.findElement(By.className("element-paragraph")).getText());
    }

    @Test
    public void findWithLoop() throws Exception {
        List<WebElement> allElements = driver.findElements(By.id("element"));
        WebElement greenElement = null;
        for (WebElement element : allElements) {
            String inputVal = element.findElement(By.tagName("input")).getDomProperty("value");
            if (inputVal.equals("green")) {
                greenElement = element;
                break;
            }
        }
        assertEquals("Fifth Element", greenElement.findElement(By.tagName("h3")).getText());
    }
}
