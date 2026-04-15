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
        System.out.println("\"Heading 2 text\"");
        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println(driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());

        System.out.println("\"Test Text 1\"");
        System.out.println(driver.findElement(By.xpath("//div[@id='test1']/p[@class='test']")).getText());
        System.out.println(driver.findElement(By.xpath("//div/p[@class='test'][1]")).getText());

        System.out.println("\"Test Text 2\"");
        System.out.println(driver.findElement(By.xpath("//div[@id='test1']/p[@class='twoTest']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@class='twoTest']")).getText());

        System.out.println("\"Test Text 3\"");
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Test Text 3')]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/*[contains(text(), '3')]")).getText());

        System.out.println("\"Test Text 4\"");
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Test Text 4')]")).getText());

        System.out.println("\"Test Text 5\"");
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Test Text 5')]")).getText());

        System.out.println("\"This is also a button\"");
        System.out.println(driver.findElement(By.xpath("//input[@id='buttonId']")).getDomAttribute("value"));
        System.out.println(driver.findElement(By.xpath("//*[@name='randomButton2']")).getDomAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
        System.out.println("\"Heading 2 text\"");
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());

        System.out.println("\"Test Text 1\"");
        System.out.println(driver.findElement(By.cssSelector("#test1 .test")).getText());
        System.out.println(driver.findElement(By.cssSelector("#test1 > p.test")).getText());

        System.out.println("\"Test Text 2\"");
        System.out.println(driver.findElement(By.cssSelector(".twoTest")).getText());
        System.out.println(driver.findElement(By.cssSelector("#test1 p.twoTest")).getText());

        System.out.println("\"Test Text 3\"");
        System.out.println(driver.findElements(By.cssSelector("#test3 p.test")).getFirst().getText());

        System.out.println("\"This is also a button\"");
        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getDomAttribute("value"));
        System.out.println(driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomAttribute("value"));
    }
}
