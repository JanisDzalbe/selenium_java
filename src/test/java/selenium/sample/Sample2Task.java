package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;

import java.util.List;


public class Sample2Task {
    WebDriver driver;

    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeDriver();

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


        WebElement heading2 = driver.findElement(By.id("heading_2"));
        System.out.println(heading2.getText());

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//          get attribute "id" and "value" of button "This is also a button" using name


        WebElement button = driver.findElement(By.name("randomButton2"));
        System.out.println(button.getAttribute("id"));
        System.out.println(button.getAttribute("value"));
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//          get first text of class "test" (should be "Test Text 1")

        List<WebElement> elements = driver.findElements(By.className("test"));
        System.out.println(elements.get(0).getText());

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//          get size text of class "test" (should be 5)
//          get text of class "test"
//          get third text of class "test" (should be "Test Text 4")

        List<WebElement> elements = driver.findElements(By.className("test"));


        System.out.println("Size: " + elements.size());
        for (WebElement el : elements) {
            System.out.println(el.getText());
        }
        System.out.println("Third: " + elements.get(2).getText());
        Thread.sleep(3000);
        driver.quit();
    }
}
