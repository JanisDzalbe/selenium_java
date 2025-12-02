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

import static org.junit.jupiter.api.Assertions.*;

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

        WebElement addButton = driver.findElement(By.xpath("(//button[text()='Add person'])"));
        WebElement resetButton = driver.findElement(By.xpath("(//button[text()='Reset List'])"));

        assertTrue(addButton.isDisplayed());
        assertTrue(addButton.isEnabled());

        assertTrue(resetButton.isDisplayed());
        assertTrue(resetButton.isEnabled());

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        assertEquals(10, people.size());

        String[] expectedNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};

        String[] expectedJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager",
                "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

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
        int initialCount = driver.findElements(By.className("w3-padding-16")).size();

        WebElement addPersonButton = driver.findElement(By.xpath("(//button[text()='Add person'])"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        addPersonButton.click();

        WebElement name = driver.findElement(By.id("name"));
        WebElement job = driver.findElement(By.id("job"));
        WebElement addModalButton = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Add']"));
        assertTrue(name.isDisplayed());
        assertTrue(job.isDisplayed());
        assertTrue(addModalButton.isDisplayed());
        assertTrue(addModalButton.isEnabled());

        name.sendKeys("Tatjana");
        job.sendKeys("Tester");

        addModalButton.click();

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));

        assertEquals(initialCount + 1, people.size());

        WebElement addedPerson = people.get(people.size() - 1);

        String actualName = addedPerson.findElement(By.className("name")).getText();
        String actualJob = addedPerson.findElement(By.className("job")).getText();

        assertEquals("Tatjana", actualName);
        assertEquals("Tester", actualJob);
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        WebElement firstPerson = people.get(0);
        WebElement firstPersonName = firstPerson.findElement(By.className("name"));
        WebElement firstPersonJob = firstPerson.findElement(By.className("job"));

        String firstPersonNameText = firstPersonName.getText();
        String firstPersonJobText  = firstPersonJob.getText();

        WebElement firstPersonEditButton = firstPerson.findElement(By.className("editbtn"));
        assertTrue(firstPersonEditButton.isDisplayed());
        assertTrue(firstPersonEditButton.isEnabled());
        firstPersonEditButton.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField  = driver.findElement(By.id("job"));

        assertTrue(nameField.isDisplayed());
        assertTrue(jobField.isDisplayed());

        assertEquals(firstPersonNameText, nameField.getAttribute("value"));
        assertEquals(firstPersonJobText, jobField.getAttribute("value"));

        String firstPersonNewJob = "Web Developer";
        jobField.clear();
        jobField.sendKeys(firstPersonNewJob);

        assertNotEquals(firstPersonNewJob, firstPersonJobText);
        assertEquals(firstPersonNewJob, jobField.getAttribute("value"));

        WebElement editButton = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Edit']"));
        assertTrue(editButton.isDisplayed());
        assertTrue(editButton.isEnabled());
        editButton.click();

        WebElement personUpdated = null;
        List<WebElement> peopleUpdated = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : peopleUpdated) {
            String nameText = person.findElement(By.className("name")).getText();
            if (nameText.equals(firstPersonNameText)) {
                personUpdated = person;
                break;
            }
        }

        String actualUpdatedJob = personUpdated.findElement(By.className("job")).getText();
        assertEquals(firstPersonNewJob, actualUpdatedJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        int initialCount = people.size();
        assertTrue(initialCount > 0);

        WebElement firstPerson = people.get(0);
        String removedPersonName = firstPerson.findElement(By.className("name")).getText();

        WebElement deleteButton = firstPerson.findElement(By.className("closebtn"));
        assertTrue(deleteButton.isDisplayed());
        assertTrue(deleteButton.isEnabled());
        deleteButton.click();

        List<WebElement> peopleUpdated = driver.findElements(By.className("w3-padding-16"));
        int newCount = peopleUpdated.size();

        assertEquals(initialCount - 1, newCount);

        boolean personStillExists = false;
        for (WebElement person : peopleUpdated) {
            String nameText = person.findElement(By.className("name")).getText();
            if (nameText.equals(removedPersonName)) {
                personStillExists = true;
                break;
            }
        }
        assertFalse(personStillExists);
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        List<String> originalList = new ArrayList<>();

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job  = person.findElement(By.className("job")).getText().trim();
            originalList.add(name + " | " + job);
        }

        WebElement firstPerson = people.get(0);

        WebElement deleteButton = firstPerson.findElement(By.className("closebtn"));
        assertTrue(deleteButton.isDisplayed());
        assertTrue(deleteButton.isEnabled());
        deleteButton.click();

        List<WebElement> peopleUpdated = driver.findElements(By.className("w3-padding-16"));
        List<String> modifiedList = new ArrayList<>();

        for (WebElement person : peopleUpdated) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job  = person.findElement(By.className("job")).getText().trim();
            modifiedList.add(name + " | " + job);
        }

        assertNotEquals(originalList, modifiedList);
        assertNotEquals(originalList.size(), modifiedList.size());

        WebElement resetButton = driver.findElement(By.xpath("//button[text()='Reset List']"));
        assertTrue(resetButton.isDisplayed());
        assertTrue(resetButton.isEnabled());
        resetButton.click();

        List<WebElement> peopleAfterReset = driver.findElements(By.className("w3-padding-16"));

        List<String> resetList = new ArrayList<>();

        for (WebElement person : peopleAfterReset) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job  = person.findElement(By.className("job")).getText().trim();
            resetList.add(name + " | " + job);
        }

        assertEquals(originalList, resetList);
        assertEquals(originalList.size(), resetList.size());
    }
}
