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
        WebElement nameField = driver.findElement(By.id("fb_name"));
        WebElement ageField = driver.findElement(By.id("fb_age"));
        WebElement maleRadio = driver.findElement(By.cssSelector("input[value='male']"));
        WebElement femaleRadio = driver.findElement(By.cssSelector("input[value='female']"));
        WebElement dK = driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(4) > input:nth-child(6)"));
        WebElement sendButton = driver.findElement(By.xpath("//*[@type='submit']"));

        assertEquals("", nameField.getAttribute("value"));
        assertEquals("", ageField.getAttribute("value"));

        List<WebElement> langBoxs = driver.findElements(By.cssSelector("#lang_check"));

        for (WebElement langBox : langBoxs) {
            assertFalse(langBox.isSelected()); // checkboxes are NOT selected
            langBox.click();
        }

        assertFalse(maleRadio.isSelected());
        assertFalse(femaleRadio.isSelected());
        assertTrue(dK.isSelected());

        assertTrue(driver.findElement(By.id("like_us")).isDisplayed());
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

        assertEquals("",driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea")).getText());
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", sendButton.getCssValue("text-decoration-color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)


    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
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
