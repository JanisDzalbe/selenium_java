package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

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

        // Heading 2 text
        driver.findElement(By.xpath("//h2[@id='heading_2']"));
        driver.findElement(By.xpath("//h2[text()='Heading 2 text']"));

        // Test Text 1
        driver.findElement(By.xpath("//p[text()='Test Text 1']"));
        driver.findElement(By.xpath("//div[@id='test1']/p[@class='test']"));

        // Test Text 2
        driver.findElement(By.xpath("//p[text()='Test Text 2']"));
        driver.findElement(By.xpath("//div[@id='test1']//p[contains(@class,'twoTest')]"));

        // Test Text 3
        driver.findElement(By.xpath("//p[text()='Test Text 3']"));
        driver.findElement(By.xpath("//div[@id='test3']/p[1]"));

        // Test Text 4
        driver.findElement(By.xpath("//p[text()='Test Text 4']"));
        driver.findElement(By.xpath("//div[@id='test3']/p[2]"));

        // Test Text 5
        driver.findElement(By.xpath("//p[text()='Test Text 5']"));
        driver.findElement(By.xpath("//div[@id='test2']/p[@class='Test']"));

        // "This is also a button"
        driver.findElement(By.xpath("//input[@id='buttonId']"));
        driver.findElement(By.xpath("//input[@value='This is also a button']"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"

        // Heading 2 text
        driver.findElement(By.cssSelector("h2#heading_2"));
        driver.findElement(By.cssSelector("#heading_2"));

        // Test Text 1
        driver.findElement(By.cssSelector("#test1 .test"));
        driver.findElement(By.cssSelector("div#test1 > p.test"));

        // Test Text 2
        driver.findElement(By.cssSelector(".twoTest"));
        driver.findElement(By.cssSelector("#test1 .twoTest"));

        // Test Text 3
        driver.findElement(By.cssSelector("#test3 p.test:nth-of-type(1)"));
        driver.findElement(By.cssSelector("#test3 p.test"));

        // Test Text 4
        driver.findElement(By.cssSelector("#test3 p.test:nth-of-type(2)"));
        driver.findElement(By.cssSelector("#test3 p.test:last-of-type"));

        // Test Text 5
        driver.findElement(By.cssSelector("#test2 .Test"));
        driver.findElement(By.cssSelector("p.Test"));

        // "This is also a button"
        driver.findElement(By.cssSelector("#buttonId"));
        driver.findElement(By.cssSelector("input[value='This is also a button']"));
    }
}
