package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;

public class FeedbackPage extends GenericSamplePage {

    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;
    @FindBy(how = How.CSS, using = ".w3-check[type='checkbox']")
    private List<WebElement> languagesSelect;
    @FindBy(how = How.CSS, using = ".w3-radio[type='radio']")
    private List<WebElement> genresSelect;
    @FindBy(how = How.CLASS_NAME, using = "w3-select")
    private WebElement optionsSelect;
    @FindBy(how = How.CSS, using = ".w3-input.w3-border[name='comment']")
    private WebElement commentInput;

    @FindBy(how = How.CLASS_NAME, using = "w3-btn-block")
    private WebElement submitButton;

    public void submitButton () {
        submitButton.click();
    }

    public void fillForm(String name, String age, List<String> languagesValue, String genreValue, String optionValue, String comment) {
        nameInput.sendKeys(name);
        ageInput.sendKeys(age);
        for (WebElement languageSelect : languagesSelect) {
            for (String languageValue : languagesValue) {
                if (Objects.equals(languageSelect.getAttribute("value"), languageValue)) {
                    languageSelect.click();
                }
            }
        }
        for (WebElement genreSelect: genresSelect) {
            if(Objects.equals(genreSelect.getAttribute("value"), genreValue)) {
                genreSelect.click();
            }
        }
        Select select = new Select(optionsSelect);
        select.selectByValue(optionValue);
        commentInput.sendKeys(comment);
    }


}
