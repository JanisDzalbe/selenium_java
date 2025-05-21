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
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByXPath() throws Exception {
        System.out.println("Find element by id using xPath:");
//         TODO:
//        1-2 ways to find text: "Heading 2 text":
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "Test Text 4"
//        1-2 ways to find text: "Test Text 5"
//        1-2 ways to find text: "This is also a button"

        /* LECTURE CODE*/
        System.out.println(driver.findElement(By.xpath("//*[@id='test1']//*[@class='test']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test1']//*[@class='twoTest']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']//*[contains(text(),'3')]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']//*[text()='Test Text 4']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@id='test2']//*[@class='Test']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@id='test2']//*[@class='Test']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@id='buttonId']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@name='randomButton2']")).getAttribute("value"));

        /*MY VARIANT*/
//        System.out.println("\t text of element with id 'heading_2' is '" +
//                driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "'");
//
//        System.out.println("\t text of element with text 'Heading 2 text' is '" +
//                driver.findElement(By.xpath("//*[contains(text(), 'Heading 2 text')]")).getText() + "'");
//        System.out.println("\t text of element with text 'Test Text 1' is '" +
//                driver.findElement(By.xpath("//*[contains(text(), 'Test Text 1')]")).getText() + "'");
//        System.out.println("\t text of element with class 'test' which is a direct child of element with id 'test1' is '" +
//                driver.findElement(By.xpath("//*[@id='test1']/p[contains(@class, 'test')]")).getText() + "'");
//        System.out.println("\t text of element with text 'Test Text 2' is '" +
//                driver.findElement(By.xpath("//*[contains(text(), 'Test Text 2')]")).getText() + "'");
//        System.out.println("\t text of element with class 'twoTest' which is a direct child of element with id 'test1' is '" +
//                driver.findElement(By.xpath("//*[@id='test1']/p[contains(@class, 'twoTest')]")).getText() + "'");
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());

        System.out.println(driver.findElement(By.cssSelector("#test1 > .test")).getText());
        System.out.println(driver.findElement(By.cssSelector("#test1 .twoTest")).getText());

        System.out.println(driver.findElement(By.cssSelector("#test3 .test:nth-of-type(1)")).getText());
        System.out.println(driver.findElements(By.cssSelector("#test3 .test")).get(0).getText());

        System.out.println(driver.findElement(By.cssSelector("input[type='button']#buttonId[name='randomButton2']")).getAttribute("value"));

    }
}
