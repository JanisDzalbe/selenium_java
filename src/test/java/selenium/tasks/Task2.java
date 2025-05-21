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
        for(WebElement inputField : driver.findElements(By.className("w3-input"))){
            assertEquals("",inputField.getAttribute("value"));
        }
        for(WebElement tickField : driver.findElements(By.className("w3-check"))){
            assertFalse(tickField.isSelected());
        }
        assertEquals("", driver.findElement(By.name("comment")).getAttribute("value"));
        assertTrue(driver.findElement(By.cssSelector(".w3-radio[value='']")).isSelected());
        Select dropDown = new Select(driver.findElement(By.className("w3-select")));
        assertEquals("Choose your option", dropDown.getFirstSelectedOption().getText());
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.className("w3-btn-block")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-btn-block")).getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
        initialFeedbackPage();
        driver.findElement(By.className("w3-btn-block")).click();
        for(WebElement field : driver.findElements(By.xpath("//*[@class='description']//span"))){
            assertTrue(field.getText().isEmpty() || field.getText().equals("null"));
        }
        // I could write it as two separate equals for each button to check colors but wanted to try boolean algebra with assertTrue
        assertTrue(driver.findElement(By.className("w3-green")).getCssValue("background-color").equals("rgba(76, 175, 80, 1)") && driver.findElement(By.className("w3-green")).getCssValue("color").equals("rgba(255, 255, 255, 1)"));
        assertTrue(driver.findElement(By.className("w3-red")).getCssValue("background-color").equals("rgba(244, 67, 54, 1)") && driver.findElement(By.className("w3-red")).getCssValue("color").equals("rgba(255, 255, 255, 1)"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        String name = "TestName";
        String age = "69";
        String comment = "Test comment";
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.id("fb_age")).sendKeys(age);
        for(WebElement tickField : driver.findElements(By.className("w3-check"))){
            tickField.click();
        }
        driver.findElement(By.cssSelector(".w3-radio[value='male']")).click();
        Select dropDown = new Select(driver.findElement(By.className("w3-select")));
        dropDown.selectByValue("Why me?");
        driver.findElement(By.name("comment")).sendKeys(comment);
        driver.findElement(By.className("w3-btn-block")).click();

        assertEquals(name, driver.findElement(By.id("name")).getText());
        assertEquals(age, driver.findElement(By.id("age")).getText());
        assertEquals("English,French,Spanish,Chinese", driver.findElement(By.id("language")).getText());
        assertEquals("male", driver.findElement(By.id("gender")).getText());
        assertEquals("Why me?", driver.findElement(By.id("option")).getText());
        assertEquals(comment, driver.findElement(By.id("comment")).getText());
        // I will now rewrite using assertEquals for better readability
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-green")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-green")).getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.className("w3-red")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-red")).getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        initialFeedbackPage();
        String name = "TestName";
        driver.findElement(By.id("fb_name")).sendKeys(name);
        driver.findElement(By.className("w3-btn-block")).click();
        driver.findElement(By.className("w3-green")).click();
        assertEquals("Thank you, "+name+", for your feedback!", driver.findElement(By.id("message")).getText());
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-panel")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-panel")).getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        initialFeedbackPage();
        driver.findElement(By.className("w3-btn-block")).click();
        driver.findElement(By.className("w3-green")).click();
        assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.className("w3-panel")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.className("w3-panel")).getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        notEmptyFeedbackPage();
        String name = "TestName";
        String age = "69";
        String comment = "Test comment";
        driver.findElement(By.className("w3-red")).click();
        assertEquals(name, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(age, driver.findElement(By.id("fb_age")).getAttribute("value"));
        for(WebElement tickField : driver.findElements(By.className("w3-check"))){
            assertTrue(tickField.isSelected());
        }
        assertTrue(driver.findElement(By.cssSelector(".w3-radio[value='male']")).isSelected());
        Select dropDown = new Select(driver.findElement(By.className("w3-select")));
        assertEquals("Why me?", dropDown.getFirstSelectedOption().getText());
        assertEquals(comment, driver.findElement(By.name("comment")).getAttribute("value"));
    }
}
