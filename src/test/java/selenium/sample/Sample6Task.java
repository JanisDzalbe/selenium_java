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
        System.out.println("Element using xPath1");
        System.out.println("\t text for element 'heading_2' is " + driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println("Element using xPath2");
        System.out.println("\t text for element 'heading_2' is " + driver.findElement(By.xpath("//h2[2]")).getText());

        System.out.println("Element using xPath1");
        System.out.println("\t text for element 'Test Text 1' is " + driver.findElement(By.xpath("//*[@class='test'][1]")).getText());
        System.out.println("Element using xPath2");
        System.out.println("\t text for element 'Test Text 1' is " + driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());

        System.out.println("Element using xPath1");
        System.out.println("\t text for element 'Test text 2' is " + driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
        System.out.println("Element using xPath2");
        System.out.println("\t text for element 'Test text 2' is " + driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText());

        System.out.println("Element using xPath1");
        System.out.println("\t text for element 'Test text 3' is " + driver.findElements(By.xpath("//*[@class='test']")).get(1).getText());
        System.out.println("Element using xPath2");
        System.out.println("\t text for element 'Test text 3' is " + driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());

        System.out.println("Element using xPath1");
        System.out.println("\t text for element 'Test text 4' is " + driver.findElements(By.xpath("//*[@class='test']")).get(2).getText());
        System.out.println("Element using xPath2");
        System.out.println("\t text for element 'Test text 4' is " + driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());

        System.out.println("Element using xPath1");
        System.out.println("\t text for element 'Test text 5' is " + driver.findElements(By.xpath("//*[@class='test']")).get(3).getText());
        System.out.println("Element using xPath2");
        System.out.println("\t text for element 'Test text 5' is " + driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText());

        System.out.println("Element using xPath1");
        System.out.println("\t text for element 'Button This' is " + driver.findElement(By.xpath("//*[@name='randomButton1']")).getDomAttribute("value"));
        System.out.println("Element using xPath2");
        System.out.println("\t text for element 'Button This' is " + driver.findElement(By.xpath("//*[@value='This is a button']")).getDomAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
        System.out.println("Element using css selector");
        System.out.println("\t text for element 'heading_2' is " + driver.findElement(By.cssSelector("#heading_2")).getText());
        System.out.println("Element using css selector");
        System.out.println("\t text for element 'heading_2' is " + driver.findElement(By.cssSelector("h2:nth-of-type(2)")).getText());

        System.out.println("Element using css selector");
        System.out.println("\t text for element 'Test text 1' is " + driver.findElement(By.cssSelector(".test")).getText());
        System.out.println("Element using css selector");
        System.out.println("\t text for element 'Test text 1' is " + driver.findElement(By.cssSelector("#test1 .test")).getText());

        System.out.println("Element using css selector");
        System.out.println("\t text for element 'Test text 2' is " + driver.findElement(By.cssSelector(".twoTest")).getText());
        System.out.println("Element using css selector");
        System.out.println("\t text for element 'Test text 2' is " + driver.findElement(By.cssSelector("#test1 .twoTest")).getText());

        System.out.println("Element using css selector");
        System.out.println("\t text for element 'Test text 3' is " + driver.findElement(By.cssSelector("#test3 p:first-child")).getText());
        System.out.println("Element using css selector");
        System.out.println("\t text for element 'Test text 3' is " + driver.findElement(By.cssSelector("#test3 .test:first-child")).getText());

        System.out.println("Element using css selector");
        System.out.println("\t text for element 'Also button' is " + driver.findElement(By.cssSelector("input[id*='buttonId']")).getDomAttribute("value"));
        System.out.println("Element using css selector");
        System.out.println("\t text for element 'Also button' is " + driver.findElement(By.cssSelector("input[name='randomButton2'][type='button']")).getDomAttribute("value"));
    }
}
