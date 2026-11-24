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
        driver.findElement(By.xpath("//*[@id='heading_2']")).getText();
        driver.findElement(By.xpath("//*[contains(text(), 'Heading 2')]")).getText();

        driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText();
        driver.findElement(By.xpath("//*[@id = 'test1']/p[@class = \"test\"]")).getText();

        driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText();
        driver.findElement(By.xpath("//p[@class = \"twoTest\"]")).getText();

        driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText();
        driver.findElement(By.xpath("//*[@id = \"test3\"]/p")).getText();

        driver.findElement(By.xpath("//*[@id = 'test3']/p[text()='Test Text 4']")).getText();

        driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText();
        driver.findElement(By.xpath("//*[@class = \"Test\"]")).getText();

        driver.findElement(By.xpath("//*[@id = 'buttonId']")).getDomAttribute("value");
        driver.findElement(By.xpath("//*[@name = \"randomButton2\"]")).getDomAttribute("value");

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
        driver.findElement(By.cssSelector("#heading_2")).getText();

        driver.findElement(By.cssSelector("#test1 .test")).getText();

        driver.findElement(By.cssSelector("#test1 > .twoTest")).getText();

        driver.findElement(By.cssSelector("input[name = 'randomButton2'][type = 'button']")).getText();
        driver.findElement(By.cssSelector("#test3 > .test:nth-child(1)")).getText();

    }
}
