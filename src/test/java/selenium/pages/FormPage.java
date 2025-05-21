package selenium.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
public class FormPage {
    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "job")
    private WebElement jobInput;

    @FindBy(xpath = "//button[@id='modal_button' and text() = 'Add']")
    private WebElement addButton;
    @FindBy(xpath = "//button[@id='modal_button' and text() = 'Cancel']")
    private WebElement cancelButton;
    @FindBy(xpath = "//button[@id='modal_button' and text() = 'Edit']")
    private WebElement editButton;

    public void enterName(String name){
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterJob(String job){
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void clickAdd(){
        addButton.click();
    }

    public void clickEdit(){
        editButton.click();
    }


    public void clickCancel(){
        cancelButton.click();
    }
}
