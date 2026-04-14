package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    private WebElement getInput() {
        return driver.findElement(By.cssSelector("input[type='text'], input[type='number'], input"));
    }
    private WebElement getSubmitButton() {
        return driver.findElement(By.xpath("//button[contains(.,'Submit')] | //input[@type='submit']"));
    }
    private String getPageText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    @Test
    public void errorOnText() {
        getInput().clear();
        getInput().sendKeys("Jana");
        getSubmitButton().click();

        String pageText = getPageText();
        assertTrue(pageText.toLowerCase().contains("error") || pageText.toLowerCase().contains("number"));
    }

    @Test
    public void errorOnNumberTooSmall() {
        getInput().clear();
        getInput().sendKeys("20");
        getSubmitButton().click();

        String pageText = getPageText();
        assertTrue(pageText.contains("50") || pageText.toLowerCase().contains("too small"));
    }

    @Test
    public void errorOnNumberTooBig() {
        getInput().clear();
        getInput().sendKeys("12900");
        getSubmitButton().click();

        String pageText = getPageText();
        assertTrue(pageText.contains("100") || pageText.toLowerCase().contains("too big"));
    }

    @Test
    public void correctSquareRoot() {

        int inputNumber = 81;
        String expectedResult = "Square root of " + inputNumber + " is 9.00";

        getInput().clear();
        getInput().sendKeys(String.valueOf(inputNumber));
        getSubmitButton().click();

        Alert alert = driver.switchTo().alert();

        //verify result text
        assertEquals(expectedResult, alert.getText());

        //close alert
        alert.accept();
    }
}
