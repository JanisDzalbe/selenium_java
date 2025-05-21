package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.Task2Page;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    static WebDriver driver;
    static Task2Page taskPage;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");

        taskPage = PageFactory.initElements(driver, Task2Page.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all fields are empty and no ticks are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
        assertEquals("", taskPage.getNameInput().getAttribute("value"));
        assertEquals("", taskPage.getAgeInput().getAttribute("value"));

        for (WebElement checkbox : taskPage.getLanguageCheckboxes()) {
            assertFalse(checkbox.isSelected());
        }

        WebElement defaultRadio = driver.findElement(By.xpath("//input[@type='radio' and @name='gender' and @checked]"));
        assertTrue(defaultRadio.isDisplayed());
        assertFalse(defaultRadio.isEnabled());

        WebElement dropdown = driver.findElement(By.id("like_us"));
        String selectedOption = new Select(dropdown).getFirstSelectedOption().getText();
        assertEquals("Choose your option", selectedOption);

        WebElement sendButton = driver.findElement(By.xpath("//button[text()='Send']"));
        String bgColor = sendButton.getCssValue("background-color");
        String textColor = sendButton.getCssValue("color");
        // Colour assertion
        assertTrue(bgColor.contains("33, 150, 243"));
        assertTrue(textColor.contains("255, 255, 255"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
        taskPage.clickSendButton();

        WebElement nameSpan = driver.findElement(By.id("name"));
        WebElement ageSpan = driver.findElement(By.id("age"));
        WebElement langSpan = driver.findElement(By.id("language"));
        WebElement genderSpan = driver.findElement(By.id("gender"));
        WebElement optionSpan = driver.findElement(By.id("option"));
        WebElement commentSpan = driver.findElement(By.id("comment"));

        assertEquals("", nameSpan.getText());
        assertEquals("", ageSpan.getText());
        assertEquals("", langSpan.getText());
        assertEquals("null", genderSpan.getText());
        assertEquals("null", optionSpan.getText());
        assertEquals("", commentSpan.getText());

        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noButton = driver.findElement(By.xpath("//button[text()='No']"));

        taskPage.assertGreenButton(yesButton);
        taskPage.assertRedButton(noButton);
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        taskPage.enterName("Kirils");
        taskPage.enterAge("21");
        taskPage.selectLanguageByValue("English");

        WebElement genderRadio = driver.findElement(By.xpath("//input[@type='radio' and @value='male']"));
        genderRadio.click();

        WebElement dropdown = driver.findElement(By.id("like_us"));
        new Select(dropdown).selectByVisibleText("Good");

        WebElement commentArea = driver.findElement(By.name("comment"));
        commentArea.clear();
        commentArea.sendKeys("I love testing!");

        taskPage.clickSendButton();

        WebElement nameSpan = driver.findElement(By.id("name"));
        WebElement ageSpan = driver.findElement(By.id("age"));
        WebElement langSpan = driver.findElement(By.id("language"));
        WebElement genderSpan = driver.findElement(By.id("gender"));
        WebElement optionSpan = driver.findElement(By.id("option"));
        WebElement commentSpan = driver.findElement(By.id("comment"));

        assertEquals("Kirils", nameSpan.getText());
        assertEquals("21", ageSpan.getText());
        assertEquals("English", langSpan.getText());
        assertEquals("male", genderSpan.getText());
        assertEquals("Good", optionSpan.getText());
        assertEquals("I love testing!", commentSpan.getText());

        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        WebElement noButton = driver.findElement(By.xpath("//button[text()='No']"));

        taskPage.assertGreenButton(yesButton);
        taskPage.assertRedButton(noButton);
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        taskPage.enterName("Kirils");
        taskPage.clickSendButton();

        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        yesButton.click();

        WebElement message = driver.findElement(By.xpath("//div[@class='w3-panel w3-green']"));
        assertEquals("Thank you, Kirils, for your feedback!", message.getText());
        taskPage.assertGreenMessage(message);
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything)
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        taskPage.clickSendButton();

        WebElement yesButton = driver.findElement(By.xpath("//button[text()='Yes']"));
        yesButton.click();

        WebElement message = driver.findElement(By.id("message"));
        assertEquals("Thank you for your feedback!", message.getText());

        taskPage.assertGreenMessage(message);
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        taskPage.enterName("Kirils");
        taskPage.enterAge("21");
        taskPage.selectLanguageByValue("English");

        WebElement genderRadio = driver.findElement(By.xpath("//input[@type='radio' and @value='male']"));
        genderRadio.click();

        WebElement dropdown = driver.findElement(By.id("like_us"));
        new Select(dropdown).selectByVisibleText("Good");

        WebElement commentArea = driver.findElement(By.name("comment"));
        commentArea.clear();
        commentArea.sendKeys("I love testing!");

        taskPage.clickSendButton();

        WebElement noButton = driver.findElement(By.xpath("//button[text()='No']"));
        noButton.click();

        assertEquals("Kirils", taskPage.getNameInput().getAttribute("value"));
        assertEquals("21", taskPage.getAgeInput().getAttribute("value"));

        List<String> selectedLanguages = new ArrayList<>();
        for (WebElement checkbox : taskPage.getLanguageCheckboxes()) {
            if (checkbox.isSelected()) {
                selectedLanguages.add(checkbox.getAttribute("value"));
            }
        }
        assertTrue(selectedLanguages.contains("English"));
        assertEquals(1, selectedLanguages.size());

        WebElement selectedRadio = driver.findElement(By.xpath("//input[@type='radio' and @name='gender' and @value='male']"));
        assertTrue(selectedRadio.isSelected());

        String selectedDropdownText = new Select(driver.findElement(By.id("like_us"))).getFirstSelectedOption().getText();
        assertEquals("Good", selectedDropdownText);

        WebElement commentAfterBack = driver.findElement(By.name("comment"));
        assertEquals("I love testing!", commentAfterBack.getAttribute("value"));
    }
}
