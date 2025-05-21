package selenium.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html

    @FindBy(how = How.ID, using = "start_green") // By.id("name")
    private WebElement startGreenButton;
    @FindBy(how = How.ID, using = "loading_green") // By.id("name")
    private WebElement loadingGreenText;
    @FindBy(how = How.ID, using = "finish_green") // By.id("name")
    private WebElement finishGreenText;

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button

    startGreenButton.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is visible
//         * 4) check that text "Green Loaded" is visible
    public void assertLoadingGreenButton(){
        assertFalse(startGreenButton.isDisplayed());
        assertTrue(loadingGreenText.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());
    }
    public void assertFinishGreenComplited(){
        assertTrue(finishGreenText.isDisplayed());
        assertFalse(startGreenButton.isDisplayed());
        assertFalse(loadingGreenText.isDisplayed());
        assertEquals("Green Loaded", finishGreenText.getText());
    }
}
