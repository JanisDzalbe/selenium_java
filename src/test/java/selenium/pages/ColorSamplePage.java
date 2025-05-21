package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(how = How.ID, using = "start_green")
    private WebElement startGreenButton; // WebElement nameInput = driver.findElement(By.id("name"));
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadingGreenText;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement finishGreenText;

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        startGreenButton.click();
    }

    public void assertLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        assertFalse(startGreenButton.isDisplayed());
        assertTrue(loadingGreenText.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());
    }

    public void finishLoadingGreen() {
        assertTrue(finishGreenText.isDisplayed());
        assertFalse(startGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertEquals("Green Loaded", finishGreenText.getText());

    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible
    public void assertStartButtonNotVisible() {
        assertFalse(startGreenButton.isDisplayed());
    }

    public void assertLoadingGreenVisible() {
        assertTrue(loadingGreenText.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());
    }

    public void assertLoadingGreenNotVisible() {
        assertFalse(loadingGreenText.isDisplayed());
    }

    public void assertGreenLoadedVisible() {
        assertTrue(finishGreenText.isDisplayed());
        assertEquals("Green Loaded", finishGreenText.getText());
    }

}
