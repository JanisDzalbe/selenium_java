package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

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
        System.out.println("=== XPATH LOCATORS ===");

        System.out.println("Heading 2 text (by id): "
                + driver.findElement(By.xpath("//*[@id='heading_2']")).getText());
        System.out.println("Heading 2 text (by tag + text): "
                + driver.findElement(By.xpath("//h2[text()='Heading 2 text']")).getText());

        System.out.println("Test Text 1 (by exact text): "
                + driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());
        System.out.println("Test Text 1 (first .test): "
                + driver.findElement(By.xpath("//*[@class='test'][1]")).getText());

        System.out.println("Test Text 2: "
                + driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText());
        System.out.println("Test Text 3: "
                + driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());
        System.out.println("Test Text 4: "
                + driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());
        System.out.println("Test Text 5: "
                + driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText());

        System.out.println("\"This is also a button\" (by name): "
                + driver.findElement(By.xpath("//*[@name='randomButton2']")).getAttribute("value"));
        System.out.println("\"This is also a button\" (by value): "
                + driver.findElement(By.xpath("//*[@value='This is also a button']")).getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
        System.out.println("=== CSS LOCATORS ===");

        System.out.println("Heading 2 text (#heading_2): "
                + driver.findElement(By.cssSelector("#heading_2")).getText());
        System.out.println("Heading 2 text (h2#heading_2): "
                + driver.findElement(By.cssSelector("h2#heading_2")).getText());

        List<WebElement> testTexts = driver.findElements(By.cssSelector(".test"));

        System.out.println("Test Text 1 (.test list index 0): "
                + testTexts.get(0).getText());
        System.out.println("Test Text 2 (.test list index 1): "
                + testTexts.get(1).getText());
        System.out.println("Test Text 3 (.test list index 2): "
                + testTexts.get(2).getText());
        System.out.println("Test Text 4 (.test list index 3): "
                + testTexts.get(3).getText());
        System.out.println("Test Text 5 (.test list index 4): "
                + testTexts.get(4).getText());

        System.out.println("\"This is also a button\" (by name): "
                + driver.findElement(By.cssSelector("[name='randomButton2']")).getDomProperty("value"));
        System.out.println("\"This is also a button\" (by value): "
                + driver.findElement(By.cssSelector("[value='This is also a button']")).getDomProperty("value"));
    }
}
