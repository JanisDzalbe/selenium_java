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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
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
        WebElement addButton = driver.findElement(By.xpath("//button[normalize-space()='Add person']"));
        WebElement resetButton = driver.findElement(By.xpath("//button[normalize-space()='Reset List']"));

        assertTrue(addButton.isDisplayed());
        assertTrue(addButton.isEnabled());
        assertTrue(resetButton.isDisplayed());
        assertTrue(resetButton.isEnabled());

        String[] expectedNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] expectedJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager", "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(10, people.size());

        for (int i = 0; i < people.size(); i++) {
            WebElement person = people.get(i);
            String actualName = person.findElement(By.className("name")).getText();
            String actualJob = person.findElement(By.className("job")).getText();
            assertEquals(expectedNames[i], actualName);
            assertEquals(expectedJobs[i], actualJob);
        }

    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        driver.findElement(By.xpath("//button[normalize-space()='Add person']")).click();

        String newName = "Liga";
        String newJob = "Administrator";

        driver.findElement(By.xpath("//button[normalize-space()='Add person']")).click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        nameField.sendKeys(newName);
        jobField.sendKeys(newJob);

        driver.findElement(By.id("modal_button")).click();

        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(11, people.size());

        List<WebElement> ligaRows = driver.findElements(By.xpath("//li[.//span[normalize-space()='Liga']]"));
        assertEquals(1, ligaRows.size());

        WebElement liga = ligaRows.get(0);

        String actualName = liga.findElement(By.className("name")).getText();

        String actualJob = liga.findElement(By.className("job")).getText();

        assertEquals(newName, actualName);
        assertEquals(newJob, actualJob);
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        WebElement mikeRow = driver.findElement(By.xpath("//li[.//span[normalize-space()='Mike']]"));
        WebElement editButton = mikeRow.findElement(By.className("editbtn"));
        editButton.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        assertEquals("Mike", nameField.getAttribute("value"));
        assertEquals("Web Designer", jobField.getAttribute("value"));

        jobField.clear();
        jobField.sendKeys("Senior Web Designer");

        driver.findElement(By.id("modal_button")).click();

        WebElement updatedMikeRow = driver.findElement(By.xpath("//li[.//span[normalize-space()='Mike']]"));
        String updatedName = updatedMikeRow.findElement(By.className("name")).getText();

        String updatedJob = updatedMikeRow.findElement(By.className("job")).getText();

        assertEquals("Mike", updatedName);
        assertEquals("Senior Web Designer", updatedJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        WebElement mikeRow = driver.findElement(By.xpath("//li[.//span[normalize-space()='Mike']]"));
        WebElement removeButton = mikeRow.findElement(By.className("closebtn"));
        removeButton.click();

        List<WebElement> mikeEntries = driver.findElements(By.xpath("//li[.//span[normalize-space()='Mike']]"));
        assertTrue(mikeEntries.isEmpty());

        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(9, people.size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        WebElement mikeRow = driver.findElement(By.xpath("//li[.//span[normalize-space()='Mike']]"));
        WebElement removeButton = mikeRow.findElement(By.className("closebtn"));
        removeButton.click();

        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(9, people.size());

        List<WebElement> mikeEntries = driver.findElements(By.xpath("//li[.//span[normalize-space()='Mike']]"));
        assertTrue(mikeEntries.isEmpty());

        WebElement resetButton = driver.findElement(By.xpath("//button[normalize-space()='Reset List']"));
        resetButton.click();

        List<WebElement> restoredPeople = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(10, restoredPeople.size());

        String[] expectedNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] expectedJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager", "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

        for (int i = 0; i < people.size(); i++) {
            WebElement person = restoredPeople.get(i);
            String actualName = person.findElement(By.className("name")).getText();
            String actualJob = person.findElement(By.className("job")).getText();
            assertEquals(expectedNames[i], actualName);
            assertEquals(expectedJobs[i], actualJob);
        }
    }
}
