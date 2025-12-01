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

        // open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
        // TODO:
        // get text "Heading 2 text" using id

        String heading2Text = driver.findElement(By.id("heading_2")).getText();
        System.out.println(heading2Text);
    }

    @Test
    public void findElementByName() throws Exception {
        // TODO:
        // get attribute "id" and "value" of button "This is also a button" using name

        WebElement button = driver.findElement(By.name("randomButton2"));
        System.out.println(button.getAttribute("id"));      // buttonId
        System.out.println(button.getAttribute("value"));   // This is also a button
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        // TODO:
        // get first text of class "test" (should be "Test Text 1")

        String firstText = driver.findElement(By.className("test")).getText();
        System.out.println(firstText);  // Test Text 1
    }

    @Test
    public void findElementByClassAll() throws Exception {
        // TODO:
        // get size of class "test" (should be 5)
        // get text of class "test"
        // get third text of class "test" (should be "Test Text 4")

        List<WebElement> allWithClassTest = driver.findElements(By.className("test"));

        // size
        System.out.println(allWithClassTest.size());   // 5

        // print all texts
        for (WebElement element : allWithClassTest) {
            System.out.println(element.getText());
        }

        // third element (index 2)
        System.out.println(allWithClassTest.get(2).getText());  // Test Text 4
    }
}