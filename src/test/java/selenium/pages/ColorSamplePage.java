package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
        @FindBy(id = "start_green")
        private WebElement startBtn;
        @FindBy(id = "loading_green")
        private WebElement loadingTxt;
        @FindBy(id = "finish_green")
        private WebElement finishTxt;

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        startBtn.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible
    public void assertLoadingText() {
        assertFalse(startBtn.isDisplayed());
        assertTrue(loadingTxt.isDisplayed());
        assertEquals("Loading green...", loadingTxt.getText());
    }

    public void assertFinishText() {
        assertTrue(finishTxt.isDisplayed());
        assertFalse(startBtn.isDisplayed());
        assertFalse(loadingTxt.isDisplayed());
        assertEquals("Green Loaded", finishTxt.getText());
    }
}
