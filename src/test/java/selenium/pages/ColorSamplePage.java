package selenium.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {
    //         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html

    //not sure if I should "combine" some of the methods, i.e. do some checks in one of the methods
    @FindBy(how = How.ID, using = "start_green")
    private WebElement greenButton;
    @FindBy (how = How.ID , using = "loading_green")
    private WebElement loadingGreenText;
    @FindBy (how = How.ID , using = "finish_green")
    private WebElement greenLoadedText;



    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        greenButton.click();
    }

    public void checkButtonNotDisplayed(){
        assertFalse(greenButton.isDisplayed());
    }

    public void checkLoadingGreenDisplayed(){
        assertTrue(loadingGreenText.isDisplayed());
        assertEquals("Loading green...", loadingGreenText.getText());
    }

    public void checkLoadingGreenNotDisplayed(){
        assertFalse(loadingGreenText.isDisplayed());
    }

    public void checkGreenLoadedDisplayed(){
        assertTrue(greenLoadedText.isDisplayed());
        assertEquals("Green Loaded", greenLoadedText.getText());
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
//         * 2) check that text "Loading green..." is visible
//         * 3) check that text "Loading green..." is not visible
//         * 4) check that text "Green Loaded" is visible
}
