package selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColorSamplePage extends GenericSamplePage {
//         TODO:
//         Define elements in page
//         see https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/FindBy.html
           @FindBy(how = How.ID,using = "start_green")
           private WebElement startGreenButton;

    @FindBy(how = How.ID,using = "loading_green")
    private WebElement loadingGreenText;

    @FindBy(how = How.ID,using = "finish_green")
    private WebElement finishGreenText;

    public void clickStartLoadingGreen() {
//         TODO:
//         implement clicking on "Start loading green" button
           startGreenButton.click();
    }

//         TODO
//         Implement methods for:
//         * 1) check that "Start loading green" button is not visible
    public void assertStartGreenButtonNotVisible () {assertFalse(startGreenButton.isDisplayed());}
//         * 2) check that text "Loading green..." is visible
    public void assertLoadingGreenTextVisible () {assertTrue(loadingGreenText.isDisplayed());}
//         * 3) check that text "Loading green..." is not visible
    public void assertLoadingGreenTextNotVisible () {assertFalse(loadingGreenText.isDisplayed());}
//         * 4) check that text "Green Loaded" is visible
    public void assertFinishGreenTextVisible () {assertTrue(finishGreenText.isDisplayed());}
}
