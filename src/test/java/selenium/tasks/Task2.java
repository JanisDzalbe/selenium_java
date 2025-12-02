package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;
    private static WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");

        wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    private static Map<String, String> getInitialList() {
        // should convert to List and implement duplicate checks accordingly
        // as this will not work if the name repeats - key must be unique
        Map<String, String> initialList = new HashMap<>();
        initialList.put("Mike", "Web Designer");
        initialList.put("Jill", "Support");
        initialList.put("Jane", "Accountant");
        initialList.put("John", "Software Engineer");
        initialList.put("Sarah", "Product Manager");
        initialList.put("Carlos", "Data Analyst");
        initialList.put("Emily", "UX Designer");
        initialList.put("David", "Project Manager");
        initialList.put("Maria", "QA Engineer");
        initialList.put("Alex", "DevOps Engineer");
        return initialList;
    }

    @Test
    public void initialPeopleList() throws Exception {
        Map<String, String> initialList = getInitialList();

//          check that "Add person" and "Reset List" buttons are displayed and enabled
        assertTrue(driver.findElement(By.xpath("//button[text()='Reset List']")).isEnabled());
        assertTrue(driver.findElement(By.xpath("//button[text()='Add person']")).isEnabled());

//          check list of people contains 10 entries with correct names and jobs
        List<WebElement> allPeople = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        assertEquals(initialList.size(), allPeople.size());

        for(WebElement person : allPeople){
            String name = person.findElement(By.xpath(".//*[contains(@class, 'name')]")).getText();
            String job = person.findElement(By.xpath(".//*[@class='job']")).getText();

            assertTrue(initialList.containsKey(name));
            assertEquals(initialList.get(name), job);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//          click "Add person"
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
        wait.until(ExpectedConditions.urlToBe("https://janisdzalbe.github.io/example-site/tasks/enter_a_new_person_with_a_job.html"));
//          fill "Name" and "Job" fields
        String name = "Mariss";
        String job = "Test Engineer";
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("job")).sendKeys(job);
//          click "Add"
        driver.findElement(By.xpath("//button[text()='Add']")).click();
//          check that new person is added to the list with correct name and job
        wait.until(ExpectedConditions.urlToBe("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs.html"));
        WebElement newEntry = driver.findElement(By.xpath("//li[*/text()='"+ name +"']"));
        assertEquals(name, newEntry.findElement(By.xpath(".//*[contains(@class, 'name')]")).getText());
        assertEquals(job, newEntry.findElement(By.xpath(".//*[@class='job']")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
    }

    @Test
    public void removeExistingPerson() throws Exception {
//          click cross (x) icon for an existing person
        Map<String, String> peopleList = getInitialList();
        String nameToRemove = peopleList.keySet().stream()  // randomly selects a name from the people list
                .skip(new Random().nextInt(peopleList.size()))
                .findFirst().get();
        System.out.println(nameToRemove);

        WebElement entryToDelete = driver.findElement(By.xpath("//li[*/text()='"+ nameToRemove +"']"));
        entryToDelete.findElement(By.xpath(".//*[contains(text(), '×')]")).click();
//          check that the person is removed from the list
        // could just check if findElement throws an NoSuchElementException?

        peopleList.remove(nameToRemove);

        wait.until(ExpectedConditions.stalenessOf(entryToDelete));
        List<WebElement> allPeople = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        assertEquals(peopleList.size(), allPeople.size());

        for(WebElement person : allPeople){
            String name = person.findElement(By.xpath(".//*[contains(@class, 'name')]")).getText();
            String job = person.findElement(By.xpath(".//*[@class='job']")).getText();

            assertTrue(peopleList.containsKey(name));
            assertEquals(peopleList.get(name), job);
        }
    }

    @Test
    public void resetList() throws Exception {
//          modify the list in any way (add, edit or remove a person)
        Map<String, String> initialList = getInitialList();
        String nameToRemove = initialList.keySet().stream()  // randomly selects a name from the people list
                .skip(new Random().nextInt(initialList.size()))
                .findFirst().get();
        System.out.println(nameToRemove);

        WebElement entryToDelete = driver.findElement(By.xpath("//li[*/text()='"+ nameToRemove +"']"));
        entryToDelete.findElement(By.xpath(".//*[contains(text(), '×')]")).click();

//          check that the list is modified
        wait.until(ExpectedConditions.stalenessOf(entryToDelete));
        List<WebElement> allPeople = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        assertNotEquals(initialList.size(), allPeople.size());

//          click "Reset List"
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

//          check that the list is back to initial state with 10 original entries
        wait.until(ExpectedConditions.stalenessOf(allPeople.getFirst()));
        allPeople = driver.findElements(By.xpath("//li[contains(@id, 'person')]"));
        assertEquals(initialList.size(), allPeople.size());

        for(WebElement person : allPeople){
            String name = person.findElement(By.xpath(".//*[contains(@class, 'name')]")).getText();
            String job = person.findElement(By.xpath(".//*[@class='job']")).getText();

            assertTrue(initialList.containsKey(name));
            assertEquals(initialList.get(name), job);
        }
    }
}
