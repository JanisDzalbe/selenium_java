package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.CheckFeedbackPage;
import selenium.pages.FeedbackPage;
import selenium.pages.FeedbackThankYouPage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    WebDriver driver;
    static FeedbackPage feedbackPage;
    static CheckFeedbackPage checkFeedbackPage;
    static FeedbackThankYouPage thankYouPage;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/tasks/provide_feedback");
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
        checkFeedbackPage = PageFactory.initElements(driver, CheckFeedbackPage.class);
        thankYouPage = PageFactory.initElements(driver, FeedbackThankYouPage.class);
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
        feedbackPage.assertAllFieldsAreEmptyOrAtInitialState();
        feedbackPage.assertSendBtnIsBlueWithWhiteText();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.clickSend();
        checkFeedbackPage.assertAllFieldsAreEmptyOrNull();
        checkFeedbackPage.assertYesBtnIsGreenWithWhiteText();
        checkFeedbackPage.assertNoBtnIsRedWithWhiteText();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        List<String> languages = new ArrayList<>();

        String name = "Kale";
        String age = "28";
        String gender = "Male";
        String option = "Good";
        String comment = "All is good.";

        languages.add("English");
        languages.add("Chinese");

        feedbackPage.enterName(name);
        feedbackPage.enterAge(age);
        feedbackPage.selectLanguage(languages);
        feedbackPage.selectGender(gender);
        feedbackPage.selectOption(option);
        feedbackPage.enterComment(comment);
        feedbackPage.clickSend();

        checkFeedbackPage.assertName(name);
        checkFeedbackPage.assertAge(age);
        checkFeedbackPage.assertLanguage(languages);
        checkFeedbackPage.assertGender(gender);
        checkFeedbackPage.assertOptionOfUs(option);
        checkFeedbackPage.assertComment(comment);

        checkFeedbackPage.assertYesBtnIsGreenWithWhiteText();
        checkFeedbackPage.assertNoBtnIsRedWithWhiteText();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        String name = "Kale";
        feedbackPage.enterName(name);
        feedbackPage.clickSend();
        checkFeedbackPage.clickYes();
        thankYouPage.assertMessageTextWithName(name);
        thankYouPage.assertMessageIsWhiteTextOnGreenBackground();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        feedbackPage.clickSend();
        checkFeedbackPage.clickYes();
        thankYouPage.assertMessageTextWithoutName();
        thankYouPage.assertMessageIsWhiteTextOnGreenBackground();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        List<String> languages = new ArrayList<>();

        String name = "Kale";
        String age = "28";
        String gender = "Male";
        String option = "Good";
        String comment = "All is good.";

        languages.add("English");
        languages.add("Chinese");

        feedbackPage.enterName(name);
        feedbackPage.enterAge(age);
        feedbackPage.selectLanguage(languages);
        feedbackPage.selectGender(gender);
        feedbackPage.selectOption(option);
        feedbackPage.enterComment(comment);
        feedbackPage.clickSend();

        checkFeedbackPage.clickNo();

        feedbackPage.assertName(name);
        feedbackPage.assertAge(age);
        feedbackPage.assertLanguage(languages);
        feedbackPage.assertGender(gender);
        feedbackPage.assertOptionOfUs(option);
        feedbackPage.assertComment(comment);
    }
}
