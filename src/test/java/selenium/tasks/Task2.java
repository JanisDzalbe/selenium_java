package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialPeopleList() throws Exception {
        WebElement addPersonButton = driver.findElement(By.cssSelector("[onClick='openModalForAddPersonWithJob()']"));
        WebElement resetListButton = driver.findElement(By.cssSelector("[onClick='resetListOfPeople()']"));

//          check that "Add person" and "Reset List" buttons are displayed and enabled
        assertTrue(addPersonButton.isDisplayed() && addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed() && resetListButton.isEnabled());
//          check list of people contains 10 entries with correct names and jobs
        List<String> names = driver.findElements(By.className("name")).stream().map(span -> span.getText()).toList();
        List<String> jobs = driver.findElements(By.className("job")).stream().map(span -> span.getText()).toList();
        assertTrue(names.contains("Mike") && jobs.contains("Web Designer"));
        assertTrue(names.contains("Jill") && jobs.contains("Support"));
        assertTrue(names.contains("Jane") && jobs.contains("Accountant"));
        assertTrue(names.contains("John") && jobs.contains("Software Engineer"));
        assertTrue(names.contains("Sarah") && jobs.contains("Product Manager"));
        assertTrue(names.contains("Carlos") && jobs.contains("Data Analyst"));
        assertTrue(names.contains("Emily") && jobs.contains("UX Designer"));
        assertTrue(names.contains("David") && jobs.contains("Project Manager"));
        assertTrue(names.contains("Maria") && jobs.contains("QA Engineer"));
        assertTrue(names.contains("Alex") && jobs.contains("DevOps Engineer"));
    }

    @Test
    public void addNewPerson() throws Exception {
        String startingURL = driver.getCurrentUrl();
        WebElement addPersonButton = driver.findElement(By.cssSelector("[onClick='openModalForAddPersonWithJob()']"));
        //          click "Add person"
        addPersonButton.click();
        assertEquals("https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html", driver.getCurrentUrl());

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement submitAddPersonButton = driver.findElement(By.cssSelector("[onClick='addPersonWithJobToList()']"));
        //          fill "Name" and "Job" fields
        nameField.clear();
        nameField.sendKeys("Bob");
        jobField.clear();
        jobField.sendKeys("Dog walker");
//          click "Add"
        submitAddPersonButton.click();
        assertEquals(startingURL, driver.getCurrentUrl());
        // NOTE: important to look up all names and jobs after adding
        List<String> names = driver.findElements(By.className("name")).stream().map(span -> span.getText()).toList();
        List<String> jobs = driver.findElements(By.className("job")).stream().map(span -> span.getText()).toList();
        //          check that new person is added to the list with correct name and job
        assertTrue(names.contains("Bob") && jobs.contains("Dog walker"));
    }

    @Test
    public void editExistingPerson() throws Exception {
        WebElement firstPersonsEditButton = driver.findElement(By.cssSelector("[onClick='openModalForEditPersonWithJob(0)']"));
        String firstPersonName = driver.findElement(By.className("name")).getText();
        String firstPersonJob = driver.findElement(By.className("job")).getText();
//          click pencil icon for an existing person
        firstPersonsEditButton.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement editButton = driver.findElement(By.cssSelector("[onClick='editPersonWithJob(0)']"));
//          check values in "Name" and "Job" fields
        assertEquals(firstPersonName, nameField.getDomProperty("value"));
        assertEquals(firstPersonJob, jobField.getDomProperty("value"));
//          change "Job" field
        jobField.clear();
        jobField.sendKeys("Dog walker");
//          click "Edit"
        editButton.click();
//          check that the person is updated in the list with new job
        firstPersonJob = driver.findElement(By.className("job")).getText();
        assertEquals("Dog walker", firstPersonJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
        WebElement firstPersonsDeleteButton = driver.findElement(By.cssSelector("[onClick='deletePerson(0)']"));
        String firstPersonName = driver.findElement(By.className("name")).getText();
        String firstPersonJob = driver.findElement(By.className("job")).getText();
//          click cross (x) icon for an existing person
        firstPersonsDeleteButton.click();
        // NOTE: important to look up all names and jobs after deleting
        List<String> names = driver.findElements(By.className("name")).stream().map(span -> span.getText()).toList();
        List<String> jobs = driver.findElements(By.className("job")).stream().map(span -> span.getText()).toList();
//          check that the person is removed from the list
        assertFalse(names.contains(firstPersonName) || jobs.contains(firstPersonJob));
    }

    @Test
    public void resetList() throws Exception {
        WebElement firstPersonsDeleteButton = driver.findElement(By.cssSelector("[onClick='deletePerson(0)']"));
        List<String> initialNames = driver.findElements(By.className("name")).stream().map(span -> span.getText()).toList();
        List<String> initialJobs = driver.findElements(By.className("job")).stream().map(span -> span.getText()).toList();
//          modify the list in any way (add, edit or remove a person)
        firstPersonsDeleteButton.click();
//          check that the list is modified
        List<String> currentNames = driver.findElements(By.className("name")).stream().map(span -> span.getText()).toList();
        List<String> currentJobs = driver.findElements(By.className("job")).stream().map(span -> span.getText()).toList();
        assertNotEquals(initialNames, currentNames);
        assertNotEquals(initialJobs, currentJobs);
//          click "Reset List"
        // NOTE: important to find reset button after deleting, otherwise throws StaleElementReferenceException
        WebElement resetListButton = driver.findElement(By.cssSelector("[onClick='resetListOfPeople()']"));
        resetListButton.click();
//          check that the list is back to initial state with 10 original entries
        currentNames = driver.findElements(By.className("name")).stream().map(span -> span.getText()).toList();
        currentJobs = driver.findElements(By.className("job")).stream().map(span -> span.getText()).toList();
        assertEquals(initialNames, currentNames);
        assertEquals(initialJobs, currentJobs);
    }
}
