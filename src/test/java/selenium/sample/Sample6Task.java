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

        // open page:
        driver.get("https://janisdzalbe.github.io/example-site/examples/locators");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }


    @Test
    public void findElementByXPath() throws Exception {
        System.out.println("Find elements by text using xPath:");


        System.out.println("\t Heading 2 text v1 is '" +
                driver.findElement(By.xpath("//*[text()='Heading 2 text']")).getText());

        System.out.println("\t Heading 2 text v2 is '" +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText());

        System.out.println("\t Test Text 1 v1 is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 1']")).getText());

        System.out.println("\t Test Text 1 v2 is '" +
                driver.findElement(By.xpath("//*[@id='test1']/p[1]")).getText());

        System.out.println("\t Test Text 2 v1 is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 2']")).getText());

        System.out.println("\t Test Text 2 v2 is '" +
                driver.findElement(By.xpath("//*[@id='test1']/p[2]")).getText());

        System.out.println("\t Test Text 3 v1 is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 3']")).getText());

        System.out.println("\t Test Text 3 v2 is '" +
                driver.findElement(By.xpath("//*[@id='test3']/p[1]")).getText());

        System.out.println("\t Test Text 4 v1 is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 4']")).getText());

        System.out.println("\t Test Text 4 v2 is '" +
                driver.findElement(By.xpath("//*[@id='test3']/p[2]")).getText());

        System.out.println("\t Test Text 5 v1 is '" +
                driver.findElement(By.xpath("//*[text()='Test Text 5']")).getText());

        System.out.println("\t Test Text 5 v2 is '" +
                driver.findElement(By.xpath("//*[@id='test2']/p[1]")).getText());

        System.out.println("\t This is also a button v1 is '" +
                driver.findElement(By.xpath("//*[@id='buttonId']")).getDomProperty("value") );

        System.out.println("\t This is also a button v2 is '" +
                driver.findElement(By.xpath("//*[@value='This is also a button']")).getDomProperty("value") );
    }


    @Test
    public void findElementByCssName() throws Exception {
        System.out.println("Find elements by text using CSS:");

        System.out.println("\t Heading 2 text v1 is '" +
                driver.findElement(By.cssSelector("#heading_2")).getText() );

        System.out.println("\t Heading 2 text v2 is '" +
                driver.findElement(By.cssSelector("h2#heading_2")).getText());

        System.out.println("\t Test Text 1 v1 is '" +
                driver.findElement(By.cssSelector("#test1 p:nth-of-type(1)")).getText());

        System.out.println("\t Test Text 2 v1 is '" +
                driver.findElement(By.cssSelector("#test1 .twoTest")).getText());

        System.out.println("\t Test Text 2 v2 is '" +
                driver.findElement(By.cssSelector("#test1 p:nth-of-type(2)")).getText());

        System.out.println("\t Test Text 3 v1 is '" +
                driver.findElement(By.cssSelector("#test3 p.test")).getText() + "'");

        System.out.println("\t Test Text 3 v2 is '" +
                driver.findElement(By.cssSelector("#test3 p:nth-of-type(1)")).getText());

        System.out.println("\t This is also a button v1 is '" +
                driver.findElement(By.cssSelector("#buttonId")).getDomProperty("value"));

        System.out.println("\t This is also a button v2 is '" +
                driver.findElement(By.cssSelector("[value='This is also a button']")).getDomProperty("value"));
    }
}