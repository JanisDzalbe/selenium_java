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
        assertTrue(driver.findElement(By.xpath("//button[text()='Add person']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//button[text()='Add person']")).isEnabled());
        assertTrue(driver.findElement(By.xpath("//button[text()='Reset List']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//button[text()='Reset List']")).isEnabled());

        List<WebElement> list = driver.findElements(By.cssSelector("#listOfPeople li"));
        HashMap<String, String> namesJobs = new HashMap<>();
        namesJobs.put("Mike", "Web Designer");
        namesJobs.put("Jill", "Support");
        namesJobs.put("Jane", "Accountant");
        namesJobs.put("John", "Software Engineer");
        namesJobs.put("Sarah", "Product Manager");
        namesJobs.put("Carlos", "Data Analyst");
        namesJobs.put("Emily", "UX Designer");
        namesJobs.put("David", "Project Manager");
        namesJobs.put("Maria", "QA Engineer");
        namesJobs.put("Alex", "DevOps Engineer");
        assertEquals(10, list.size());
        for (WebElement l : list) {
            String name = l.findElement(By.className("name")).getText();
            String job = l.findElement(By.className("job")).getText();
            assertTrue(namesJobs.containsKey(name));
            assertEquals(namesJobs.get(name), job);
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
        driver.findElement((By.id("name"))).sendKeys("Oleksandr");
        driver.findElement((By.id("job"))).sendKeys("Java Developer");
        driver.findElement(By.id("modal_button")).click();
        assertEquals("Oleksandr", driver.findElement(By.xpath("//span[text()='Oleksandr']")).getText());
        assertEquals("Java Developer", driver.findElement(By.xpath("//span[text()='Java Developer']")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        driver.findElement(By.xpath("//*[@id='person9']")).findElement(By.className("editbtn")).click();
        assertEquals("Alex", driver.findElement(By.id("name")).getDomProperty("value"));
        assertEquals("DevOps Engineer", driver.findElement(By.id("job")).getDomProperty("value"));
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Front");
        driver.findElement(By.id("modal_button")).click();
        assertEquals("Alex", driver.findElement(By.xpath("//span[text()='Alex']")).getText());
        assertEquals("Front", driver.findElement(By.xpath("//span[text()='Front']")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        driver.findElement(By.xpath("//*[@id='person3']")).findElement(By.className("closebtn")).click();
        driver.findElements(By.xpath("//span[text()='John']")).size();
        assertEquals(0, driver.findElements(By.xpath("//span[text()='John']")).size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        driver.findElement(By.xpath("//*[@id='person9']")).findElement(By.className("editbtn")).click();
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Fired");
        driver.findElement(By.id("modal_button")).click();
        assertEquals("Fired", driver.findElement(By.xpath("//span[text()='Fired']")).getText());
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
        List<WebElement> list = driver.findElements(By.cssSelector("#listOfPeople li"));
        HashMap<String, String> namesJobs = new HashMap<>();
        namesJobs.put("Mike", "Web Designer");
        namesJobs.put("Jill", "Support");
        namesJobs.put("Jane", "Accountant");
        namesJobs.put("John", "Software Engineer");
        namesJobs.put("Sarah", "Product Manager");
        namesJobs.put("Carlos", "Data Analyst");
        namesJobs.put("Emily", "UX Designer");
        namesJobs.put("David", "Project Manager");
        namesJobs.put("Maria", "QA Engineer");
        namesJobs.put("Alex", "DevOps Engineer");
        assertEquals(10, list.size());
        for (WebElement l : list) {
            String name = l.findElement(By.className("name")).getText();
            String job = l.findElement(By.className("job")).getText();
            assertTrue(namesJobs.containsKey(name));
            assertEquals(namesJobs.get(name), job);
        }
    }
}
