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
        System.out.println("Find Element using Xpath:");
        System.out.println("\t text of element with the to find the text Heading 2  " + driver.findElement(By.xpath("//*[@id=\"heading_2\"]")).getText());
        System.out.println("\t text of element with the to find the text Heading 2  " + driver.findElement(By.xpath("//h2[@id=\"heading_2\"]")).getText());
        System.out.println("\t text of element with the to find the text Test Text 1  " + driver.findElement(By.xpath("//*[@id='test1']/p[@class='test']")).getText());
        System.out.println("\t text of element with the to find the text Test Text 2  " + driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
        System.out.println("\t text of element with the to find the text Test Text 3  " + driver.findElement(By.xpath("//*[@id='test3']/*[contains(text(),'3')]")).getText());
        System.out.println("\t text of element with the to find the text Test Text 4  " + driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());
        System.out.println("\t text of element with the to find the text Button  " + driver.findElement(By.xpath("//*[@id='buttonId']")).getDomAttribute("value"));

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
        System.out.println("\t text of element with the to find the text Heading 2  " + driver.findElement(By.cssSelector("#heading_2")).getText());
        System.out.println("\t " + driver.findElement(By.cssSelector("#test1 .test")).getText());
        System.out.println("\t " + driver.findElement(By.cssSelector("#test1 > .twoTest")).getText());
        System.out.println("\t " + driver.findElement(By.cssSelector("#test3 > .test:nth-of-type(1)")).getText());
        System.out.println("\t " + driver.findElements(By.cssSelector("#test3 > .test")).getFirst().getText());
        System.out.println("\t " + driver.findElement(By.cssSelector("#buttonId")).getDomAttribute("value"));
        System.out.println("\t " + driver.findElement(By.cssSelector("input[name*='Button2']")).getDomAttribute("value"));
    }
}
