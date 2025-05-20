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

        //Obviously text would work pretty much anywhere, but that's kinda boring
        System.out.println(driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
        System.out.println(driver.findElement(By.xpath("//*[text() = 'Heading 2 text']")).getText());

        System.out.println(driver.findElement(By.xpath("//div[@id='test1']//p[@class = 'test']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@id='test1']//p[contains(@class,'test')]")).getText());

        System.out.println(driver.findElement(By.xpath("//div[@id='test1']//p[@class = 'twoTest']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@id='test1']//p[contains(@class,'twoTest')]")).getText());

        // is [1] here best practice?
        System.out.println(driver.findElement(By.xpath("//div[@id='test3']//p[@class = 'test'][1]")).getText());
//        System.out.println(driver.findElement(By.xpath("//div[@id='test3']//p[contains(@class,'test')][1]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Text 3')]")).getText());

        System.out.println(driver.findElement(By.xpath("//div[@id='test3']//p[@class = 'test'][2]")).getText());
//        System.out.println(driver.findElement(By.xpath("//div[@id='test3']//p[contains(@class,'test')][2]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Text 3')]")).getText());

        System.out.println(driver.findElement(By.xpath("//div[@id='test2']//p[@class = 'Test']")).getText());
        System.out.println(driver.findElement(By.xpath("//div[@id='test2']//p[contains(@class,'Test')]")).getText());

        System.out.println(driver.findElement(By.xpath("//input[@id='buttonId']")).getDomAttribute("value"));
        System.out.println(driver.findElement(By.xpath("//input[@name='randomButton2']")).getDomAttribute("value"));

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("h2#heading_2")).getText());

        System.out.println(driver.findElement(By.cssSelector("div#test1 > .test")).getText());
        System.out.println(driver.findElement(By.cssSelector("div#test1 .test")).getText());

        System.out.println(driver.findElement(By.cssSelector("div#test1 > .twoTest")).getText());
        System.out.println(driver.findElement(By.cssSelector("div#test1  .twoTest")).getText());

        System.out.println(driver.findElement(By.cssSelector("div#test3 > .test")).getText());
        System.out.println(driver.findElement(By.cssSelector("div#test3  .test")).getText());

        System.out.println(driver.findElement(By.cssSelector("input#buttonId")).getDomAttribute("value"));
        System.out.println(driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomAttribute("value"));


    }
}
