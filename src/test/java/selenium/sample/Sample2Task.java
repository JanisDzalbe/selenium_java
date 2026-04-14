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
        System.out.println("FindElementById");
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
        System.out.println("findElementByName");
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("id"));
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        System.out.println("findElementByClassFirst");
//        System.out.println(driver.findElement(By.className("test")).getText());
        System.out.println(driver.findElements(By.className("test")).getFirst().getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {
        System.out.println("findElementByClassAll");
        System.out.println(driver.findElements(By.className("test")).size());
        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));
        for (WebElement elementWithClass : driver.findElements(By.className("test"))) {
            System.out.println(elementWithClass.getText());
        }
        System.out.println(allElementsWithClass.get(2).getText());
    }
}
