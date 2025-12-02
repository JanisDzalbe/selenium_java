package selenium.sample;

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
//         TODO:
//          1-2 ways to find text: "Heading 2 text":
        // 1-2 ways to find text: "Heading 2 text"
        System.out.println("Find 'Heading 2 text' using XPath:");
        System.out.println("\t Way 1 (by tag name): '" +
                driver.findElement(By.xpath("(//h2)[2]")).getText());   //first h2 is h1 so we read second
        System.out.println("\t Way 2 (by id): '" +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + "'");
        System.out.println("--------------------------------------");

//          1-2 ways to find text: "Test Text 1"
        System.out.println("Find 'Test Text 1' using XPath:");
        System.out.println("\t Way 1 (by class): '" +
                driver.findElement(By.xpath("//*[contains(@class,'test')]")).getText() + "'");
        System.out.println("\t Way 2 (by text content): '" +
                driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText() + "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "Test Text 2"
        System.out.println("Find 'Test Text 2' using XPath:");
        System.out.println("\t Way 1 (by class): '" +
                driver.findElement(By.xpath("//*[@class='twoTest']")).getText() + "'");
        System.out.println("\t Way 2 (by text content): '" +
                driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText() + "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "Test Text 3"
        System.out.println("Find 'Test Text 3' using XPath:");
        System.out.println("\t Way 1 (by class): '" +
                driver.findElement(By.xpath("(//*[contains(@class,'test')])[2]")).getText()+ "'"); //we don't need first of this class
        System.out.println("\t Way 2 (by text content): '" +
                driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText() + "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "Test Text 4"
        System.out.println("Find 'Test Text 3' using XPath:");
        System.out.println("\t Way 1 (by class): '" +
                driver.findElement(By.xpath("(//*[contains(@class,'test')])[3]")).getText()+ "'"); //we don't need first of this class
        System.out.println("\t Way 2 (by text content): '" +
                driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText() + "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "Test Text 5"
        System.out.println("Find 'Test Text 3' using XPath:");
        System.out.println("\t Way 1 (by class): '" +
                driver.findElement(By.xpath("(//*[contains(@class,'Test')])[2]")).getText()+ "'"); //we don't need first of this class
        System.out.println("\t Way 2 (by text content): '" +
                driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText() + "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "This is also a button"
        System.out.println("Find 'This is also a button' using XPath:");
        System.out.println("\t Way 1 (by name attribute): '" +
                driver.findElement(By.xpath("//*[@name='randomButton2']")).getDomProperty("value") + "'");
        System.out.println("\t Way 2 (by value attribute): '" +
                driver.findElement(By.xpath("//*[@value='This is also a button']")).getDomProperty("value") + "'");
        System.out.println("--------------------------------------");
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
        System.out.println("Find 'Heading 2 text' using CSS:");
        System.out.println("\t Way 1 (by tag name): '" +
                driver.findElement(By.cssSelector("h2:nth-of-type(2)")).getText() + "'");       //we read second element we find
        System.out.println("\t Way 2 (by id): '" +
                driver.findElement(By.cssSelector("#heading_2")).getText() + "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "Test Text 1"
        System.out.println("Find 'Test Text 1' using CSS:");
        System.out.println("\t Way 1 (by class, first p): '" +
                driver.findElement(By.cssSelector("div#test1 p.test")).getText()+ "'");
        System.out.println("\t Way 1 (by child selector): '" +
                driver.findElement(By.cssSelector("#test1 > p.test")).getText()+ "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "Test Text 2"
        System.out.println("Find 'Test Text 2' using CSS:");
        System.out.println("\t Way 1 (by parent id and child class): '" +
                driver.findElement(By.cssSelector("#test1 .twoTest")).getText() + "'");
        System.out.println("\t Way 3 (attribute selector): '" +
                driver.findElement(By.cssSelector("#test1 p[class='twoTest']")).getText() + "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "Test Text 3"
        System.out.println("Find 'Test Text 3' using CSS:");
        System.out.println("\t Way 1 (by parent id and child class): '" +
                driver.findElement(By.cssSelector("#test3 p.test")).getText()+ "'");
        System.out.println("\t Way 2 (by nth paragraph): '" +
                driver.findElement(By.cssSelector("#test3 p:nth-of-type(1)")).getText() + "'");
        System.out.println("--------------------------------------");
//          1-2 ways to find text: "This is also a button"
        System.out.println("Find 'This is also a button' using CSS:");
        System.out.println("\t Way 1 (by name attribute): '" +
                driver.findElement(By.cssSelector("[name='randomButton2']")).getDomProperty("value") + "'");
        System.out.println("\t Way 2 (by value attribute): '" +
                driver.findElement(By.cssSelector("[value='This is also a button']")).getDomProperty("value") + "'");
    }
}
