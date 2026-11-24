package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

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

        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println(driver.findElement(By.xpath("//h2[2]")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());

//          1-2 ways to find text: "Test Text 1"

        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/p[@class='test']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[4]/p[contains(@class, 'test')]")).getText());
        assertEquals("Test Text 1", driver.findElement(By.xpath("//*[@id='test1']/p[@class='test']")).getText());

//          1-2 ways to find text: "Test Text 2"

        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/p[@class='twoTest']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[4]/p[contains(@class, 'twoTest')]")).getText());
        assertEquals("Test Text 2", driver.findElement(By.xpath("//p[@class='twoTest']")).getText());

//          1-2 ways to find text: "Test Text 3"

        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/p[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//p[.='Test Text 3']")).getText());
        assertEquals("Test Text 3", driver.findElements(By.xpath("//*[@id='test3']/p")).get(0).getText());

//          1-2 ways to find text: "Test Text 4"

        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText());
        System.out.println(driver.findElement(By.xpath("//p[.='Test Text 4']")).getText());
        assertEquals("Test Text 4", driver.findElement(By.xpath("//*[@id='test3']/p[text()='Test Text 4']")).getText());

//          1-2 ways to find text: "Test Text 5"

        System.out.println(driver.findElement(By.xpath("//*[@id='test2']/p[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test2'] / *[contains(@class, 'Test')]")).getText());
        assertEquals("Test Text 5", driver.findElement(By.xpath("//*[@class='Test']")).getText());


//          1-2 ways to find text: "This is also a button"

        System.out.println(driver.findElement(By.xpath("//*[@id='buttonId']")).getDomProperty("value"));
        System.out.println(driver.findElement(By.xpath("//input[@name='randomButton2']")).getDomProperty("value"));
        assertEquals("This is also a button", driver.findElement(By.xpath("//*[@name='randomButton2']")).getDomAttribute("value"));

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:

//          1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("h2:nth-of-type(2)")).getText());
        assertEquals("Heading 2 text", driver.findElement(By.cssSelector("#heading_2")).getText());

//          1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.cssSelector("#test1 > p.test")).getText());
        assertEquals("Test Text 1", driver.findElement(By.cssSelector("#test1 .test")).getText());

//          1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector("#test1 > p.twoTest")).getText());
        System.out.println(driver.findElement(By.cssSelector("p.twoTest")).getText());
        assertEquals("Test Text 2", driver.findElement(By.cssSelector("#test1 > .twoTest")).getText());

//          1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.cssSelector("#test3 > p:nth-of-type(1)")).getText());
        assertEquals("Test Text 3", driver.findElement(By.cssSelector("#test3 > .test:nth-child(1)")).getText());

//          1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getDomProperty("value"));
        assertEquals("This is also a button", driver.findElement(By.cssSelector("input[type='button']#buttonId[name='randomButton2']")).getDomAttribute("value"));

    }
}
