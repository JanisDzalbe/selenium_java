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

        // TODO:
//         check that all field are empty and no ticks are clicked

        WebElement name = driver.findElement(By.id("fb_name"));
        assertTrue(name.getAttribute("value").isEmpty());

        WebElement age = driver.findElement(By.id("fb_age"));
        assertTrue(age.getAttribute("value").isEmpty());

        List<WebElement> selectLanguage = driver.findElements(By.className("w3-check"));
        for (WebElement languages : selectLanguage) {
            assertFalse(languages.isSelected());
        }

        WebElement comment = driver.findElement(By.name("comment"));
        assertTrue(age.getAttribute("value").isEmpty());

        assertFalse(driver.findElement(By.cssSelector("input[type='radio'][value='male']")).isSelected());
        assertFalse(driver.findElement(By.cssSelector("input[type='radio'][value='female']")).isSelected());

//         "Don't know" is selected in "Genre"
        assertTrue(driver.findElement(By.cssSelector("input[type='radio'][name='gender'][disabled]")).isSelected());
//
//         "Choose your option" in "How do you like us?"
        Select howDoYouLikeUs = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", howDoYouLikeUs.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.className("w3-blue")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-blue")).getCssValue("border-bottom-color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.className("w3-blue")).click();

//         check fields are empty or "null"
        assertTrue(driver.findElement(By.id("name")).getText().isBlank());
        assertTrue(driver.findElement(By.id("age")).getText().isBlank());
        assertTrue(driver.findElement(By.id("language")).getText().isBlank());
        assertEquals("null", driver.findElement(By.id("gender")).getText().trim());
        assertEquals("null", driver.findElement(By.id("option")).getText().trim());
        assertTrue(driver.findElement(By.id("comment")).getText().isBlank());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("border-bottom-color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("border-bottom-color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Dora");

        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys("25");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("#lang_check input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }
        WebElement englishCheckbox = driver.findElement(By.cssSelector("#lang_check input[type='checkbox'][value='English']"));
        if (!englishCheckbox.isSelected()) {
            englishCheckbox.click();
        }
        WebElement gender = driver.findElement(By.cssSelector("input[type='radio'][name='gender'][value='female']"));
        gender.click();

        Select howDoYouLikeUs = new Select(driver.findElement(By.id("like_us")));
        howDoYouLikeUs.selectByVisibleText("Ok, i guess");

        WebElement comment = driver.findElement(By.name("comment"));
        comment.sendKeys("No comment");

        WebElement send = driver.findElement(By.className("w3-blue"));
        send.click();

//         check fields are filled correctly
        assertEquals("Dora", driver.findElement(By.id("name")).getText());
        assertEquals("25", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("female", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals("No comment", driver.findElement(By.id("comment")).getText());

//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("border-bottom-color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("border-bottom-color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Dora");

//         click "Send"
        WebElement send = driver.findElement(By.className("w3-blue"));
        send.click();

//         click "Yes"
        WebElement yes = driver.findElement(By.className("w3-green"));
        yes.click();

//         check message text: "Thank you, NAME, for your feedback!"
        WebElement message = driver.findElement(By.id("fb_thx"));
        assertEquals("Thank you, Dora, for your feedback!", message.getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("border-bottom-color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        WebElement send = driver.findElement(By.className("w3-blue"));
        send.click();

//         click "Yes"
        WebElement yes = driver.findElement(By.className("w3-green"));
        yes.click();

//         check message text: "Thank you for your feedback!"
        WebElement message = driver.findElement(By.id("fb_thx"));
        assertEquals("Thank you for your feedback!", message.getText());

//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("border-bottom-color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys("Dora");

        WebElement age = driver.findElement(By.id("fb_age"));
        age.sendKeys("25");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("#lang_check input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }
        WebElement englishCheckbox = driver.findElement(By.cssSelector("#lang_check input[type='checkbox'][value='English']"));
        if (!englishCheckbox.isSelected()) {
            englishCheckbox.click();
        }
        WebElement gender = driver.findElement(By.cssSelector("input[type='radio'][name='gender'][value='female']"));
        gender.click();

        Select howDoYouLikeUs = new Select(driver.findElement(By.id("like_us")));
        howDoYouLikeUs.selectByVisibleText("Ok, i guess");

        WebElement comment = driver.findElement(By.name("comment"));
        comment.sendKeys("No comment");

//         click "Send"
        WebElement send = driver.findElement(By.className("w3-blue"));
        send.click();

//         click "No"
        WebElement no = driver.findElement(By.className("w3-red"));
        no.click();

//         check fields are filled correctly
        assertEquals("Dora", name.getAttribute("value"));
        assertEquals("25", age.getAttribute("value"));
        assertTrue(englishCheckbox.isSelected());
        assertTrue(gender.isSelected());
        assertEquals("Ok, i guess", howDoYouLikeUs.getFirstSelectedOption().getText());
        assertEquals("No comment", comment.getAttribute("value"));

    }
}