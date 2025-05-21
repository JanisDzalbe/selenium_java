package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackPage {
    @FindBy(id = "fb_name")
    WebElement nameInput;
    @FindBy(id = "fb_age")
    WebElement ageInput;
    @FindBy(className = "w3-check")
    private List<WebElement> language;
    @FindBy(className = "w3-radio")
    private List<WebElement> gender;
    @FindBy(id = "like_us")
    private WebElement option;
    @FindBy(css = "textarea[name='comment']")
    private WebElement commentInput;
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public void checkName(String name) {
        assertEquals(name, nameInput.getAttribute("value"), "Name is not correct");
    }

    public void checkAge(String age) {
        assertEquals(age, ageInput.getAttribute("value"), "Age is not correct");
    }

    public void checkLanguage(String language) {
        for(WebElement element : this.language){
            if((element.getAttribute("value").toLowerCase()).equals(language.toLowerCase())){
                assertTrue(element.isSelected(), "Language is not correct");
            }
        }
    }

    public void checkGender(String gender) {
        for(WebElement element : this.gender){
            if((element.getAttribute("value").toLowerCase()).equals(gender.toLowerCase())){
                assertTrue(element.isSelected(), "Gender is not correct");
            }
        }
    }

    public void checkOption(String likeUs) {
        Select select = new Select(this.option);
        assertEquals(likeUs, select.getFirstSelectedOption().getText(), "Option is not correct.");
    }

    public void checkComment(String comment) {
        assertEquals(comment, commentInput.getAttribute("value"), "Comment is not correct");
    }

    public void checkEmptyFeedback(){
        checkName("");
        checkAge("");
        checkLanguage("");
        checkGender("");
        checkOption("Choose your option");
        checkComment("");
    }

    public void checkButtonStyle() {
        assertEquals("rgba(33, 150, 243, 1)", submitButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", submitButton.getCssValue("color"));
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void enterName(String name){
        nameInput.clear();
        nameInput.sendKeys(name);
        checkName(name);
    }

    public void enterAge(String age){
        ageInput.clear();
        ageInput.sendKeys(age);
        checkAge(age);
    }

    public void selectLanguage(String language){
        for(WebElement element : this.language){
            if((element.getAttribute("value").toLowerCase()).equals(language.toLowerCase())){
                element.click();
            }
        }
        checkLanguage(language);
    }

    public void selectGender(String gender){
        for(WebElement element : this.gender){
            if((element.getAttribute("value").toLowerCase()).equals(gender.toLowerCase())){
                element.click();
            }
        }
        checkGender(gender);
    }
    public void selectOption(String option){
        Select select = new Select(this.option);
        select.selectByVisibleText(option);
        checkOption(option);
    }

    public void enterComment(String comment){
        commentInput.clear();
        commentInput.sendKeys(comment);
        checkComment(comment);
    }



}
