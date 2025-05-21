package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no ticks are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement messageField = driver.findElement(By.id("message"));

        assertTrue(nameField.getAttribute("value").isEmpty());
        assertTrue(emailField.getAttribute("value").isEmpty());
        assertTrue(messageField.getAttribute("value").isEmpty());

        Select genreDropdown = new Select(driver.findElement(By.id("genre")));
        WebElement selectedGenreOption = genreDropdown.getFirstSelectedOption();
        assertEquals("Don't know", selectedGenreOption.getText());

        WebElement sendButton = driver.findElement(By.id("send"));
        String buttonBackgroundColor = sendButton.getCssValue("background-color");
        String buttonTextColor = sendButton.getCssValue("color");
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)

        WebElement sendButton = driver.findElement(By.id("send"));
        sendButton.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement messageField = driver.findElement(By.id("message"));

        assertTrue(nameField.getAttribute("value").isEmpty() || nameField.getAttribute("value") == null);
        assertTrue(emailField.getAttribute("value").isEmpty() || emailField.getAttribute("value") == null);
        assertTrue(messageField.getAttribute("value").isEmpty() || messageField.getAttribute("value") == null);

        WebElement yesButton = driver.findElement(By.id("yes"));
        String yesButtonBackgroundColor = yesButton.getCssValue("background-color");
        String yesButtonTextColor = yesButton.getCssValue("color");

        String yesBackgroundColorHex = Color.fromString(yesButtonBackgroundColor).asHex();
        String yesTextColorHex = Color.fromString(yesButtonTextColor).asHex();

        assertTrue(yesBackgroundColorHex.toLowerCase().equals("#198754") ||
                        yesBackgroundColorHex.toLowerCase().equals("#28a745") ||
                        yesBackgroundColorHex.toLowerCase().equals("#008000"),
                "Yes button should have green background, got: " + yesBackgroundColorHex);
        assertEquals("#ffffff", yesTextColorHex.toLowerCase(), "Yes button should have white text");

        WebElement noButton = driver.findElement(By.id("no"));
        String noButtonBackgroundColor = noButton.getCssValue("background-color");
        String noButtonTextColor = noButton.getCssValue("color");

        String noBackgroundColorHex = Color.fromString(noButtonBackgroundColor).asHex();
        String noTextColorHex = Color.fromString(noButtonTextColor).asHex();

        assertTrue(noBackgroundColorHex.toLowerCase().equals("#dc3545") ||
                        noBackgroundColorHex.toLowerCase().equals("#dc3545") ||
                        noBackgroundColorHex.toLowerCase().equals("#ff0000"),
                "No button should have red background, got: " + noBackgroundColorHex);
        assertEquals("#ffffff", noTextColorHex.toLowerCase(), "No button should have white text");
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)

        // Fill the whole form
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement messageField = driver.findElement(By.id("message"));
        String testName = "Test Name";
        String testEmail = "test.name@example.com";
        String testMessage = "Test message";

        nameField.sendKeys(testName);
        emailField.sendKeys(testEmail);
        messageField.sendKeys(testMessage);

        WebElement sendButton = driver.findElement(By.id("send"));
        sendButton.click();

        WebElement yesButton = driver.findElement(By.id("yes"));
        String yesButtonBackgroundColor = yesButton.getCssValue("background-color");
        String yesButtonTextColor = yesButton.getCssValue("color");

        String yesBackgroundColorHex = Color.fromString(yesButtonBackgroundColor).asHex();
        String yesTextColorHex = Color.fromString(yesButtonTextColor).asHex();

        assertTrue(yesBackgroundColorHex.toLowerCase().equals("#198754") ||
                        yesBackgroundColorHex.toLowerCase().equals("#28a745") ||
                        yesBackgroundColorHex.toLowerCase().equals("#008000"),
                "Yes button should have green background");
        assertEquals("#ffffff", yesTextColorHex.toLowerCase(), "Yes button should have white text");

        WebElement noButton = driver.findElement(By.id("no"));
        String noButtonBackgroundColor = noButton.getCssValue("background-color");
        String noButtonTextColor = noButton.getCssValue("color");

        String noBackgroundColorHex = Color.fromString(noButtonBackgroundColor).asHex();
        String noTextColorHex = Color.fromString(noButtonTextColor).asHex();

        assertTrue(noBackgroundColorHex.toLowerCase().equals("#dc3545") ||
                        noBackgroundColorHex.toLowerCase().equals("#dc3545") ||
                        noBackgroundColorHex.toLowerCase().equals("#ff0000"),
                "No button should have red background");
        assertEquals("#ffffff", noTextColorHex.toLowerCase(), "No button should have white text");
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background

        WebElement nameField = driver.findElement(By.id("name"));
        String testName = "Test Name";
        nameField.sendKeys(testName);

        WebElement sendButton = driver.findElement(By.id("send"));
        sendButton.click();

        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("yes")));
        yesButton.click();

        WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("thankYou")));
        String expectedMessage = "Thank you, " + testName + ", for your feedback!";
        assertEquals(expectedMessage, thankYouMessage.getText());

        // Check color of text is white with green background
        String messageBackgroundColor = thankYouMessage.getCssValue("background-color");
        String messageTextColor = thankYouMessage.getCssValue("color");

        String backgroundColorHex = Color.fromString(messageBackgroundColor).asHex();
        String textColorHex = Color.fromString(messageTextColor).asHex();

        assertTrue(backgroundColorHex.toLowerCase().equals("#198754") ||
                        backgroundColorHex.toLowerCase().equals("#28a745") ||
                        backgroundColorHex.toLowerCase().equals("#008000"),
                "Thank you message should have green background");
        assertEquals("#ffffff", textColorHex.toLowerCase(), "Thank you message should have white text");
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background

        WebElement sendButton = driver.findElement(By.id("send"));
        sendButton.click();

        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("yes")));
        yesButton.click();

        WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("thankYou")));

        String expectedMessage = "Thank you for your feedback!";
        assertEquals(expectedMessage, thankYouMessage.getText(),
                "Thank you message should not include a name");

        String messageBackgroundColor = thankYouMessage.getCssValue("background-color");
        String messageTextColor = thankYouMessage.getCssValue("color");

        String backgroundColorHex = Color.fromString(messageBackgroundColor).asHex();
        String textColorHex = Color.fromString(messageTextColor).asHex();

        assertTrue(backgroundColorHex.toLowerCase().equals("#198754") ||
                        backgroundColorHex.toLowerCase().equals("#28a745") ||
                        backgroundColorHex.toLowerCase().equals("#008000"),
                "Thank you message should have green background");
        assertEquals("#ffffff", textColorHex.toLowerCase(), "Thank you message should have white text");
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement messageField = driver.findElement(By.id("message"));

        String testName = "Test Name";
        String testEmail = "test.name@test.com";
        String testMessage = "This is another test message for the feedback form.";

        nameField.sendKeys(testName);
        emailField.sendKeys(testEmail);
        messageField.sendKeys(testMessage);

        WebElement sendButton = driver.findElement(By.id("send"));
        sendButton.click();

        WebElement noButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("no")));
        noButton.click();

        assertEquals(testName, nameField.getAttribute("value"));
        assertEquals(testEmail, emailField.getAttribute("value"));
        assertEquals(testMessage, messageField.getAttribute("value"));

    }
}