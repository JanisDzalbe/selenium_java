package selenium.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackPage {
    // Decided to implement them as different classes for better code readability in use.
    // Not quite sure if that is best practice, but hopefully it is :)
    // Implemented getters for certain specific one time use cases

    public static class InputPage {
        public InputPage(){}
        @FindBy(id = "fb_name")
        private WebElement nameInput;
        public WebElement getNameInput(){
            return nameInput;
        }

        @FindBy(id = "fb_age")
        private WebElement ageInput;
        public WebElement getAgeInput(){
            return ageInput;
        }

        // I dislike creating a seperate list just for the languages, but I couldn't think of a clean way without out (same with genders)
        @FindBy(css = "div#lang_check .w3-check")
        private List<WebElement> languageCheckboxList;
        public List<WebElement> getLanguageCheckboxList(){
            return languageCheckboxList;
        }
        @FindBy(css = "div#lang_check .w3-validate")
        private List<WebElement> languagesList;
        public List<WebElement> getLanguagesList(){
            return languagesList;
        }

        @FindBy(css = ".w3-radio[name='gender']")
        private List<WebElement> genderRadioList;
        public List<WebElement> getGenderRadioList(){
            return genderRadioList;
        }
        //messed around for so long to find a way to find the associated label like this.
        @FindBy(css = ".w3-radio[name='gender'] + label")
        private List<WebElement> genderList;
        public List<WebElement> getGenderList(){
            return genderList;
        }

        // weird that select doesnt work on pagefactory so u have to use workarounds - https://stackoverflow.com/questions/37668410/automate-drop-down-using-page-factory-model
        @FindBy(css = "select#like_us")
        private WebElement opinionDropdown;
        public Select getOpinionSelect() {
            return new Select(opinionDropdown);
        }

        @FindBy (css = "textarea[name='comment']")
        private WebElement commentInput;
        public WebElement getCommentInput(){
            return commentInput;
        }
        @FindBy (css = "button[type='submit']")
        private WebElement submitButton;
        public WebElement getSubmitButton(){
            return submitButton;
        }

        public void enterName(String name) {
            nameInput.clear();
            nameInput.sendKeys(name);
        }

        public void enterAge(String age) {
            ageInput.clear();
            ageInput.sendKeys(age);
        }

        public void selectLanguage(int languageIndex) {
            languageCheckboxList.get(languageIndex).click();
        }

        public void selectGender(int genderIndex) {
            genderRadioList.get(genderIndex).click();
        }

        public void selectOpinion(int opinionIndex) {
            getOpinionSelect().selectByIndex(opinionIndex);
        }

        public void enterComment(String comment) {
            commentInput.clear();
            commentInput.sendKeys(comment);
        }

        public void clickSubmit() {
            submitButton.click();
        }

        public void enterAll(String name, String age, int languageIndex, int genderIndex, int opinionIndex, String comment){
            enterName(name);
            enterAge(age);
            selectLanguage(languageIndex);
            selectGender(genderIndex);
            selectOpinion(opinionIndex);
            enterComment(comment);
        }

        // ---------------------------------------
        // Assertions

        public void assertNameInput(String name){
            assertEquals(name,nameInput.getAttribute("value"));
        }

        public void assertAgeInput(String age){
            assertEquals(age, ageInput.getAttribute("value"));
        }

        // Is there a better way of doing this?
        public void assertLanguageSelectionEmpty() {
            for(WebElement cbox : languageCheckboxList) {
                assertFalse(cbox.isSelected());
            }
        }

        public void assertLanguageSelection(int languageIndex) {
            assertTrue(languageCheckboxList.get(languageIndex).isSelected());
        }

        // Probably not good practice doing it like this if the radio gets rearranged or gets another element inserted
        public void assertGenderSelectionEmpty(){
            assertTrue(genderRadioList.get(2).isSelected());
            assertFalse(genderRadioList.get(0).isSelected());
            assertFalse(genderRadioList.get(1).isSelected());
        }

        public void assertGenderSelection(int genderIndex) {
            assertTrue(genderRadioList.get(genderIndex).isSelected());
        }

        public void assertOpinionSelectionEmpty() {
            assertEquals("Choose your option", getOpinionSelect().getFirstSelectedOption().getText());
        }

        public void assertOpinionSelection(int opinionIndex) {
            //I dislike this
            assertTrue(getOpinionSelect().getOptions().get(opinionIndex).isSelected());
        }

        public void assertCommentInput(String comment) {
            assertEquals(comment, commentInput.getAttribute("value"));
        }

        public void assertButtonColor(){
            assertTrue(submitButton.getCssValue("background-color").contains("rgba(33, 150, 243, 1)"));
            assertTrue(submitButton.getCssValue("color").contains("rgba(255, 255, 255, 1)"));
        }

        // Super weird, at first it recognized null as empty for input fields, now it recognizes ""
        public void assertAllEmpty(){
            assertNameInput("");
            assertAgeInput("");
            assertLanguageSelectionEmpty();
            assertGenderSelectionEmpty();
            assertOpinionSelectionEmpty();
            assertCommentInput("");
        }

        public void assertAll(String name, String age, int languageIndex, int genderIndex, int opinionIndex, String comment){
            assertNameInput(name);
            assertAgeInput(age);
            assertLanguageSelection(languageIndex);
            assertGenderSelection(genderIndex);
            assertOpinionSelection(opinionIndex);
            assertCommentInput(comment);
            assertButtonColor();
        }





    }

    public static class ConfirmPage {
        public ConfirmPage(){}
        @FindBy (css = "span#name")
        private WebElement nameText;
        public WebElement getNameText() {
            return nameText;
        }
        @FindBy (css = "span#age")
        private WebElement ageText;
        public WebElement getAgeText() {
            return ageText;
        }
        @FindBy (css = "span#language")
        private WebElement languageText;
        public WebElement getLanguageText() {
            return languageText;
        }
        @FindBy (css = "span#gender")
        private WebElement genderText;
        public WebElement getGenderText() {
            return genderText;
        }
        @FindBy (css = "span#option")
        private WebElement opinionText;
        public WebElement getOpinionText() {
            return opinionText;
        }
        @FindBy (css = "span#comment")
        private WebElement commentText;
        public WebElement getCommentText() {
            return commentText;
        }

        @FindBy (css = "button.w3-green")
        private WebElement yesButton;
        public WebElement getYesButton(){
            return yesButton;
        }
        @FindBy (css = "button.w3-red")
        private WebElement noButton;
        public WebElement getNoButton(){
            return noButton;
        }

        // -----------------
        // Interactions
        public void clickYesButton() {
            yesButton.click();
        }
        public void clickNoButton() {
            noButton.click();
        }

        // -------------
        // Assertions

        public void assertNameText(String name) {
            assertEquals(name, nameText.getText());
        }

        public void assertAgeText(String age) {
            assertEquals(age, ageText.getText());
        }

        public void assertLanguageText(String language) {
            assertEquals(language, languageText.getText());
        }

        public void assertGenderText(String gender) {
            assertEquals(gender, genderText.getText());
        }

        public void assertOpinionText(String opinion) {
            assertEquals(opinion, opinionText.getText());
        }
        public void assertCommentText(String comment) {
            assertEquals(comment, commentText.getText());
        }

        public void assertAllEmpty(){
            assertNameText("");
            assertAgeText("");
            assertLanguageText("");
            assertGenderText("null");
            assertOpinionText("null");
            assertCommentText("");
        }

        public void assertAll(String name, String age, String language, String gender, String opinion, String comment){
            assertNameText(name);
            assertAgeText(age);
            assertLanguageText(language);
            assertGenderText(gender);
            assertOpinionText(opinion);
            assertCommentText(comment);
        }


        public void assertYesButtonColor(){
            assertTrue(yesButton.getCssValue("background-color").contains("rgba(76, 175, 80, 1)"));
            assertTrue(yesButton.getCssValue("color").contains("rgba(255, 255, 255, 1)"));
        }

        public void assertNoButtonColor(){
            assertTrue(noButton.getCssValue("background-color").contains("rgba(244, 67, 54, 1)"));
            assertTrue(noButton.getCssValue("color").contains("rgba(255, 255, 255, 1)"));
        }

    }

    public static class ThankYouPage{
        public ThankYouPage(){}
        @FindBy (css = ".w3-green")
        private WebElement thankYouDiv;
        public WebElement getThankYouDiv(){
            return thankYouDiv;
        }

        @FindBy (id="message")
        private WebElement messageText;
        public WebElement getMessageText(){
            return messageText;
        }

        public void assertName(String name){
            //not sure if this is the optimal solution for this. Didn't want to use contains because if the name is Thank it'd still pass, even if it doesnt show the name properly
            if (name == null || name.isEmpty()) {
                assertEquals("Thank you for your feedback!", messageText.getText());
            } else {
                assertEquals("Thank you, " + name + ", for your feedback!", messageText.getText());
            }

        }

        public void assertColor(){
            assertTrue(thankYouDiv.getCssValue("background-color").contains("rgba(76, 175, 80, 1)"));
            assertTrue(messageText.getCssValue("color").contains("rgba(255, 255, 255, 1)"));
        }
    }

}
