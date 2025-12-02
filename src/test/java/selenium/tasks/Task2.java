package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class Task2 {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialPeopleList() throws Exception {
        //check that "Add person" and "Reset List" buttons are displayed and enabled
        WebElement addButton = driver.findElement(By.xpath("//button[normalize-space()='Add person']"));
        WebElement resetButton = driver.findElement(By.xpath("//button[normalize-space()='Reset List']"));
        assertTrue(addButton.isDisplayed());
        assertTrue(addButton.isEnabled());
        assertTrue(resetButton.isDisplayed());
        assertTrue(resetButton.isEnabled());
        //check list of people contains 10 entries with correct names and jobs
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));
        assertEquals(10, people.size(), "List should contain 10 entries");
        String[] expectedNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] expectedJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager", "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};
        for (int i = 0; i < expectedNames.length; i++) {
            WebElement personRow = people.get(i);

            String actualName = personRow.findElement(By.cssSelector(".name")).getText().trim();
            String actualJob  = personRow.findElement(By.cssSelector(".job")).getText().trim();

            assertEquals(expectedNames[i], actualName, "Name mismatch in row " + (i + 1));
            assertEquals(expectedJobs[i], actualJob,  "Job mismatch in row " + (i + 1));
        }
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
    }

    @Test
    public void addNewPerson() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        List<WebElement> before = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));
        int beforeSize = before.size();
        assertEquals(10, beforeSize, "Initial list must contain 10 people");

        // 2) Click "Add person"
        driver.findElement(By.id("addPersonBtn")).click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement jobField = driver.findElement(By.id("job"));

        // 4) Fill data
        String newName = "Ihor";
        String newJob = "Automation Tester";

        nameField.clear();
        nameField.sendKeys(newName);
        jobField.clear();
        jobField.sendKeys(newJob);

        // 5) Click Add
        driver.findElement(By.id("modal_button")).click();

        wait.until(d -> d.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).size() == beforeSize + 1);
        List<WebElement> after = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));
        assertEquals(beforeSize + 1, after.size(), "Person was not added");

        WebElement lastRow = after.get(after.size() - 1);
        String actualName = lastRow.findElement(By.cssSelector(".name")).getText().trim();
        String actualJob  = lastRow.findElement(By.cssSelector(".job")).getText().trim();
        // 9) Assert values
        assertEquals(newName, actualName);
        assertEquals(newJob, actualJob);
    }

    @Test
    public void editExistingPerson() throws Exception {
        //click pencil icon for an existing person
        WebElement firstRow = driver.findElement(By.cssSelector("#listOfPeople > div.w3-padding-16:nth-child(1)"));
        String originalName = firstRow.findElement(By.cssSelector(".name")).getText().trim();
        String originalJob  = firstRow.findElement(By.cssSelector(".job")).getText().trim();
        firstRow.findElement(By.cssSelector(".editbtn")).click();
        //check values in "Name" and "Job" fields
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addEditPerson")));
        WebElement nameField = modal.findElement(By.id("name"));
        WebElement jobField  = modal.findElement(By.id("job"));

        assertEquals(originalName, nameField.getAttribute("value").trim());
        assertEquals(originalJob,  jobField.getAttribute("value").trim());
        //change "Job" field
        String newJob = "Senior " + originalJob;
        jobField.clear();
        jobField.sendKeys(newJob);
        //click "Edit"
        modal.findElement(By.id("modal_button")).click();
        //check that the person is updated in the list with new job
        wait.until(ExpectedConditions.invisibilityOf(modal));
        WebElement updatedRow = driver.findElement(By.cssSelector("#listOfPeople > div.w3-padding-16:nth-child(1)"));
        String updatedName = updatedRow.findElement(By.cssSelector(".name")).getText().trim();
        String updatedJob  = updatedRow.findElement(By.cssSelector(".job")).getText().trim();
        assertEquals(originalName, updatedName);
        assertEquals(newJob, updatedJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
        List<WebElement> before = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));
        int beforeSize = before.size();
        assertTrue(beforeSize > 0, "List should not be empty!");
        WebElement firstRow = before.get(0);
        String nameToRemove = firstRow.findElement(By.cssSelector(".name")).getText().trim();
        //click cross (x) icon for an existing person
        firstRow.findElement(By.cssSelector(".closebtn")).click();
        wait.until(d -> d.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).size() == beforeSize - 1);
        //check that the person is removed from the list
        List<WebElement> after = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));
        assertEquals(beforeSize - 1, after.size(), "Person was NOT removed!");
        boolean stillPresent = after.stream().anyMatch(row -> row.getText().contains(nameToRemove));
        assertFalse(stillPresent, "Removed person still exists in the list!");
    }

    @Test
    public void resetList() throws Exception {
        String[] expectedNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] expectedJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager", "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};
        List<WebElement> initial = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));
        assertEquals(10, initial.size(), "Initial list must contain 10 people");
        //modify the list in any way (add, edit or remove a person)
        WebElement firstRow = initial.get(0);
        firstRow.findElement(By.cssSelector(".closebtn")).click();
        //check that the list is modified
        wait.until(d -> d.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).size() == 9);
        List<WebElement> modified = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));
        assertEquals(9, modified.size(), "List was not modified!");
        //click "Reset List"
        driver.findElement(By.xpath("//button[normalize-space()='Reset List']")).click();
        wait.until(d -> d.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16")).size() == 10);
        List<WebElement> reset = driver.findElements(By.cssSelector("#listOfPeople > div.w3-padding-16"));
        assertEquals(10, reset.size(), "List was not reset to 10 people.");
        //check that the list is back to initial state with 10 original entries
        for (int i = 0; i < 10; i++) {
            WebElement rows = reset.get(i);
            String actualName = rows.findElement(By.cssSelector(".name")).getText().trim();
            String actualJob  = rows.findElement(By.cssSelector(".job")).getText().trim();

            assertEquals(expectedNames[i], actualName, "Name mismatch in row " + (i + 1));
            assertEquals(expectedJobs[i],  actualJob,  "Job mismatch in row " + (i + 1));
        }
    }
}
