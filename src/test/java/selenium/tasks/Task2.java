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
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));

        assertEquals(10, personList.size());

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

        for(int i = 0; i < personList.size(); i++) {

            WebElement person = personList.get(i);

            String actualName = person.findElement(By.cssSelector(".name")).getText();
            String actualJob = person.findElement(By.cssSelector(".job")).getText();

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
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        addPersonButton.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        nameField.sendKeys("Katya");
        jobField.sendKeys("Administrator");

        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
        addButton.click();

        WebElement newPerson = driver.findElement(By.xpath("//*[contains(@class, 'name') and text()='Katya']/parent::*"));

        assertEquals("Katya", newPerson.findElement(By.className("name")).getText());
        assertEquals("Administrator", newPerson.findElement(By.className("job")).getText());

    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        WebElement pencilIcon = driver.findElement(By.cssSelector(".fa.fa-pencil"));
        pencilIcon.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        assertEquals("Mike", nameField.getAttribute("value"));
        assertEquals("Web Designer", jobField.getAttribute("value"));

        jobField.clear();
        jobField.sendKeys("Scrum Master");

        WebElement editButton = driver.findElement(By.xpath("//button[text()='Edit']"));
        editButton.click();

        WebElement mike = driver.findElement(By.xpath("//*[contains(@class, 'name') and text()='Mike']/parent::*"));
        assertEquals("Scrum Master", mike.findElement(By.className("job")).getText());

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        WebElement mike = driver.findElement(By.xpath("//*[contains(@class, 'name') and text()='Mike']/parent::*"));
        WebElement crossIcon = driver.findElement(By.xpath("//*[@id='person0']//*[contains(@class, 'closebtn')]"));
        crossIcon.click();

        List<WebElement> mikeAfterDelete = driver.findElements(By.xpath("//*[contains(@class, 'name') and text()='Mike']"));
        assertEquals(0, mikeAfterDelete.size());

    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        WebElement mike = driver.findElement(By.xpath("//*[contains(@class, 'name') and text()='Mike']/parent::*"));
        WebElement crossIcon = mike.findElement(By.cssSelector(".closebtn"));
        crossIcon.click();

        List<WebElement> mikeAfterDelete = driver.findElements(By.xpath("//*[contains(@class, 'name') and text()='Mike']"));
        assertEquals(0, mikeAfterDelete.size());

        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));
        resetListButton.click();

        List<WebElement> personList = driver.findElements(By.cssSelector("[id*='person']"));
        assertEquals(10, personList.size());

        WebElement mikeAfterReset = driver.findElement(By.xpath("//*[contains(@class, 'name') and text()='Mike']/parent::*"));
        assertTrue(mikeAfterReset.isDisplayed());

    }
}
