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
        WebElement addPersonBtn = driver.findElements(By.xpath("//button[text()='Add person']")).get(0);
        WebElement resetListBtn = driver.findElements(By.xpath("//button[text()='Reset List']")).get(0);

        assertTrue(addPersonBtn.isDisplayed());
        assertTrue(addPersonBtn.isEnabled());
        assertTrue(resetListBtn.isDisplayed());
        assertTrue(resetListBtn.isEnabled());

//        check list of people contains 10 entries with correct names and jobs
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

        List<WebElement> peopleList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(10, peopleList.size());

        String[] expectedNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] expectedJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager",
                "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

        for (int i = 0; i < peopleList.size(); i++) {
            assertEquals(expectedNames[i], peopleList.get(i).findElement(By.className("name")).getText());
            assertEquals(expectedJobs[i], peopleList.get(i).findElement(By.className("job")).getText());
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
        driver.findElements(By.xpath("//button[text()='Add person']")).get(0).click();

//          fill "Name" and "Job" fields
        String newName = "Filips";
        String newJob = "Forwarding agent";

        driver.findElement(By.id("name")).sendKeys(newName);
        driver.findElement(By.id("job")).sendKeys(newJob);

//          click "Add"
        driver.findElement(By.id("modal_button")).click();

//          check that new person is added to the list with correct name and job
        List<WebElement> peopleList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(11, peopleList.size());

        WebElement addedPerson = peopleList.get(10);
        assertEquals(newName, addedPerson.findElement(By.className("name")).getText());
        assertEquals(newJob, addedPerson.findElement(By.className("job")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          check values in "Name" and "Job" fields
        driver.findElement(By.cssSelector("#person0 .editbtn")).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        assertEquals("Mike", nameInput.getDomProperty("value"));
        assertEquals("Web Designer", jobInput.getDomProperty("value"));

//          change "Job" field
        String updatedJob = "General Designer";
        jobInput.clear();
        jobInput.sendKeys(updatedJob);

//          click "Edit"
        driver.findElement(By.id("modal_button")).click();

//          check that the person is updated in the list with new job
        WebElement editedPerson = driver.findElement(By.id("person0"));
        assertEquals("Mike", editedPerson.findElement(By.className("name")).getText());
        assertEquals(updatedJob, editedPerson.findElement(By.className("job")).getText());

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
        driver.findElement(By.cssSelector("#person0 .closebtn")).click();

//          check that the person is removed from the list
        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(9, personList.size());

        for (WebElement person : personList) {
            assertFalse(person.findElement(By.className("name")).getText().equals("Mike"));
        }
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
        driver.findElement(By.cssSelector("#person0 .closebtn")).click();

//          check that the list is modified
        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(9, personList.size());

//          click "Reset List"
        driver.findElements(By.xpath("//button[text()='Reset List']")).get(0).click();

//          check that the list is back to initial state with 10 original entries
        personList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(10, personList.size());

        String[] expectedNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] expectedJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager",
                "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

        for (int i = 0; i < personList.size(); i++) {
            assertEquals(expectedNames[i], personList.get(i).findElement(By.className("name")).getText());
            assertEquals(expectedJobs[i], personList.get(i).findElement(By.className("job")).getText());
        }
    }
}
