package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;
    private Map<String, String> employeeAndJob;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");

        employeeAndJob = new LinkedHashMap<>();

        employeeAndJob.put("Mike", "Web Designer");
        employeeAndJob.put("Jill", "Support");
        employeeAndJob.put("Jane", "Accountant");
        employeeAndJob.put("John", "Software Engineer");
        employeeAndJob.put("Sarah", "Product Manager");
        employeeAndJob.put("Carlos", "Data Analyst");
        employeeAndJob.put("Emily", "UX Designer");
        employeeAndJob.put("David", "Project Manager");
        employeeAndJob.put("Maria", "QA Engineer");
        employeeAndJob.put("Alex", "DevOps Engineer");
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
        List<WebElement> addPersonButton = driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Add person']"));
        List<WebElement> resetListButton = driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']"));

        assertTrue(addPersonButton.getFirst().isDisplayed());
        assertTrue(addPersonButton.getFirst().isEnabled());

        assertTrue(resetListButton.getFirst().isDisplayed());
        assertTrue(resetListButton.getFirst().isEnabled());

        List<WebElement> nameList = driver.findElements(By.cssSelector("[id*='person']"));
        assertEquals(employeeAndJob.size(), nameList.size());
        for (WebElement element : nameList) {
            String elementName = element.findElement(By.className("name")).getText();
            String elementJob  = element.findElement(By.className("job")).getText();
            assertTrue(employeeAndJob.containsKey(elementName));
            assertEquals(employeeAndJob.get(elementName), elementJob);
        }

    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        WebElement addPersonButton = driver.findElement(By.xpath("//*[@id='addPersonBtn' and text()='Add person']"));

        String expectedName = "Maksym";
        String expectedJobTitle = "QA Automation";

        addPersonButton.click();

        WebElement name = driver.findElement(By.id("name"));
        WebElement job = driver.findElement(By.id("job"));

        WebElement addButton = driver.findElement(By.xpath("//*[@id='modal_button' and text()='Add']"));

        name.clear();
        job.clear();

        name.sendKeys(expectedName);
        job.sendKeys(expectedJobTitle);

        addButton.click();

        List<WebElement> newPerson = driver.findElements(By.cssSelector("[id*='person']"));
        for (WebElement element : newPerson) {
            String elementName = element.findElement(By.className("name")).getText();
            String elementJob = element.findElement(By.className("job")).getText();
            if (elementName.equals(expectedName) && elementJob.equals(expectedJobTitle)) {
                assertEquals(expectedName, elementName);
                assertEquals(expectedJobTitle, elementJob);
            }
        }


    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        List<WebElement> newPerson = driver.findElements(By.cssSelector("[id*='person']"));
        newPerson.getFirst().findElement(By.xpath("//*[@class='fa fa-pencil']")).click();

        String expectedName = "Mike";
        String expectedJobForFirstCheck = "Web Designer";
        String expectedJobTitleForTheLastCheck = "Java Developer";

        WebElement name = driver.findElement(By.id("name"));
        WebElement job = driver.findElement(By.id("job"));

        assertEquals(expectedName, name.getDomProperty("value"));
        assertEquals(expectedJobForFirstCheck, job.getDomProperty("value"));

        job.clear();
        job.sendKeys(expectedJobTitleForTheLastCheck);

        WebElement addButton = driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']"));

        addButton.click();

        List<WebElement> newUpdatedPersons = driver.findElements(By.cssSelector("[id*='person']"));
        for (WebElement element : newUpdatedPersons) {
            if (element.findElement(By.className("name")).getText().equals(expectedName)) {
                assertEquals(expectedJobTitleForTheLastCheck, element.findElement(By.className("job")).getText());
            }
        }
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        List<WebElement> newPerson = driver.findElements(By.cssSelector("[id*='person']"));
        newPerson.getFirst().findElement(By.xpath("//*[contains(@class,'closebtn') and text()='×']")).click();

        String expectedName = "Mike";
        String expectedJobTitle = "Web Designer";

        List<WebElement> newUpdatedPersons = driver.findElements(By.cssSelector("[id*='person']"));
        for (WebElement element : newUpdatedPersons) {
            assertNotEquals(expectedName, element.findElement(By.className("name")).getText());
        }
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        List<WebElement> newPerson = driver.findElements(By.cssSelector("[id*='person']"));
        newPerson.getFirst().findElement(By.xpath("//*[contains(@class,'closebtn') and text()='×']")).click();

        String expectedName = "Mike";
        String expectedJobTitle = "Web Designer";

        List<WebElement> newUpdatedPersons = driver.findElements(By.cssSelector("[id*='person']"));
        for (WebElement element : newUpdatedPersons) {
            assertNotEquals(expectedName, element.findElement(By.className("name")).getText());
        }

        WebElement resetListButton = driver.findElement(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']"));

        resetListButton.click();

        List<WebElement> nameList = driver.findElements(By.cssSelector("[id*='person']"));
        assertEquals(employeeAndJob.size(), nameList.size());
        for (WebElement element : nameList) {
            String elementName = element.findElement(By.className("name")).getText();
            String elementJob  = element.findElement(By.className("job")).getText();
            assertTrue(employeeAndJob.containsKey(elementName));
            assertEquals(employeeAndJob.get(elementName), elementJob);
        }
    }
}
