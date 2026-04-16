package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ColorSamplePage extends GenericSamplePage {

    // Define elements using @FindBy
    @FindBy(id = "start_green")
    private WebElement startGreenButton;

    @FindBy(id = "loading_green")
    private WebElement loadingGreenText;

    @FindBy(id = "finish_green")
    private WebElement successGreenMessage;

    public void clickStartLoadingGreen() {
        startGreenButton.click();
    }

    public boolean isStartButtonVisible() {
        try {
            return startGreenButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoadingTextVisible() {
        try {
            return loadingGreenText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoadingText() {
        return loadingGreenText.getText();
    }

    public boolean isSuccessMessageVisible() {
        try {
            return successGreenMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessageText() {
        return successGreenMessage.getText();
    }
}