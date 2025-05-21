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
        List<WebElement> name_surname = driver.findElements(By.cssSelector(".w3-input[type='text']"));

        for (WebElement box : name_surname) {
            assertFalse(box.isSelected());
        }

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }

        WebElement option3 = driver.findElement(By.xpath("(//input[@type='radio'])[3]"));
        assertTrue(option3.isSelected());

        Select likeUs = new Select(driver.findElement(By.id("like_us")));

        likeUs.selectByVisibleText("Good");

        WebElement send_button = driver.findElement(By.className("w3-blue"));
        assertEquals("rgba(33, 150, 243, 1)", send_button.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", send_button.getCssValue("color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)

        driver.findElement(By.className("w3-blue")).click();

        List<WebElement> fields = driver.findElements(By.className("description"));

        for (WebElement field : fields) {
            String text = field.getText();
            assertTrue(text.isEmpty() || text.equalsIgnoreCase("null"));
        }

        WebElement yes_button = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", yes_button.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yes_button.getCssValue("color"));

        WebElement no_button = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(244, 67, 54, 1)", no_button.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", no_button.getCssValue("color"));

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        String name = "German";
        String age = "21";
        String comment = "Hello world!";

        WebElement name_field = driver.findElement(By.id("fb_name"));
        name_field.sendKeys(name);

        WebElement age_field = driver.findElement(By.id("fb_age"));
        age_field.sendKeys(age);

        WebElement lang_option = driver.findElement(By.cssSelector(".w3-check[value='English'][type='checkbox']"));
        lang_option.click();
        assertTrue(lang_option.isSelected());

        WebElement genre_option = driver.findElement(By.xpath("(//input[@type='radio'])[1]"));
        genre_option.click();
        assertTrue(genre_option.isSelected());

        Select likeUs = new Select(driver.findElement(By.id("like_us")));
        likeUs.selectByVisibleText("Good");

        WebElement comment_field = driver.findElement(By.name("comment"));
        comment_field.sendKeys(comment);

        driver.findElement(By.className("w3-blue")).click();
        Thread.sleep(5000);

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
    }
}
