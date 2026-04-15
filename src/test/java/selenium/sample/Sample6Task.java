package selenium.sample;
import org.openqa.selenium.support.ui.Select;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

        System.out.println(driver.findElement(By.xpath("//*[@id=\"heading_2\"]")).getText());//heading
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());//text1
        System.out.println(driver.findElement(By.xpath("//*[@class ='twoTest']")).getText());//text2
        System.out.println(driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());//text3
        System.out.println(driver.findElement(By.xpath("//*[@id='test3']/*[contains(text(),'4')]")).getText());//text4
        System.out.println(driver.findElement(By.xpath("//*[@id=\"test2\"]/*[@class=\"Test\"]")).getText());
        System.out.println(driver.findElement(By.xpath("//*[@id='buttonId']")).getDomAttribute("value"));


//         TODO:
//          1-2 ways to find text: "Heading 2 text":
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "Test Text 4"
//          1-2 ways to find text: "Test Text 5"
//          1-2 ways to find text: "This is also a button"

    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"
        System.out.println(driver.findElement(By.cssSelector("#heading_2")).getText());//heading2
        System.out.println(driver.findElement(By.cssSelector("#test1 .test")).getText());//text1
        System.out.println(driver.findElement(By.cssSelector("#test1 .twoTest")).getText());//text2
        System.out.println(driver.findElement(By.cssSelector("#test3 > .test:nth-of-type(1)")).getText());//test3
        System.out.println(driver.findElement(By.cssSelector("#buttonId")).getDomAttribute("value"));



    }
}
