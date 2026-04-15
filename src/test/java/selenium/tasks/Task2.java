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
import java.util.Arrays;
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
        WebElement addPersonButton = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Add person')]"));
        WebElement resetListButton = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Reset List')]"));
        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        List<String> expectedPeople = Arrays.asList(
                "Mike, Web Designer",
                "Jill, Support",
                "Jane, Accountant",
                "John, Software Engineer",
                "Sarah, Product Manager",
                "Carlos, Data Analyst",
                "Emily, UX Designer",
                "David, Project Manager",
                "Maria, QA Engineer",
                "Alex, DevOps Engineer"
        );
        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(10, people.size());
        List<String> actualPeople = new ArrayList<>();

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job = person.findElement(By.className("job")).getText().trim();

            actualPeople.add(name + ", " + job);
        }
        assertEquals(expectedPeople, actualPeople);
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        WebElement addPersonButton = driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Add person')]"));
        addPersonButton.click();

        String name = "Joseph";
        WebElement nameInput = driver.findElement(By.cssSelector(".w3-input[id='name']"));
        nameInput.sendKeys(name);

        String job = "Actor";
        WebElement jobInput = driver.findElement(By.cssSelector(".w3-input[id='job']"));
        jobInput.sendKeys(job);

        WebElement addButton = driver.findElement(By.id("modal_button"));
        addButton.click();

        List<WebElement> employees = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        for(WebElement person : employees) {
            String personName = person.findElement(By.xpath("./span[contains(@class,'name')]")).getText();
            if (personName.equals(name)) {
                String personJob = person.findElement(By.xpath(".//span[contains(@class, 'job')]")).getText();
                assertEquals(job, personJob);
                break;
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
        driver.findElement(By.xpath("//span[contains(@onclick,'openModalForEditPersonWithJob(0)')]")).click();
        WebElement name = driver.findElement(By.id("name"));
        WebElement job = driver.findElement(By.id("job"));
        assertEquals("Mike", name.getDomProperty("value"));
        assertEquals("Web Designer", job.getDomProperty("value"));
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Pilot");
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
        WebElement updatedjob = driver.findElement(By.id("person0"));
        assertEquals("Pilot", updatedjob.findElement(By.className("job")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        driver.findElements(By.xpath("//span[text()='Maria']"));
        driver.findElement(By.xpath("//span[contains(@onclick,'deletePerson(0)')]")).click();
        assertFalse(driver.findElements(By.xpath("//span[text()='Maria']")).isEmpty());

    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries\
        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        List<String> actualPeople = new ArrayList<>();

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job = person.findElement(By.className("job")).getText().trim();

            actualPeople.add(name + ", " + job);
        }
        int list =  driver.findElements(By.xpath("//li[starts-with(@id,'person')]")).size();
        driver.findElement(By.xpath("//span[contains(@onclick,'deletePerson(0)')]")).click();
        int listWithdDelete =  driver.findElements(By.xpath("//li[starts-with(@id,'person')]")).size();
        assertNotEquals(list, listWithdDelete);
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
        int listAfterReset = driver.findElements(By.xpath("//li[starts-with(@id,'person')]")).size();
        assertEquals(list, listAfterReset);
        List<WebElement> persons = driver.findElements(By.cssSelector("li[id^='person']"));
        assertEquals(10, persons.size());
        List<String> newActualPeople = new ArrayList<>();

        for (WebElement person : persons) {
            String name = person.findElement(By.className("name")).getText().trim();
            String job = person.findElement(By.className("job")).getText().trim();

            newActualPeople.add(name + ", " + job);
        }
        assertEquals(actualPeople,newActualPeople);
    }
}
