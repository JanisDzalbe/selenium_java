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
//         TODO: get text "Heading 2 text" using id
        System.out.println("Text of Heading 2 text: " + driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO: get attribute "id" and "value" of button "This is also a button" using name
        System.out.println("This is also a button name: " + driver.findElement(By.name("randomButton2")).getDomAttribute("value")); // "This is also a button"
        System.out.println("This is also a button id: " + driver.findElement(By.name("randomButton2")).getDomAttribute("id")); // "buttonId"
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO: get first text of class "test" (should be "Test Text 1")
        System.out.println("First text of class test: " + driver.findElement(By.className("test")).getText()); // "Test Text 1"
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//          get size text of class "test" (should be 5)
//          get text of class "test"
//          get third text of class "test" (should be "Test Text 4")
        System.out.println("Size text of class test: " + driver.findElements(By.className("test")).size()); // 5
        System.out.println("Text of class test: " + driver.findElements(By.className("test")).get(0).getText());
        System.out.println("Third text of class test: " + driver.findElements(By.className("test")).get(2).getText()); // "Test Text 4"
    }
}
