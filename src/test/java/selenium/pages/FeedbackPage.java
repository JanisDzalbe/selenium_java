package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackPage extends GenericSamplePage {
    @FindBy(id = "fb_name")
    private WebElement nameInput;
    @FindBy(id = "fb_age")
    private WebElement ageInput;
    @FindBy(className = "w3-check")
    private List<WebElement> languageOptions;
    @FindBy(className = "w3-radio")
    private List<WebElement> genderOptions;
    @FindBy(id = "like_us")
    private WebElement likeUsOptions;
    @FindBy(css = "textarea[name='comment']")
    private WebElement commentInput;
    @FindBy(css = "button[type = 'submit']")
    private WebElement sendButton;

    public void assertAllFieldsAreEmptyOrAtInitialState() {
        Select likeUsSelect = new Select(likeUsOptions);
        assertEquals("", nameInput.getAttribute("value"));
        assertEquals("", ageInput.getAttribute("value"));
        assertEquals("", commentInput.getAttribute("value"));
        assertEquals("Choose your option", likeUsSelect.getFirstSelectedOption().getText());
        for (WebElement option : languageOptions) {
            assertFalse(option.isSelected());
        }
        assertFalse(genderOptions.get(0).isSelected());
        assertFalse(genderOptions.get(1).isSelected());
        assertFalse(genderOptions.get(2).isEnabled());
        assertTrue(genderOptions.get(2).isSelected());
    }

    public void assertSendBtnIsBlueWithWhiteText() {
        assertEquals("Send", sendButton.getText());
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    public void clickSend() {
        sendButton.click();
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void selectLanguage(List<String> languages) {
        for (WebElement option : languageOptions) {
            if (languages.contains(option.getAttribute("value"))) {
                option.click();
            }
        }
    }

    public void selectGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                genderOptions.get(0).click();
                break;
            case "female":
                genderOptions.get(1).click();
                break;
        }
    }

    public void selectOption(String opinion) {
        Select likeUsSelect = new Select(likeUsOptions);
        likeUsSelect.selectByVisibleText(opinion);
    }

    public void enterComment(String comment) {
        commentInput.clear();
        commentInput.sendKeys(comment);
    }

    public void assertName(String name) {
        assertEquals(name, nameInput.getAttribute("value"));
    }

    public void assertAge(String age) {
        assertEquals(age, ageInput.getAttribute("value"));
    }

    public void assertLanguage(List<String> languages) {
        for (WebElement option : languageOptions) {
            if (languages.contains(option.getAttribute("value"))) {
                assertTrue(option.isSelected());
            } else {
                assertFalse(option.isSelected());
            }
        }
    }

    public void assertGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                assertTrue(genderOptions.get(0).isSelected());
                assertFalse(genderOptions.get(1).isSelected());
                assertFalse(genderOptions.get(2).isEnabled());
                break;
            case "female":
                assertTrue(genderOptions.get(1).isSelected());
                assertFalse(genderOptions.get(0).isSelected());
                assertFalse(genderOptions.get(2).isEnabled());
                break;
        }
    }

    public void assertOptionOfUs(String option) {
        Select likeUsSelect = new Select(likeUsOptions);
        assertEquals(option, likeUsSelect.getFirstSelectedOption().getText());
    }

    public void assertComment(String comment) {
        assertEquals(comment, commentInput.getAttribute("value"));
    }
}
