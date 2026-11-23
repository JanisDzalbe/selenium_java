
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

    // Runs before each test
    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();

        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // Runs after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    // 1. Find element by ID
    @Test
    public void findElementByID() throws Exception {

        WebElement heading = driver.findElement(By.id("heading_2"));

        System.out.println(heading.getText());
    }

    // 2. Find element by Name
    @Test
    public void findElementByName() throws Exception {

        WebElement button = driver.findElement(By.name("randomButton2"));

        System.out.println(button.getAttribute("id"));
        System.out.println(button.getAttribute("value"));
    }

    // 3. Find first element by Class Name
    @Test
    public void findElementByClassFirst() throws Exception {

        WebElement firstElement = driver.findElement(By.className("test"));

        System.out.println(firstElement.getText());
    }

    // 4. Find all elements by Class Name
    @Test
    public void findElementByClassAll() throws Exception {

        List<WebElement> elements = driver.findElements(By.className("test"));

        // Print number of elements
        System.out.println("Number of elements: " + elements.size());

        // Print text of all elements
        for (WebElement element : elements) {
            System.out.println(element.getText());
        }

        // Print third element
        System.out.println("Third element: " + elements.get(2).getText());
    }
}
