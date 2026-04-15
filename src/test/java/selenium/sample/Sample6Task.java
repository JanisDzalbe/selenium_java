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
        System.out.println("Heading 2: "
                + driver.findElement(By.xpath("//h2[@id='heading_2']")).getText());
        System.out.println("Heading 2 (by text): "
                + driver.findElement(By.xpath("//h2[text()='Heading 2 text']")).getText());
        System.out.println("Test Text 1: "
                + driver.findElement(By.xpath("//div[@id='test1']/p[1]")).getText());
        System.out.println("Test Text 2: "
                + driver.findElement(By.xpath("//p[@class='twoTest']")).getText());
        System.out.println("Test Text 3: "
                + driver.findElement(By.xpath("//div[@id='test3']/p[1]")).getText());
        System.out.println("Test Text 4: "
                + driver.findElement(By.xpath("//div[@id='test3']/p[2]")).getText());
        System.out.println("Test Text 5: "
                + driver.findElement(By.xpath("//p[@class='Test']")).getText());
        System.out.println("Button text: "
                + driver.findElement(By.xpath("//input[@id='buttonId']")).getDomProperty("value"));
        System.out.println("Button text (by name): "
                + driver.findElement(By.xpath("//input[@name='randomButton2']")).getDomProperty("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
        System.out.println("Heading 2: "
                + driver.findElement(By.cssSelector("#heading_2")).getText());
        System.out.println("Test Text 1: "
                + driver.findElement(By.cssSelector("#test1 .test")).getText());
        System.out.println("Test Text 2: "
                + driver.findElement(By.cssSelector(".twoTest")).getText());
        System.out.println("Test Text 3: "
                + driver.findElement(By.cssSelector("#test3 p:nth-child(1)")).getText());
        System.out.println("Button text: "
                + driver.findElement(By.cssSelector("#buttonId")).getDomProperty("value"));
        System.out.println("Button text (by attribute): "
                + driver.findElement(By.cssSelector("input[name='randomButton2']")).getDomProperty("value"));
    }
}