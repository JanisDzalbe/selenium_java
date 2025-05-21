package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackThankYouPage extends GenericSamplePage {
    @FindBy(css = ".w3-panel.w3-green")
    WebElement messagePanel;

    public void assertMessageTextWithName(String name) {
        assertEquals("Thank you, " + name + ", for your feedback!", messagePanel.getText());
    }

    public void assertMessageTextWithoutName() {
        assertEquals("Thank you for your feedback!", messagePanel.getText());
    }

    public void assertMessageIsWhiteTextOnGreenBackground() {
        assertEquals("rgba(76, 175, 80, 1)", messagePanel.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", messagePanel.getCssValue("color"));
    }
}
