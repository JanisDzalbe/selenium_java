package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text":
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "Test Text 4"
//          1-2 ways to find text: "Test Text 5"
//          1-2 ways to find text: "This is also a button"
        // Find Heading 2 text.
        // Find Heading 1.
        assertEquals("Heading 1",
                driver.findElement(By.xpath("//h2[@id='heading_1']")).getText());

        // Find sample text 1.
        assertEquals("sample text 1",
                driver.findElement(By.xpath("//p[text()='sample text 1']")).getText());

        // Find sample text 2.
        assertEquals("sample text 2",
                driver.findElement(By.xpath("//p[text()='sample text 2']")).getText());

        // Find unbelievable sample text.
        assertEquals("unbelievable sample text",
                driver.findElement(By.xpath("//p[text()='unbelievable sample text']")).getText());

        // Find amazing sample text.
        assertEquals("amazing sample text",
                driver.findElement(By.xpath("//p[text()='amazing sample text']")).getText());

        // Find dummy text.
        assertEquals("dummy text",
                driver.findElement(By.xpath("//p[@id='dummy']")).getText());

    }



    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"

        // # - means that we are looking element by using its ID
        // . - class
        // Find Heading 2 text
        assertEquals("Heading 2 text",
                driver.findElement(By.cssSelector("#heading_2")).getText());

        // Find Test Text 1.
        assertEquals("Test Text 1",
                driver.findElement(By.cssSelector("#test1 .test")).getText());

        // Find Test Text 2.
        assertEquals("Test Text 2",
                driver.findElement(By.cssSelector("#test1 .twoTest")).getText());

        // Find Test Text 3.
        assertEquals("Test Text 3",
                driver.findElement(By.cssSelector("#test3 .test")).getText());

        // Find This is also a button.
        assertEquals("This is also a button",
                driver.findElement(By.cssSelector("input[value='This is also a button']")).getAttribute("value"));
    }
}

