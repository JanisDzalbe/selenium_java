package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // this approach works if there are no duplicate names (or no persons with two jobs?)
        // I choose it because it works nicely even if the list of the people and their jobs is shuffled

        Map<String, String> peopleAndJobs = Map.of(
                "Mike", "Web Designer",
                "Jill", "Support",
                "Jane", "Accountant",
                "John", "Software Engineer",
                "Sarah", "Product Manager",
                "Carlos", "Data Analyst",
                "Emily", "UX Designer",
                "David", "Project Manager",
                "Maria", "QA Engineer",
                "Alex", "DevOps Engineer"
        );

        WebElement addPersonButton = driver.findElement(By.xpath("//button[@id='addPersonBtn' and text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[@id='addPersonBtn' and text()='Reset List']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        assertEquals(10, elements.size());

        Map<String, String> peopleAndJobsOnWebsite = new HashMap<>();
        for (WebElement element: elements) {
            String name = element.findElement(By.className("name")).getText();
            String job = element.findElement(By.className("job")).getText();
            peopleAndJobsOnWebsite.put(name, job);
        }
        assertEquals(peopleAndJobs, peopleAndJobsOnWebsite);

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
    }

    @Test
    public void addNewPerson() throws Exception {

        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        addPersonButton.click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        nameInput.sendKeys("Ilmars");
        jobInput.sendKeys("Junior Tester");

        WebElement addButton = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Add']"));
        addButton.click();

        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        String name = elements.getLast().findElement(By.className("name")).getText();
        String job = elements.getLast().findElement(By.className("job")).getText();

        assertEquals(11, elements.size());
        assertEquals("Ilmars", name);
        assertEquals("Junior Tester", job);

//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
    }

    @Test
    public void editExistingPerson() throws Exception {
        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        WebElement element = elements.get(2);
        WebElement pencil = element.findElement(By.className("fa-pencil"));
        pencil.click();

        assertEquals("Jane", driver.findElement(By.id("name")).getDomProperty("value"));
        assertEquals("Accountant", driver.findElement(By.id("job")).getDomProperty("value"));
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("CEO");

        WebElement editButton = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Edit']"));
        editButton.click();

        elements = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        element = elements.get(2);
        assertEquals("Jane", element.findElement(By.className("name")).getText());
        assertEquals("CEO", element.findElement(By.className("job")).getText());

//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
    }

    @Test
    public void removeExistingPerson() throws Exception {

        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        WebElement element = elements.getFirst(); // deleting Mike
        WebElement deletionElement = element.findElement(By.className("closebtn"));
        deletionElement.click();

        elements = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        for (WebElement e : elements) {
            assertNotEquals("Mike", e.findElement(By.className("name")).getText());
        }

//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
    }

    @Test
    public void resetList() throws Exception {

        removeExistingPerson(); // it removes Mike from the list and checks that Mike is no longer in the list
        WebElement resetListButton = driver.findElement(By.xpath("//button[@id='addPersonBtn' and text()='Reset List']"));
        resetListButton.click();
        initialPeopleList();

//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
    }
}
