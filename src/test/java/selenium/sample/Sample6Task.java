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
//         TODO:
//        1-2 ways to find text: "Heading 2 text":
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "Test Text 4"
//        1-2 ways to find text: "Test Text 5"
//        1-2 ways to find text: "This is also a button"


        System.out.println("Find element by id using xPath:");
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "'");
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.xpath("//*[@class='test']")).getText() + "'");
        System.out.println("\t text of element with class 'twoTest' is '" +
                driver.findElement(By.xpath("//*[@class='twoTest']")).getText() + "'");
        System.out.println("\t text of element with id 'test3' is '" +
                driver.findElement(By.xpath("//*[@id='test3']/p")).getText() + "'");
        System.out.println("\t text of element with id 'test3' is '" +
                driver.findElement(By.xpath("//*[@id='test3']/p[text()='Test Text 4']")).getText() + "'");


        System.out.println("\t text of element with class 'text' is '" +
                driver.findElement(By.xpath("//*[@class='Test']")).getText() + "'");


        System.out.println("\t value of element with name 'randomButton2' is '" +
                driver.findElement(By.xpath("//*[@name='randomButton2']")).getAttribute("value") + "'");
        System.out.println("\t value of element with type 'button' is '" +
                driver.findElement(By.xpath("//*[@type='button']")).getAttribute("value") + "'");
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "This is also a button"


        System.out.println("Find element by id using xPath:");
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.cssSelector("#heading_2")).getText() + "'");
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.cssSelector(".test")).getText() + "'");
        System.out.println("\t text of element with class 'twoTest' is '" +
                driver.findElement(By.cssSelector(".twoTest")).getText() + "'");
        System.out.println("\t text of element with id 'test3' is '" +
                driver.findElement(By.cssSelector("#test3 > p")).getText() + "'");
        System.out.println("\t value of element with name 'randomButton2' is '" +
                driver.findElement(By.cssSelector("input[name='randomButton2'][type='button']")).getAttribute("value") + "'");
    }
}
