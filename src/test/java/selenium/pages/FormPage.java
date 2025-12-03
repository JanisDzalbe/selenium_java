package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage {

    private final WebDriver driver;

    private final By nameInput = By.id("name");
    private final By jobInput = By.id("job");
    private final By addButton = By.xpath("//button[normalize-space(.)='Add']");
    private final By editButton = By.xpath("//button[normalize-space(.)='Edit']");
    private final By cancelButton = By.xpath("//button[normalize-space(.)='Cancel']");

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    private void fillForm(Person person) {
        WebElement name = driver.findElement(nameInput);
        WebElement job = driver.findElement(jobInput);
        name.clear();
        name.sendKeys(person.getName());
        job.clear();
        job.sendKeys(person.getJob());
    }

    public ListPage submitAdd(Person person) {
        fillForm(person);
        driver.findElement(addButton).click();
        return new ListPage(driver);
    }

    public ListPage submitEdit(Person person) {
        fillForm(person);
        driver.findElement(editButton).click();
        return new ListPage(driver);
    }

    public ListPage cancel() {
        driver.findElement(cancelButton).click();
        return new ListPage(driver);
    }
}
