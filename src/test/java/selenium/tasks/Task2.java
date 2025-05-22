package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

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

        assertFalse(driver.findElement(By.cssSelector("input[value='English']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[value='French']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[value='Spanish']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[value='Chinese']")).isSelected());

        assertFalse(driver.findElement(By.cssSelector("input[name='gender'][value='male']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[name='gender'][value='female']")).isSelected());
        WebElement dontKnowRadio = driver.findElement(By.cssSelector("input[name='gender'][disabled]"));
        assertTrue(dontKnowRadio.isSelected());

        Select select = new Select(driver.findElement(By.id("like_us")));
        WebElement firstOption = select.getFirstSelectedOption();
        assertEquals("", firstOption.getAttribute("value"));

        WebElement sendBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        assertEquals("rgba(255, 255, 255, 1)", sendBtn.getCssValue("color"));
        assertEquals("rgba(33, 150, 243, 1)", sendBtn.getCssValue("background-color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        assertTrue(driver.findElement(By.id("name")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("age")).getText().isEmpty());
        assertTrue(driver.findElement(By.id("language")).getText().isEmpty());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertTrue(driver.findElement(By.id("comment")).getText().isEmpty());

        WebElement yesBtn = driver.findElement(By.cssSelector(".w3-green"));
        WebElement noBtn = driver.findElement(By.cssSelector(".w3-red"));
        assertEquals("rgba(76, 175, 80, 1)", yesBtn.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesBtn.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noBtn .getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noBtn.getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        driver.findElement(By.id("fb_name")).sendKeys("Walter White");
        driver.findElement(By.id("fb_age")).sendKeys("52");
        driver.findElement(By.cssSelector("input[value='English']")).click();;
        driver.findElement(By.cssSelector("input[value='French']")).click();
        driver.findElement(By.cssSelector("input[value='Spanish']")).click();
        driver.findElement(By.cssSelector("input[value='Chinese']")).click();

        driver.findElement(By.cssSelector("input[name='gender'][value='male']")).click();
        Select select = new Select(driver.findElement(By.id("like_us")));
        select.selectByVisibleText("Bad");
        driver.findElement(By.cssSelector("textarea[name='comment']")).sendKeys("Breaking Bad!");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        assertEquals("Walter White", driver.findElement(By.id("name")).getText());
        assertEquals("52", driver.findElement(By.id("age")).getText());
        assertEquals("English,French,Spanish,Chinese", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Bad", driver.findElement(By.id("option")).getText());
        assertEquals("Breaking Bad!", driver.findElement(By.id("comment")).getText());

        WebElement yesBtn = driver.findElement(By.cssSelector(".w3-green"));
        WebElement noBtn = driver.findElement(By.cssSelector(".w3-red"));
        assertEquals("rgba(76, 175, 80, 1)", yesBtn.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesBtn.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", noBtn .getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noBtn.getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        driver.findElement(By.cssSelector("#fb_name")).sendKeys("Walter");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.cssSelector(".w3-green")).click();

        WebElement message = driver.findElement(By.cssSelector("#message"));
        assertEquals("Thank you, Walter, for your feedback!", message.getText());

        WebElement msgPanel = driver.findElement(By.cssSelector(".w3-panel"));
        assertEquals("rgba(76, 175, 80, 1)", msgPanel.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", msgPanel.getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.cssSelector(".w3-green")).click();

        WebElement message = driver.findElement(By.id("message"));
        assertEquals("Thank you for your feedback!", message.getText());

        WebElement msgPanel = driver.findElement(By.cssSelector(".w3-panel"));
        assertEquals("rgba(76, 175, 80, 1)", msgPanel.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", msgPanel.getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        driver.findElement(By.id("fb_name")).sendKeys("Walter White");
        driver.findElement(By.id("fb_age")).sendKeys("52");
        driver.findElement(By.cssSelector("input[value='English']")).click();;
        driver.findElement(By.cssSelector("input[value='French']")).click();
        driver.findElement(By.cssSelector("input[value='Spanish']")).click();
        driver.findElement(By.cssSelector("input[value='Chinese']")).click();
        driver.findElement(By.cssSelector("input[name='gender'][value='male']")).click();
        Select select = new Select(driver.findElement(By.id("like_us")));
        select.selectByVisibleText("Bad");
        driver.findElement(By.cssSelector("textarea[name='comment']")).sendKeys("Breaking Bad!");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.cssSelector(".w3-red")).click();

        assertEquals("Walter White", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("52", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue(driver.findElement(By.cssSelector("input[value='English']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector("input[value='French']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector("input[value='Spanish']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector("input[value='Chinese']")).isSelected());
        assertTrue(driver.findElement(By.cssSelector("input[name='gender'][value='male']")).isSelected());
        Select select2 = new Select(driver.findElement(By.id("like_us")));
        WebElement firstOption = select2.getFirstSelectedOption();
        assertEquals("Bad", firstOption.getAttribute("value"));
        assertEquals("Breaking Bad!", driver.findElement(By.cssSelector("textarea[name='comment']")).getAttribute("value"));
    }
}
