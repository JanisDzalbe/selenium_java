package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample6Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    /**
     * @description textMatchMode uses xpath's literal text() match, setting it to false uses more creative expressions
     * @throws Exception
     */
    @Test
    public void findElementByXPath() throws Exception {
        boolean textMatchMode = true;
        WebElement foundElement;
        // 1-2 ways to find text: "Heading 2 text":
        foundElement = textMatchMode ? driver.findElement(By.xpath("//*[text()='Heading 2 text']")) :
                driver.findElement(By.xpath("//*[@id='heading_2']"));
        assertEquals("Heading 2 text", foundElement.getText());
        // 1-2 ways to find text: "Test Text 1"
        foundElement = textMatchMode ? driver.findElement(By.xpath("//*[text()='Test Text 1']")) :
                driver.findElement(By.xpath("//*[@id='test1']/p"));
        assertEquals("Test Text 1", foundElement.getText());
        // 1-2 ways to find text: "Test Text 2"
        foundElement = textMatchMode ? driver.findElement(By.xpath("//*[text()='Test Text 2']")) :
                driver.findElement(By.xpath("//*[@class='twoTest']"));
        assertEquals("Test Text 2", foundElement.getText());
        // 1-2 ways to find text: "Test Text 3"
        foundElement = textMatchMode ? driver.findElement(By.xpath("//*[text()='Test Text 3']")) :
                driver.findElement(By.xpath("//*[@id='test3']/p"));
        assertEquals("Test Text 3", foundElement.getText());
        // 1-2 ways to find text: "Test Text 4"
        foundElement = textMatchMode ? driver.findElement(By.xpath("//*[text()='Test Text 4']")) :
                driver.findElements(By.xpath("//*[@id='test3']/p")).get(1);
        assertEquals("Test Text 4", foundElement.getText());
        // 1-2 ways to find text: "Test Text 5"
        foundElement = textMatchMode ? driver.findElement(By.xpath("//*[text()='Test Text 5']")) :
                driver.findElement(By.xpath("//*[@id='test2']/p"));
        assertEquals("Test Text 5", foundElement.getText());
        // 1-2 ways to find text: "This is also a button"
        foundElement = textMatchMode ? driver.findElement(By.xpath("//*[@value='This is also a button']")) :
                driver.findElements(By.xpath("//input")).get(1);
        assertEquals("This is also a button", foundElement.getDomAttribute("value"));

    }

    /**
     * @description flip `alternateMode` between true and false to use different css selectors for some elements
     * @throws Exception
     */
    @Test
    public void findElementByCssName() throws Exception {
        boolean alternateMode = true;
        WebElement foundElement;
//      1-2 ways to find text: "Heading 2 text"
        foundElement = driver.findElement(By.cssSelector("#heading_2"));
        assertEquals("Heading 2 text", foundElement.getText());
//      1-2 ways to find text: "Test Text 1"
        foundElement = alternateMode ? driver.findElement(By.cssSelector("div#test1 > p")) :
                driver.findElement(By.cssSelector(".test"));
        assertEquals("Test Text 1", foundElement.getText());
//      1-2 ways to find text: "Test Text 2"
        foundElement = alternateMode ? driver.findElements(By.cssSelector("div#test1 > p")).get(1) :
                driver.findElement(By.cssSelector(".twoTest"));
        assertEquals("Test Text 2", foundElement.getText());
//      1-2 ways to find text: "Test Text 3"
        foundElement = alternateMode ? driver.findElement(By.cssSelector("div#test3 > p")) :
                driver.findElements(By.cssSelector(".test")).get(1);
        assertEquals("Test Text 3", foundElement.getText());
//      1-2 ways to find text: "This is also a button"
        foundElement = alternateMode ? driver.findElements(By.cssSelector("input")).get(1) :
                driver.findElement(By.cssSelector("#buttonId"));
        assertEquals("This is also a button", foundElement.getDomAttribute("value"));
    }
}
