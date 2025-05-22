package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
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

        assertEquals("", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("fb_age")).getAttribute("value"));

        List<String> languages = Arrays.asList("English", "French", "Spanish", "Chinese");
        for (String lang : languages) {
            assertFalse(driver.findElement(By.cssSelector("input[type='checkbox'][value='" + lang + "']")).isSelected());
        }

        WebElement dontKnow = driver.findElement(By.cssSelector("input[type='radio'][disabled]"));
        assertTrue(dontKnow.isSelected());

        Select select = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", select.getFirstSelectedOption().getText());

        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        List<String> fieldIds = List.of("name", "age", "language", "gender", "option", "comment");
        for (String fieldId : fieldIds) {
            String value = driver.findElement(By.id(fieldId)).getText();
            assertTrue(value.isEmpty() || value.equalsIgnoreCase("null"));
        }

        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-green"));
        WebElement noButton = driver.findElement(By.cssSelector("button.w3-red"));

        assertTrue(yesButton.getCssValue("background-color").contains("rgba(76, 175, 80, 1)"));
        assertTrue(yesButton.getCssValue("color").contains("rgba(255, 255, 255, 1)"));

        assertTrue(noButton.getCssValue("background-color").contains("rgba(244, 67, 54, 1)"));
        assertTrue(noButton.getCssValue("color").contains("rgba(255, 255, 255, 1)"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)

        String inputName = "Kirils Lebeds";
        String inputAge = "37";
        String selectGender = "male";
        String selectOption = "Good";
        String writeComment = "Teamwork Makes the Dream Work!";

        driver.findElement(By.id("fb_name")).sendKeys(inputName);
        driver.findElement(By.id("fb_age")).sendKeys(inputAge);
        driver.findElement(By.cssSelector("input[type='checkbox'][value='English']")).click();
        driver.findElement(By.cssSelector("input[type='checkbox'][value='French']")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='" + selectGender + "']")).click();

        Select dropdownMenu = new Select(driver.findElement(By.id("like_us")));
        dropdownMenu.selectByVisibleText(selectOption);

        driver.findElement(By.name("comment")).sendKeys(writeComment);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        assertEquals(inputName, driver.findElement(By.id("name")).getText());
        assertEquals(inputAge, driver.findElement(By.id("age")).getText());
        assertEquals("English,French", driver.findElement(By.id("language")).getText());
        assertEquals(selectGender, driver.findElement(By.id("gender")).getText());
        assertEquals(selectOption, driver.findElement(By.id("option")).getText());
        assertEquals(writeComment, driver.findElement(By.id("comment")).getText());

        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-green"));
        WebElement noButton = driver.findElement(By.cssSelector("button.w3-red"));

        assertTrue(yesButton.getCssValue("background-color").contains("rgba(76, 175, 80, 1)"));
        assertTrue(yesButton.getCssValue("color").contains("rgba(255, 255, 255, 1)"));

        assertTrue(noButton.getCssValue("background-color").contains("rgba(244, 67, 54, 1)"));
        assertTrue(noButton.getCssValue("color").contains("rgba(255, 255, 255, 1)"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background

        String inputName = "Kirils Lebeds";

        driver.findElement(By.id("fb_name")).sendKeys(inputName);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.w3-green")));

        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-green"));
        yesButton.click();

        //WebElement thankYou = driver.findElement(By.id("message"));
        //WebElement thankYou = driver.findElement(By.xpath("//*[@id='fb_thx']//div[contains(@class, 'w3-panel') and contains(@class, 'w3-green')]//h2[@id='message']"));
        WebElement thankYou = driver.findElement(By.xpath("//*[@id='fb_thx']//div[contains(@class, 'w3-panel') and contains(@class, 'w3-green')]"));
        String expectedMessage = "Thank you, " + inputName + ", for your feedback!";
        assertEquals(expectedMessage, thankYou.getText());

        assertTrue(thankYou.getCssValue("background-color").contains("rgba(76, 175, 80, 1)"));
        assertTrue(thankYou.getCssValue("color").contains("rgba(255, 255, 255, 1)"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement yesButton = driver.findElement(By.cssSelector("button.w3-green"));
        yesButton.click();

        WebElement thankYou = driver.findElement(By.xpath("//*[@id='fb_thx']//div[contains(@class, 'w3-panel') and contains(@class, 'w3-green')]"));
        String expectedMessage = "Thank you for your feedback!";
        assertEquals(expectedMessage, thankYou.getText());

        assertTrue(thankYou.getCssValue("background-color").contains("rgba(76, 175, 80, 1)"));
        assertTrue(thankYou.getCssValue("color").contains("rgba(255, 255, 255, 1)"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly

        String inputName = "Kirils Lebeds";
        String inputAge = "37";
        String selectGender = "male";
        String selectOption = "Good";
        String writeComment = "Teamwork Makes the Dream Work!";

        driver.findElement(By.id("fb_name")).sendKeys(inputName);
        driver.findElement(By.id("fb_age")).sendKeys(inputAge);
        driver.findElement(By.cssSelector("input[type='checkbox'][value='English']")).click();
        driver.findElement(By.cssSelector("input[type='checkbox'][value='French']")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='" + selectGender + "']")).click();

        Select dropdownMenuSend = new Select(driver.findElement(By.id("like_us")));
        dropdownMenuSend.selectByVisibleText(selectOption);

        driver.findElement(By.name("comment")).sendKeys(writeComment);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement noButton = driver.findElement(By.cssSelector("button.w3-red"));
        noButton.click();

        assertEquals(inputName, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(inputAge, driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue(driver.findElement(By.cssSelector("input[type='checkbox'][value='English']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector("input[type='checkbox'][value='French']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector("input[type='radio'][value='" + selectGender + "']")).isSelected());

        Select dropdownMenuBack = new Select(driver.findElement(By.id("like_us")));
        assertEquals(selectOption, dropdownMenuBack.getFirstSelectedOption().getText());

        assertEquals(writeComment, driver.findElement(By.name("comment")).getAttribute("value"));
    }
}
