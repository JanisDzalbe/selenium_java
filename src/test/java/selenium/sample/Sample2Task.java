package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

public class Sample2Task {
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
    public void findElementByID() throws Exception {
        System.out.println("Test method: findElementByID");
        System.out.println(driver.findElement(By.id("heading_2")).getText());
        System.out.println();
    }

    @Test
    public void findElementByName() throws Exception {
        System.out.println("Test method: findElementByName");
        WebElement buttonElement = driver.findElement(By.name("randomButton2"));
        System.out.println(buttonElement.getDomAttribute("id"));
        System.out.println(buttonElement.getDomProperty("value"));
        System.out.println();
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        System.out.println("Test method: findElementsByClassFirst");
        System.out.println(driver.findElements(By.className("test")).getFirst().getText());
        System.out.println();
    }

    @Test
    public void findElementByClassAll() throws Exception {
        System.out.println("Test method: findElementByClassAll");
        List<WebElement> testClassElements = driver.findElements(By.className("test"));
        System.out.println("Size of list = " + testClassElements.size());
        for (WebElement e : testClassElements) {
            System.out.println("text = " + e.getText());
        }
        System.out.println("Third text = " + testClassElements.get(2).getText());
        System.out.println();
    }
}
