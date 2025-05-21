package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v134.page.Page;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.ColorSamplePage;
import selenium.utility.BootcampUtils;

import selenium.pages.FeedbackPage;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2 {
    WebDriver driver;
    static FeedbackPage.InputPage feedbackInputPage;
    static FeedbackPage.ConfirmPage feedbackConfirmPage;
    static FeedbackPage.ThankYouPage feedbackThankYouPage;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        feedbackInputPage = PageFactory.initElements(driver, FeedbackPage.InputPage.class);
        feedbackConfirmPage = PageFactory.initElements(driver, FeedbackPage.ConfirmPage.class);
        feedbackThankYouPage = PageFactory.initElements(driver, FeedbackPage.ThankYouPage.class);
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
        feedbackInputPage.assertAllEmpty();
        feedbackInputPage.assertButtonColor();

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or "null"
//         check button colors
//         (green with white letter and red with white letters)
        feedbackInputPage.assertAllEmpty();
        feedbackInputPage.clickSubmit();

        feedbackConfirmPage.assertAllEmpty();
        feedbackConfirmPage.assertYesButtonColor();
        feedbackConfirmPage.assertNoButtonColor();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        String testName = "TestName";
        String testAge = "80";
        int testLanguageIndex = 2;
        String testLanguage = feedbackInputPage.getLanguagesList().get(testLanguageIndex).getText();
        int testGenderIndex = 0;
        String testGender = feedbackInputPage.getGenderList().get(testGenderIndex).getText().toLowerCase(); //output afterwards is lowercase
        int testOpinionIndex = 2;
        // such weird code for this
        String testOpinion = feedbackInputPage.getOpinionSelect().getOptions().get(testOpinionIndex).getText();
        String testComment = "TestComment";
        feedbackInputPage.enterAll(testName, testAge, testLanguageIndex, testGenderIndex, testOpinionIndex, testComment);

        feedbackInputPage.assertAll(testName, testAge, testLanguageIndex, testGenderIndex, testOpinionIndex, testComment);

        feedbackInputPage.clickSubmit();

        feedbackConfirmPage.assertAll(testName, testAge, testLanguage, testGender, testOpinion, testComment);

        feedbackConfirmPage.assertYesButtonColor();
        feedbackConfirmPage.assertNoButtonColor();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        feedbackInputPage.assertAllEmpty();

        String testName = "TestName";
        feedbackInputPage.enterName(testName);
        feedbackInputPage.assertNameInput(testName);

        feedbackInputPage.clickSubmit();

        feedbackConfirmPage.assertNameText(testName);
        feedbackConfirmPage.clickYesButton();

        feedbackThankYouPage.assertName(testName);
        feedbackThankYouPage.assertColor();
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        feedbackInputPage.assertAllEmpty();
        feedbackInputPage.clickSubmit();

        feedbackConfirmPage.assertNameText("");
        feedbackConfirmPage.clickYesButton();

        feedbackThankYouPage.assertName("");
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        String testName = "TestName";
        String testAge = "80";
        int testLanguageIndex = 2;
        String testLanguage = feedbackInputPage.getLanguagesList().get(testLanguageIndex).getText();
        int testGenderIndex = 0;
        String testGender = feedbackInputPage.getGenderList().get(testGenderIndex).getText().toLowerCase(); //output afterwards is lowercase
        int testOpinionIndex = 2;
        // such weird code for this
        String testOpinion = feedbackInputPage.getOpinionSelect().getOptions().get(testOpinionIndex).getText();
        String testComment = "TestComment";
        feedbackInputPage.enterAll(testName, testAge, testLanguageIndex, testGenderIndex, testOpinionIndex, testComment);

        feedbackInputPage.assertAll(testName, testAge, testLanguageIndex, testGenderIndex, testOpinionIndex, testComment);

        feedbackInputPage.clickSubmit();

        feedbackConfirmPage.assertAll(testName, testAge, testLanguage, testGender, testOpinion, testComment);

        feedbackConfirmPage.clickNoButton();

        feedbackInputPage.assertAll(testName, testAge, testLanguageIndex, testGenderIndex, testOpinionIndex, testComment);
    }

}
