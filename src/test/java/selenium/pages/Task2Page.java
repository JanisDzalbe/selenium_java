package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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
    // Methods
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

    public void clickSendButton() {
        sendButton.click();
    }

    public void assertGreenButton(WebElement button) {
        String bgColor = button.getCssValue("background-color");
        String textColor = button.getCssValue("color");

        assertTrue(bgColor.contains("76, 175, 80"));
        assertTrue(textColor.contains("255, 255, 255"));
    }

    public void assertRedButton(WebElement button) {
        String bgColor = button.getCssValue("background-color");
        String textColor = button.getCssValue("color");

        assertTrue(bgColor.contains("244, 67, 54"));
        assertTrue(textColor.contains("255, 255, 255"));
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

}
