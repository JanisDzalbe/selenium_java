package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class  Task2 {
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
        assertEquals("", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("", driver.findElement(By.id("fb_age")).getAttribute("value"));
        List<WebElement> checks = driver.findElements(By.className("w3-check"));
        for (WebElement lang : checks) {
            assertFalse(lang.isSelected());
        }
        assertEquals("", driver.findElement(By.name("comment")).getText());

//         "Don't know" is selected in "Genre"
        List<WebElement> radios = driver.findElements(By.className("w3-radio"));
        for (WebElement genre : radios) {
            assertNotEquals("null", genre.isSelected());
        }
        assertTrue(driver.findElement(By.xpath("//*[@type='radio' and @value='']")).isSelected());
//         "Choose your option" in "How do you like us?"
        assertTrue(driver.findElement(By.xpath("//option[@value='']")).isSelected());
//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.className("w3-blue")).getCssValue("background-color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.className("w3-blue")).click();
//         check fields are empty or "null"
        assertEquals("", driver.findElement(By.id("name")).getText());
        assertEquals("", driver.findElement(By.id("age")).getText());
        assertEquals("", driver.findElement(By.id("language")).getText());
        assertEquals("null", driver.findElement(By.id("gender")).getText());
        assertEquals("null", driver.findElement(By.id("option")).getText());
        assertEquals("", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.id("fb_name")).sendKeys("Andrejs");
        driver.findElement(By.id("fb_age")).sendKeys("40");
        driver.findElement(By.xpath("//*[@value='Chinese']")).click();
        driver.findElement(By.xpath("//*[@value='male']")).click();
        driver.findElement(By.xpath("//*[@value='Why me?']")).click();
        driver.findElement(By.name("comment")).sendKeys("No comments");
        driver.findElement(By.className("w3-blue")).click();
//         check fields are filled correctly
        assertEquals("Andrejs", driver.findElement(By.id("name")).getText());
        assertEquals("40", driver.findElement(By.id("age")).getText());
        assertEquals("Chinese", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Why me?", driver.findElement(By.id("option")).getText());
        assertEquals("No comments", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        driver.findElement(By.id("fb_name")).sendKeys("Andrejs");
//         click "Send"
        driver.findElement(By.className("w3-blue")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, Andrejs, for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.className("w3-blue")).click();
//         click "Yes"
        driver.findElement(By.className("w3-green")).click();
//         check message text: "Thank you for your feedback!"
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.id("message")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("Andrejs");
        driver.findElement(By.id("fb_age")).sendKeys("40");
        driver.findElement(By.xpath("//*[@value='Chinese']")).click();
        driver.findElement(By.xpath("//*[@value='male']")).click();
        driver.findElement(By.xpath("//*[@value='Why me?']")).click();
        driver.findElement(By.name("comment")).sendKeys("No comments");
//         click "Send"
        driver.findElement(By.className("w3-blue")).click();
//         click "No"
        driver.findElement(By.className("w3-red")).click();
//         check fields are filled correctly
        assertEquals("Andrejs", driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals("40", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertTrue(driver.findElement(By.xpath("//*[@value='Chinese']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[@value='male']")).isSelected());
        assertTrue(driver.findElement(By.xpath("//*[@value='Why me?']")).isSelected());
        assertEquals("No comments", driver.findElement(By.name("comment")).getAttribute("value"));
    }
}
