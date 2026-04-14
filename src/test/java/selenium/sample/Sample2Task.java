package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

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
//         TODO:
//          get text "Heading 2 text" using id
        assertEquals("Heading 2 text", driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//          get attribute "id" and "value" of button "This is also a button" using name
        WebElement button = driver.findElement(By.name("randomButton2"));
        System.out.println(button.getDomProperty("id"));
        System.out.println(button.getDomProperty("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//          get first text of class "test" (should be "Test Text 1")
        assertEquals("Test Text 1", driver.findElements(By.className("test")).getFirst().getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//          get size text of class "test" (should be 5)
        assertEquals(5, driver.findElements(By.className("test")).size());
//          get text of class "test"
        System.out.println(driver.findElements(By.className("test")).getFirst().getText());
//          get third text of class "test" (should be "Test Text 4")
        assertEquals("Test Text 4", driver.findElements(By.className("test")).get(2).getText());
    }
}
