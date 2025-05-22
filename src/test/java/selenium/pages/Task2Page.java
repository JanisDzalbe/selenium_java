package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Task2Page extends GenericSamplePage {
    // Definition
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;

    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;

    @FindBy(how = How.NAME, using = "language")
    List<WebElement> languageCheckboxes;

    @FindBy(how = How.XPATH, using = "//button[text()='Send']")
    private WebElement sendButton;

    @FindBy(xpath = "//input[@type='radio']")
    private List<WebElement> genderRadios;

    @FindBy(name = "comment")
    private WebElement commentArea;

    @FindBy(xpath = "//input[@type='radio' and @name='gender' and @checked]")
    private WebElement defaultGenderRadio;

    @FindBy(id = "like_us")
    private WebElement likeUsDropdown;

    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement yesButton;

    @FindBy(xpath = "//button[text()='No']")
    private WebElement noButton;

    // Getters
    public WebElement getNameInput() {
        return nameInput;
    }

    public WebElement getAgeInput() {
        return ageInput;
    }

    public List<WebElement> getLanguageCheckboxes() {
        return languageCheckboxes;
    }

    public String getSelectedLikeUsOption() {
        return new Select(likeUsDropdown).getFirstSelectedOption().getText();
    }

    // Methods
    public void assertInputsEmpty() {
        assertEquals("", nameInput.getAttribute("value"));
        assertEquals("", ageInput.getAttribute("value"));
    }

    public void assertNoLanguagesSelected() {
        for (WebElement cb : languageCheckboxes) {
            assertFalse(cb.isSelected());
        }
    }

    public void assertDefaultGenderDisabled() {
        assertTrue(defaultGenderRadio.isDisplayed());
        assertFalse(defaultGenderRadio.isEnabled());
    }

    public void assertDefaultLikeUs() {
        assertEquals("Choose your option", getSelectedLikeUsOption());
    }

    public void assertSendButtonDefaultColor() {
        assertButtonColor(sendButton, "33, 150, 243", "255, 255, 255");
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void selectLanguageByValue(String value) {
        for (WebElement checkbox : languageCheckboxes) {
            if (checkbox.getAttribute("value").equalsIgnoreCase(value)) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                break;
            }
        }
    }
    public void selectGender(String value) {
        for (WebElement radio : genderRadios) {
            if (radio.getAttribute("value").equalsIgnoreCase(value)) {
                radio.click();
                break;
            }
        }
    }

    public void selectLikeUsOption(String visibleText) {
        new Select(likeUsDropdown).selectByVisibleText(visibleText);
    }

    public void enterComment(String comment) {
        commentArea.clear();
        commentArea.sendKeys(comment);
    }

    public void clickSendButton() {
        sendButton.click();
    }
    public void clickYesButton() {
        yesButton.click();
    }

    public void clickNoButton() {
        noButton.click();
    }

    private void assertButtonColor(WebElement button, String expectedBgRgb) {
        String bgColor   = button.getCssValue("background-color");
        String textColor = button.getCssValue("color");

        assertTrue(bgColor.contains(expectedBgRgb));
        assertTrue(textColor.contains("255, 255, 255"));
    }

    public void assertYesButtonIsGreen() {
        assertButtonColor(yesButton, "76, 175, 80");
    }

    public void assertNoButtonIsRed() {
        assertButtonColor(noButton, "244, 67, 54");
    }

    public void assertGreenMessage(WebElement messageElement) {
        String textColor = messageElement.getCssValue("color");
        String bgColor;

        if (messageElement.getTagName().equals("h2")) {
            WebElement parent = messageElement.findElement(By.xpath("./ancestor::div[contains(@class, 'w3-green')]"));
            bgColor = parent.getCssValue("background-color");
        } else {
            bgColor = messageElement.getCssValue("background-color");
        }

        assertTrue(textColor.contains("255, 255, 255"));
        assertTrue(bgColor.contains("76, 175, 80"));
    }
    private void assertButtonColor(WebElement button, String expectedBgRgb, String expectedTextRgb) {
        String bg = button.getCssValue("background-color");
        String text = button.getCssValue("color");
        assertTrue(bg.contains(expectedBgRgb));
        assertTrue(text.contains(expectedTextRgb));
    }
}
