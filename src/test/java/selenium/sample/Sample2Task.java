package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;
import org.openqa.selenium.WebElement;

import java.util.List;


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

     //method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
//         TODO:
//         get text "Heading 2 text" using id
        WebElement heading2 = driver.findElement(By.id("heading_2"));
        System.out.println("Text from ID 'heading_2': " + heading2.getText());
        driver.quit();
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//         get attribute "id" and "value" of button "This is also a button" using name
        WebElement randbutton = driver.findElement(By.name("randomButton2"));
        String buttonId = randbutton.getAttribute("id");
        String buttonValue = randbutton.getAttribute("value");
        System.out.println("Button ID: " + buttonId);
        System.out.println("Button Value: " + buttonValue);
        driver.quit();
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//         get first text of class "test" (should be "Test Text 1")
        WebElement firstText = driver.findElement(By.className("text"));
        System.out.println("First text with class 'text': " + firstText.getText());
        driver.quit();
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//         get size text of class "test" (should be 5)
        List<WebElement> allTextElements = driver.findElements(By.className("text"));
        System.out.println("Total elements with class 'text': " + allTextElements.size());
//         get text of class "test"
        for (int i = 0; i < allTextElements.size(); i++) {
            System.out.println("Text " + (i + 1) + ": " + allTextElements.get(i).getText());
        }
//         get third text of class "test" (should be "Test Text 4")
        if (allTextElements.size() >= 3) {
            System.out.println("Third text: " + allTextElements.get(2).getText());
        }

        driver.quit();
    }
}
