package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(id = "start_green")
    private WebElement startLoadingGreen;
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadingGreen;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement greenLoaded;

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        startLoadingGreen.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible

    public void checkStartLoadingGreenButtonIsNotVisible() {
        assertFalse(startLoadingGreen.isDisplayed());
    }

    public void checkLoadingGreenTextIsVisible() {
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());
    }

    public void checkLoadingGreenTextIsNotVisible() {
        assertFalse(loadingGreen.isDisplayed());
    }

    public void checkGreenLoadedTextIsVisible() {
        assertTrue(greenLoaded.isDisplayed());
        assertEquals("Green Loaded", greenLoaded.getText());
    }
}
