package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

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
        System.out.println(driver.findElement(By.id("heading_2")).getText());

//         TODO:
//          get text "Heading 2 text" using id
    }

    @Test
    public void findElementByName() throws Exception {
        System.out.println(driver.findElement(By.name("randomnButton2")).getAttribute("value"));
        System.out.println(driver.findElement(By.name("randomnButton2")).getAttribute("id"));

//         TODO:
//          get attribute "id" and "value" of button "This is also a button" using name
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        System.out.println(driver.findElement(By.className("test")).getText());

//         TODO:
//          get first text of class "test" (should be "Test Text 1")
    }

    @Test
    public void findElementByClassAll() throws Exception {
        System.out.println(driver.findElements(By.className("text")).size());

//         TODO:
//          get size text of class "test" (should be 5)
//          get text of class "test"
//          get third text of class "test" (should be "Test Text 4")
    }
}
