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
        System.out.println(driver.findElement(By.xpath("//body/h2[@id='heading_2']")));
        System.out.println(driver.findElement(By.xpath("//*[@id='heading_2']")));
//          1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.xpath("//body/div[@id='test1']/p[@class='test']")));
        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/*[@class='test']")));
//          1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.xpath("//body/div[@id='test1']/p[@class='twoTest']")));
        System.out.println(driver.findElement(By.xpath("//*[@id='test1']/*[@class='twoTest']")));
//          1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.xpath("//body/div[@id='test3']/p[@class='test']")));
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/*[@class='test']")));
//          1-2 ways to find text: "Test Text 4"
        System.out.println(driver.findElement(By.xpath("//body/div[@id='test3']/p[2]")));
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/*[@class='test'][2]")));
//          1-2 ways to find text: "Test Text 5"
        System.out.println(driver.findElement(By.xpath("//body/div[@id='test2']/p[@class='Test']")));
        System.out.println(driver.findElement(By.xpath("//*[@id='test2']/*[@class='Test']")));
//          1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.xpath("//body/input[@id='buttonId']")));
        System.out.println(driver.findElement(By.xpath("//*[@id='buttonId']")));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
        System.out.println(driver.findElement(By.cssSelector("body>h2#heading_2")));
        System.out.println(driver.findElement(By.cssSelector("#heading_2")));
//          1-2 ways to find text: "Test Text 1"
        System.out.println(driver.findElement(By.cssSelector("body>div#test1>p.test")));
        System.out.println(driver.findElement(By.cssSelector("#test1 > p.test") ));
//          1-2 ways to find text: "Test Text 2"
        System.out.println(driver.findElement(By.cssSelector("body>div#test1>p.twoTest")));
        System.out.println(driver.findElement(By.cssSelector("#test1 > p.twoTest")));
//          1-2 ways to find text: "Test Text 3"
        System.out.println(driver.findElement(By.cssSelector("body>div#test3> p:nth-child(1)")));
        System.out.println(driver.findElement(By.cssSelector("#test3 > p:first-of-type")));
//          1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("body>input#buttonId")));
        System.out.println(driver.findElement(By.cssSelector("#buttonId")));
    }
}
