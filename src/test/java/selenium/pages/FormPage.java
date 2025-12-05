package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import selenium.customDataTable.PersonData;

public class FormPage {
    private ListPage listPage;

    @FindBy(how = How.ID, using = "name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "job")
    private WebElement jobInput;
    @FindBy(how = How.XPATH, using = "//*[text()='Add']")
    private WebElement addPersonButton;
    @FindBy(how = How.XPATH, using = "//*[text()='Edit']")
    private WebElement editButton;
    @FindBy(how = How.XPATH, using = "//*[text()='Cancel']")
    private WebElement backButton;

    public String getPageBaseUrl() {
        return "https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html";
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }
    public void enterJob(String job) {
        jobInput.clear();
        jobInput.sendKeys(job);
    }
    public void clickAddButton() {
        addPersonButton.click();
    }
    public void clickEditButton() {
        editButton.click();
    }
    public void clickBackButton() {
        backButton.click();
    }
    public void enterNameJobAndClickEdit(String name, String job) {
        enterName(name);
        enterJob(job);
        clickEditButton();
    }
    public void enterJobAndClickEdit(String job) {
        enterJob(job);
        clickEditButton();
    }
    public void enterNameJobAndClickAdd(String name, String job) {
        enterName(name);
        enterJob(job);
        clickAddButton();
    }
}
