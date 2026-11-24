package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2 {
    static final List<String> INITIAL_PEOPLE = List.of(
            "Mike, Web Designer",
            "Jill, Support",
            "Jane, Accountant",
            "John, Software Engineer",
            "Sarah, Product Manager",
            "Carlos, Data Analyst",
            "Emily, UX Designer",
            "David, Project Manager",
            "Maria, QA Engineer",
            "Alex, DevOps Engineer");
    static final By PERSON = By.cssSelector("#listOfPeople li");
    static final By ADD_PERSON = By.xpath("//button[text()='Add person']");
    static final By RESET_LIST = By.xpath("//button[text()='Reset List']");
    static final By EDIT_BUTTON = By.xpath("//button[text()='Edit']");
    static final By NAME_FIELD = By.id("name");
    static final By JOB_FIELD = By.id("job");

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForMenu();
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialPeopleList() throws Exception {
        // check that "Add person" and "Reset List" buttons are displayed and enabled
        // (each of them is shown twice: above and below the list)
        for (By locator : List.of(ADD_PERSON, RESET_LIST)) {
            List<WebElement> buttons = driver.findElements(locator);
            assertEquals(2, buttons.size());
            for (WebElement button : buttons) {
                assertTrue(button.isDisplayed());
                assertTrue(button.isEnabled());
            }
        }

        // check list of people contains 10 entries with correct names and jobs
        assertEquals(10, getPeople().size());
        assertEquals(INITIAL_PEOPLE, getPeople());
    }

    @Test
    public void addNewPerson() throws Exception {
        String name = "Anna";
        String job = "Test Automation Engineer";
        List<String> expectedPeople = new ArrayList<>(INITIAL_PEOPLE);
        assertEquals(expectedPeople, getPeople());

        // click "Add person", fill "Name" and "Job" fields, click "Add"
        addPerson(name, job);

        // check that new person is added to the list with correct name and job
        expectedPeople.add(name + ", " + job);
        assertEquals(expectedPeople, getPeople());
    }

    @Test
    public void editExistingPerson() throws Exception {
        String name = "John";
        String oldJob = "Software Engineer";
        String newJob = "Software Architect";
        List<String> expectedPeople = new ArrayList<>(INITIAL_PEOPLE);
        assertEquals(expectedPeople, getPeople());

        // click pencil icon for an existing person
        openEditForm(name);

        // check values in "Name" and "Job" fields
        assertEquals(name, driver.findElement(NAME_FIELD).getDomProperty("value"));
        assertEquals(oldJob, driver.findElement(JOB_FIELD).getDomProperty("value"));

        // change "Job" field, click "Edit"
        submitNewJob(newJob);

        // check that the person is updated in the list with new job
        expectedPeople.set(expectedPeople.indexOf(name + ", " + oldJob), name + ", " + newJob);
        assertEquals(expectedPeople, getPeople());
    }

    @Test
    public void removeExistingPerson() throws Exception {
        String name = "Jane";
        String job = "Accountant";
        List<String> expectedPeople = new ArrayList<>(INITIAL_PEOPLE);
        assertEquals(expectedPeople, getPeople());

        // click cross (x) icon for an existing person
        removePerson(name);

        // check that the person is removed from the list
        expectedPeople.remove(name + ", " + job);
        assertEquals(expectedPeople, getPeople());
    }

    @Test
    public void resetList() throws Exception {
        // add a person, check that the list is modified, reset it and check that it is back to initial state
        addPerson("Anna", "Test Automation Engineer");
        assertTrue(getPeople().contains("Anna, Test Automation Engineer"));
        clickAndWaitForNewPage(driver.findElement(RESET_LIST));
        assertEquals(INITIAL_PEOPLE, getPeople());

        // the same after editing a person
        openEditForm("John");
        submitNewJob("Software Architect");
        assertTrue(getPeople().contains("John, Software Architect"));
        clickAndWaitForNewPage(driver.findElement(RESET_LIST));
        assertEquals(INITIAL_PEOPLE, getPeople());

        // the same after removing a person
        removePerson("Jane");
        assertFalse(getPeople().contains("Jane, Accountant"));
        clickAndWaitForNewPage(driver.findElement(RESET_LIST));
        assertEquals(INITIAL_PEOPLE, getPeople());
    }

    // returns all people in the list as "name, job"
    private List<String> getPeople() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(PERSON, 0));
        List<String> people = new ArrayList<>();
        for (WebElement person : driver.findElements(PERSON)) {
            people.add(person.findElement(By.className("name")).getText() + ", "
                    + person.findElement(By.className("job")).getText());
        }
        return people;
    }

    private WebElement findPerson(String name) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[.//*[contains(@class, 'name') and text()='" + name + "']]")));
    }

    private void addPerson(String name, String job) {
        clickAndWaitForNewPage(driver.findElement(ADD_PERSON));
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add']")));
        driver.findElement(NAME_FIELD).sendKeys(name);
        driver.findElement(JOB_FIELD).sendKeys(job);
        clickAndWaitForNewPage(addButton);
    }

    private void openEditForm(String name) {
        clickAndWaitForNewPage(findPerson(name).findElement(By.className("editbtn")));
        wait.until(ExpectedConditions.elementToBeClickable(EDIT_BUTTON));
    }

    private void submitNewJob(String job) {
        WebElement jobField = driver.findElement(JOB_FIELD);
        jobField.clear();
        jobField.sendKeys(job);
        clickAndWaitForNewPage(driver.findElement(EDIT_BUTTON));
    }

    private void removePerson(String name) {
        clickAndWaitForNewPage(findPerson(name).findElement(By.className("closebtn")));
    }

    // every action on this page opens or reloads a page, so wait until the old one is gone
    private void clickAndWaitForNewPage(WebElement element) {
        WebElement oldPage = driver.findElement(By.tagName("html"));
        element.click();
        wait.until(ExpectedConditions.stalenessOf(oldPage));
        waitForMenu();
    }

    // menu is loaded separately and moves the rest of the page down, so wait for it before clicking
    private void waitForMenu() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#header > *")));
    }
}
