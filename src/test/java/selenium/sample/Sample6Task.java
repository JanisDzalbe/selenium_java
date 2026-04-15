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
        System.out.println("\t text of element with id 'heading_2' is '" +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "'");
        System.out.println("\t text of element with id 'test1' is '" +
                driver.findElement(By.xpath("//*[@id='test1']")).getText() + "'");
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.xpath("//*[@class='test']")).getText() + "'");
        System.out.println("\t text of element with id 'test1' is '" +
                driver.findElement(By.xpath("//*[@id='test1']")).getText() + "'");
        System.out.println("\t text of element with class 'twoTest' is '" +
                driver.findElement(By.xpath("//*[@class='twoTest']")).getText() + "'");
        System.out.println("\t text of element with id 'test3' is '" +
                driver.findElement(By.xpath("//*[@id='test3']")).getText() + "'");
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.xpath("//*[@class='test']")).getText() + "'");
        System.out.println("\t text of element with id 'test3' is '" +
                driver.findElement(By.xpath("//*[@id='test3']")).getText() + "'");
        System.out.println("\t text of element with class 'test' is '" +
                driver.findElement(By.xpath("//*[@class='test']")).getText() + "'");
        System.out.println("\t text of element with id 'test2' is '" +
                driver.findElement(By.xpath("//*[@id='test2']")).getText() + "'");
        System.out.println("\t text of element with class 'Test' is '" +
                driver.findElement(By.xpath("//*[@class='Test']")).getText() + "'");
        System.out.println("\t text of element with id 'buttonId' is '" +
                driver.findElement(By.xpath("//*[@id='buttonId']")).getText() + "'");
        System.out.println("\t text of element with value 'This is also a button' is '" +
                driver.findElement(By.xpath("//*[@value='This is also a button']")).getText() + "'");
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

        System.out.println(driver.findElement(By.cssSelector("#test1 .test")).getText());

        System.out.println(driver.findElement(By.cssSelector("#test1 > .twoTest")).getText());

        System.out.println(driver.findElement(By.cssSelector("#test3 > .test:nth-of-type(1)")).getText());

        System.out.println(driver.findElements(By.cssSelector("#test3 > .test")).getFirst().getText());

        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getDomAttribute("value"));

        System.out.println(driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomAttribute("value"));
    }
}
