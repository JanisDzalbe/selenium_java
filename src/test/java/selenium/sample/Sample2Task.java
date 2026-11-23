package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

/**
 * Activity 2: Locating web elements by ID, name, and className, and retrieving text and DOM attributes.
 */
public class Sample2Task {
    WebDriver driver;

    /**
     * Initializes ChromeDriver and opens the locators example page before each test.
     *
     * @throws Exception if driver initialization fails
     */
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    /**
     * Closes the browser after each test.
     *
     * @throws Exception if browser teardown fails
     */
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    /**
     * Finds and prints the text of "Heading 2 text" using its ID locator.
     *
     * @throws Exception if the element cannot be found
     */
    @Test
    public void findElementByID() throws Exception {
        // get text "Heading 2 text" using id
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    /**
     * Finds the button "This is also a button" by its name attribute and prints its "id" and "value" attributes.
     *
     * @throws Exception if the element cannot be found
     */
    @Test
    public void findElementByName() throws Exception {
        // get attribute "id" and "value" of button "This is also a button" using name
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("id"));
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("value"));
    }

    /**
     * Finds and prints the first element with class "test" ("Test Text 1").
     *
     * @throws Exception if the element cannot be found
     */
    @Test
    public void findElementByClassFirst() throws Exception {
        // get first text of class "test" (should be "Test Text 1")
        System.out.println(driver.findElement(By.className("test")).getText());
    }

    /**
     * Finds all elements with class "test", prints the count, loops through all texts, and prints "Test Text 4".
     *
     * @throws Exception if elements cannot be retrieved
     */
    @Test
    public void findElementByClassAll() throws Exception {
        // get size text of class "test" (should be 5)
        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));
        System.out.println(allElementsWithClass.size());

        // get text of class "test"
        for (WebElement elementWithClass : allElementsWithClass) {
            System.out.println(elementWithClass.getText());
        }

        // get third text of class "test" (should be "Test Text 4")
        System.out.println(allElementsWithClass.get(3).getText());
    }
}
