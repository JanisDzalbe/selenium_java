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
        System.out.println(driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
        System.out.println(driver.findElement(By.xpath("//p[text()='Test Text 1']")).getText());
        System.out.println(driver.findElement(By.xpath("//p[@class='twoTest']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@id='test3']/p[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//p[text()='Test Text 4']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@id='test2']/p[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//input[@name='randomButton2']")).getDomAttribute("value"));
        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(@class,'twoTest')]")).getText());
        System.out.println(driver.findElement(By.xpath("//p[text()='Test Text 3']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(),'Test Text 4')]")).getText());
        System.out.println(driver.findElement(By.xpath("//p[text()='Test Text 5']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='buttonId']")).getDomAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());
        System.out.println(driver.findElement(By.cssSelector("#test1 p:first-child")).getText());
        System.out.println(driver.findElement(By.cssSelector(".twoTest")).getText());
        System.out.println(driver.findElement(By.cssSelector("#test3 .test")).getText());
        System.out.println(driver.findElement(By.cssSelector("input#buttonId")).getDomAttribute("value"));
        System.out.println(driver.findElement(By.cssSelector("h2#heading_2")).getText());
        System.out.println(driver.findElement(By.cssSelector("#test1 .test")).getText());
        System.out.println(driver.findElement(By.cssSelector("p.twoTest")).getText());
        System.out.println(driver.findElement(By.cssSelector("div#test3 > p.test")).getText());
        System.out.println(driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomAttribute("value"));
    }
}
