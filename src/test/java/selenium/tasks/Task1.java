package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;



public class Task1 {
    WebDriver driver;
    private WebElement getNumberInput() {
        return driver.findElement(By.id("numb"));
    }

    private WebElement getSubmitButton() {
        return driver.findElement(By.tagName("button"));
    }
    private WebElement getErrorElement() {
        return driver.findElement(By.id("ch1_error"));
    }


    @BeforeEach
    public void openPage() {

        driver = BootcampUtils.initializeDriver();

        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void errorOnText() {
//        TODO
//         enter a text instead of a number, check that correct error is shown
        WebElement input = getNumberInput();
        WebElement submit = getSubmitButton();

        input.clear();
        input.sendKeys("abc");
        submit.click();

        WebElement error = getErrorElement();
        String errorText = error.getText();


        assertTrue(errorText != null && !errorText.trim().isEmpty(),
                "Error expected");

        // и что она говорит про диапазон 50..100
        assertTrue(errorText.contains("number"));
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//         enter number which is too small (positive number below 50), check that correct error is shown

        WebElement input = getNumberInput();
        WebElement submit = getSubmitButton();

        int tooSmall = 10;   // < 50
        input.clear();
        input.sendKeys(String.valueOf(tooSmall));
        submit.click();

        WebElement error = getErrorElement();
        String errorText = error.getText();

        assertTrue(errorText != null && !errorText.trim().isEmpty(),
                "Error expected");
        assertTrue(errorText.contains("small"));
    }

    @Test
    public void errorOnNumberTooBig() {
//        TODO
//         enter number which is too big (above 100), check that correct error is shown
        WebElement input = getNumberInput();
        WebElement submit = getSubmitButton();

        int tooSmall = 150;   // > 100
        input.clear();
        input.sendKeys(String.valueOf(tooSmall));
        submit.click();

        WebElement error = getErrorElement();
        String errorText = error.getText();

        assertTrue(errorText != null && !errorText.trim().isEmpty(),
                "Error expected");
        assertTrue(errorText.contains("big"));
    }

    @Test
    public void correctSquareRoot() {
//        TODO
//         enter a number between 50 and 100 digit in the input, then press submit
//         and check that no error is shown and that square root is calculated correctly
//         NOTE: input and assertion values have to be defined as variables
        int inputNumber = 64;
        String inputAsString = String.valueOf(inputNumber);

        WebElement input = getNumberInput();
        WebElement submit = getSubmitButton();

        input.clear();
        input.sendKeys(inputAsString);
        submit.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertTrue(alertText.contains(inputAsString));


        double expectedSqrt = Math.sqrt(inputNumber);
        String expectedSqrtStr = String.format("%.2f", expectedSqrt);


        assertTrue(alertText.contains(expectedSqrtStr));

        alert.accept();
    }
}
