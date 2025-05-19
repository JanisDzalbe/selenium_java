package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.sql.SQLOutput;
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
    System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//         get attribute "id" and "value" of button "This is also a button" using name
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("id"));
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("value"));

        //better practice way to do it probably
        WebElement button = driver.findElement(By.name("randomButton2"));
        System.out.println(button.getDomAttribute("id"));
        System.out.println(button.getDomAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//         get first text of class "test" (should be "Test Text 1")
        System.out.println(driver.findElement(By.className("test")).getText());
        //dont think it's necessary to make a test here, but I'm doing it for practice :)
        Assertions.assertEquals("Test Text 1", driver.findElement(By.className("test")).getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//         get size text of class "test" (should be 5)
//         get text of class "test"
//         get third text of class "test" (should be "Test Text 4")
        List<WebElement> classes = driver.findElements(By.className("test"));
        System.out.println(classes.size());
        
        for(WebElement element : classes) {
            System.out.println(element.getText());
        }

        System.out.println(classes.get(2).getText());

        // once, again, don't think it's neede but I'm doing it because why not :)
        Assertions.assertEquals(5, classes.size());
        Assertions.assertEquals("Test Text 4", classes.get(2).getText());


    }
}
