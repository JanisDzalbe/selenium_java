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
        // "Heading 2 text"
        assertEquals("Heading 2 text", driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.xpath("//h2[text()='Heading 2 text']")).getText());

        // "Test Text 1"
        assertEquals("Test Text 1", driver.findElement(By.xpath("//div[@id='test1']/p[@class='test']")).getText());
        assertEquals("Test Text 1", driver.findElement(By.xpath("//p[text()='Test Text 1']")).getText());

        // "Test Text 2"
        assertEquals("Test Text 2", driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
        assertEquals("Test Text 2", driver.findElement(By.xpath("//div[@id='test1']/p[2]")).getText());

        // "Test Text 3"
        assertEquals("Test Text 3", driver.findElement(By.xpath("//div[@id='test3']/p[1]")).getText());
        assertEquals("Test Text 3", driver.findElement(By.xpath("//p[contains(text(), 'Text 3')]")).getText());

        // "Test Text 4"
        assertEquals("Test Text 4", driver.findElement(By.xpath("//div[@id='test3']/p[2]")).getText());
        assertEquals("Test Text 4", driver.findElement(By.xpath("//p[text()='Test Text 4']")).getText());

        // "Test Text 5"
        assertEquals("Test Text 5", driver.findElement(By.xpath("//div[@id='test2']/p[1]")).getText());
        assertEquals("Test Text 5", driver.findElement(By.xpath("//p[@class='Test']")).getText());

        // "This is also a button"
        assertEquals("This is also a button", driver.findElement(By.xpath("//input[@id='buttonId']")).getDomProperty("value"));
        assertEquals("This is also a button", driver.findElement(By.xpath("//input[@type='button' and @name='randomButton2']")).getDomProperty("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
        // "Heading 2 text"
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("#heading_2")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("h2#heading_2")).getText());

        // "Test Text 1"
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1 > .test")).getText());
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("div#test1 > p:first-child")).getText());

        // "Test Text 2"
        assertEquals("Test Text 2", driver.findElement(By.cssSelector(".twoTest")).getText());
        assertEquals("Test Text 2", driver.findElement(By.cssSelector("#test1 > p:nth-child(2)")).getText());

        // "Test Text 3"
        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3 > .test")).getText());
        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3 > p:first-child")).getText());

        // "This is also a button"
        assertEquals("This is also a button", driver.findElement(By.cssSelector("#buttonId")).getDomProperty("value"));
        assertEquals("This is also a button", driver.findElement(By.cssSelector("input[type='button'][name='randomButton2']")).getDomProperty("value"));
    }
}
