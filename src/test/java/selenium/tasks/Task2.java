package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.FeedbackPage;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;
    static FeedbackPage feedbackPage;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {

        assertTrue(driver.findElement(By.id("fb_name")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("fb_age")).getText().isEmpty());

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox: checkBoxes) {
            assertFalse(checkBox.isSelected());

        }

        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.className("w3-btn-block")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-btn-block")).getCssValue("border-bottom-color"));

//         TODO:
//         check that all field are empty and no ticks are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
    }

    @Test
    public void emptyFeedbackPage() throws Exception {

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        feedbackPage.submitButton();
        wait.until(ExpectedConditions.urlContains("https://acctabootcamp.github.io/site/tasks/check_feedback.html"));

        List<WebElement> descriptions = driver.findElements(By.cssSelector(".w3-card-4 .description"));

        for (WebElement description : descriptions) {
            String[] parts = description.getText().split(":\\s*", 2);
            String fieldValue = parts.length > 1 ? parts[1] : "";
            if (fieldValue.equals("")) {
                assertEquals("", fieldValue);
            } else if (fieldValue.equals("null")) {
                assertEquals("null", fieldValue);
            } else {
                throw new IllegalArgumentException("Unexpected field value: " + fieldValue);
            }
        }

        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("border-bottom-color"));

        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("border-bottom-color"));

//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);

        String name = "Tomas";
        String age = "18";
        List<String> languageValues = Arrays.asList("English", "French", "Spanish", "Chinese");
        String genre = "male";
        String option = "Good";
        String comment = "Thank you for service!";

        List<String> expectedResults = new ArrayList<>();
        expectedResults.add("Your name: " + name);
        expectedResults.add("Your age: " + age);
        expectedResults.add("Your language: English,French,Spanish,Chinese");
        expectedResults.add("Your genre: male");
        expectedResults.add("Your option of us: Good");
        expectedResults.add("Your comment: " + comment);

        feedbackPage.fillForm(name, age, languageValues, genre, option, comment);

        feedbackPage.submitButton();
        wait.until(ExpectedConditions.urlContains("https://acctabootcamp.github.io/site/tasks/check_feedback.html"));

        List<WebElement> descriptions = driver.findElements(By.cssSelector(".w3-card-4 .description"));

        for (int i = 0; i < descriptions.size(); i++) {
            assertEquals(descriptions.get(i).getText(), expectedResults.get(i));
        }

        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("border-bottom-color"));

        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("border-bottom-color"));
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        String name = "Tomas";
        String expectedFeedbackMessage= "Thank you, " + name +", for your feedback!";

        driver.findElement(By.id("fb_name")).sendKeys("Tomas");

        feedbackPage.submitButton();
        wait.until(ExpectedConditions.urlContains("https://acctabootcamp.github.io/site/tasks/check_feedback.html"));

        driver.findElement(By.className("w3-green")).click();

        wait.until(ExpectedConditions.urlContains("https://acctabootcamp.github.io/site/tasks/thank_you_for_feedback.html"));

        assertEquals(expectedFeedbackMessage, driver.findElement(By.id("message")).getText());

        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.cssSelector(".w3-panel.w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.cssSelector(".w3-panel.w3-green")).getCssValue("color"));
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        String expectedFeedbackMessage= "Thank you for your feedback!";

        feedbackPage.submitButton();
        wait.until(ExpectedConditions.urlContains("https://acctabootcamp.github.io/site/tasks/check_feedback.html"));

        driver.findElement(By.className("w3-green")).click();

        wait.until(ExpectedConditions.urlContains("https://acctabootcamp.github.io/site/tasks/thank_you_for_feedback.html"));

        assertEquals(expectedFeedbackMessage, driver.findElement(By.id("message")).getText());
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.cssSelector(".w3-panel.w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.cssSelector(".w3-panel.w3-green")).getCssValue("color"));

//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        List <WebElement> languages = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        String name = "Tomas";
        String age = "18";
        List<String> languageValues = Arrays.asList("English", "French", "Spanish", "Chinese");
        String genre = "male";
        String option = "Good";
        String comment = "Thank you for service!";

        feedbackPage.fillForm(name, age, languageValues, genre, option, comment);

        feedbackPage.submitButton();
        wait.until(ExpectedConditions.urlContains("https://acctabootcamp.github.io/site/tasks/check_feedback.html"));

        driver.findElement(By.className("w3-red")).click();
        wait.until(ExpectedConditions.urlContains("https://acctabootcamp.github.io/site/tasks/provide_feedback"));

        assertEquals(name, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(age, driver.findElement(By.id("fb_age")).getAttribute("value"));
        for (WebElement language: languages) {
            assertTrue(language.isSelected());
        }
        assertTrue(driver.findElement(By.cssSelector(".w3-radio[type='radio'][value='male']")).isSelected());
        assertEquals(option, driver.findElement(By.id("like_us")).getAttribute("value"));
        assertEquals(comment, driver.findElement(By.cssSelector(".w3-input.w3-border[name='comment']")).getAttribute("value"));
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
    }
}
