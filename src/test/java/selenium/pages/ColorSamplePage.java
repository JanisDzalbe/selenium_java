package selenium.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {

    @FindBy(how = How.ID, using = "start_green")
    private WebElement greenButton;
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadingGreenText;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement finishGreen;
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html

    public void clickStartLoadingGreen() {
        greenButton.click();
//         TODO:
//         implement clicking on "Start loading green" button
    }

    public void startButtonNotVisible() {
        assertFalse(greenButton.isDisplayed());
        assertTrue(loadingGreenText.isDisplayed());
        assertEquals("Loading green..." ,loadingGreenText.getText());
    }

    public void loadingGreenVisible () {
        assertTrue(loadingGreenText.isDisplayed());
        assertFalse(greenButton.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());
    }

    public void loadingGreenNotVisible() {
        assertTrue(finishGreen.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
    }
    public void greenLoadedDisplayed() {
        assertTrue(finishGreen.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible
}
