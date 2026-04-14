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
 * Demonstrates finding elements using different locators
 */
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
    public void findElementByID() throws Exception {

        // find element by id and print text
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {

        // find element by name and print id + value attributes
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("id"));
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {

        // get first element with class "test"
        System.out.println(driver.findElement(By.className("test")).getText());
        System.out.println(driver.findElements(By.className("test")).getFirst().getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {


        // print size
        System.out.println(driver.findElements(By.className("test")).size());

        // print all text
        for (WebElement elementWithClass : driver.findElements(By.className("test"))) {
            System.out.println(elementWithClass.getText());
        }
            System.out.println(driver.findElements(By.className("test")).get(2).getText());
        }

    }
