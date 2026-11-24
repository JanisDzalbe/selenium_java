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
        System.out.println(driver.findElement(By.xpath("//*[text()='Heading 2 text']")).getText());
        //System.out.println(driver.findElement(By.xpath"//h2[@id=/*heading_2/"]")).getText());

        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText());

        System.out.println(driver.findElement(By.xpath("//*[@value='This is also a button']")).getDomProperty("value"));//text didnt work since its a value type
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"

        System.out.println(driver.findElement(By.cssSelector("h2")).getText());

        System.out.println(driver.findElement(By.cssSelector("p.test")).getText());

        System.out.println(driver.findElement(By.cssSelector("p.twotest")).getText());

        System.out.println(driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText());

        System.out.println(driver.findElement(By.cssSelector("#test3 .test:first-child")).getText());

        System.out.println(driver.findElement(By.cssSelector("[value='This is also a button']")).getDomProperty("value"));
    }
}
