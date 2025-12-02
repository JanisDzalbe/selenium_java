package selenium.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PeopleWithJobsPage {

    private final WebDriver driver;

    public PeopleWithJobsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getPersonNames() {
        List<WebElement> nameEls = driver.findElements(By.cssSelector("span.name"));
        List<String> names = new ArrayList<>();
        for (WebElement el : nameEls) {
            names.add(el.getText().trim());
        }
        return names;
    }

    public List<String> getPersonJobs() {
        List<WebElement> jobEls = driver.findElements(By.cssSelector("span.job"));
        List<String> jobs = new ArrayList<>();
        for (WebElement el : jobEls) {
            jobs.add(el.getText().trim());
        }
        return jobs;
    }

    public int getPeopleCount() {
        return driver.findElements(By.cssSelector("span.name")).size();
    }

    public void clickAddPersonButton() {
        driver.findElement(By.xpath("//button[normalize-space()='Add person']")).click();
    }

    public void fillAddPersonForm(String name, String job) {
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        WebElement nameInput = inputs.get(0);
        WebElement jobInput  = inputs.get(1);

        nameInput.clear();
        nameInput.sendKeys(name);
        jobInput.clear();
        jobInput.sendKeys(job);
    }

    public void submitAddPerson() {
        driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
    }

    public void addPerson(String name, String job) {
        clickAddPersonButton();
        fillAddPersonForm(name, job);
        submitAddPerson();
    }

    public void openEditModalByIndex(int index) {
        List<WebElement> editButtons = driver.findElements(By.cssSelector("span.editbtn"));
        editButtons.get(index).click();
    }

    public void changeJobInEditModal(String newJob) {
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        WebElement jobInput = inputs.get(1);
        jobInput.clear();
        jobInput.sendKeys(newJob);
    }

    public String getNameFromEditModal() {
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        return inputs.get(0).getAttribute("value");
    }

    public String getJobFromEditModal() {
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        return inputs.get(1).getAttribute("value");
    }

    public void submitEdit() {
        driver.findElement(By.xpath("//button[normalize-space()='Edit']")).click();
    }

    public void cancelEdit() {
        driver.findElement(By.xpath("//button[normalize-space()='Cancel']")).click();
    }


    public void deletePersonByIndex(int index) {
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("span.closebtn"));
        deleteButtons.get(index).click();
    }


    public boolean isTablePresent() {
        return !driver.findElements(By.tagName("table")).isEmpty();
    }

    public boolean isAddPersonButtonVisible() {
        List<WebElement> buttons = driver.findElements(By.xpath("//button[normalize-space()='Add person']"));
        return !buttons.isEmpty() && buttons.get(0).isDisplayed() && buttons.get(0).isEnabled();
    }
}