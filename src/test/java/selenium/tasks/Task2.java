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
        WebElement addBtn = driver.findElement(By.id("addPersonBtn"));
        WebElement resetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addBtn.isDisplayed() && addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed() && resetBtn.isEnabled());

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, people.size());

        String[][] expected = {
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

        for (int i = 0; i < expected.length; i++) {
            String name = people.get(i).findElement(By.className("name")).getText();
            String job = people.get(i).findElement(By.className("job")).getText();
            assertEquals(expected[i][0], name);
            assertEquals(expected[i][1], job);
        }

    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        driver.findElement(By.id("addPersonBtn")).click();
        Thread.sleep(500);
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        String newName = "Test User";
        String newJob = "Tester";

        nameInput.sendKeys(newName);
        jobInput.sendKeys(newJob);

        driver.findElement(By.id("modal_button")).click();

        Thread.sleep(500);

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        WebElement lastPerson = people.get(people.size() - 1);

        String addedName = lastPerson.findElement(By.className("name")).getText();
        String addedJob = lastPerson.findElement(By.className("job")).getText();

        assertEquals(newName, addedName);
        assertEquals(newJob, addedJob);

    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        WebElement personToEdit = null;

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            if (name.equals("John")) {
                personToEdit = person;
                break;
            }
        }

        assertNotNull(personToEdit, "John person not found");
        personToEdit.findElement(By.className("editbtn")).click();

        Thread.sleep(500);
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        assertEquals("John", nameInput.getAttribute("value"));
        assertEquals("Software Engineer", jobInput.getAttribute("value"));

        String newJob = "Senior Software Engineer";
        jobInput.clear();
        jobInput.sendKeys(newJob);

        driver.findElement(By.id("modal_button")).click();

        Thread.sleep(500);
        people = driver.findElements(By.cssSelector("#listOfPeople li"));
        personToEdit = null;
        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            if (name.equals("John")) {
                personToEdit = person;
                break;
            }
        }

        assertNotNull(personToEdit, "Updated John person not found");
        String updatedJob = personToEdit.findElement(By.className("job")).getText();
        assertEquals(newJob, updatedJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        int initialSize = people.size();
        assertTrue(initialSize > 0, "Not Empty List");

        WebElement personToRemove = people.get(0);
        String nameToRemove = personToRemove.findElement(By.className("name")).getText();

        personToRemove.findElement(By.className("closebtn")).click();

        Thread.sleep(500);

        people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(initialSize - 1, people.size(), "Check for user removed");

        boolean personStillExists = people.stream()
                .anyMatch(p -> p.findElement(By.className("name")).getText().equals(nameToRemove));
        assertFalse(personStillExists, "Remained list of users");
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        int initialSize = people.size();
        assertEquals(10, initialSize, "This list if equal to 10");

        people.get(0).findElement(By.className("closebtn")).click();
        Thread.sleep(500);

        List<WebElement> modifiedPeople = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertNotEquals(initialSize, modifiedPeople.size(), "Changed List");

        driver.findElements(By.id("addPersonBtn")).get(1).click();
        Thread.sleep(500);

        List<WebElement> resetPeople = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(initialSize, resetPeople.size(), "Reverted List");

        String[] names = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] jobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager", "Data Analyst",
                "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

        for (int i = 0; i < 10; i++) {
            WebElement person = resetPeople.get(i);
            assertEquals(names[i], person.findElement(By.className("name")).getText());
            assertEquals(jobs[i], person.findElement(By.className("job")).getText());
        }
    }
}
