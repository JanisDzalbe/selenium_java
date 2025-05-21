package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckFeedbackPage extends GenericSamplePage {
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "age")
    private WebElement age;
    @FindBy(id = "language")
    private WebElement languages;
    @FindBy(id = "gender")
    private WebElement gender;
    @FindBy(id = "option")
    private WebElement optionOfUs;
    @FindBy(id = "comment")
    private WebElement comment;
    @FindBy(css = "button.w3-green")
    private WebElement yesButton;
    @FindBy(css = "button.w3-red")
    private WebElement noButton;

    public void assertAllFieldsAreEmptyOrNull() {
        assertName("");
        assertAge("");
        assertLanguage(new ArrayList<>());
        assertGender("null");
        assertOptionOfUs("null");
        assertComment("");
    }

    public void assertYesBtnIsGreenWithWhiteText() {
        assertEquals("Yes", yesButton.getText());
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
    }

    public void assertNoBtnIsRedWithWhiteText() {
        assertEquals("No", noButton.getText());
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    public void assertName(String name) {
        assertEquals(name, this.name.getText());
    }

    public void assertAge(String age) {
        assertEquals(age, this.age.getText());
    }

    public void assertLanguage(List<String> languages) {
        if (languages.isEmpty()) {
            assertEquals("", this.languages.getText());
            return;
        }
        for (String language : languages) {
            assertTrue(this.languages.getText().contains(language));
        }
    }

    public void assertGender(String gender) {
        assertEquals(gender.toLowerCase(), this.gender.getText().toLowerCase());
    }

    public void assertOptionOfUs(String optionOfUs) {
        assertEquals(optionOfUs, this.optionOfUs.getText());
    }

    public void assertComment(String comment) {
        assertEquals(comment, this.comment.getText());
    }

    public void clickYes() {
        yesButton.click();
    }

    public void clickNo() {
        noButton.click();
    }
}
