package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.FeedbackPage;
import selenium.pages.FeedbackSubmitPage;
import selenium.pages.FeedbackThanksPage;

import java.io.File;
import java.time.Duration;

public class Task2 {
    WebDriver driver;
    static FeedbackPage feedbackPage;
    static FeedbackSubmitPage feedbackSubmitPage;
    static FeedbackThanksPage feedbackThanksPage;
    static String name = "name";
    static String age = "20";
    static String language1 = "English";
    static String gender = "Female";
    static String option = "Good";
    static String comment = "comment";

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        feedbackSubmitPage = PageFactory.initElements(driver, FeedbackSubmitPage.class);
        feedbackThanksPage = PageFactory.initElements(driver, FeedbackThanksPage.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//        TODO:
//         check that all field are empty and no ticks are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
        feedbackPage.checkEmptyFeedback();
        feedbackPage.checkButtonStyle();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//        TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.clickSubmitButton();
        feedbackSubmitPage.checkEmptyFeedback();
        feedbackSubmitPage.checkYesButtonColor();
        feedbackSubmitPage.checkNoButtonColor();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//        TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.enterName(name);
        feedbackPage.enterAge(age);
        feedbackPage.selectLanguage(language1);
        feedbackPage.selectGender(gender);
        feedbackPage.selectOption(option);
        feedbackPage.enterComment(comment);
        feedbackPage.clickSubmitButton();
        feedbackSubmitPage.checkName(name);
        feedbackSubmitPage.checkAge(age);
        feedbackSubmitPage.checkLanguage(language1);
        feedbackSubmitPage.checkGender(gender);
        feedbackSubmitPage.checkOption(option);
        feedbackSubmitPage.checkComment(comment);
        feedbackSubmitPage.checkYesButtonColor();
        feedbackSubmitPage.checkNoButtonColor();

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//        TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        feedbackPage.enterName(name);
        feedbackPage.clickSubmitButton();
        feedbackSubmitPage.clickYesButton();
        feedbackThanksPage.checkMessage(name);
        feedbackThanksPage.checkMessageColor();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//        TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        feedbackPage.clickSubmitButton();
        feedbackSubmitPage.clickYesButton();
        feedbackThanksPage.checkMessageNoName();
        feedbackThanksPage.checkMessageColor();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//        TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        feedbackPage.enterName(name);
        feedbackPage.enterAge(age);
        feedbackPage.selectLanguage(language1);
        feedbackPage.selectGender(gender);
        feedbackPage.selectOption(option);
        feedbackPage.enterComment(comment);
        feedbackPage.clickSubmitButton();
        feedbackSubmitPage.clickNoButton();
        feedbackPage.checkName(name);
        feedbackPage.checkAge(age);
        feedbackPage.checkLanguage(language1);
        feedbackPage.checkGender(gender);
        feedbackPage.checkOption(option);
        feedbackPage.checkComment(comment);
    }
}
