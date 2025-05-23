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

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            assertFalse(checkbox.isSelected());
        }

        WebElement dontKnow = driver.findElement(By.cssSelector("input[type='radio'][value='']"));
        assertTrue(dontKnow.isSelected());

        Select select = new Select(driver.findElement(By.id("like_us")));
        WebElement selectedOption = select.getFirstSelectedOption();
        assertEquals("Choose your option", selectedOption.getText());

        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        assertTrue(sendButton.getAttribute("class").contains("w3-blue"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));

//         TODO:
//         check that all field are empty and no ticks are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());

        WebElement confirmYes = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement confirmNo = driver.findElement(By.xpath("//button[text()='No']"));

        assertTrue(confirmYes.getAttribute("class").contains("w3-green"));
        assertTrue(confirmNo.getAttribute("class").contains("w3-red"));


//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
        driver.findElement(By.name("name")).sendKeys("Ruslan");
        driver.findElement(By.name("age")).sendKeys("20");

        driver.findElement(By.cssSelector("input[type='checkbox'][value='English']")).click();
        driver.findElement(By.cssSelector("input[type='checkbox'][value='Spanish']")).click();

        driver.findElement(By.cssSelector("input[type='radio'][value='male']")).click();

        Select dropdown = new Select(driver.findElement(By.name("option")));
        dropdown.selectByVisibleText("Why me?");

        driver.findElement(By.name("comment")).sendKeys("Fullfilled form)");

        WebElement sendButton = driver.findElement(By.cssSelector("button[type='submit']"));
        sendButton.click();

        assertEquals("Ruslan", driver.findElement(By.id("name")).getText());
        assertEquals("20", driver.findElement(By.id("age")).getText());
        assertEquals("English,Spanish", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Why me?", driver.findElement(By.id("option")).getText());
        assertEquals("Fullfilled form)", driver.findElement(By.id("comment")).getText());

        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noButton = driver.findElement(By.xpath("//button[text()='No']"));

        assertTrue(yesButton.getAttribute("class").contains("w3-green"));
        assertTrue(noButton.getAttribute("class").contains("w3-red"));
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
        String inputName = "Ruslan";
        driver.findElement(By.name("name")).sendKeys(inputName);

        WebElement sendBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        sendBtn.click();

        driver.findElement(By.xpath("//button[text()='Yes']")).click();

        WebElement messageHeader = driver.findElement(By.id("message"));
        String expected = "Thank you, " + inputName + ", for your feedback!";
        assertEquals(expected, messageHeader.getText());

        WebElement messagePanel = messageHeader.findElement(By.xpath("./ancestor::div[contains(@class, 'w3-panel')]"));
        assertEquals("rgba(76, 175, 80, 1)", messagePanel.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", messageHeader.getCssValue("color"));

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