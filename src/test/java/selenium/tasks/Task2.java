package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.HashMap;
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
            assertTrue(driver.findElement(By.xpath("//*[@onclick = 'openModalForAddPersonWithJob()']")).isDisplayed());
            assertTrue( driver.findElement(By.xpath("//*[@onclick = 'openModalForAddPersonWithJob()']")).isEnabled());
            assertTrue(driver.findElement(By.xpath("//*[@onclick = 'resetListOfPeople()']")).isDisplayed());
            assertTrue( driver.findElement(By.xpath("//*[@onclick = 'resetListOfPeople()']")).isEnabled());
//          check list of people contains 10 entries with correct names and jobs
            List<WebElement> peoplenames = driver.findElements(By.className("name"));
            List<WebElement> peoplejobs = driver.findElements(By.className("job"));
            HashMap peoplemap = new HashMap();
            for(int i = 0; i < peoplejobs.size(); i++) {
                String name = peoplenames.get(i).getText();
                String job = peoplejobs.get(i).getText();
                peoplemap.put(name, name +", " + job);
            }

//        Mike, Web Designer
            assertEquals("Mike, Web Designer", peoplemap.get("Mike"));
//        Jill, Support
            assertEquals("Jill, Support", peoplemap.get("Jill"));
//        Jane, Accountant
            assertEquals("Jane, Accountant", peoplemap.get("Jane"));
//        John, Software Engineer
            assertEquals("John, Software Engineer", peoplemap.get("John"));
//        Sarah, Product Manager
            assertEquals("Sarah, Product Manager", peoplemap.get("Sarah"));
//        Carlos, Data Analyst
            assertEquals("Carlos, Data Analyst", peoplemap.get("Carlos"));
//        Emily, UX Designer
            assertEquals("Emily, UX Designer", peoplemap.get("Emily"));
//        David, Project Manager
            assertEquals("David, Project Manager", peoplemap.get("David"));
//        Maria, QA Engineer
            assertEquals("Maria, QA Engineer", peoplemap.get("Maria"));
//        Alex, DevOps Engineer
            assertEquals("Alex, DevOps Engineer",  peoplemap.get("Alex"));
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
            driver.findElement(By.id("addPersonBtn")).click();
//          fill "Name" and "Job" fields
            WebElement name = driver.findElement(By.id("name"));
            WebElement job = driver.findElement(By.id("job"));
            String jobname = "Boss";
            String personname = "Test";
            name.sendKeys(personname);
            job.sendKeys(jobname);
//          click "Add"
            driver.findElement(By.xpath("//*[contains(@onclick, 'addPersonWithJobToList')]")).click();

//          check that new person is added to the list with correct name and job
            List<WebElement> peoplenames = driver.findElements(By.className("name"));
            List<WebElement> peoplejobs = driver.findElements(By.className("job"));
            HashMap peoplemap = new HashMap();
            for(int i = 0; i < peoplejobs.size(); i++) {
                String name1 = peoplenames.get(i).getText();
                String job1 = peoplejobs.get(i).getText();
                peoplemap.put(name1, name1 +", " + job1);
            }
        assertEquals(personname + ", " + jobname, peoplemap.get(personname));
        assertEquals(11, peoplenames.size());
        assertEquals(11, peoplejobs.size());

    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
            driver.findElement(By.xpath(    "//*[@onclick = 'openModalForEditPersonWithJob(0)']")).click();
//          check values in "Name" and "Job" fields
            WebElement name = driver.findElement(By.id("name"));
            WebElement job = driver.findElement(By.id("job"));
            assertEquals("Mike", name.getAttribute("value"));
            assertEquals("Web Designer", job.getAttribute("value"));
            String jobname = "Test";
//          change "Job" field
            job.clear();
            job.sendKeys(jobname);
//          click "Edit"
            driver.findElement(By.xpath("//*[@onclick = 'editPersonWithJob(0)']")).click();
//          check that the person is updated in the list with new job
            List<WebElement> peoplenames = driver.findElements(By.className("name"));
            List<WebElement> peoplejobs = driver.findElements(By.className("job"));
            assertEquals("Mike", peoplenames.get(0).getText());
            assertEquals(jobname, peoplejobs.get(0).getText());

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
            String removedName = driver.findElement(By.cssSelector("#person0 .name")).getText();
            driver.findElement(By.xpath("//*[contains(@onclick, 'deletePerson(0)')]")).click();
//          check that the person is removed from the list
            List<WebElement> peoplenames = driver.findElements(By.className("name"));
            List<WebElement> peoplejobs = driver.findElements(By.className("job"));
            assertEquals(81,  peoplejobs.size() * peoplenames.size()); // instead of two assertEquals only two numbers that can give 81 when mult are 9s
            for (WebElement name : peoplenames) {
                assertNotEquals(removedName, name.getText());
            }

    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
            driver.findElement(By.id("addPersonBtn")).click();
            WebElement name = driver.findElement(By.id("name"));
            WebElement job = driver.findElement(By.id("job"));
            String jobname = "Boss";
            String personname = "Test";
            name.sendKeys(personname);
            job.sendKeys(jobname);
            driver.findElement(By.xpath("//*[contains(@onclick, 'addPersonWithJobToList')]")).click();

            List<WebElement> peoplenames = driver.findElements(By.className("name"));
            List<WebElement> peoplejobs = driver.findElements(By.className("job"));
            HashMap peoplemap = new HashMap();
            for(int i = 0; i < peoplejobs.size(); i++) {
                String name1 = peoplenames.get(i).getText();
                String job1 = peoplejobs.get(i).getText();
                peoplemap.put(name1, name1 +", " + job1);
            }

//          check that the list is modified
            assertEquals(personname + ", " + jobname, peoplemap.get(personname));
            assertEquals(11, peoplenames.size());
            assertEquals(11, peoplejobs.size());
//          click "Reset List"
            driver.findElement(By.xpath("//*[contains(@onclick, 'resetListOfPeople')]")).click();
//          check that the list is back to initial state with 10 original entries
             peoplenames = driver.findElements(By.className("name"));
             peoplejobs = driver.findElements(By.className("job"));
            assertEquals(10, peoplenames.size());
            assertEquals(10, peoplejobs.size());


    }
}
