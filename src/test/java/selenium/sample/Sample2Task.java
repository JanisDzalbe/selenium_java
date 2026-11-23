package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
//         TODO:
//          get text "Heading 2 text" using id
        String headingText = driver.findElement(By.id("heading_2")).getText();
        System.out.println("Heading text: " + headingText);

        Assertions.assertEquals("Heading 2 text", headingText);
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//          get attribute "id" and "value" of button "This is also a button" using name
        WebElement button = driver.findElement(By.name("randomButton2"));

        String idValue = button.getDomAttribute("id");
        String valueAttr = button.getDomAttribute("value");

        System.out.println("Button id: " + idValue);
        System.out.println("Button value: " + valueAttr);
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//          get first text of class "test" (should be "Test Text 1")
        String firstText = driver.findElement(By.className("test")).getText();
        System.out.println("First text: " + firstText);

        Assertions.assertEquals("Test Text 1", firstText);
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//          get size text of class "test" (should be 5)
//          get text of class "test"
//          get third text of class "test" (should be "Test Text 4")
        List<WebElement> elements = driver.findElements(By.className("test"));

        // 1. Check and print total count (should be 5)
        int size = elements.size();
        System.out.println("Total elements with class 'test': " + size);
        Assertions.assertEquals(5, size);

        // 2. Print text of each element with class "test"
        for (WebElement element : elements) {
            System.out.println("Item text: " + element.getText());
        }

        // 3. Get the third element (index 2 -> "Test Text 4")
        String thirdText = elements.get(2).getText();
        System.out.println("Third text: " + thirdText);
        Assertions.assertEquals("Test Text 4", thirdText);
    }
    }

