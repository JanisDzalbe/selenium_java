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

        System.out.println("\t text of element with id 'heading_2' is '" +
            driver.findElement(By.xpath("//*[@id=\"heading_2\"]")).getText() + "'");
        assertEquals("Heading 2 text" , driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        assertEquals("Heading 2 text" , driver.findElement(By.xpath("//h2[contains(text(),\"2\")]")).getText());
        assertEquals("Heading 2 text",
                driver.findElement(By.id("heading_2")).getText());
        System.out.println("\t text of element with id 'test1' is '" +
                driver.findElement(By.xpath("//*[@id=\"test1\"]/p[1]")).getText() + "'");
        System.out.println("\t text of element with id 'test2' is '" +
                driver.findElement(By.xpath("//*[@id=\"test1\"]/p[2]")).getText() + "'");
        System.out.println("\t text of element with id 'test3' is '" +
                driver.findElement(By.xpath("//*[@id=\"test3\"]/p[1]")).getText() + "'");
        System.out.println("\t text of element with id 'test4' is '" +
                driver.findElement(By.xpath("//*[@id=\"test3\"]/p[2]")).getText() + "'");
        System.out.println("\t text of element with id 'test5' is '" +
                driver.findElement(By.xpath("//*[@id=\"test2\"]/p[1]")).getText() + "'");
        assertEquals("This is also a button", driver.findElement(By.xpath("//*[@id=\"buttonId\"]")).getDomAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.xpath("//input[@name='randomButton2']")).getDomAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"

        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("#heading_2")).getText());
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1 .test")).getText());
        assertEquals("Test Text 2", driver.findElement(By.cssSelector(".twoTest")).getText());
        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3 .test")).getText());

        assertEquals("This is also a button", driver.findElement(By.cssSelector("#buttonId")).getDomAttribute("value"));
        assertEquals("This is also a button", driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomAttribute("value"));

    }
}
