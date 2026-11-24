package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Individual Task 2: End-to-end CRUD and reset tests for the "List of people with jobs" page.
 */
public class Task2 {
    WebDriver driver;
    WebDriverWait wait;

    private static final String[][] INITIAL_PEOPLE = {
            {"Mike", "Web Designer"},
            {"Jill", "Support"},
            {"Jane", "Accountant"},
            {"John", "Software Engineer"},
            {"Sarah", "Product Manager"},
            {"Carlos", "Data Analyst"},
            {"Emily", "UX Designer"},
            {"David", "Project Manager"},
            {"Maria", "QA Engineer"},
            {"Alex", "DevOps Engineer"}
    };

    /**
     * Initializes ChromeDriver, configures an explicit wait, and opens the Task 2 page before each test.
     */
    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class);
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    /**
     * Closes the browser after each test.
     */
    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * Verifies that the "Add person" and "Reset List" buttons are visible and enabled,
     * and checks that all 10 initial entries match the expected names and jobs.
     *
     * @throws Exception if any expected element cannot be found
     */
    @Test
    public void initialPeopleList() throws Exception {
//         TODO:
//          check that "Add person" and "Reset List" buttons are displayed and enabled
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

//          check list of people contains 10 entries with correct names and jobs
//        Mike, Web Designer
//        Jill, Support
//        Jane, Accountant
//        John, Software Engineer
//        Sarah, Product Manager
//        Carlos, Data Analyst
//        Emily, UX Designer
//        David, Project Manager
//        Maria, QA Engineer
//        Alex, DevOps Engineer
        assertInitialPeopleList();
    }

    /**
     * Adds a new person via the "Add person" form and verifies they appear in the list
     * with the expected name and job.
     *
     * @throws Exception if any form or list element cannot be found
     */
    @Test
    public void addNewPerson() throws Exception {
        String newName = "Anna";
        String newJob = "QA Automation Engineer";

//         TODO:
//          click "Add person"
        driver.findElement(By.xpath("//button[text()='Add person']")).click();

//          fill "Name" and "Job" fields
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement jobInput = driver.findElement(By.id("job"));
        nameInput.sendKeys(newName);
        jobInput.sendKeys(newJob);

//          click "Add"
        driver.findElement(By.id("modal_button")).click();

//          check that new person is added to the list with correct name and job
        List<WebElement> people = wait.until(
                ExpectedConditions.numberOfElementsToBe(By.cssSelector("#listOfPeople li"), 11)
        );
        WebElement addedPerson = people.get(10);
        assertEquals(newName, addedPerson.findElement(By.className("name")).getText());
        assertEquals(newJob, addedPerson.findElement(By.className("job")).getText());
    }

    /**
     * Opens the edit form for an existing person, verifies pre-filled values, updates the job,
     * saves the change, and verifies the updated job in the list.
     *
     * @throws Exception if any person, form, or list element cannot be found
     */
    @Test
    public void editExistingPerson() throws Exception {
        String targetName = "Mike";
        String originalJob = "Web Designer";
        String updatedJob = "Senior Web Designer";

//         TODO:
//          click pencil icon for an existing person
        WebElement person = findPersonByName(targetName);
        person.findElement(By.className("editbtn")).click();

//          check values in "Name" and "Job" fields
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement jobInput = driver.findElement(By.id("job"));
        assertEquals(targetName, nameInput.getDomProperty("value"));
        assertEquals(originalJob, jobInput.getDomProperty("value"));

//          change "Job" field
        jobInput.clear();
        jobInput.sendKeys(updatedJob);

//          click "Edit"
        driver.findElement(By.id("modal_button")).click();

//          check that the person is updated in the list with new job
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#listOfPeople li"), 10));
        WebElement updatedPerson = findPersonByName(targetName);
        assertEquals(targetName, updatedPerson.findElement(By.className("name")).getText());
        assertEquals(updatedJob, updatedPerson.findElement(By.className("job")).getText());
    }

    /**
     * Removes an existing person using the cross icon and verifies they are no longer in the list.
     *
     * @throws Exception if the person or list elements cannot be found
     */
    @Test
    public void removeExistingPerson() throws Exception {
        String targetName = "Jill";

//         TODO:
//          click cross (x) icon for an existing person
        WebElement person = findPersonByName(targetName);
        person.findElement(By.className("closebtn")).click();

//          check that the person is removed from the list
        List<WebElement> people = wait.until(
                ExpectedConditions.numberOfElementsToBe(By.cssSelector("#listOfPeople li"), 9)
        );
        assertEquals(9, people.size());
        List<WebElement> removedPerson = driver.findElements(
                By.xpath("//li[.//span[contains(@class, 'name') and text()='" + targetName + "']]")
        );
        assertTrue(removedPerson.isEmpty());
    }

    /**
     * Modifies the list by removing a person, confirms the modification, clicks "Reset List",
     * and verifies all 10 original entries are restored.
     *
     * @throws Exception if any list or button element cannot be found
     */
    @Test
    public void resetList() throws Exception {
        String targetName = "Jill";

//         TODO:
//          modify the list in any way (add, edit or remove a person)
        WebElement person = findPersonByName(targetName);
        person.findElement(By.className("closebtn")).click();

//          check that the list is modified
        List<WebElement> modifiedPeople = wait.until(
                ExpectedConditions.numberOfElementsToBe(By.cssSelector("#listOfPeople li"), 9)
        );
        assertEquals(9, modifiedPeople.size());
        List<WebElement> removedPerson = driver.findElements(
                By.xpath("//li[.//span[contains(@class, 'name') and text()='" + targetName + "']]")
        );
        assertTrue(removedPerson.isEmpty());

//          click "Reset List"
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

//          check that the list is back to initial state with 10 original entries
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#listOfPeople li"), 10));
        assertInitialPeopleList();
    }

    /**
     * Finds a person's list item by their exact displayed name.
     *
     * @param name the person's name
     * @return the matching {@code <li>} WebElement
     */
    private WebElement findPersonByName(String name) {
        return driver.findElement(
                By.xpath("//li[.//span[contains(@class, 'name') and text()='" + name + "']]")
        );
    }

    /**
     * Asserts that the people list contains exactly the 10 default entries with their original names and jobs.
     */
    private void assertInitialPeopleList() {
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(INITIAL_PEOPLE.length, people.size());

        for (int i = 0; i < INITIAL_PEOPLE.length; i++) {
            String actualName = people.get(i).findElement(By.className("name")).getText();
            String actualJob = people.get(i).findElement(By.className("job")).getText();
            assertEquals(INITIAL_PEOPLE[i][0], actualName);
            assertEquals(INITIAL_PEOPLE[i][1], actualJob);
        }
    }
}