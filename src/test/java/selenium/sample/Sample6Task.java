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
//          1-2 ways to find text: "Heading 2 text":
        System.out.println("\t Heading 2 text " +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
//          1-2 ways to find text: "Test Text 1"
        System.out.println("\t Test Text 1 " +
                driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());
//          1-2 ways to find text: "Test Text 2"
        System.out.println("\t Test Text 2 " +
                driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
//          1-2 ways to find text: "Test Text 3"
        System.out.println("\t Test Text 3 " +
                driver.findElement(By.xpath("//*[@id='test3']/*[contains(text(), '3')]")).getText());
//          1-2 ways to find text: "Test Text 4"
        System.out.println("\t Test Text 4 " +
                driver.findElement(By.xpath("//*[@id='test3']/*[2]")).getText());
//          1-2 ways to find text: "Test Text 5"
        System.out.println("\t Test Text 5 " +
                driver.findElement(By.xpath("//*[@class='Test']")).getText());
//          1-2 ways to find text: "This is also a button"
        System.out.println("\t This is also a button " +
                driver.findElement(By.xpath("//input[@name='randomButton2']")).getDomAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//          1-2 ways to find text: "Heading 2 text"
        System.out.println("\t Heading 2 text " +
                driver.findElement(By.cssSelector("#heading_2")).getText());
//          1-2 ways to find text: "Test Text 1"
        System.out.println("\t Test Text 1 " +
                driver.findElement(By.cssSelector("#test1 .test")).getText());
//          1-2 ways to find text: "Test Text 2"
        System.out.println("\t Test Text 2 " +
                driver.findElement(By.cssSelector(".twoTest")).getText());
//          1-2 ways to find text: "Test Text 3"
        System.out.println("\t Test Text 3 " +
                driver.findElement(By.cssSelector("#test3 p:nth-of-type(1)")).getText());
//          1-2 ways to find text: "This is also a button"
        System.out.println("\t This is also a button " +
                driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomAttribute("value"));
    }
}
