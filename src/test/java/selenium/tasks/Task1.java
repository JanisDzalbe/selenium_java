package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty(
                "webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension()
        );
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/enter_a_number");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    // helper: find the number input (first non-submit/non-button <input>)
    private WebElement getNumberInput() {
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        for (WebElement input : inputs) {
            String type = input.getAttribute("type");
            if (type == null ||
                    (!type.equalsIgnoreCase("submit") && !type.equalsIgnoreCase("button"))) {
                return input;
            }
        }
        throw new IllegalStateException("Could not find number input field");
    }

    // helper: find the submit button
    private WebElement getSubmitButton() {
        // try <button> with text "Submit" first
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        for (WebElement button : buttons) {
            String text = button.getText().trim();
            if (text.equalsIgnoreCase("Submit")) {
                return button;
            }
        }
        if (!buttons.isEmpty()) {
            return buttons.get(0);
        }

        // fallback: input type="submit"
        return driver.findElement(By.cssSelector("input[type='submit']"));
    }

    // helper: after submit, assume the *last* <p> is the dynamic message
    private WebElement getMessageParagraph() {
        List<WebElement> paragraphs = driver.findElements(By.tagName("p"));
        if (paragraphs.isEmpty()) {
            throw new IllegalStateException("No <p> elements found on the page");
        }
        return paragraphs.get(paragraphs.size() - 1);
    }

    @Test
    public void errorOnText() {
        // enter a text instead of a number, check that correct error is shown
        WebElement numberInput = getNumberInput();
        WebElement submitButton = getSubmitButton();

        numberInput.clear();
        numberInput.sendKeys("abc");
        submitButton.click();

        WebElement message = getMessageParagraph();
        String messageText = message.getText();

        // this already worked for you, keep it generic:
        assertTrue(
                messageText.toLowerCase().contains("number"),
                "Expected an error about invalid number, but got: " + messageText
        );
    }

    @Test
    public void errorOnNumberTooSmall() {
        // enter number which is too small (positive number below 50), check that correct error is shown
        WebElement numberInput = getNumberInput();
        WebElement submitButton = getSubmitButton();

        numberInput.clear();
        numberInput.sendKeys("40");
        submitButton.click();

        WebElement message = getMessageParagraph();
        String messageText = message.getText();

        // adjust to the actual text from your output: "Number is too small"
        assertEquals("Number is too small", messageText);
    }

    @Test
    public void errorOnNumberTooBig() {
        // enter number which is too big (above 100), check that correct error is shown
        WebElement numberInput = getNumberInput();
        WebElement submitButton = getSubmitButton();

        numberInput.clear();
        numberInput.sendKeys("150");
        submitButton.click();

        WebElement message = getMessageParagraph();
        String messageText = message.getText();

        // adjust to the actual text from your output: "Number is too big"
        assertEquals("Number is too big", messageText);
    }

    @Test
    public void correctSquareRoot() {
        // enter a number between 50 and 100, press submit
        WebElement numberInput = getNumberInput();
        WebElement submitButton = getSubmitButton();

        int inputNumber = 64;
        numberInput.clear();
        numberInput.sendKeys(String.valueOf(inputNumber));
        submitButton.click();

        // for a valid number, the page shows a JS alert:
        double sqrt = Math.sqrt(inputNumber);
        String expectedSqrtString = String.format("%.2f", sqrt);
        String expectedAlertText = "Square root of " + inputNumber + " is " + expectedSqrtString;

        Alert alert = driver.switchTo().alert();
        assertEquals(expectedAlertText, alert.getText());

        // accept the alert (close it)
        alert.accept();

        // optional: you could also check that no error message is shown
        // e.g., ensure the message paragraph does not contain "too small"/"too big"
        WebElement message = getMessageParagraph();
        String messageText = message.getText().toLowerCase();
        assertFalse(
                messageText.contains("too small") || messageText.contains("too big"),
                "Did not expect an error for valid input, but got: " + messageText
        );
    }
}
