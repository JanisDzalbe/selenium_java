package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class Task2 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeDriver();


        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }


    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
    private List<String> getNames() {
        List<WebElement> nameEls = driver.findElements(By.cssSelector("span.name"));
        List<String> names = new ArrayList<>();
        for (WebElement el : nameEls) {
            names.add(el.getText().trim());
        }
        return names;
    }

    private List<String> getJobs() {
        List<WebElement> jobEls = driver.findElements(By.cssSelector("span.job"));
        List<String> jobs = new ArrayList<>();
        for (WebElement el : jobEls) {
            jobs.add(el.getText().trim());
        }
        return jobs;
    }

    @Test
    public void initialPeopleList() throws Exception {
//         TODO:
//          check that "Add person" and "Reset List" buttons are displayed and enabled
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
        WebElement addPersonButton = driver.findElement(By.xpath("//button[normalize-space()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[normalize-space()='Reset List']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());
        List<String> actualNames = getNames();
        List<String> actualJobs = getJobs();

        assertEquals(10, actualNames.size(), "Must be 10 names");
        assertEquals(10, actualJobs.size(), "Must be 10 occumapint");

        String[] expectedNames = {
                "Mike",
                "Jill",
                "Jane",
                "John",
                "Sarah",
                "Carlos",
                "Emily",
                "David",
                "Maria",
                "Alex"
        };

        String[] expectedJobs = {
                "Web Designer",
                "Support",
                "Accountant",
                "Software Engineer",
                "Product Manager",
                "Data Analyst",
                "UX Designer",
                "Project Manager",
                "QA Engineer",
                "DevOps Engineer"
        };

        for (int i = 0; i < expectedNames.length; i++) {
            assertEquals(expectedNames[i], actualNames.get(i), "Name in string " + i);
            assertEquals(expectedJobs[i], actualJobs.get(i), "Occupation in string " + i);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job

        List<String> namesBefore = getNames();
        List<String> jobsBefore  = getJobs();
        int initialSize = namesBefore.size();
        WebElement addPersonButton = driver.findElement(By.xpath("//button[normalize-space()='Add person']"));
        addPersonButton.click();
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        assertTrue(inputs.size() >= 2, "2 input required");
        WebElement nameInput = inputs.get(0);
        WebElement jobInput  = inputs.get(1);

        String newName = "Zoe";
        String newJob  = "tester";

        nameInput.clear();
        nameInput.sendKeys(newName);
        jobInput.clear();
        jobInput.sendKeys(newJob);

        WebElement addButton = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
        addButton.click();

        List<String> namesAfter = getNames();
        List<String> jobsAfter  = getJobs();
        assertEquals(initialSize + 1, namesAfter.size());
        assertEquals(initialSize + 1, jobsAfter.size());
        assertEquals(newName, namesAfter.get(namesAfter.size() - 1));
        assertEquals(newJob,  jobsAfter.get(jobsAfter.size() - 1));
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        List<String> namesBefore = getNames();
        List<String> jobsBefore  = getJobs();
        assertFalse(namesBefore.isEmpty());

        String originalName = namesBefore.get(0);
        String originalJob  = jobsBefore.get(0);
        WebElement firstEdit = driver.findElements(By.cssSelector("span.editbtn")).get(0);
        firstEdit.click();
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        WebElement nameInput = inputs.get(0);
        WebElement jobInput  = inputs.get(1);
        assertEquals(originalName, nameInput.getAttribute("value"));
        assertEquals(originalJob,  jobInput.getAttribute("value"));

        String newJob = originalJob + " (edited)";
        jobInput.clear();
        jobInput.sendKeys(newJob);

        driver.findElement(By.xpath("//button[normalize-space()='Edit']")).click();

        List<String> jobsAfter = getJobs();
        assertEquals(newJob, jobsAfter.get(0));
    }


    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        List<String> namesBefore = getNames();
        int initialSize = namesBefore.size();
        assertTrue(initialSize > 0, "List must not be empty");

        String nameToRemove = namesBefore.get(0);


        WebElement firstDelete = driver.findElements(By.cssSelector("span.closebtn")).get(0);
        firstDelete.click();

        List<String> namesAfter = getNames();
        assertEquals(initialSize - 1, namesAfter.size());
        assertFalse(namesAfter.contains(nameToRemove));
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries

        List<String> namesInitial = getNames();
        List<String> jobsInitial  = getJobs();
        assertEquals(10, namesInitial.size());
        assertEquals(10, jobsInitial.size());

        WebElement addPersonButton = driver.findElement(By.xpath("//button[normalize-space()='Add person']"));
        addPersonButton.click();

        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        assertTrue(inputs.size() >= 2);
        WebElement nameInput = inputs.get(0);
        WebElement jobInput  = inputs.get(1);

        nameInput.clear();
        nameInput.sendKeys("Temp Name");
        jobInput.clear();
        jobInput.sendKeys("Temp Job");

        WebElement addButton = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
        addButton.click();

        List<String> namesModified = getNames();
        List<String> jobsModified  = getJobs();
        assertEquals(namesInitial.size() + 1, namesModified.size());
        assertEquals(jobsInitial.size() + 1,  jobsModified.size());
        WebElement resetListButton = driver.findElement(By.xpath("//button[normalize-space()='Reset List']"));
        resetListButton.click();
        List<String> namesAfterReset = getNames();
        List<String> jobsAfterReset  = getJobs();

        assertEquals(namesInitial.size(), namesAfterReset.size());
        assertEquals(jobsInitial.size(), jobsAfterReset.size());

        for (int i = 0; i < namesInitial.size(); i++) {
            assertEquals(namesInitial.get(i), namesAfterReset.get(i), "Name in line " + i + " after Reset");
            assertEquals(jobsInitial.get(i), jobsAfterReset.get(i), "Occupation in line " + i + " after Reset");
        }
    }
}
