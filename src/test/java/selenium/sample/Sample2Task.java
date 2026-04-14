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
    public void findElementByID() throws Exception {
        // get text "Heading 2 text" using id
        WebElement heading = driver.findElement(By.id("heading_2"));
        String text = heading.getText();
        System.out.println("Text by id: " + text);
        assertEquals("Heading 2 text", text);
    }

    @Test
    public void findElementByName() throws Exception {
        // get attribute "id" and "value" of button "This is also a button" using name
        WebElement button = driver.findElement(By.name("randomButton2"));
        String id = button.getAttribute("id");
        String value = button.getAttribute("value");
        System.out.println("Button id: " + id);
        System.out.println("Button value: " + value);
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        // get first text of class "test" (should be "Test Text 1")
        WebElement firstTest = driver.findElement(By.className("test"));
        String text = firstTest.getText();
        System.out.println("First text: " + text);
        assertEquals("Test Text 1", text);
    }

    @Test
    public void findElementByClassAll() throws Exception {
        // findElements returns ALL matching elements as a List
        List<WebElement> testElements = driver.findElements(By.className("test"));

        // size should be 5
        int size = testElements.size();
        System.out.println("Size: " + size);
        assertEquals(5, size);

        // print text of every element
        for (WebElement el : testElements) {
            System.out.println("Text: " + el.getText());
        }

        // third text (index 2) should be "Test Text 4"
        String thirdText = testElements.get(2).getText();
        System.out.println("Third text: " + thirdText);
        assertEquals("Test Text 4", thirdText);
    }
}