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
        assertTrue(driver.findElement(By.id("fb_name")).getAttribute("value").isEmpty());
        assertTrue(driver.findElement(By.id("fb_age")).getAttribute("value").isEmpty());

        assertFalse(driver.findElements(By.cssSelector("#lang_check")).isEmpty());

        assertTrue(driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(4) > input:nth-child(6)")).isSelected());

        Select selectElement = new Select(driver.findElement(By.id("like_us")));
        String selectedOption = selectElement.getFirstSelectedOption().getText();
        assertEquals("Choose your option", selectedOption);

        assertEquals("rgba(33, 150, 243, 1)",driver.findElement(By.className("w3-blue")).getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)",driver.findElement(By.className("w3-blue")).getCssValue("color"));

//         TODO:
//         check that all field are empty and no ticks are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
        driver.findElement(By.className("w3-blue")).click();

        WebElement YourName = driver.findElement(By.id("name"));
        WebElement YourAge = driver.findElement(By.id("age"));
        WebElement YourLanguage = driver.findElement(By.id("language"));
        WebElement YourGenre = driver.findElement(By.id("gender"));
        WebElement YourOptionOfUs = driver.findElement(By.id("option"));
        WebElement YourComment = driver.findElement(By.id("comment"));
        WebElement YesButton = driver.findElement(By.cssSelector("#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-green.w3-xlarge"));
        WebElement NoButton = driver.findElement(By.cssSelector("#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-red.w3-xlarge"));

        assertTrue(YourName.getText().equals("null") || YourName.getText().isEmpty());
        assertTrue(YourAge.getText().equals("null") || YourAge.getText().isEmpty());
        assertTrue(YourLanguage.getText().equals("null") || YourLanguage.getText().isEmpty());
        assertTrue(YourGenre.getText().equals("null") || YourGenre.getText().isEmpty());
        assertTrue(YourOptionOfUs.getText().equals("null") || YourOptionOfUs.getText().isEmpty());
        assertTrue(YourComment.getText().equals("null") || YourComment.getText().isEmpty());

        assertEquals("rgba(76, 175, 80, 1)",YesButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",YesButton.getCssValue("text-decoration-color"));
        assertEquals("rgba(244, 67, 54, 1)",NoButton.getCssValue("background-color"));
        assertEquals("rgb(255, 255, 255)",NoButton.getCssValue("text-decoration-color"));
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
           driver.findElement(By.id("fb_name")).sendKeys("Ben");
           driver.findElement(By.id("fb_age")).sendKeys("25");
           driver.findElement(By.cssSelector("#lang_check > input:nth-child(2)")).click();
           driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(4) > input:nth-child(2)")).click();
           Select selectElement = new Select(driver.findElement(By.id("like_us")));
           selectElement.selectByVisibleText("Good");
           driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea")).sendKeys("hello");
           driver.findElement(By.className("w3-blue")).click();

            WebElement YourName = driver.findElement(By.id("name"));
            WebElement YourAge = driver.findElement(By.id("age"));
            WebElement YourLanguage = driver.findElement(By.id("language"));
            WebElement YourGenre = driver.findElement(By.id("gender"));
            WebElement YourOptionOfUs = driver.findElement(By.id("option"));
            WebElement YourComment = driver.findElement(By.id("comment"));
            WebElement YesButton = driver.findElement(By.cssSelector("#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-green.w3-xlarge"));
            WebElement NoButton = driver.findElement(By.cssSelector("#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-red.w3-xlarge"));

            assertEquals("Ben",YourName.getText());
            assertEquals("25",YourAge.getText());
            assertEquals("English",YourLanguage.getText());
            assertEquals("male",YourGenre.getText());
            assertEquals("Good",YourOptionOfUs.getText());
            assertEquals("hello",YourComment.getText());

           assertEquals("rgba(76, 175, 80, 1)",YesButton.getCssValue("background-color"));
           assertEquals("rgb(255, 255, 255)",YesButton.getCssValue("text-decoration-color"));
           assertEquals("rgba(244, 67, 54, 1)",NoButton.getCssValue("background-color"));
           assertEquals("rgb(255, 255, 255)",NoButton.getCssValue("text-decoration-color"));
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
           driver.findElement(By.id("fb_name")).sendKeys("Ben");
           driver.findElement(By.className("w3-blue")).click();
           driver.findElement(By.cssSelector("#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-green.w3-xlarge")).click();
           assertEquals("Thank you, Ben, for your feedback!",driver.findElement(By.id("message")).getText());
           assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.cssSelector("#fb_thx > div")).getCssValue("background-color"));
           assertEquals("rgba(255, 255, 255, 1)",driver.findElement(By.id("message")).getCssValue("color"));
//
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
           driver.findElement(By.className("w3-blue")).click();
           driver.findElement(By.cssSelector("#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-green.w3-xlarge")).click();
           assertEquals("Thank you for your feedback!",driver.findElement(By.id("message")).getText());
           assertEquals("rgba(76, 175, 80, 1)",driver.findElement(By.cssSelector("#fb_thx > div")).getCssValue("background-color"));  
           assertEquals("rgba(255, 255, 255, 1)",driver.findElement(By.id("message")).getCssValue("color"));                          

//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
          driver.findElement(By.id("fb_name")).sendKeys("Ben");
          driver.findElement(By.id("fb_age")).sendKeys("25");
          driver.findElement(By.cssSelector("#lang_check > input:nth-child(2)")).click();
          driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(4) > input:nth-child(2)")).click();

          Select selectElement = new Select(driver.findElement(By.id("like_us")));
          selectElement.selectByVisibleText("Good");

          driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea")).sendKeys("hello");
          driver.findElement(By.className("w3-blue")).click();
          driver.findElement(By.cssSelector("#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-red.w3-xlarge")).click();

          assertEquals("Ben",driver.findElement(By.id("fb_name")).getAttribute("value"));
          assertEquals("25",driver.findElement(By.id("fb_age")).getAttribute("value"));
          assertTrue(driver.findElement(By.cssSelector("#lang_check > input:nth-child(2)")).isSelected());
          assertTrue(driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(4) > input:nth-child(2)")).isSelected());
          assertEquals("Good", selectElement.getFirstSelectedOption().getText());
          assertEquals("hello", driver.findElement(By.cssSelector("#fb_form > form > div:nth-child(6) > textarea")).getAttribute("value"));
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
    }
}

