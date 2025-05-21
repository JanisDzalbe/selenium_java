package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.FeedbackPage;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2 {
    WebDriver driver;
    static FeedbackPage feedbackPage;

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
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);


        assertEquals("", feedbackPage.getNameInput());
        assertEquals("", feedbackPage.getAge());
        assertEquals("", feedbackPage.getCommentInput());
        assertEquals(0, feedbackPage.getLanguages().size());
        assertEquals("", feedbackPage.getGenders());
        feedbackPage.setHowDoYouLikeUs("Ok, i guess");
        assertEquals( "rgba(255, 255, 255, 1)", feedbackPage.getButtonColor());
        assertEquals("rgba(33, 150, 243, 1)", feedbackPage.getButtonBGColor());
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);

        feedbackPage.button.click();
        assertEquals("", feedbackPage.getNameResult());
        assertEquals("", feedbackPage.getAgeResult());
        assertEquals("", feedbackPage.getLanguageResult());
        assertEquals("null", feedbackPage.getGenreResult());
        assertEquals("null", feedbackPage.getOptionResult());
        assertEquals("", feedbackPage.getCommentResult());
        assertEquals( "rgba(255, 255, 255, 1)", feedbackPage.getYesButtonColor());
        assertEquals("rgba(76, 175, 80, 1)", feedbackPage.getYesButtonBGColor());
        assertEquals( "rgba(255, 255, 255, 1)", feedbackPage.getNoButtonColor());
        assertEquals("rgba(244, 67, 54, 1)", feedbackPage.getNoButtonBGColor());
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);

        feedbackPage.setNameInput("Tamara");
        feedbackPage.enterAge("38");
        feedbackPage.setLanguages("English");
        feedbackPage.setGenders("female");
        feedbackPage.setHowDoYouLikeUs("Ok, i guess");
        feedbackPage.setCommentInput("thanks");

        feedbackPage.button.click();

        assertEquals("Tamara", feedbackPage.getNameResult());
        assertEquals("38", feedbackPage.getAgeResult());
        assertEquals("English", feedbackPage.getLanguageResult());
        assertEquals("female", feedbackPage.getGenreResult());
        assertEquals("Ok, i guess", feedbackPage.getOptionResult());
        assertEquals("thanks", feedbackPage.getCommentResult());
        assertEquals( "rgba(255, 255, 255, 1)", feedbackPage.getYesButtonColor());
        assertEquals("rgba(76, 175, 80, 1)", feedbackPage.getYesButtonBGColor());
        assertEquals( "rgba(255, 255, 255, 1)", feedbackPage.getNoButtonColor());
        assertEquals("rgba(244, 67, 54, 1)", feedbackPage.getNoButtonBGColor());
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);

        feedbackPage.setNameInput("Tamara");
        feedbackPage.button.click();
        feedbackPage.yesButton.click();
        assertEquals( "Thank you, Tamara, for your feedback!", feedbackPage.getMessageResult());
        assertEquals( "rgba(255, 255, 255, 1)", feedbackPage.getMessageResultColor());
        assertEquals("rgba(76, 175, 80, 1)", feedbackPage.getMessageResultBGColor());
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);

        feedbackPage.button.click();
        feedbackPage.yesButton.click();

        assertEquals( "Thank you for your feedback!", feedbackPage.getMessageResult());
        assertEquals( "rgba(255, 255, 255, 1)", feedbackPage.getMessageResultColor());
        assertEquals("rgba(76, 175, 80, 1)", feedbackPage.getMessageResultBGColor());

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);

        feedbackPage.setNameInput("Tamara");
        feedbackPage.enterAge("38");
        feedbackPage.setLanguages("English");
        feedbackPage.setGenders("female");
        feedbackPage.setHowDoYouLikeUs("Ok, i guess");
        feedbackPage.setCommentInput("thanks");
        feedbackPage.button.click();
        feedbackPage.noButton.click();
        List<String> selectedLanguages = feedbackPage.getLanguages();
        assertTrue(selectedLanguages.contains("English"));
        assertEquals("Tamara", feedbackPage.getNameInput());
        assertEquals("38", feedbackPage.getAge());
        assertEquals("female", feedbackPage.getGenders());
        assertEquals("Ok, i guess", feedbackPage.getHowDoYouLikeUs());
        assertEquals("thanks", feedbackPage.getCommentInput());
    }
}
