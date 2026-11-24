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
import java.util.List;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

        WebElement addButton = driver.findElement(By.xpath("(//button[normalize-space()='Add person'])[1]"));
        assertTrue(addButton.isDisplayed());
        assertTrue(addButton.isEnabled());

        WebElement resetButton = driver.findElement(By.xpath("(//button[normalize-space()='Reset List'])[1]"));
        assertTrue(resetButton.isDisplayed());
        assertTrue(resetButton.isEnabled());

        List<WebElement> people = driver.findElements(By.xpath("//ul[@id='listOfPeople']//li"));
        assertEquals(10, people.size());

        String[][] expectedPeople = {
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
        for (String[] person : expectedPeople) {

            WebElement name = driver.findElement(By.xpath("//ul[@id='listOfPeople']//span[contains(@class,'name') and normalize-space()='" + person[0] + "']"));
            WebElement job = driver.findElement(By.xpath("//ul[@id='listOfPeople']//li[.//span[contains(@class,'name') and normalize-space()='" + person[0] + "']]//span[contains(@class,'job')]"));


            assertEquals(person[0], name.getText());
            assertEquals(person[1], job.getText());
        }

    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job

        driver.findElement(By.xpath("(//button[text()='Add person'])[1]")).click();

        driver.findElement(By.id("name")).sendKeys("Kath");
        driver.findElement(By.id("job")).sendKeys("Tester");

        driver.findElement(By.id("modal_button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul[@id='listOfPeople']//li"), 11));

        WebElement newPerson = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and normalize-space()='Kath']]"));

        assertEquals("Kath", newPerson.findElement(By.className("name")).getText());
        assertEquals("Tester", newPerson.findElement(By.className("job")).getText());



    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        WebElement mike = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Mike']]"));
        mike.findElement(By.className("editbtn")).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        assertEquals("Mike", nameInput.getAttribute("value"));

        String originalJob = jobInput.getAttribute("value");
        assertNotNull(originalJob);
        assertFalse(originalJob.isEmpty());

        jobInput.clear();
        jobInput.sendKeys("Not a web designer");

        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        WebElement updatedMike = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Mike']]"));
        assertEquals("Not a web designer", updatedMike.findElement(By.className("job")).getText());


    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        WebElement mike = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Mike']]"));

        mike.findElement(By.className("closebtn")).click();

        assertTrue(driver.findElements(By.xpath("//li[.//span[contains(@class,'name') and text()='Mike']]")).isEmpty());

        assertEquals(9, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        WebElement mike = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Mike']]"));
        mike.findElement(By.className("closebtn")).click();

        assertEquals(9, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        assertEquals(10, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());
        assertFalse(driver.findElements(By.xpath("//li[.//span[contains(@class,'name') and text()='Mike']]")).isEmpty());
    }
}

