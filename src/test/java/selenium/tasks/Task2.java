package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2 {
    WebDriver driver;
    WebDriverWait wait;

    // locator for all people
    By allPeople = By.cssSelector("#listOfPeople li");

    // method which is being run before each test
    @BeforeEach
    public void openPage() {
        // Initialize driver
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();

        // Create a wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    // method which is being run after each test
    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    // finds a person in the list by name
    public WebElement findPerson(String name) {
        return driver.findElement(By.xpath("//li[.//span[text()='" + name + "']]"));
    }

    // returns the job of a person with given name
    public String getJob(String name) {
        return findPerson(name).findElement(By.className("job")).getText();
    }

    // checks that the list contains the 10 original people with correct names and jobs
    public void checkInitialList() {
        assertEquals(10, driver.findElements(allPeople).size());

        assertEquals("Web Designer", getJob("Mike"));
        assertEquals("Support", getJob("Jill"));
        assertEquals("Accountant", getJob("Jane"));
        assertEquals("Software Engineer", getJob("John"));
        assertEquals("Product Manager", getJob("Sarah"));
        assertEquals("Data Analyst", getJob("Carlos"));
        assertEquals("UX Designer", getJob("Emily"));
        assertEquals("Project Manager", getJob("David"));
        assertEquals("QA Engineer", getJob("Maria"));
        assertEquals("DevOps Engineer", getJob("Alex"));
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

        checkInitialList();
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        String name = "Anna";
        String job = "Automation QA";

        driver.findElement(By.xpath("//button[text()='Add person']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // wait until the new person appears in the list
        wait.until(ExpectedConditions.numberOfElementsToBe(allPeople, 11));
        assertEquals(job, getJob(name));
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        String name = "Jane";
        String newJob = "Senior Accountant";

        findPerson(name).findElement(By.className("editbtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        assertEquals(name, nameInput.getDomProperty("value"));
        assertEquals("Accountant", jobInput.getDomProperty("value"));

        jobInput.clear();
        jobInput.sendKeys(newJob);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        // wait until the new job is shown in the list
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//li[.//span[text()='" + name + "']]//*[contains(@class,'job')]"), newJob));
        assertEquals(newJob, getJob(name));
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        String name = "John";

        findPerson(name).findElement(By.className("closebtn")).click();

        // wait until the person disappears from the list
        wait.until(ExpectedConditions.numberOfElementsToBe(allPeople, 9));
        assertEquals(0, driver.findElements(By.xpath("//li[.//span[text()='" + name + "']]")).size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        findPerson("Mike").findElement(By.className("closebtn")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(allPeople, 9));

        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(allPeople, 10));

        checkInitialList();
    }
}