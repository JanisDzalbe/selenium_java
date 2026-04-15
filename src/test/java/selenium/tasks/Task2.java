package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeEdgeDriver();
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

        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addBtn.isDisplayed());
        assertTrue(addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed());
        assertTrue(resetBtn.isEnabled());

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

        for (int i = 0; i < people.size(); i++) {
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
        driver.findElement(By.xpath("//button[text()='Add person']")).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add']"));

        String name = "Yusuf";
        String job = "Test Automation Intern";

        nameInput.sendKeys(name);
        jobInput.sendKeys(job);
        addBtn.click();

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        WebElement lastPerson = people.getLast();

        assertEquals(name, lastPerson.findElement(By.className("name")).getText());
        assertEquals(job, lastPerson.findElement(By.className("job")).getText());
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
        WebElement firstPerson = people.getFirst();

        String originalName = firstPerson.findElement(By.className("name")).getText();

        firstPerson.findElement(By.className("editbtn")).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        WebElement editBtn = driver.findElement(By.xpath("//button[text()='Edit']"));

        assertEquals(originalName, nameInput.getAttribute("value"));

        String newJob = "Automation and Software Engineer";
        jobInput.clear();
        jobInput.sendKeys(newJob);

        editBtn.click();

        people = driver.findElements(By.cssSelector("#listOfPeople li"));
        firstPerson = people.getFirst();

        assertEquals(newJob, firstPerson.findElement(By.className("job")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        int initialSize = people.size();

        WebElement firstPerson = people.getFirst();
        String removedName = firstPerson.findElement(By.className("name")).getText();

        firstPerson.findElement(By.className("closebtn")).click();

        people = driver.findElements(By.cssSelector("#listOfPeople li"));

        assertEquals(initialSize - 1, people.size());

        boolean exists = false;

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            if (name.equals(removedName)) {
                exists = true;
                break;
            }
        }

        assertFalse(exists);
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        people.getFirst().findElement(By.className("closebtn")).click();

        people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertTrue(people.size() < 10);

        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, people.size());
    }
}
