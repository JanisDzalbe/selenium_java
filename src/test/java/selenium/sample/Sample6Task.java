package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.sql.SQLOutput;

public class Sample6Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text":
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "Test Text 4"
//        1-2 ways to find text: "Test Text 5"
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println(driver.findElement(By.xpath("//h2[text()='Heading 2 text']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@id='test1']//*[@class='test']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@id='test1']//*[@class='twoTest']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@id='test3']//*[@class='test']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText());
        System.out.println(driver.findElement(By.xpath("//p[@class='test' and text()='Test Text 4']")).getText());

        System.out.println(driver.findElement(By.xpath("//div[@id='test2']/p[@class='Test']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@id='test2']//p[contains(text(), 'Text 5')]")).getText());

        System.out.println(driver.findElement(By.xpath("//input[@id='buttonId']")).getDomAttribute("value"));
        System.out.println(driver.findElement(By.xpath("//input[@value='This is also a button']")).getDomAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("h2#heading_2")).getText());
        System.out.println(driver.findElement(By.cssSelector("h2[id=\"heading_2\"]")).getText());

        System.out.println(driver.findElement(By.cssSelector("div#test1 p.test")).getText());
        System.out.println(driver.findElement(By.cssSelector("div#test1 p:first-child")).getText());

        System.out.println(driver.findElement(By.cssSelector("p.twoTest")).getText());
        System.out.println(driver.findElement(By.cssSelector("div#test1 p:nth-child(2)")).getText());

        System.out.println(driver.findElement(By.cssSelector("div#test3 p.test")).getText());
        System.out.println(driver.findElement(By.cssSelector("div#test3 p:first-child")).getText());

        System.out.println(driver.findElement(By.cssSelector("input#buttonId")).getDomAttribute("value"));
        System.out.println(driver.findElement(By.cssSelector("input[value=\"This is also a button\"]")).getDomAttribute("value"));
    }
}
