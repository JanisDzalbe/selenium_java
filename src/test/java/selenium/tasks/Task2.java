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
        // Check Add person button
        WebElement addPersonButton = driver.findElement(
                By.xpath("//button[text()='Add person']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());

        // Check Reset List button
        WebElement resetListButton = driver.findElement(
                By.xpath("//button[text()='Reset List']"));

        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        // Check all 10 people and their jobs
        checkOriginalPeople();
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        // Click Add person
        driver.findElement(
                By.xpath("//button[text()='Add person']")).click();

        // Fill Name and Job
        driver.findElement(By.id("name")).sendKeys("Jha");
        driver.findElement(By.id("job")).sendKeys("Automation Tester");

        // Click Add
        driver.findElement(
                By.xpath("//button[text()='Add']")).click();

        // Find the newly added person
        WebElement jha = driver.findElement(
                By.xpath("//li[.//span[text()='Jha']]"));

        // Verify name and job
        assertEquals("Jha",
                jha.findElement(By.className("name")).getText());

        assertEquals("Automation Tester",
                jha.findElement(By.className("job")).getText());

        // Verify list now contains 11 people
        assertEquals(11,
                driver.findElements(By.cssSelector("#listOfPeople li")).size());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        // Find John
        WebElement john = driver.findElement(
                By.xpath("//li[.//span[text()='John']]"));

        // Click John's pencil icon
        john.findElement(By.className("editbtn")).click();

        // Verify existing Name and Job values
        assertEquals("John",
                driver.findElement(By.id("name")).getAttribute("value"));

        assertEquals("Software Engineer",
                driver.findElement(By.id("job")).getAttribute("value"));

        // Change Job
        WebElement job = driver.findElement(By.id("job"));

        job.clear();
        job.sendKeys("Senior Software Engineer");

        // Click Edit
        driver.findElement(
                By.xpath("//button[text()='Edit']")).click();

        // Find John again
        WebElement updatedJohn = driver.findElement(
                By.xpath("//li[.//span[text()='John']]"));

        // Verify new Job
        assertEquals("Senior Software Engineer",
                updatedJohn.findElement(By.className("job")).getText());

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        // Find John
        WebElement john = driver.findElement(
                By.xpath("//li[.//span[text()='John']]"));

        // Click John's cross icon
        john.findElement(By.className("closebtn")).click();

        // Check John is removed
        assertEquals(0,
                driver.findElements(
                        By.xpath("//li[.//span[text()='John']]")).size());

        // Verify 9 people remain
        assertEquals(9,
                driver.findElements(By.cssSelector("#listOfPeople li")).size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        // Modify the list by removing John
        WebElement john = driver.findElement(
                By.xpath("//li[.//span[text()='John']]"));

        john.findElement(By.className("closebtn")).click();

        // Verify the list is modified
        assertEquals(9,
                driver.findElements(By.cssSelector("#listOfPeople li")).size());

        // Click Reset List
        driver.findElement(
                By.xpath("//button[text()='Reset List']")).click();

        // Verify all original people and jobs are restored
        checkOriginalPeople();
    }

    // Reusable method to check the original 10 people
    public void checkOriginalPeople() {

        String[] names = {
                "Mike", "Jill", "Jane", "John", "Sarah",
                "Carlos", "Emily", "David", "Maria", "Alex"
        };

        String[] jobs = {
                "Web Designer", "Support", "Accountant",
                "Software Engineer", "Product Manager",
                "Data Analyst", "UX Designer", "Project Manager",
                "QA Engineer", "DevOps Engineer"
        };

        // Check total number of people
        List<WebElement> people = driver.findElements(
                By.cssSelector("#listOfPeople li"));

        assertEquals(10, people.size());

        // Verify every person's name and job
        for (int i = 0; i < names.length; i++) {

            WebElement person = driver.findElement(
                    By.xpath("//li[.//span[text()='" + names[i] + "']]"));

            assertEquals(names[i],
                    person.findElement(By.className("name")).getText());

            assertEquals(jobs[i],
                    person.findElement(By.className("job")).getText());
        }
    }
}
