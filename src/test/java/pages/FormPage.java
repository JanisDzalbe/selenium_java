package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage {

    WebDriver driver;

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(id = "job")
    WebElement inputJob;

    @FindBy(id = "modal_button")
    WebElement submitButton;

    @FindBy(xpath = "//button[text()='Cancel']")
    WebElement cancelButton;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillForm(String name, String job) {
        inputName.clear();
        inputName.sendKeys(name);

        inputJob.clear();
        inputJob.sendKeys(job);
    }

    public void submit() {
        submitButton.click();
    }

    public void cancel() {
        cancelButton.click();
    }
}
