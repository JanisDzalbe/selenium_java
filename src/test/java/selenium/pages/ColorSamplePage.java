package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.lang.annotation.Retention;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {
    @FindBy(how = How.ID, using = "start_green")
    private WebElement loadGreenButton;

    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadingGreenText;

    @FindBy(how = How.ID, using = "finish_green")
    private WebElement loadedGreenText;

    public void clickStartLoadingGreen() {
        loadGreenButton.click();
    }

    public void loadingButtonIsNotVisible() {
        assertFalse(loadGreenButton.isDisplayed());
    }

    public void loadingGreenTextIsVisible() {
        assertTrue(loadingGreenText.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());
    }

    public void loadingGreenTextIsNotVisible() {
        assertFalse(loadingGreenText.isDisplayed());
    }

    public void loadingGreenTextHasCompleted() {
        assertTrue(loadedGreenText.isDisplayed());
    }
}

