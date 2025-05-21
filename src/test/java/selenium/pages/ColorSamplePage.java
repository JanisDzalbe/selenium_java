package selenium.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {
//        TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(how = How.ID, using = "start_green")
    private WebElement startGreen;
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadingGreen;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement finishGreen;

    public void clickStartLoadingGreen() {
//        TODO:
//         implement clicking on "Start loading green" button
        startGreen.click();
    }

//        TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible
    public void checkStartLoadingGreenInvisible() {
        assertFalse(startGreen.isDisplayed());
    }

    public void checkLoadingGreen() {
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());
    }

    public void checkLoadingGreenInvisible() {
        assertFalse(loadingGreen.isDisplayed());
    }

    public void checkFinishGreen() {
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }

}
