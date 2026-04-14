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
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
        // get text "Heading 2 text" using id
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
        // get attribute "id" and "value" of button "This is also a button"
        WebElement button = driver.findElement(By.name("randomButton2"));
        System.out.println(button.getDomAttribute("id"));
        System.out.println(button.getDomAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        // get first text of class "test"
        System.out.println(driver.findElement(By.className("test")).getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {
        // get all elements with class "test"
        List<WebElement> elements = driver.findElements(By.className("test"));

        // get size
        System.out.println(elements.size()); // should be 5

        // get all text
        for (WebElement element : elements) {
            System.out.println(element.getText());
        }

        // get third text (index 2)
        System.out.println(elements.get(2).getText()); // "Test Text 4"
    }
}