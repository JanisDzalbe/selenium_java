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
        System.out.println(driver.findElement(By.xpath("//h2[text()='Heading 2 text']")).getText());
        System.out.println(driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
//          1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.xpath("//p[text()='Test Text 1']")).getText());
//          1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.xpath("//p[@class='twoTest']")).getText());
//          1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.xpath("//div[@id='test3']/p[1]")).getText());
//          1-2 ways to find text: "Test Text 4"
        System.out.println(driver.findElement(By.xpath("//div[@id='test3']/p[2]")).getText());
//          1-2 ways to find text: "Test Text 5"
        System.out.println(driver.findElement(By.xpath("//p[text()='Test Text 5']")).getText());
//          1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.xpath("//input[@id='buttonId']")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());
        System.out.println(driver.findElement(By.cssSelector("h2[id='heading_2']")).getText());
//          1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.cssSelector("div[id='test1'] > .test")).getText());
//          1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector(".twoTest")).getText());
//          1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.cssSelector("#test3 p:first-child")).getText());
//
//          1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getAttribute("value"));
    }
}
