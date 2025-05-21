package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class FeedbackPage extends GenericSamplePage{

    public String emptyAssertError = "Field was not empty";
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;
    @FindBy(how = How.CLASS_NAME, using = "w3-check")
    private List<WebElement> languages;
    @FindBy(how = How.CLASS_NAME, using = "w3-radio")
    private List<WebElement> genders;
    @FindBy(how = How.ID, using = "like_us")
    private WebElement howDoYouLikeUs;
    @FindBy(how = How.NAME, using = "comment")
    private WebElement commentInput;
    @FindBy(how = How.TAG_NAME, using = "button")
    public WebElement button;
    @FindBy(how = How.ID, using = "name")
    private WebElement nameResult;
    @FindBy(how = How.ID, using = "age")
    private WebElement ageResult;
    @FindBy(how = How.ID, using = "language")
    private WebElement languageResult;
    @FindBy(how = How.ID, using = "gender")
    private WebElement genreResult;
    @FindBy(how = How.ID, using = "option")
    private WebElement optionResult;
    @FindBy(how = How.ID, using = "comment")
    private WebElement commentResult;
    @FindBy(how = How.CLASS_NAME, using = "w3-green")
    public WebElement yesButton;
    @FindBy(how = How.CLASS_NAME, using = "w3-red")
    public WebElement noButton;
    @FindBy(how = How.ID, using = "message")
    private WebElement messageResult;
    @FindBy(how = How.CLASS_NAME, using = "w3-green")
    private WebElement messageResultWrapper;

    public FeedbackPage(){
    }

    public String getNameInput(){
        return nameInput.getAttribute("value");
    }

    public String getButtonColor () {
        return button.getCssValue("color");
    }

    public String getButtonBGColor () {
        return button.getCssValue("background-color");
    }

    public void setNameInput(String nameInput){
        this.nameInput.sendKeys(nameInput);
    }

    public String getAge() {
        return ageInput.getAttribute("value");
    }

    public void enterAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void setLanguages(String languageName) {
        for (WebElement element : this.languages){
            if(element.getAttribute("value").equals(languageName)){
                element.click();
            }
        }
    }

    public List<String> getLanguages() {
        List <String> selectedLanguages = new ArrayList<String>();

        for (WebElement element : this.languages){
            if (element.isSelected()) {
                selectedLanguages.add(element.getAttribute("value"));
            }
        }
        return selectedLanguages;
    }

    public String getGenders() {
        for(WebElement element : this.genders){
            if(element.isSelected()){
                return element.getAttribute("value");
            }
        }
        return "";
    }

    public void setGenders(String gender) {
        for (WebElement element : this.genders){
            if(element.getAttribute("value").equals(gender)){
                element.click();
            }
        }
    }

    public String getHowDoYouLikeUs() {
        Select howDoYouLikeUs = new Select(this.howDoYouLikeUs);
        return howDoYouLikeUs.getFirstSelectedOption().getText();
    }

    public void setHowDoYouLikeUs(String value) {
        Select howDoYouLikeUs = new Select(this.howDoYouLikeUs);
        howDoYouLikeUs.selectByVisibleText(value);
    }

    public String getCommentInput() {
        return commentInput.getAttribute("value");
    }

    public void setCommentInput(String commentInput) {
        this.commentInput.sendKeys(commentInput);
    }


    public String getNameResult(){
        return nameResult.getText();
    }

    public String getAgeResult() {
        return ageResult.getText();
    }
    public String getLanguageResult() {
        return languageResult.getText();
    }
    public String getGenreResult() {
        return genreResult.getText();
    }
    public String getOptionResult() {
        return optionResult.getText();
    }

    public String getCommentResult() {
        return commentResult.getText();
    }

    public String getYesButtonColor () {
        return yesButton.getCssValue("color");
    }

    public String getYesButtonBGColor () {
        return yesButton.getCssValue("background-color");

    }

    public String getNoButtonColor () {
        return noButton.getCssValue("color");
    }

    public String getNoButtonBGColor () {
        return noButton.getCssValue("background-color");

    }
    public String getMessageResult() {
        return messageResult.getText();
    }
    public String getMessageResultColor () {
        return messageResult.getCssValue("color");
    }

    public String getMessageResultBGColor () {
        return messageResultWrapper.getCssValue("background-color");
    }


}
