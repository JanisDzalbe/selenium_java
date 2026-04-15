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
        WebElement addPerson = driver.findElement(By.xpath("//button[@id='addPersonBtn' and text()='Add person']"));
        WebElement resetList = driver.findElement(By.xpath("//button[@id='addPersonBtn' and text()='Reset List']"));
        assertTrue(addPerson.isDisplayed());
        assertTrue(addPerson.isEnabled());
        assertTrue(resetList.isDisplayed());
        assertTrue(resetList.isEnabled());


        List<WebElement> persons = driver.findElements(By.cssSelector("#listOfPeople li"));
        HashMap<String, String> people = new HashMap<>();
        people.put("Mike", "Web Designer");
        people.put("Jill", "Support");
        people.put("Jane", "Accountant");
        people.put("John", "Software Engineer");
        people.put("Sarah", "Product Manager");
        people.put("Carlos", "Data Analyst");
        people.put("Emily", "UX Designer");
        people.put("David", "Project Manager");
        people.put("Maria", "QA Engineer");
        people.put("Alex", "DevOps Engineer");
        assertEquals(10, persons.size());

        for (WebElement l : persons) {
            String name = l.findElement(By.className("name")).getText();
            String job = l.findElement(By.className("job")).getText();
            assertTrue(people.containsKey(name));
            assertEquals(people.get(name), job);

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

        driver.findElement(By.id("name")).sendKeys("MikeTyson");
        driver.findElement(By.id("job")).sendKeys("TeamLeader");

        driver.findElement(By.id("modal_button")).click();

        assertEquals("MikeTyson", driver.findElement(By.xpath("//span[text()='MikeTyson']")).getText());
        assertEquals("TeamLeader", driver.findElement(By.xpath("//span[text()='TeamLeader']")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        driver.findElement(By.id("person4")).findElement(By.className("editbtn")).click();

        assertEquals("Sarah", driver.findElement(By.id("name")).getDomProperty("value"));

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Product Review");

        driver.findElement(By.id("modal_button")).click();

        assertEquals("Sarah", driver.findElement(By.xpath("//span[text()='Sarah']")).getText());
        assertEquals("Product Review", driver.findElement(By.xpath("//span[text()='Product Review']")).getText());

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        driver.findElement(By.xpath("//*[@id='person8']")).findElement(By.className("closebtn")).click();

        driver.findElements(By.xpath("//span[text()='Maria']")).size();
        assertEquals(0, driver.findElements(By.xpath("//span[text()='Maria']")).size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
        driver.findElement(By.id("name")).sendKeys("Borat");
        driver.findElement(By.id("job")).sendKeys("Supervisor");
        driver.findElement(By.id("modal_button")).click();

        assertEquals("Borat", driver.findElement(By.xpath("//span[text()='Borat']")).getText());
        assertEquals("Supervisor", driver.findElement(By.xpath("//span[text()='Supervisor']")).getText());

        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        List<WebElement> persons = driver.findElements(By.cssSelector("#listOfPeople li"));
        HashMap<String, String> people = new HashMap<>();
        people.put("Mike", "Web Designer");
        people.put("Jill", "Support");
        people.put("Jane", "Accountant");
        people.put("John", "Software Engineer");
        people.put("Sarah", "Product Manager");
        people.put("Carlos", "Data Analyst");
        people.put("Emily", "UX Designer");
        people.put("David", "Project Manager");
        people.put("Maria", "QA Engineer");
        people.put("Alex", "DevOps Engineer");
        assertEquals(10, persons.size());

        for (WebElement l : persons) {
            String name = l.findElement(By.className("name")).getText();
            String job = l.findElement(By.className("job")).getText();
            assertTrue(people.containsKey(name));
            assertEquals(people.get(name), job);


        }
    }
}