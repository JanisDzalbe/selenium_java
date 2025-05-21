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
        assertEquals("", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("fb_age")).getAttribute("value"));

        List<WebElement> languages = driver.findElements(By.name("language"));
        for (WebElement lang:languages){
            assertFalse(lang.isSelected());
        }

        List<WebElement> genders = driver.findElements(By.name("gender"));
        boolean selected = false;
        for (WebElement gend:genders){
            if (gend.isSelected() && gend.getAttribute("value").isEmpty()){
                selected = true;
            }
        }
        assertTrue(selected);

        WebElement select = driver.findElement(By.id("like_us"));
        WebElement selectedOption = select.findElement(By.cssSelector("option:checked"));
        assertEquals("Choose your option", selectedOption.getText());

        WebElement sendBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        String backgroundColor = sendBtn.getCssValue("background-color");
        String textColor = sendBtn.getCssValue("color");

        assertTrue(backgroundColor.contains("33, 150, 243") || backgroundColor.contains("blue")); // blue
        assertTrue(textColor.contains("255, 255, 255") || textColor.contains("white")); // white


//         TODO:
//         check that all field are empty and no ticks are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
        WebElement sendBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        sendBtn.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("fb_thx")));

        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());

        WebElement yesBtn = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noBtn = driver.findElement(By.xpath("//button[text()='No']"));

        assertTrue(yesBtn.getAttribute("class").contains("w3-green"));
        assertTrue(noBtn.getAttribute("class").contains("w3-red"));

//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {

        driver.findElement(By.id("fb_name")).sendKeys("Daniils Grigorjevs");
        driver.findElement(By.id("fb_age")).sendKeys("28");

        WebElement englishCheckbox = driver.findElement(By.cssSelector("input[type='checkbox'][value='English']"));
        if (!englishCheckbox.isSelected()) {
            englishCheckbox.click();
        }

        driver.findElement(By.name("gender")).click();

        Select optSelect = new Select(driver.findElement(By.id("like_us")));
        optSelect.selectByVisibleText("Ok, i guess");

        driver.findElement(By.name("comment")).sendKeys("It was very good experience");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("fb_thx")));

        assertEquals("Daniils Grigorjevs", driver.findElement(By.id("name")).getText());
        assertEquals("28", driver.findElement(By.id("age")).getText());
        assertEquals("English", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Ok, i guess", driver.findElement(By.id("option")).getText());
        assertEquals("It was very good experience", driver.findElement(By.id("comment")).getText());

        WebElement yesBtn = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noBtn = driver.findElement(By.xpath("//button[text()='No']"));

        assertTrue(yesBtn.getAttribute("class").contains("w3-green"));
        assertTrue(noBtn.getAttribute("class").contains("w3-red"));
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {

        WebElement nameInput = driver.findElement(By.id("fb_name"));
        String name = "Daniils Grigorjevs";
        nameInput.sendKeys(name);

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.findElement(By.xpath("//button[text()='Yes']")).click();

        WebElement message = driver.findElement(By.id("message"));

        assertEquals("Thank you, " +  name + ", for your feedback!", message.getText());

        WebElement panel = driver.findElement(By.cssSelector("#fb_thx .w3-panel"));
        String backgroundColor = panel.getCssValue("background-color");
        String color = message.getCssValue("color");

        assertEquals("rgba(76, 175, 80, 1)", backgroundColor);
        assertEquals("rgba(255, 255, 255, 1)", color);
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.xpath("//button[text()='Yes']")).click();

        WebElement message = driver.findElement(By.id("message"));
        assertEquals("Thank you for your feedback!", message.getText());

        WebElement panel = driver.findElement(By.cssSelector("#fb_thx .w3-panel"));
        String backgroundColor = panel.getCssValue("background-color");
        String color = message.getCssValue("color");

        assertEquals("rgba(76, 175, 80, 1)", backgroundColor);
        assertEquals("rgba(255, 255, 255, 1)", color);

//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
        driver.findElement(By.id("fb_name")).sendKeys("Daniils Grigorjevs");
        driver.findElement(By.id("fb_age")).sendKeys("28");

        WebElement englishCheckbox = driver.findElement(By.cssSelector("input[type='checkbox'][value='English']"));
        if (!englishCheckbox.isSelected()) {
            englishCheckbox.click();
        }

        driver.findElement(By.cssSelector("input[name='gender'][value='male']")).click();

        Select optSelect = new Select(driver.findElement(By.id("like_us")));
        optSelect.selectByVisibleText("Ok, i guess");

        driver.findElement(By.name("comment")).sendKeys("It was very good experience");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("fb_thx")));

        driver.findElement(By.xpath("//button[text()='No']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("fb_name")));

        assertEquals("Daniils Grigorjevs", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("28", driver.findElement(By.id("fb_age")).getAttribute("value"));

        WebElement englishCheckboxAfter = driver.findElement(By.cssSelector("input[type='checkbox'][value='English']"));
        assertTrue(englishCheckboxAfter.isSelected());

        WebElement genderMaleAfter = driver.findElement(By.cssSelector("input[name='gender'][value='male']"));
        assertTrue(genderMaleAfter.isSelected());

        Select optSelectAfter = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Ok, i guess", optSelectAfter.getFirstSelectedOption().getText());

        assertEquals("It was very good experience", driver.findElement(By.name("comment")).getAttribute("value"));



//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
    }
}
