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

//          get text "Heading 2 text" using id
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {

//          get attribute "id" and "value" of button "This is also a button" using name
        WebElement element = driver.findElement(By.name("randomButton2"));
//        System.out.println(element.getAttribute("id"));
//        System.out.println(element.getAttribute("value"));
        System.out.println(element.getDomAttribute("id"));
        System.out.println(element.getDomAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {

//          get first text of class "test" (should be "Test Text 1")
//        System.out.println(driver.findElement(By.className("test")).getText());
        System.out.println(driver.findElements(By.className("test")).getFirst().getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {

//          get size text of class "test" (should be 5)
        List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));
        System.out.println("Size of elements array: " + allElementsWithClass.size());
//          get text of class "test"
        for (WebElement elementWithClass : allElementsWithClass) {
            System.out.println(elementWithClass.getText());
        }
//          get third text of class "test" (should be "Test Text 4")
        System.out.println("-----------------------");
        System.out.println(allElementsWithClass.get(2).getText());
    }
}
