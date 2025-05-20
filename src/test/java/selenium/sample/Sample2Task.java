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
        driver.get("https://acctabootcamp.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
//         TODO:
//         get text "Heading 2 text" using id
        String h2 = driver.findElement(By.id("heading_2")).getText();
        System.out.println("H2: " + h2);
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//         get attribute "id" and "value" of button "This is also a button" using name
        WebElement btn = driver.findElement(By.name("randomButton2"));
        String id = btn.getAttribute("id");
        String value = btn.getAttribute("value");

        System.out.println("Id: " + id);
        System.out.println("Value: " + value);
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//         get first text of class "test" (should be "Test Text 1")
        WebElement elem = driver.findElement(By.className("test"));
        String txt = elem.getText();

        System.out.println("Element text: " + txt);
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//         get size text of class "test" (should be 5)
//         get text of class "test"
//         get third text of class "test" (should be "Test Text 4")
        List<WebElement> elements = driver.findElements(By.className("test"));

        int size = elements.size();
        System.out.println("List size: " + size);

        for (WebElement el : elements) {
            System.out.println("Text: " + el.getText());
        }

        String txt4 = elements.get(2).getText();
        System.out.println("Third text: " + txt4);
    }
}
