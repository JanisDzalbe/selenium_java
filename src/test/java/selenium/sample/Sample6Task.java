package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

public class Sample6Task {
    WebDriver driver;

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByXPath() throws Exception {

        // Heading 2 text
        System.out.println(driver.findElement(By.xpath("//h2")).getText());
        System.out.println(driver.findElement(By.xpath("//*[text()='Heading 2 text']")).getText());

        // Test Text 1
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());
        System.out.println(driver.findElement(By.xpath("(//p)[1]")).getText());

        // Test Text 2
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText());
        System.out.println(driver.findElement(By.xpath("(//p)[2]")).getText());

        // Test Text 3
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());
        System.out.println(driver.findElement(By.xpath("(//p)[3]")).getText());

        // Test Text 4
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());
        System.out.println(driver.findElement(By.xpath("(//p)[4]")).getText());

        // Test Text 5
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText());
        System.out.println(driver.findElement(By.xpath("(//p)[5]")).getText());

        // This is also a button
        System.out.println(driver.findElement(By.xpath("//*[@value='This is also a button']")).getDomProperty("value"));
        System.out.println(driver.findElement(By.xpath("//input[contains(@value,'also')]")).getDomProperty("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {

        // Heading 2 text
        System.out.println(driver.findElement(By.cssSelector("h2")).getText());
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());

        // Test Text 1
        System.out.println(driver.findElement(By.cssSelector("p:nth-of-type(1)")).getText());
        System.out.println(driver.findElement(By.cssSelector(".text")).getText());

        // Test Text 2
        System.out.println(driver.findElement(By.cssSelector("p:nth-of-type(2)")).getText());
        System.out.println(driver.findElement(By.cssSelector(".text.unbelievable")).getText());

        // Test Text 3
        System.out.println(driver.findElement(By.cssSelector("p:nth-of-type(3)")).getText());

        // This is also a button
        System.out.println(driver.findElement(By.cssSelector("[value='This is also a button']")).getDomProperty("value"));
        System.out.println(driver.findElement(By.cssSelector("input[value='This is also a button']")).getDomProperty("value"));
    }
}