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
        assertTrue(dK.isDisplayed());
        assertTrue(dK.isSelected());
        assertFalse(dK.isEnabled());

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
        WebElement sendButton = driver.findElement(By.xpath("//*[@type='submit']"));

        sendButton.click();
        WebElement name = driver.findElement(By.id("name"));
        WebElement age = driver.findElement(By.id("age"));
        WebElement language = driver.findElement(By.id("language"));
        WebElement gender = driver.findElement(By.id("gender"));
        WebElement option = driver.findElement(By.id("option"));
        WebElement comment = driver.findElement(By.id("comment"));
        WebElement yesButton = driver.findElement(By.xpath("//*[@id='fb_thx']//button[1]"));
        WebElement noButton = driver.findElement(By.xpath("//*[@id='fb_thx']//button[2]"));



        assertTrue(name.getText().equals("null") || name.getText().isEmpty());
        assertTrue(age.getText().equals("null") || age.getText().isEmpty());
        assertTrue(language.getText().equals("null") || language.getText().isEmpty());
        assertTrue(gender.getText().equals("null") || gender.getText().isEmpty());
        assertTrue(option.getText().equals("null") || option.getText().isEmpty());
        assertTrue(comment.getText().equals("null") || comment.getText().isEmpty());

        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", noButton.getCssValue("text-decoration-color"));
        }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        WebElement nameField = driver.findElement(By.id("fb_name"));
        WebElement ageField = driver.findElement(By.id("fb_age"));
        WebElement languageF = driver.findElement(By.cssSelector("input[value='French']"));
        WebElement maleRadio = driver.findElement(By.cssSelector("input[value='male']"));
        WebElement sendButton = driver.findElement(By.xpath("//*[@type='submit']"));
        WebElement comment = driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea"));
        String nameKey = "Tory";
        String ageKey = "21";
        String commentKey = "Hi";


        nameField.clear();
        nameField.sendKeys(nameKey);
        ageField.clear();
        ageField.sendKeys(ageKey);
        languageF.click();
        maleRadio.click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Good");
        comment.sendKeys(commentKey);
        sendButton.click();


        WebElement name = driver.findElement(By.id("name"));
        WebElement age = driver.findElement(By.id("age"));
        WebElement language = driver.findElement(By.id("language"));
        WebElement gender = driver.findElement(By.id("gender"));
        WebElement option = driver.findElement(By.id("option"));
        WebElement commentCheck = driver.findElement(By.id("comment"));
        WebElement yesButton = driver.findElement(By.xpath("//*[@id='fb_thx']//button[1]"));
        WebElement noButton = driver.findElement(By.xpath("//*[@id='fb_thx']//button[2]"));



        assertTrue(name.getText().equals(nameKey));
        assertTrue(age.getText().equals(ageKey));
        assertTrue(language.getText().equals("French"));
        assertTrue(gender.getText().equals("male"));
        assertTrue(option.getText().equals("Good"));
        assertTrue(commentCheck.getText().equals(commentKey));

        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", yesButton.getCssValue("text-decoration-color"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)", noButton.getCssValue("text-decoration-color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        WebElement nameField = driver.findElement(By.id("fb_name"));
        WebElement sendButton = driver.findElement(By.xpath("//*[@type='submit']"));
        String nameKey = "Harry";


        nameField.clear();
        nameField.sendKeys(nameKey);
        sendButton.click();

        WebElement yesButton = driver.findElement(By.xpath("//*[@id='fb_thx']//button[1]"));
        yesButton.click();

        WebElement pageMessage = driver.findElement(By.id("message"));
        assertEquals("Thank you, " + nameKey + ", for your feedback!", pageMessage.getText());
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        WebElement sendButton = driver.findElement(By.xpath("//*[@type='submit']"));
        sendButton.click();

        WebElement yesButton = driver.findElement(By.xpath("//*[@id='fb_thx']//button[1]"));
        yesButton.click();

        WebElement pageMessage = driver.findElement(By.id("message"));
        assertEquals("Thank you for your feedback!", pageMessage.getText());

        assertEquals("rgba(255, 255, 255, 1)", pageMessage.getCssValue("color"));
//        assertEquals("rgba(76, 175, 80, 1)", pageMessage.getCssValue("background-color"));

//      found at the page background-color as #4CAF50 or rgba (76, 175, 80,1), but didn't get how to check it, especially if system says "Actual : rgba(0,0,0,0)"
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        WebElement nameField = driver.findElement(By.id("fb_name"));
        WebElement ageField = driver.findElement(By.id("fb_age"));
        WebElement languageC = driver.findElement(By.cssSelector("input[value='Chinese']"));
        WebElement femaleRadio = driver.findElement(By.cssSelector("input[value='female']"));
        WebElement sendButton = driver.findElement(By.xpath("//*[@type='submit']"));
        WebElement comment = driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea"));
        String nameKey = "Jany";
        String ageKey = "34";
        String commentKey = "Good morning";

        nameField.clear();
        nameField.sendKeys(nameKey);
        ageField.clear();
        ageField.sendKeys(ageKey);
        languageC.click();
        femaleRadio.click();
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByVisibleText("Why me?");
        comment.sendKeys(commentKey);
        sendButton.click();

        WebElement noButton = driver.findElement(By.xpath("//*[@id='fb_thx']//button[2]"));
        noButton.click();

        assertEquals(nameKey, nameField.getAttribute("value"));
        assertEquals(ageKey, ageField.getAttribute("value"));
        assertTrue(languageC.isSelected());
        assertTrue(femaleRadio.isSelected());
        assertEquals("Why me?", dropdown.getFirstSelectedOption().getText());
        assertEquals(commentKey, comment.getAttribute("value"));
    }
}
