package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Activity 6: Practicing locating page elements using different XPath and CSS selector strategies.
 */
public class Sample6Task {
    WebDriver driver;

    /**
     * Initializes ChromeDriver and opens the Locators example page before each test.
     *
     * @throws Exception if driver initialization fails
     */
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    /**
     * Closes the browser after each test.
     *
     * @throws Exception if browser teardown fails
     */
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    /**
     * Finds each target heading, paragraph text, and button on the page using two different XPath locators.
     *
     * @throws Exception if any element cannot be found
     */
    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text":
        String heading2Way1 = driver.findElement(By.xpath("//*[@id='heading_2']")).getText();
        String heading2Way2 = driver.findElement(By.xpath("//h2[text()='Heading 2 text']")).getText();
        System.out.println("Heading 2 text (XPath 1): " + heading2Way1);
        System.out.println("Heading 2 text (XPath 2): " + heading2Way2);
        assertEquals("Heading 2 text", heading2Way1);
        assertEquals("Heading 2 text", heading2Way2);

//          1-2 ways to find text: "Test Text 1"
        String testText1Way1 = driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText();
        String testText1Way2 = driver.findElement(By.xpath("//*[@id='test1']/p[@class='test']")).getText();
        System.out.println("Test Text 1 (XPath 1): " + testText1Way1);
        System.out.println("Test Text 1 (XPath 2): " + testText1Way2);
        assertEquals("Test Text 1", testText1Way1);
        assertEquals("Test Text 1", testText1Way2);

//          1-2 ways to find text: "Test Text 2"
        String testText2Way1 = driver.findElement(By.xpath("//*[@class='twoTest']")).getText();
        String testText2Way2 = driver.findElement(By.xpath("//*[@id='test1']/p[2]")).getText();
        System.out.println("Test Text 2 (XPath 1): " + testText2Way1);
        System.out.println("Test Text 2 (XPath 2): " + testText2Way2);
        assertEquals("Test Text 2", testText2Way1);
        assertEquals("Test Text 2", testText2Way2);

//          1-2 ways to find text: "Test Text 3"
        String testText3Way1 = driver.findElement(By.xpath("//*[@id='test3']/p[1]")).getText();
        String testText3Way2 = driver.findElement(By.xpath("//div[@id='test3']/p[text()='Test Text 3']")).getText();
        System.out.println("Test Text 3 (XPath 1): " + testText3Way1);
        System.out.println("Test Text 3 (XPath 2): " + testText3Way2);
        assertEquals("Test Text 3", testText3Way1);
        assertEquals("Test Text 3", testText3Way2);

//          1-2 ways to find text: "Test Text 4"
        String testText4Way1 = driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText();
        String testText4Way2 = driver.findElement(By.xpath("//p[text()='Test Text 4']")).getText();
        System.out.println("Test Text 4 (XPath 1): " + testText4Way1);
        System.out.println("Test Text 4 (XPath 2): " + testText4Way2);
        assertEquals("Test Text 4", testText4Way1);
        assertEquals("Test Text 4", testText4Way2);

//          1-2 ways to find text: "Test Text 5"
        String testText5Way1 = driver.findElement(By.xpath("//*[@class='Test']")).getText();
        String testText5Way2 = driver.findElement(By.xpath("//*[@id='test2']/p[1]")).getText();
        System.out.println("Test Text 5 (XPath 1): " + testText5Way1);
        System.out.println("Test Text 5 (XPath 2): " + testText5Way2);
        assertEquals("Test Text 5", testText5Way1);
        assertEquals("Test Text 5", testText5Way2);

//          1-2 ways to find text: "This is also a button"
        String buttonWay1 = driver.findElement(By.xpath("//*[@id='buttonId']")).getDomProperty("value");
        String buttonWay2 = driver.findElement(By.xpath("//input[@name='randomButton2']")).getDomProperty("value");
        System.out.println("This is also a button (XPath 1): " + buttonWay1);
        System.out.println("This is also a button (XPath 2): " + buttonWay2);
        assertEquals("This is also a button", buttonWay1);
        assertEquals("This is also a button", buttonWay2);
    }

    /**
     * Finds each target heading, paragraph text, and button on the page using two different CSS selectors.
     *
     * @throws Exception if any element cannot be found
     */
    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
        String heading2Way1 = driver.findElement(By.cssSelector("#heading_2")).getText();
        String heading2Way2 = driver.findElement(By.cssSelector("body > h2:nth-of-type(2)")).getText();
        System.out.println("Heading 2 text (CSS 1): " + heading2Way1);
        System.out.println("Heading 2 text (CSS 2): " + heading2Way2);
        assertEquals("Heading 2 text", heading2Way1);
        assertEquals("Heading 2 text", heading2Way2);

//          1-2 ways to find text: "Test Text 1"
        String testText1Way1 = driver.findElement(By.cssSelector("#test1 > p.test")).getText();
        String testText1Way2 = driver.findElement(By.cssSelector("#test1 > p:nth-child(1)")).getText();
        System.out.println("Test Text 1 (CSS 1): " + testText1Way1);
        System.out.println("Test Text 1 (CSS 2): " + testText1Way2);
        assertEquals("Test Text 1", testText1Way1);
        assertEquals("Test Text 1", testText1Way2);

//          1-2 ways to find text: "Test Text 2"
        String testText2Way1 = driver.findElement(By.cssSelector(".twoTest")).getText();
        String testText2Way2 = driver.findElement(By.cssSelector("#test1 > p:nth-child(2)")).getText();
        System.out.println("Test Text 2 (CSS 1): " + testText2Way1);
        System.out.println("Test Text 2 (CSS 2): " + testText2Way2);
        assertEquals("Test Text 2", testText2Way1);
        assertEquals("Test Text 2", testText2Way2);

//          1-2 ways to find text: "Test Text 3"
        String testText3Way1 = driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText();
        String testText3Way2 = driver.findElement(By.cssSelector("div#test3 .test")).getText();
        System.out.println("Test Text 3 (CSS 1): " + testText3Way1);
        System.out.println("Test Text 3 (CSS 2): " + testText3Way2);
        assertEquals("Test Text 3", testText3Way1);
        assertEquals("Test Text 3", testText3Way2);

//          1-2 ways to find text: "This is also a button"
        String buttonWay1 = driver.findElement(By.cssSelector("#buttonId")).getDomProperty("value");
        String buttonWay2 = driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomProperty("value");
        System.out.println("This is also a button (CSS 1): " + buttonWay1);
        System.out.println("This is also a button (CSS 2): " + buttonWay2);
        assertEquals("This is also a button", buttonWay1);
        assertEquals("This is also a button", buttonWay2);
    }
}