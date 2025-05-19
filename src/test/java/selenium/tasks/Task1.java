package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static selenium.sample.Sample9.wait;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        String expectedError = "Please enter a number";
        String expectedErrorSelector = "#ch1_error";
        String inputFieldSelector = "#numb";
        String text = "ftdchj";
        String submitSelector = ".w3-btn";

        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));

        inputField.sendKeys(text);
        submit.click();

        assertEquals(expectedError, error.getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        BUG: if I enter number 49 or 42 no errors where seen
//        TODO
//        enter number which is too small (positive number below 50), check that correct error is seen

        String expectedError = "Number is too small";
        String expectedErrorSelector = "#ch1_error";
        String inputFieldSelector = "#numb";
        String number = "10";
        String submitSelector = ".w3-btn";

        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));

        inputField.sendKeys(number);
        submit.click();

        assertEquals(expectedError, error.getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        String expectedError = "Number is too big";
        String expectedErrorSelector = "#ch1_error";
        String inputFieldSelector = "#numb";
        String number = "1000";
        String submitSelector = ".w3-btn";

        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));

        inputField.sendKeys(number);
        submit.click();

        assertEquals(expectedError, error.getText());
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//        enter a number between 50 and 100 digit in the input, then press submit
//        and check that no error is seen and that square root is calculated correctly
//        NOTE: input value is hardcoded, but square root used in assertions should be calculated in code

        String expectedError = "";
        String expectedErrorSelector = "#ch1_error";
        String inputFieldSelector = "#numb";
        String number = "50";
        String submitSelector = ".w3-btn";

        WebElement inputField = driver.findElement(By.cssSelector(inputFieldSelector));
        WebElement error = driver.findElement(By.cssSelector(expectedErrorSelector));
        WebElement submit = driver.findElement(By.cssSelector(submitSelector));

        inputField.sendKeys(number);
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String result = alert.getText();
        alert.accept();

        double sqrt = Math.sqrt(Double.parseDouble(number));
        String formattedSqrt = String.format("%.2f", sqrt);
        String expectedResult = "Square root of " + number + " is " + formattedSqrt;

        System.out.println(result);
        assertEquals(expectedError, error.getText());
        assertEquals(expectedResult, result);
    }
}
