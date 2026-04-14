package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Bonus {
    WebDriver driver;
    ListPage listPage;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
        listPage = new ListPage(driver);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void addPerson() {
        List<Person> before = listPage.getAllPeople();

        Person newPerson = new Person("Wajid", "Test Automation Engineer");
        FormPage form = listPage.clickAddPerson();
        form.setName(newPerson.getName())
                .setJob(newPerson.getJob())
                .submitAdd();

        List<Person> after = listPage.getAllPeople();

        // one extra person
        assertEquals(before.size() + 1, after.size());
        // original people unchanged and in the same order
        assertEquals(before, after.subList(0, before.size()));
        // new person is the last one
        assertEquals(newPerson, after.get(after.size() - 1));
    }

    @Test
    public void editPerson() {
        List<Person> before = listPage.getAllPeople();
        int indexToEdit = 0;
        Person updated = new Person(before.get(indexToEdit).getName(), "Senior Web Designer");

        FormPage form = listPage.clickEdit(indexToEdit);
        // verify existing values are pre-filled
        assertEquals(before.get(indexToEdit).getName(), form.getName());
        assertEquals(before.get(indexToEdit).getJob(), form.getJob());

        form.setJob(updated.getJob()).submitEdit();

        List<Person> after = listPage.getAllPeople();

        assertEquals(before.size(), after.size());
        assertEquals(updated, after.get(indexToEdit));
        for (int i = 0; i < before.size(); i++) {
            if (i == indexToEdit) continue;
            assertEquals(before.get(i), after.get(i), "Person at index " + i + " should not have changed");
        }
    }

    @Test
    public void editPersonCancel() {
        List<Person> before = listPage.getAllPeople();
        int indexToEdit = 0;

        FormPage form = listPage.clickEdit(indexToEdit);
        form.setJob("This should not be saved").cancel();

        List<Person> after = listPage.getAllPeople();
        assertEquals(before, after);
    }

    @Test
    public void deletePerson() {
        List<Person> before = listPage.getAllPeople();
        int indexToDelete = 0;
        Person removed = before.get(indexToDelete);

        listPage.clickDelete(indexToDelete);

        List<Person> after = listPage.getAllPeople();
        assertEquals(before.size() - 1, after.size());
        assertFalse(after.contains(removed), "Removed person should no longer be in the list");
        assertEquals(before.subList(1, before.size()), after);
    }

    @Test
    public void deletePersonAll() {
        int initialCount = listPage.getPersonCount();

        // repeatedly delete index 0 until list is empty
        for (int i = 0; i < initialCount; i++) {
            listPage.clickDelete(0);
        }

        assertEquals(0, listPage.getPersonCount());

        // Add person button still present and working
        assertTrue(listPage.isAddPersonButtonDisplayed());
        assertTrue(listPage.isAddPersonButtonEnabled());

        FormPage form = listPage.clickAddPerson();
        form.setName("Test").setJob("Tester").submitAdd();
        assertEquals(1, listPage.getPersonCount());
    }
}

/* ====================== Person data class ====================== */
class Person {
    private final String name;
    private final String job;

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() { return name; }
    public String getJob() { return job; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return Objects.equals(name, p.name) && Objects.equals(job, p.job);
    }

    @Override
    public int hashCode() { return Objects.hash(name, job); }

    @Override
    public String toString() { return name + " (" + job + ")"; }
}

/* ====================== ListPage (page object) ====================== */
class ListPage {
    private final WebDriver driver;

    private final By addPersonButton = By.xpath("//button[text()='Add person']");
    private final By resetListButton = By.xpath("//button[text()='Reset List']");
    private final By personRows      = By.xpath("//li[starts-with(@id,'person')]");
    private final By deleteIcons     = By.xpath("//span[contains(@onclick,'deletePerson')]");
    private final By editIcons       = By.xpath("//span[contains(@onclick,'openModalForEditPersonWithJob')]");

    public ListPage(WebDriver driver) {
        this.driver = driver;
    }

    public FormPage clickAddPerson() {
        driver.findElement(addPersonButton).click();
        return new FormPage(driver);
    }

    public FormPage clickEdit(int index) {
        driver.findElements(editIcons).get(index).click();
        return new FormPage(driver);
    }

    public void clickDelete(int index) {
        driver.findElements(deleteIcons).get(index).click();
    }

    public void clickResetList() {
        driver.findElement(resetListButton).click();
    }

    public boolean isAddPersonButtonDisplayed() {
        return driver.findElement(addPersonButton).isDisplayed();
    }

    public boolean isAddPersonButtonEnabled() {
        return driver.findElement(addPersonButton).isEnabled();
    }

    public int getPersonCount() {
        return driver.findElements(personRows).size();
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        List<WebElement> rows = driver.findElements(personRows);
        for (WebElement row : rows) {
            String name = row.findElement(By.className("name")).getText();
            String job = row.findElement(By.className("job")).getText();
            people.add(new Person(name, job));
        }
        return people;
    }

    public Person getPerson(int index) {
        return getAllPeople().get(index);
    }
}

/* ====================== FormPage (page object) ====================== */
class FormPage {
    private final WebDriver driver;

    private final By nameInput    = By.id("name");
    private final By jobInput     = By.id("job");
    private final By addButton    = By.xpath("//button[text()='Add']");
    private final By editButton   = By.xpath("//button[text()='Edit']");
    private final By cancelButton = By.xpath("//button[text()='Cancel']");

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getName() {
        return driver.findElement(nameInput).getAttribute("value");
    }

    public String getJob() {
        return driver.findElement(jobInput).getAttribute("value");
    }

    public FormPage setName(String name) {
        WebElement el = driver.findElement(nameInput);
        el.clear();
        el.sendKeys(name);
        return this;
    }

    public FormPage setJob(String job) {
        WebElement el = driver.findElement(jobInput);
        el.clear();
        el.sendKeys(job);
        return this;
    }

    public ListPage submitAdd() {
        driver.findElement(addButton).click();
        return new ListPage(driver);
    }

    public ListPage submitEdit() {
        driver.findElement(editButton).click();
        return new ListPage(driver);
    }

    public ListPage cancel() {
        driver.findElement(cancelButton).click();
        return new ListPage(driver);
    }
}