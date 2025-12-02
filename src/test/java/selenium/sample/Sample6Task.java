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
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "Test Text 4"
//          1-2 ways to find text: "Test Text 5"
//          1-2 ways to find text: "This is also a button"

      // H2 text
      System.out.println("\t text of element with id 'heading_2' is '" +
              driver.findElement(By.xpath("//h2[@id='heading_2']")).getText() + "'");
      System.out.println("\t text of element with id 'heading_2' is '" +
              driver.findElement(By.xpath("/html/body/h2[2]")).getText() + "'");

      // test text 1
      System.out.println("\t text of element is '" +
              driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText() + "'");
      System.out.println("\t text of element is '" +
              driver.findElement(By.xpath("/html/body/div[4]/p[1]")).getText() + "'");
      System.out.println("\t text of element is '" +
              driver.findElement(By.xpath("//div[@Id='test1']/p[@class='test']")).getText() + "'");

      // test text 2
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("//*[@id='test1']/p[2]")).getText() + "'");
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("/html/body/div[4]/p[2]")).getText() + "'");
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("//div[@Id='test1']/p[@class='twoTest']")).getText() + "'");

      // test text 3
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("//*[@id='test3']/p[1]")).getText() + "'");
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("/html/body/div[5]/p[1]")).getText() + "'");

      // test text 4
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText() + "'");
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("/html/body/div[5]/p[2]")).getText() + "'");
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText() + "'");

      // test text 5
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("//*[@id='test2']/p[1]")).getText() + "'");
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("/html/body/div[6]/p[1]")).getText() + "'");
      // this is also a button
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("//*[@id='buttonId']")).getDomAttribute("value") + "'");
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("/html/body/input[2]")).getDomAttribute("value")+ "'");
      System.out.println("\t text of element  is '" +
              driver.findElement(By.xpath("//input[@name='randomButton2' and @type='button' and @id='buttonId']")).getDomAttribute("value") + "'");
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//          1-2 ways to find text: "Heading 2 text"
//          1-2 ways to find text: "Test Text 1"
//          1-2 ways to find text: "Test Text 2"
//          1-2 ways to find text: "Test Text 3"
//          1-2 ways to find text: "This is also a button"

// h2 text
      System.out.println("\t text of element with id 'heading_2' is '" +
              driver.findElement(By.cssSelector("#heading_2")).getText() + "'");
      // h2 text
      System.out.println("\t text of element with id 'heading_2' is '" +
              driver.findElement(By.cssSelector("h2#heading_2")).getText() + "'");
// test text 1
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector("#test1 > p.test")).getText() + "'");
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector("div#test1 > p.test")).getText() + "'");
      // test text 2
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector("#test1 > p.twoTest")).getText() + "'");
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector(" p.twoTest")).getText() + "'");
      // test text 3
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector("#test3 > p:nth-child(1)")).getText() + "'");
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector("div#test3>p")).getText() + "'");


      // this is also a button
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector("#buttonId")).getDomAttribute("value") + "'");
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector("input#buttonId")).getDomAttribute("value") + "'");
      System.out.println("\t text of element is '" +
              driver.findElement(By.cssSelector("input[value='This is also a button']")).getDomAttribute("value") + "'");

    }
}
