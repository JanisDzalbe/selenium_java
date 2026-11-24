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
        // 1-2 ways to find text: "Heading 2 text"
        System.out.println("Heading 2 text (nth h2): " + driver.findElement(By.xpath("(//h2)[2]")).getText());
        System.out.println("Heading 2 text (text): " + driver.findElement(By.xpath("//*[text()='Heading 2 text']")).getText());

        // 1-2 ways to find text: "Test Text 1"
        System.out.println("Test Text 1 (class): " + driver.findElement(By.xpath("//*[@class='test']")).getText());
        System.out.println("Test Text 1 (text): " + driver.findElement(By.xpath("//p[text()='Test Text 1']")).getText());

        // 1-2 ways to find text: "Test Text 2"
        System.out.println("Test Text 2 (class): " + driver.findElement(By.xpath("//*[@class='twoTest']")).getText());
        System.out.println("Test Text 2 (text): " + driver.findElement(By.xpath("//p[text()='Test Text 2']")).getText());

        // 1-2 ways to find text: "Test Text 3"
        System.out.println("Test Text 3 (contains): " + driver.findElement(By.xpath("//*[contains(text(), 'Test Text 3')]")).getText());
        System.out.println("Test Text 3 (following-sibling): " + driver.findElement(By.xpath("//p[text()='Test Text 3']")).getText());

        // 1-2 ways to find text: "Test Text 4"
        System.out.println("Test Text 4 (text): " + driver.findElement(By.xpath("//p[text()='Test Text 4']")).getText());
        System.out.println("Test Text 4 (following-sibling): " + driver.findElement(By.xpath("//*[contains(text(), 'Test Text 4')]")).getText());

        // 1-2 ways to find text: "Test Text 5"
        System.out.println("Test Text 5 (class): " + driver.findElement(By.xpath("//*[@class='Test']")).getText());
        System.out.println("Test Text 5 (text): " + driver.findElement(By.xpath("//*[contains(text(), 'Test Text 5')]")).getText());

        // 1-2 ways to find text: "This is also a button"
        System.out.println("Button text (value): " + driver.findElement(By.xpath("//*[@value='This is also a button']")).getDomAttribute("value"));
        System.out.println("Button text (name): " + driver.findElement(By.xpath("//*[@name='randomButton2']")).getDomAttribute("value"));
    }


    @Test
    public void findElementByCssName() throws Exception {
        //TODO:
        // 1-2 ways to find text: "Heading 2 text"
        System.out.println("Heading 2 text (nth-of-type): " + driver.findElement(By.cssSelector("h2:nth-of-type(2)")).getText());


        // 1-2 ways to find text: "Test Text 1"
        System.out.println("Test Text 1 (class): " + driver.findElement(By.cssSelector(".test")).getText());
        System.out.println("Test Text 1 (p.test): " + driver.findElement(By.cssSelector("p.test")).getText());

        // 1-2 ways to find text: "Test Text 2"
        System.out.println("Test Text 2 (class): " + driver.findElement(By.cssSelector(".twoTest")).getText());
        System.out.println("Test Text 2 (adjacent sibling): " + driver.findElement(By.cssSelector("p.twoTest")).getText());

        // 1-2 ways to find text: "Test Text 3"
        System.out.println("Test Text 3 (2nd div 3rd p): " + driver.findElement(By.cssSelector("div + div > p:nth-of-type(3)")).getText());


        // 1-2 ways to find text: "This is also a button"
        System.out.println("Button text (value): " + driver.findElement(By.cssSelector("[value='This is also a button']")).getDomAttribute("value"));
        System.out.println("Button text (name): " + driver.findElement(By.cssSelector("[name='randomButton2']")).getDomAttribute("value"));
    }
}
