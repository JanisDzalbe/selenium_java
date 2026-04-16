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

    private List<WebElement> peopli() {
        return driver.findElements(By.xpath("//li[starts-with(@id,'person')]"));
    }

    private WebElement findpeoplebyname(String name) {
        return driver.findElement(By.xpath("//li[starts-with(@id,'person')][.//*[text()='" + name + "']]"));
    }

    @Test
    public void initialPeopleList() throws Exception {
//         TODO:
//          check that "Add person" and "Reset List" buttons are displayed and enabled

        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addBtn.isDisplayed());
        assertTrue(addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed());
        assertTrue(resetBtn.isEnabled());

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

        List<WebElement> persons = peopli();
        assertEquals(10, persons.size());

        assertEquals("Web Designer", findpeoplebyname("Mike").findElement(By.className("job")).getText());
        assertEquals("Support", findpeoplebyname("Jill").findElement(By.className("job")).getText());
        assertEquals("Accountant", findpeoplebyname("Jane").findElement(By.className("job")).getText());
        assertEquals("Software Engineer", findpeoplebyname("John").findElement(By.className("job")).getText());
        assertEquals("Product Manager", findpeoplebyname("Sarah").findElement(By.className("job")).getText());
        assertEquals("Data Analyst", findpeoplebyname("Carlos").findElement(By.className("job")).getText());
        assertEquals("UX Designer", findpeoplebyname("Emily").findElement(By.className("job")).getText());
        assertEquals("Project Manager", findpeoplebyname("David").findElement(By.className("job")).getText());
        assertEquals("QA Engineer", findpeoplebyname("Maria").findElement(By.className("job")).getText());
        assertEquals("DevOps Engineer", findpeoplebyname("Alex").findElement(By.className("job")).getText());
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
        driver.findElement(By.id("addPersonBtn")).click();
//          fill "Name" and "Job" fields
//      add name "Edvards"
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Edvards");
//      add job
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Unemployed");
//          click "Add"
        driver.findElement(By.id("modal_button")).click();
//          check that new person is added to the list with correct name and job

        assertNotNull(findpeoplebyname("Edvards"));
        assertEquals("Unemployed", findpeoplebyname("Edvards").findElement(By.className("job")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
        driver.findElement(By.xpath("//span[contains(@onclick,'openModalForEditPersonWithJob(5)')]")).click();
//          check values in "Name" and "Job" fields
        assertEquals("Carlos", driver.findElement(By.id("name")).getDomProperty("value"));
        WebElement jobInput = driver.findElement(By.id("job"));
        assertEquals("Data Analyst", jobInput.getDomProperty("value"));
//          change "Job" field
        jobInput.clear();
        jobInput.sendKeys("Unemployed");
//          click "Edit"
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
//          check that the person is updated in the list with new job
        assertEquals("Unemployed",
                driver.findElement(By.id("person5")).findElement(By.className("job")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
        int peopleCountBefore = peopli().size();

        driver.findElement(By.xpath("//span[contains(@onclick,'deletePerson(0)')]")).click();
//          check that the person is removed from the list
        int peopleCountAfter = peopli().size();
        assertEquals(peopleCountBefore - 1, peopleCountAfter);
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//     modify the list in any way
        driver.findElement(By.xpath("//span[contains(@onclick,'deletePerson(0)')]")).click();

//     check that the list is modified
        assertEquals(9, peopli().size());

//     click "Reset List"
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

//     check that the list is back to initial state with 10 original entries
        assertEquals(10, peopli().size());

        assertEquals("Web Designer", findpeoplebyname("Mike").findElement(By.className("job")).getText());
        assertEquals("Support", findpeoplebyname("Jill").findElement(By.className("job")).getText());
        assertEquals("Accountant", findpeoplebyname("Jane").findElement(By.className("job")).getText());
        assertEquals("Software Engineer", findpeoplebyname("John").findElement(By.className("job")).getText());
        assertEquals("Product Manager", findpeoplebyname("Sarah").findElement(By.className("job")).getText());
        assertEquals("Data Analyst", findpeoplebyname("Carlos").findElement(By.className("job")).getText());
        assertEquals("UX Designer", findpeoplebyname("Emily").findElement(By.className("job")).getText());
        assertEquals("Project Manager", findpeoplebyname("David").findElement(By.className("job")).getText());
        assertEquals("QA Engineer", findpeoplebyname("Maria").findElement(By.className("job")).getText());
        assertEquals("DevOps Engineer", findpeoplebyname("Alex").findElement(By.className("job")).getText());
    }
}