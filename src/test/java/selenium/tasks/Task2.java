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

    // helper: get all person rows
    private List<WebElement> getPeopleRows() {
        return driver.findElements(By.xpath("//li[starts-with(@id,'person')]"));
    }

    // helper: find person row by name
    private WebElement findPersonRowByName(String name) {
        for (WebElement row : getPeopleRows()) {
            String currentName = row.findElement(By.className("name")).getText();
            if (currentName.equals(name)) {
                return row;
            }
        }
        return null;
    }

    // helper: convert list to "Name, Job"
    private List<String> getNameJobPairs() {
        List<String> pairs = new ArrayList<>();
        for (WebElement row : getPeopleRows()) {
            String name = row.findElement(By.className("name")).getText();
            String job = row.findElement(By.className("job")).getText();
            pairs.add(name + ", " + job);
        }
        return pairs;
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

        // find buttons by visible text
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));

        // check buttons are displayed and enabled
        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        // check that list contains 10 entries
        List<String> actualPeople = getNameJobPairs();
        assertEquals(10, actualPeople.size());

        // expected initial list
        List<String> expectedPeople = List.of(
                "Mike, Web Designer",
                "Jill, Support",
                "Jane, Accountant",
                "John, Software Engineer",
                "Sarah, Product Manager",
                "Carlos, Data Analyst",
                "Emily, UX Designer",
                "David, Project Manager",
                "Maria, QA Engineer",
                "Alex, DevOps Engineer"
        );

        // check exact list content
        assertEquals(expectedPeople, actualPeople);
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job

        String newName = "Olivia";
        String newJob = "Business Analyst";

        // click "Add person"
        driver.findElement(By.xpath("//button[text()='Add person']")).click();

        // fill "Name" and "Job" fields
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        nameField.clear();
        nameField.sendKeys(newName);

        jobField.clear();
        jobField.sendKeys(newJob);

        // click "Add"
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // check that new person is added
        WebElement newPersonRow = findPersonRowByName(newName);
        assertNotNull(newPersonRow);
        assertEquals(newJob, newPersonRow.findElement(By.className("job")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job

        String personName = "Jane";
        String oldJob = "Accountant";
        String newJob = "Senior Accountant";

        // find person row
        WebElement personRow = findPersonRowByName(personName);
        assertNotNull(personRow);

        // click pencil icon
        personRow.findElement(By.className("editbtn")).click();

        // check fields in modal
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        assertEquals(personName, nameField.getDomProperty("value"));
        assertEquals(oldJob, jobField.getDomProperty("value"));

        // change job field
        jobField.clear();
        jobField.sendKeys(newJob);

        // click "Edit"
        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        // check updated list
        WebElement updatedPersonRow = findPersonRowByName(personName);
        assertNotNull(updatedPersonRow);
        assertEquals(newJob, updatedPersonRow.findElement(By.className("job")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list

        String personToRemove = "Mike";

        // check person exists before removing
        WebElement personRow = findPersonRowByName(personToRemove);
        assertNotNull(personRow);

        int sizeBefore = getPeopleRows().size();

        // click cross icon
        personRow.findElement(By.className("closebtn")).click();

        int sizeAfter = getPeopleRows().size();

        // check that one person was removed
        assertEquals(sizeBefore - 1, sizeAfter);

        // check that removed person is no longer in list
        assertNull(findPersonRowByName(personToRemove));
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries

        // remove one person to modify list
        WebElement personRow = findPersonRowByName("Mike");
        assertNotNull(personRow);
        personRow.findElement(By.className("closebtn")).click();

        // check list is modified
        assertEquals(9, getPeopleRows().size());
        assertNull(findPersonRowByName("Mike"));

        // click "Reset List"
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        // expected initial list
        List<String> expectedPeople = List.of(
                "Mike, Web Designer",
                "Jill, Support",
                "Jane, Accountant",
                "John, Software Engineer",
                "Sarah, Product Manager",
                "Carlos, Data Analyst",
                "Emily, UX Designer",
                "David, Project Manager",
                "Maria, QA Engineer",
                "Alex, DevOps Engineer"
        );

        // check that list is restored
        List<String> actualPeople = getNameJobPairs();
        assertEquals(10, actualPeople.size());
        assertEquals(expectedPeople, actualPeople);
    }
}