package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.utility.BootcampUtils;
import java.util.List;

import static java.lang.reflect.Array.get;

public class Sample2Task {
    WebDriver driver;

    @BeforeEach
    public void startingTests() throws Exception {
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }
    @Test
    public void findElementByID() throws Exception {
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }
    @Test
    public void findElementByName() throws Exception {
//         TODO:
//         get attribute "id" and "value" of button "This is also a button" using name
        WebElement button = driver.findElement(By.name("randomButton2"));
        System.out.println(button.getAttribute("id"));
        System.out.println(button.getAttribute("value"));
    }
    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//         get first text of class "test" (should be "Test Text 1")
        System.out.println(driver.findElement(By.className("test")).getText());
    }
    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//         get size text of class "test" (should be 5)
//         get text of class "test"
//         get third text of class "test" (should be "Test Text 4")
    List<WebElement> elementsTest = driver.findElements(By.className("test"));
        System.out.println(elementsTest.size());
        for (WebElement testElemet : elementsTest) {
            System.out.println(testElemet.getText());
        }
        System.out.println(elementsTest.get(2).getText());
          }
}