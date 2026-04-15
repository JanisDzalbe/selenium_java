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
        System.out.println("Using xPath:");

        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println(driver.findElements(By.xpath("//h2")).get(1).getText());

        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/*[@class='test']")).getText());
        System.out.println(driver.findElements(By.xpath("//div[@id='test1']/p")).getFirst().getText());

        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/p[@class='twoTest']")).getText());
        System.out.println(driver.findElements(By.xpath("//div[@id='test1']/p")).get(1).getText());

        System.out.println(driver.findElements(By.xpath("//div[@id='test3']/p")).getFirst().getText());

        System.out.println(driver.findElements(By.xpath("//div[@id='test3']/p")).get(1).getText());

        System.out.println(driver.findElement(By.xpath("//div[@id='test2']/p[@class='Test']")).getText());

        System.out.println(driver.findElement(By.xpath("//input[@id='buttonId']")).getDomProperty("value"));

//         TODO:
//          1-2 ways to find text: "Heading 2 text":
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "Test Text 4"
//          1-2 ways to find text: "Test Text 5"
//          1-2 ways to find text: "This is also a button"
    }

    @Test
    public void findElementByCssName() throws Exception {

        System.out.println("Using css selectors:");

        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());
        System.out.println(driver.findElements(By.cssSelector("h2")).get(1).getText());

        System.out.println(driver.findElement(By.cssSelector("#test1 > .test")).getText());
        System.out.println(driver.findElements(By.cssSelector("#test1 > p")).getFirst().getText());

        System.out.println(driver.findElement(By.cssSelector("#test1 > .twoTest")).getText());
        System.out.println(driver.findElements(By.cssSelector("#test1 > p")).get(1).getText());

        System.out.println(driver.findElements(By.cssSelector("#test3 > p")).getFirst().getText());

        System.out.println(driver.findElements(By.cssSelector("#test3 > p")).get(1).getText());

        System.out.println(driver.findElement(By.cssSelector("#test2 > p.Test")).getText());

        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getDomProperty("value"));

//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
    }
}
