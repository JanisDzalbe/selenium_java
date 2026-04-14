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
        String text = driver.findElement(By.id("heading2")).getText();
        System.out.println(text);
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//          get attribute "id" and "value" of button "This is also a button" using name
        WebElement button = driver.findElement(By.name("randomButton2"));
        String id = button.getAttribute("id");
        String value = button.getAttribute("value");

        System.out.println(id);
        System.out.println(value);
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//          get first text of class "test" (should be "Test Text 1")
        String text = driver.findElement(By.className("test")).getText();
        System.out.println(text);
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//          get size text of class "test" (should be 5)
//          get text of class "test"
//          get third text of class "test" (should be "Test Text 4")
        List<WebElement> elements = driver.findElements(By.className("test"));
        int size = elements.size();
        System.out.println("Size: " + size);
        assertEquals(5, size);

        for (WebElement el : elements) {
            System.out.println(el.getText());
        }
        String thirdText = elements.get(2).getText();
        System.out.println("Third: " + thirdText);
        assertEquals("Test Text 4", thirdText);
    }
}
