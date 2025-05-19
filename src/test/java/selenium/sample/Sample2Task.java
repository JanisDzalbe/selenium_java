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

public class Sample2Task {
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
    public void findElementByID() throws Exception {
        // TODO:
        // get text "Heading 2 text" using id
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
        // TODO:
        // get attribute "id" and "value" of button "This is also a button" using name
        System.out.println(driver.findElement(By.name("button2")).getAttribute("id"));
        System.out.println(driver.findElement(By.name("button2")).getAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        // TODO:
        // get first text of class "test" (should be "Test Text 1")
        System.out.println(driver.findElement(By.className("test")).getText());

    }

    @Test
    public void findElementByClassAll() throws Exception {
        // TODO:
        // get size text of class "test" (should be 5)
        List<WebElement> testElements = driver.findElements(By.className("test"));
        int size = testElements.size();
        System.out.println(size);

        System.out.println("All test texts:");
        for (WebElement element : testElements) {
            System.out.println(element.getText());
        }
        // Get third text of class "test" (should be "Test Text 4")
        System.out.println(testElements.get(2).getText());
    }
}