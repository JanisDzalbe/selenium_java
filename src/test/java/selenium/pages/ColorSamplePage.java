package selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
    @FindBy(how = How.ID, using = "start_green")
    private WebElement startButton;
    @FindBy(how = How.ID, using = "loading_green")
    private WebElement loadingElement;
    @FindBy(how = How.ID, using = "finish_green")
    private WebElement finishElement;

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
        startButton.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible +
//         * 2) check that text "Loading green..." is visible +
//         * 3) check that text "Loading green..." is not visible +
//         * 4) check that text "Green Loaded" is visible +
    public void checkStartButtonNotVisible (){
        assertFalse(startButton.isDisplayed());
    }
    public void checkLoadingElementVisible(){
        assertTrue(loadingElement.isDisplayed());
    }
    public void checkLoadingElementNotVisible(){
        assertFalse(loadingElement.isDisplayed());
    }
    public void checkFinishElementVisible(){

        assertTrue(finishElement.isDisplayed());
    }
}
