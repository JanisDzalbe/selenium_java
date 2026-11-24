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
        assertEquals("Heading 2 text", driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.xpath("//h2[text()=\"Heading 2 text\"]")).getText());

        assertEquals("Test Text 1", driver.findElement(By.xpath("//div[@id = \"test1\"]/p[1]")).getText());
        assertEquals("Test Text 1", driver.findElement(By.xpath("//p[text() = \"Test Text 1\"]")).getText());

        assertEquals("Test Text 2", driver.findElement(By.xpath("//div[@id = \"test1\"]/p[2]")).getText());
        assertEquals("Test Text 2", driver.findElement(By.xpath("//p[text() = \"Test Text 2\"]")).getText());

        assertEquals("Test Text 3", driver.findElement(By.xpath("//div[@id = \"test3\"]/p[1]")).getText());
        assertEquals("Test Text 3", driver.findElement(By.xpath("//p[text() = \"Test Text 3\"]")).getText());

        assertEquals("Test Text 4", driver.findElement(By.xpath("//div[@id = \"test3\"]/p[2]")).getText());
        assertEquals("Test Text 4", driver.findElement(By.xpath("//p[text() = \"Test Text 4\"]")).getText());

        assertEquals("Test Text 5", driver.findElement(By.xpath("//div[@id = \"test2\"]/p[1]")).getText());
        assertEquals("Test Text 5", driver.findElement(By.xpath("//p[text() = \"Test Text 5\"]")).getText());

        assertEquals("This is also a button", driver.findElement(By.xpath("//input[@name=\"randomButton2\"]")).getDomAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.xpath("//input[@value=\"This is also a button\"]")).getDomAttribute("value"));


    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("#Heading_2")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("h2#Heading_2")).getText());

        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1 p:first-child")).getText());
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1 p.test")).getText());

        assertEquals("Test Text 2", driver.findElement(By.cssSelector("#test1 p:nth-of-type(2)")).getText());
        assertEquals("Test Text 2", driver.findElement(By.cssSelector("#test1 p.twoTest")).getText());

        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3 p:nth-of-type(1)")).getText());
        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3 p:first-child")).getText());

        assertEquals("This is also a button", driver.findElement(By.cssSelector("#buttonId")).getDomAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomAttribute("value"));
    }
}
