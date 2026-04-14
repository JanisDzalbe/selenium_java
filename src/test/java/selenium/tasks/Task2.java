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

    private final By addPersonButton = By.xpath("//button[text()='Add person']");
    private final By resetListButton = By.xpath("//button[text()='Reset List']");
    private final By nameSpans = By.className("name");
    private final By jobSpans = By.className("job");
    private final By deleteIcons = By.xpath("//span[contains(@onclick,'deletePerson')]");
    private final By editIcons = By.xpath("//span[contains(@onclick,'openModalForEditPersonWithJob')]");

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
        WebElement addBtn = driver.findElement(addPersonButton);
        WebElement resetBtn = driver.findElement(resetListButton);
        assertTrue(addBtn.isDisplayed());
        assertTrue(addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed());
        assertTrue(resetBtn.isEnabled());

        String[][] expected = {
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

        List<WebElement> names = driver.findElements(nameSpans);
        List<WebElement> jobs = driver.findElements(jobSpans);

        assertEquals(10, names.size(), "List should have 10 people");
        assertEquals(10, jobs.size(), "List should have 10 jobs");

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i][0], names.get(i).getText(), "Name at index " + i);
            assertEquals(expected[i][1], jobs.get(i).getText(), "Job at index " + i);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
        String newName = "Wajid";
        String newJob = "Test Automation Engineer";

        driver.findElement(addPersonButton).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        nameInput.clear();
        nameInput.sendKeys(newName);
        jobInput.clear();
        jobInput.sendKeys(newJob);

        driver.findElement(By.xpath("//button[text()='Add']")).click();

        List<WebElement> names = driver.findElements(nameSpans);
        List<WebElement> jobs = driver.findElements(jobSpans);

        assertEquals(11, names.size(), "List should now have 11 people");
        assertEquals(newName, names.get(names.size() - 1).getText());
        assertEquals(newJob, jobs.get(jobs.size() - 1).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
        int indexToEdit = 0;
        String expectedOldName = "Mike";
        String expectedOldJob = "Web Designer";
        String newJob = "Senior Web Designer";

        driver.findElements(editIcons).get(indexToEdit).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        assertEquals(expectedOldName, nameInput.getAttribute("value"));
        assertEquals(expectedOldJob, jobInput.getAttribute("value"));

        jobInput.clear();
        jobInput.sendKeys(newJob);

        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        List<WebElement> names = driver.findElements(nameSpans);
        List<WebElement> jobs = driver.findElements(jobSpans);
        assertEquals(expectedOldName, names.get(indexToEdit).getText());
        assertEquals(newJob, jobs.get(indexToEdit).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
        int indexToRemove = 0;
        String removedName = "Mike";

        driver.findElements(deleteIcons).get(indexToRemove).click();

        List<WebElement> names = driver.findElements(nameSpans);
        assertEquals(9, names.size(), "List should have 9 people after removal");
        for (WebElement name : names) {
            assertNotEquals(removedName, name.getText());
        }
    }

    @Test
    public void resetList() throws Exception {
        driver.findElements(deleteIcons).get(0).click();
        assertEquals(9, driver.findElements(nameSpans).size());

        driver.findElement(resetListButton).click();

        List<WebElement> names = driver.findElements(nameSpans);
        List<WebElement> jobs = driver.findElements(jobSpans);
        assertEquals(10, names.size());
        assertEquals("Mike", names.get(0).getText());
        assertEquals("Web Designer", jobs.get(0).getText());
        assertEquals("Alex", names.get(9).getText());
        assertEquals("DevOps Engineer", jobs.get(9).getText());
    }
}