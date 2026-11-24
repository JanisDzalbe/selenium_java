package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Sample9Task {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // load web page
        driver.get("https://janisdzalbe.github.io/example-site/examples/loading_color");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//          * 1) click on start loading green button
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"

        WebElement greenButton =driver.findElement(By.id("start_green"));
        greenButton.click();
        assertFalse(greenButton.isDisplayed());
        assertTrue(driver.findElement(By.xpath("/*[contains(text(),'Loading green...')]")).isDisplayed());

        Thread.sleep(5000);

        // Check that both button is  displayed
        assertFalse(greenButton.isDisplayed());
        // Check that loading text is not displayed
        assertFalse(driver.findElement(By.xpath("//*[contains(text(),'Loading green...')]")).isDisplayed());
        // Check that success message is displayed
        assertTrue(driver.findElement(By.xpath("//*[@id='finish_green']/label")).isDisplayed());




    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//          * 1) click on start loading green button
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement greenButton =driver.findElement(By.id("start_green"));
        greenButton.click();
        assertFalse(greenButton.isDisplayed());
        assertTrue(driver.findElement(By.xpath("/*[contains(text(),'Loading green...')]")).isDisplayed());

        //  success message should check first
        assertTrue(driver.findElement(By.xpath("//*[@id='finish_green']/label")).isDisplayed());
        // Check that both button is  displayed
        assertFalse(greenButton.isDisplayed());
        // Check that loading text is not displayed
        assertFalse(driver.findElement(By.xpath("//*[contains(text(),'Loading green...')]")).isDisplayed());


    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//          * 1) click on start loading green button
//          * 2) check that button does not appear,
//          * but loading text is seen instead   "Loading green..."
//          * 3) check that both button
//          * and loading text is not seen,
//          * success is seen instead "Green Loaded"

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        WebElement greenButton =driver.findElement(By.id("start_green"));
        greenButton.click();
        assertFalse(greenButton.isDisplayed());
        assertTrue(driver.findElement(By.xpath("/*[contains(text(),'Loading green...')]")).isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Loading green...')]")));
        //  success message should check first
        assertTrue(driver.findElement(By.xpath("//*[@id='finish_green']/label")).isDisplayed());
        // Check that both button is  displayed
        assertFalse(greenButton.isDisplayed());
        // Check that loading text is not displayed
        assertFalse(driver.findElement(By.xpath("//*[contains(text(),'Loading green...')]")).isDisplayed());


    }

    @Test
    public void loadGreenAndBlueBonus() {
//         TODO:
//          * 0) wait until button to load green and blue appears
//          * 1) click on start loading green and blue button
//          * 2) check that button does not appear, but loading text is seen instead for green
//          * 3) check that button does not appear, but loading text is seen instead for green and blue
//          * 4) check that button and loading green does not appear,
//          * but loading text is seen instead for blue and success for green is seen
//          * 5) check that both button and loading text is not seen, success is seen instead
    }

}