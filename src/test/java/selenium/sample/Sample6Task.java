package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        WebElement heading2_1 = driver.findElement(By.xpath("//h2"));
        assertEquals("Heading 2 text", heading2_1.getText());
        WebElement heading2_2 = driver.findElement(By.xpath("//*[contains(text(),'Heading 2')]"));
        assertEquals("Heading 2 text", heading2_2.getText());

        WebElement testText1_1 = driver.findElement(By.xpath("//p[@id='para1']"));
        assertEquals("Test Text 1", testText1_1.getText());
        WebElement testText1_2 = driver.findElement(By.xpath("//div[@class='container']/p[1]"));
        assertEquals("Test Text 1", testText1_2.getText());

        WebElement testText2_1 = driver.findElement(By.xpath("//p[@class='para']"));
        assertEquals("Test Text 2", testText2_1.getText());
        WebElement testText2_2 = driver.findElement(By.xpath("//div[@class='container']/p[2]"));
        assertEquals("Test Text 2", testText2_2.getText());

        WebElement testText3_1 = driver.findElement(By.xpath("//p[@name='para3']"));
        assertEquals("Test Text 3", testText3_1.getText());
        WebElement testText3_2 = driver.findElement(By.xpath("//div[@class='container']/p[3]"));
        assertEquals("Test Text 3", testText3_2.getText());

        WebElement testText4_1 = driver.findElement(By.xpath("//p[contains(text(),'Test Text 4')]"));
        assertEquals("Test Text 4", testText4_1.getText());
        WebElement testText4_2 = driver.findElement(By.xpath("//div[@class='container']/p[4]"));
        assertEquals("Test Text 4", testText4_2.getText());

        WebElement testText5_1 = driver.findElement(By.xpath("//div[@class='container']/p[5]"));
        assertEquals("Test Text 5", testText5_1.getText());
        WebElement testText5_2 = driver.findElement(By.xpath("//p[contains(text(),'Test Text 5')]"));
        assertEquals("Test Text 5", testText5_2.getText());

        WebElement button1 = driver.findElement(By.xpath("//button[@id='button2']"));
        assertEquals("This is also a button", button1.getText());
        WebElement button2 = driver.findElement(By.xpath("//button[contains(text(),'This is also')]"));
        assertEquals("This is also a button", button2.getText());

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "This is also a button"
        WebElement heading2_1 = driver.findElement(By.cssSelector("h2"));
        assertEquals("Heading 2 text", heading2_1.getText());
        WebElement heading2_2 = driver.findElement(By.cssSelector(".container h2"));
        assertEquals("Heading 2 text", heading2_2.getText());

        WebElement testText1_1 = driver.findElement(By.cssSelector("#para1"));
        assertEquals("Test Text 1", testText1_1.getText());
        WebElement testText1_2 = driver.findElement(By.cssSelector(".container p:first-of-type"));
        assertEquals("Test Text 1", testText1_2.getText());

        WebElement testText2_1 = driver.findElement(By.cssSelector(".para"));
        assertEquals("Test Text 2", testText2_1.getText());
        WebElement testText2_2 = driver.findElement(By.cssSelector(".container p:nth-of-type(2)"));
        assertEquals("Test Text 2", testText2_2.getText());

        WebElement testText3_1 = driver.findElement(By.cssSelector("p[name='para3']"));
        assertEquals("Test Text 3", testText3_1.getText());
        WebElement testText3_2 = driver.findElement(By.cssSelector(".container p:nth-of-type(3)"));
        assertEquals("Test Text 3", testText3_2.getText());

        WebElement button1 = driver.findElement(By.cssSelector("#button2"));
        assertEquals("This is also a button", button1.getText());
        WebElement button2 = driver.findElement(By.cssSelector("button:nth-of-type(2)"));
        assertEquals("This is also a button", button2.getText());
    }
}