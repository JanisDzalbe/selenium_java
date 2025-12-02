package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;       // ✅ REQUIRED
import selenium.utility.BootcampUtils;


public class Sample6Task {
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
    public void findElementByXPath() throws Exception {

        // Heading 2 text
        driver.findElement(By.xpath("//h2[@id='heading_2']"));
        driver.findElement(By.xpath("//h2[contains(text(),'Heading 2 text')]"));

        // Test Text 1 (div id="test1")
        driver.findElement(By.xpath("//div[@id='test1']"));
        driver.findElement(By.xpath("//*[@id='test1']"));

        // Test Text 2 (div id="test2")
        driver.findElement(By.xpath("//div[@id='test2']"));
        driver.findElement(By.xpath("//*[@id='test2']"));

        // Test Text 3 (div id="test3")
        driver.findElement(By.xpath("//div[@id='test3']"));
        driver.findElement(By.xpath("//*[@id='test3']"));

        // Test Text 4 (div id="standartText")
        driver.findElement(By.xpath("//div[@id='standartText']"));
        driver.findElement(By.xpath("//*[@id='standartText']"));

        // Test Text 5 (div id="nonStandartText")
        driver.findElement(By.xpath("//div[@id='nonStandartText']"));
        driver.findElement(By.xpath("//*[@id='nonStandartText']"));

        // "This is also a button" (input with id="buttonId")
        driver.findElement(By.xpath("//input[@id='buttonId']"));
        driver.findElement(By.xpath("//input[@value='This is also a button']"));

    }



    @Test
    public void findElementByCssName() throws Exception {

        // Heading 2 text
        driver.findElement(By.xpath("//h2[@id='heading_2']"));

        // Test Text 1
        driver.findElement(By.xpath("//div[@id='test1']"));

        // Test Text 2
        driver.findElement(By.xpath("//div[@id='test2']"));

        // Test Text 3
        driver.findElement(By.xpath("//div[@id='test3']"));

        // "This is also a button"
        driver.findElement(By.xpath("//input[@id='buttonId']"));
    }

}
