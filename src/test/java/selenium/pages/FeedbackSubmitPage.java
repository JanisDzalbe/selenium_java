package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedbackSubmitPage {
    @FindBy(id = "name")
    WebElement nameInput;
    @FindBy(id = "age")
    WebElement age;
    @FindBy(id = "language")
    WebElement language;
    @FindBy(id = "gender")
    WebElement gender;
    @FindBy(id = "option")
    WebElement option;
    @FindBy(id = "comment")
    WebElement comment;
    @FindBy(className = "w3-green")
    WebElement yesButton;
    @FindBy(className = "w3-red")
    WebElement noButton;

    public void checkName(String name) {
        assertEquals(name, nameInput.getText());
    }

    public void checkAge(String age) {
        assertEquals(age, this.age.getText());
    }

    public void checkLanguageEmpty()
    {
        assertEquals("", this.language.getText());
    }

    public void checkLanguage(String language) {
        assertTrue((this.language.getText().toLowerCase()).contains(language.toLowerCase()));
    }

    public void checkGender(String gender) {
        assertEquals(gender.toLowerCase(), this.gender.getText().toLowerCase());
    }

    public void checkOption(String option) {
        assertEquals(option, this.option.getText());
    }

    public void checkComment(String comment) {
        assertEquals(comment, this.comment.getText());
    }

    public void checkEmptyFeedback(){
        checkName("");
        checkAge("");
        checkLanguageEmpty();
        checkGender("null");
        checkOption("null");
        checkComment("");
    }

    public void checkYesButtonColor(){
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
    }

    public void checkNoButtonColor(){
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    public void clickYesButton() {
        yesButton.click();
    }

    public void clickNoButton() {
        noButton.click();
    }

}
