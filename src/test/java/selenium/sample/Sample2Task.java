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
        // Get text "Heading 2 text" using id
        WebElement heading2 = driver.findElement(By.id("heading_2"));
        String headingText = heading2.getText();
        System.out.println("Text from heading 2: " + headingText);
        assertEquals("Heading 2 text", headingText);
    }

    @Test
    public void findElementByName() throws Exception {
        // Get attribute "id" and "value" of button "This is also a button" using name
        WebElement button = driver.findElement(By.name("button2"));
        String buttonId = button.getAttribute("id");
        String buttonValue = button.getAttribute("value");

        System.out.println("Button ID: " + buttonId);
        System.out.println("Button Value: " + buttonValue);

        assertEquals("button2_id", buttonId);
        assertEquals("This is also a button", buttonValue);
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        // Get first text of class "test" (should be "Test Text 1")
        WebElement firstTestElement = driver.findElement(By.className("test"));
        String firstTestText = firstTestElement.getText();

        System.out.println("First test text: " + firstTestText);
        assertEquals("Test Text 1", firstTestText);
    }

    @Test
    public void findElementByClassAll() throws Exception {
        // Get size text of class "test" (should be 5)
        List<WebElement> testElements = driver.findElements(By.className("test"));
        int size = testElements.size();

        // Get text of all class "test" elements
        System.out.println("Number of elements with class 'test': " + size);
        assertEquals(5, size);

        System.out.println("All test texts:");
        for (WebElement element : testElements) {
            System.out.println(element.getText());
        }

        // Get third text of class "test" (should be "Test Text 4")
        String thirdTestText = testElements.get(2).getText();
        System.out.println("Third test text: " + thirdTestText);
        assertEquals("Test Text 4", thirdTestText);
    }
}