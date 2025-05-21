package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackThanksPage {
    @FindBy(id = "message")
    private WebElement message;
    @FindBy(className = "w3-green")
    private WebElement messageDiv;

    public void checkMessage(String name) {
        assertEquals("Thank you, " + name + ", for your feedback!", message.getText());
    }

    public void checkMessageNoName() {
        assertEquals("Thank you for your feedback!", message.getText());
    }

    public void checkMessageColor() {
        assertEquals("rgba(76, 175, 80, 1)", messageDiv.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", messageDiv.getCssValue("color"));
    }
}
