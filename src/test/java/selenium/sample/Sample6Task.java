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
        System.out.println("\tHeading 2 text (by tag): " +
                driver.findElement(By.xpath("//h2[text()='Heading 2 text']")).getText());
        System.out.println("\tHeading 2 text (by id): " +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println("\tTest Text 1: " +
                driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println("\tTest Text 2: " +
                driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println("\tTest Text 3: " +
                driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());
//        1-2 ways to find text: "Test Text 4"
        System.out.println("\tTest Text 4: " +
                driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());
//        1-2 ways to find text: "Test Text 5"
        System.out.println("\tTest Text 5: " +
                driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println("\tButton text (value='This is also a button'): " +
                driver.findElement(By.xpath("//*[@value='This is also a button']")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println("\tHeading 2 text (by tag): " +
                driver.findElement(By.cssSelector("h2")).getText());
        System.out.println("\tHeading 2 text (by id): " +
                driver.findElement(By.cssSelector("#heading_1")).getText());
//        1-2 ways to find text: "Test Text 1"
        System.out.println("\tTest Text 1: " +
                driver.findElement(By.cssSelector("p:nth-of-type(1)")).getText());
//        1-2 ways to find text: "Test Text 2"
        System.out.println("\tTest Text 2: " +
                driver.findElement(By.cssSelector("p:nth-of-type(2)")).getText());
//        1-2 ways to find text: "Test Text 3"
        System.out.println("\tTest Text 3: " +
                driver.findElement(By.cssSelector("p:nth-of-type(3)")).getText());
//        1-2 ways to find text: "This is also a button"
        System.out.println("\tButton text (value='This is also a button'): " +
                driver.findElement(By.cssSelector("[value='This is also a button']")).getAttribute("value"));
    }
}
