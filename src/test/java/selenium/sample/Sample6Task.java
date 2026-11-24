package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        //Way 1
        System.out.println("Find element by id using xPath:");
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "'");

        System.out.println("Find element by all classes using xPath:");
        System.out.println("\t text of element with class 'text' is '" +
                driver.findElement(By.xpath("//*[@class='test']")).getText() + "'");

        System.out.println("Find element by all classes using xPath:");
        System.out.println("\t text of element with class 'text' is '" +
                driver.findElement(By.xpath("//*[@class='twoTest']")).getText() + "'");

        System.out.println("Find element by Text using xPath:");
        System.out.println("\t class of element with text 'Test Text 3' is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 3']")).getDomAttribute("class")+ "'");

                System.out.println("Find element by Text using xPath:");
        System.out.println("\t class of element with text 'Test Text 4' is '" +
                        driver.findElement(By.xpath("//*[text()='Test Text 4']")).getDomAttribute("class")+ "'");

                System.out.println("Find element by Text using xPath:");
        System.out.println("\t class of element with text 'Test Text 5' is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 5']")).getDomAttribute("class")+ "'");

                System.out.println("Find element by id using xPath:");
        System.out.println("\t text of element with text 'This is also a button' is '" +
                driver.findElement(By.xpath("//*[@name='randomButton2']")).getText() + "'");

        //Way2

        System.out.println("Find element by id using xPath:");
        System.out.println("\t text of element with text 'This is also a button' is '" +
                driver.findElement(By.xpath("//*[@id='buttonId']")).getText() + "'");



    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
        //Way 1
        System.out.println("Find element by id using CSS:");
        System.out.println("\t text of element with id 'heading_2' is '" + driver.findElement(By.cssSelector("#heading_2")).getText() + "'");
        System.out.println("Find element by id using CSS:");
        System.out.println("\t text of element with class 'Test Text 1' is '" + driver.findElement(By.cssSelector(".test")).getText() + "'");
        System.out.println("Find element by id using CSS:");
        System.out.println("\t text of element with class 'Test Text 2' is '" + driver.findElement(By.cssSelector(".twotest")).getText() + "'");

        //assertEquals("\t text of element with class 'Test Text 3' is , driver.findElement(By.cssSelector("#test3 > .test")).getText());
        System.out.println("Find element by id using CSS:");
        System.out.println("\t text of element with class 'Test Text 3' is '" + driver.findElement(By.cssSelector("#test3 > p:first-child")).getText());
        System.out.println("Find element by id using CSS:");
        assertEquals("This is also a button", driver.findElement(By.cssSelector("#buttonId")).getDomProperty("value"));
    }
}
