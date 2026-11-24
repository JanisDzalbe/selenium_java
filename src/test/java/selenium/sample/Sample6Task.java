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
        System.out.println(driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'text 2')]")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@id='test3']")).getText());

        System.out.println(driver.findElement(By.xpath("//div[5]/p[2]")).getText());

        // for button using xpath
        System.out.println(driver.findElement(By.xpath("//*[@value='This is also a button']")).getDomProperty("value"));
        System.out.println(driver.findElement(By.xpath("//*[@id='buttonId']")).getDomProperty("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"

        System.out.println(driver.findElement(By.cssSelector("[id='heading_2']")).getText());
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());

        System.out.println(driver.findElement(By.cssSelector("[id='test1']")).getText());

        System.out.println(driver.findElement(By.cssSelector("#test1>p.twoTest")).getText());

        System.out.println(driver.findElement(By.cssSelector("[id='test3']")).getText());

        System.out.println(driver.findElement(By.cssSelector("[name='randomButton2']")).getDomProperty("value"));
    }
}
