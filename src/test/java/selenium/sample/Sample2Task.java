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
        System.out.println(driver.findElement(By.id("heading_2")).getText());

    }

    @Test
    public void findElementByName() throws Exception {
        System.out.println(driver.findElement(By.name("randomButton2")).getAttribute("id"));
        System.out.println(driver.findElement(By.name("randomButton2")).getAttribute("value"));
        System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("id"));
                System.out.println(driver.findElement(By.name("randomButton2")).getDomAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        WebElement first = driver.findElement(By.className("test"));
        System.out.println("First class text: " + first.getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {
        List<WebElement> elems = driver.findElements(By.className("test"));
        System.out.println("Total test elements: " + elems.size());

        for (WebElement el : elems) {
            System.out.println(el.getText());
        }

        System.out.println("Third element text: " + elems.get(2).getText());
    }
}
