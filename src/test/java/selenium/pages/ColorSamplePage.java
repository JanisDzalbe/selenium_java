package selenium.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html

    // Define elements using @FindBy
    @FindBy(id = "start_green")
    private WebElement startGreenButton;

    @FindBy(id = "loading_green")
    private WebElement loadingGreenLabel;

    @FindBy(id = "finish_green")
    private WebElement finishGreenLabel;


    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        startGreenButton.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible


    public void ClickGreenStartButton() {
        startGreenButton.click();
    }

    public boolean isGreenButtonVisible() {
        try {
            return startGreenButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGreenLoadingVisible() {
        try {
            return loadingGreenLabel.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getGreenLoadingText() {
        try {
            return loadingGreenLabel.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isGreenSuccessVisible() {
        try {
            return finishGreenLabel.isDisplayed() && finishGreenLabel.getText().contains("Green Loaded");
        } catch (Exception e) {
            return false;
        }
    }

    public String getGreenSuccessText() {
        try {
            return finishGreenLabel.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
