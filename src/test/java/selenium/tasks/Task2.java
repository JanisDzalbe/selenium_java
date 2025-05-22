package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
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
        taskPage.assertInputsEmpty();
        taskPage.assertNoLanguagesSelected();
        taskPage.assertDefaultGenderDisabled();
        taskPage.assertDefaultLikeUs();
        taskPage.assertSendButtonDefaultColor();
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

        taskPage.assertYesButtonIsGreen();
        taskPage.assertNoButtonIsRed();

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

        taskPage.selectGender("male");
        taskPage.selectLikeUsOption("Good");
        taskPage.enterComment("I love testing!");

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

        taskPage.assertYesButtonIsGreen();
        taskPage.assertNoButtonIsRed();
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
        taskPage.clickYesButton();

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
        taskPage.clickYesButton();

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
        taskPage.selectGender("male");
        taskPage.selectLikeUsOption("Good");
        taskPage.enterComment("I love testing!");

        taskPage.clickSendButton();
        taskPage.clickNoButton();

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

        assertEquals("Good", taskPage.getSelectedLikeUsOption());

        WebElement commentAfterBack = driver.findElement(By.name("comment"));
        assertEquals("I love testing!", commentAfterBack.getAttribute("value"));
    }
}
