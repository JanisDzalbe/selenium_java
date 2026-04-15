package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample6Task {
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
    public void findElementByXPath() throws Exception {
        // "Heading 2 text"
        WebElement h2_byId   = driver.findElement(By.xpath("//h2[@id='heading_2']"));
        WebElement h2_byText = driver.findElement(By.xpath("//h2[text()='Heading 2 text']"));
        assertEquals("Heading 2 text", h2_byId.getText());
        assertEquals("Heading 2 text", h2_byText.getText());

        // "Test Text 1"
        WebElement t1_byText     = driver.findElement(By.xpath("//*[text()='Test Text 1']"));
        WebElement t1_byContains = driver.findElement(By.xpath("//*[contains(text(),'Test Text 1')]"));
        assertEquals("Test Text 1", t1_byText.getText());
        assertEquals("Test Text 1", t1_byContains.getText());

        // "Test Text 2"
        WebElement t2_byText     = driver.findElement(By.xpath("//*[text()='Test Text 2']"));
        WebElement t2_byContains = driver.findElement(By.xpath("//*[contains(text(),'Test Text 2')]"));
        assertEquals("Test Text 2", t2_byText.getText());
        assertEquals("Test Text 2", t2_byContains.getText());

        // "Test Text 3"
        WebElement t3_byText     = driver.findElement(By.xpath("//*[text()='Test Text 3']"));
        WebElement t3_byContains = driver.findElement(By.xpath("//*[contains(text(),'Test Text 3')]"));
        assertEquals("Test Text 3", t3_byText.getText());
        assertEquals("Test Text 3", t3_byContains.getText());

        // "Test Text 4"
        WebElement t4_byText     = driver.findElement(By.xpath("//*[text()='Test Text 4']"));
        WebElement t4_byContains = driver.findElement(By.xpath("//*[contains(text(),'Test Text 4')]"));
        assertEquals("Test Text 4", t4_byText.getText());
        assertEquals("Test Text 4", t4_byContains.getText());

        // "Test Text 5"
        WebElement t5_byText     = driver.findElement(By.xpath("//*[text()='Test Text 5']"));
        WebElement t5_byContains = driver.findElement(By.xpath("//*[contains(text(),'Test Text 5')]"));
        assertEquals("Test Text 5", t5_byText.getText());
        assertEquals("Test Text 5", t5_byContains.getText());

        // "This is also a button"
        WebElement btn_byValue = driver.findElement(By.xpath("//input[@value='This is also a button']"));
        WebElement btn_byName  = driver.findElement(By.xpath("//input[@name='randomButton2']"));
        WebElement btn_byId    = driver.findElement(By.xpath("//input[@id='buttonId']"));
        assertEquals("This is also a button", btn_byValue.getAttribute("value"));
        assertEquals("This is also a button", btn_byName.getAttribute("value"));
        assertEquals("This is also a button", btn_byId.getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
        // "Heading 2 text"
        WebElement h2_byId   = driver.findElement(By.cssSelector("#heading_2"));
        WebElement h2_byAttr = driver.findElement(By.cssSelector("h2[id='heading_2']"));
        assertEquals("Heading 2 text", h2_byId.getText());
        assertEquals("Heading 2 text", h2_byAttr.getText());

        // "Test Text 1" - first element with class 'test'
        WebElement t1_byClass      = driver.findElement(By.cssSelector(".test"));
        WebElement t1_byFirstOfType = driver.findElements(By.cssSelector(".test")).get(0);
        assertEquals("Test Text 1", t1_byClass.getText());
        assertEquals("Test Text 1", t1_byFirstOfType.getText());

        // "Test Text 2" - second element with class 'test'
        WebElement t2_byIndex = driver.findElements(By.cssSelector(".test")).get(1);
        WebElement t2_byNth   = driver.findElement(By.cssSelector(".test:nth-of-type(2)"));
        assertEquals("Test Text 2", t2_byIndex.getText());
        assertEquals("Test Text 2", t2_byNth.getText());

        // "Test Text 3"
        WebElement t3_byIndex = driver.findElements(By.cssSelector(".test")).get(2);
        WebElement t3_byNth   = driver.findElement(By.cssSelector(".test:nth-of-type(3)"));
        assertEquals("Test Text 3", t3_byIndex.getText());
        assertEquals("Test Text 3", t3_byNth.getText());

        // "This is also a button"
        WebElement btn_byId    = driver.findElement(By.cssSelector("#buttonId"));
        WebElement btn_byName  = driver.findElement(By.cssSelector("input[name='randomButton2']"));
        WebElement btn_byValue = driver.findElement(By.cssSelector("input[value='This is also a button']"));
        assertEquals("This is also a button", btn_byId.getAttribute("value"));
        assertEquals("This is also a button", btn_byName.getAttribute("value"));
        assertEquals("This is also a button", btn_byValue.getAttribute("value"));
    }
}