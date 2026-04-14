package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample2Task {
    WebDriver driver;

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() {

        //gets text from H2 using id
        String text = driver.findElement(By.id("heading_2")).getText();

        System.out.println(text);
        assertEquals("Heading 2 text", text);
    }

    @Test
    public void findElementByName() {

        //gets attribute id and value of button "This is also a button"
        WebElement button = driver.findElement(By.name("randomButton2"));

        String id = button.getDomAttribute("id");
        String value = button.getDomAttribute("value");

        System.out.println("ID: " + id);
        System.out.println("Value: " + value);

        assertEquals("buttonId", id);
        assertEquals("This is also a button", value);
    }

    @Test
    public void findElementByClassFirst() {

        //gets first text of class "test"
        String text = driver.findElement(By.className("test")).getText();

        System.out.println(text);
        assertEquals("Test Text 1", text);
    }

    @Test
    public void findElementByClassAll() {

        List<WebElement> elements = driver.findElements(By.className("test"));

        //size
        int size = elements.size();
        System.out.println("Size: " + size);
        assertEquals(5, size);

        //all text
        for (WebElement el : elements) {
            System.out.println(el.getText());
        }

        //third text
        String thirdText = elements.get(2).getText();
        System.out.println("Third text: " + thirdText);

        assertEquals("Test Text 4", thirdText);
    }
}
