package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<String> people = new ArrayList<>(Arrays.asList(
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
        ));

        WebElement addPersonButton = driver.findElement(
                By.xpath("//*[@id='addPersonBtn' and contains(text(),'Add person')]"));
        WebElement resetListButton = driver.findElement(
                By.xpath("//*[@id='addPersonBtn' and contains(text(),'Reset List')]"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        List<WebElement> employees = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        for (int i = 0; i < employees.size(); i++) {
            String name = employees.get(i).findElement(By.xpath("./span[contains(@class, 'name')]")).getText();
            String job = employees.get(i).findElement(By.xpath("./span[contains(@class, 'job')]")).getText();

            String actual = name + ", " + job;
            assertEquals(people.get(i), actual);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        WebElement addPersonButton = driver.findElement(
                By.xpath("//*[@id='addPersonBtn' and contains(text(),'Add person')]"));
        addPersonButton.click();

        String name = "Erik";
        WebElement nameInput = driver.findElement(By.cssSelector(".w3-input[id='name']"));
        nameInput.sendKeys(name);

        String job = "Developer";
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
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        String newJob = "Head of Support";

        WebElement person1 = driver.findElement(By.id("person1"));
        person1.findElement(By.xpath(".//span[contains(@class, 'editbtn')]")).click();

        WebElement nameInput = driver.findElement(By.cssSelector(".w3-input[id='name']"));
        WebElement jobInput = driver.findElement(By.cssSelector(".w3-input[id='job']"));

        assertEquals("Jill", nameInput.getAttribute("value"));
        assertEquals("Support", jobInput.getAttribute("value"));

        jobInput.clear();
        jobInput.sendKeys(newJob);

        driver.findElement(By.id("modal_button")).click();

        WebElement updatedPerson = driver.findElement(By.id("person1"));
        String updatedJob = updatedPerson.findElement(By.cssSelector(".job")).getText();

        assertEquals(newJob, updatedJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        WebElement person1 = driver.findElement(By.id("person1"));
        String removedName = person1.findElement(By.xpath(".//span[contains(@class, 'name')]")).getText();
        WebElement removeButton = person1.findElement(By.xpath(".//span[contains(@class,'closebtn')and text()='×']"));
        removeButton.click();

        List<WebElement> deletedPerson = driver.findElements(By.id("person1"));
        assertEquals(0, deletedPerson.size());

        List<WebElement> names = driver.findElements(By.cssSelector(".name"));
        for (WebElement n : names) {
            assertNotEquals(removedName, n.getText());
        }
    }

    @Test
    public void resetList() throws Exception {
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        int originalSize = driver.findElements(By.xpath("//li[contains(@id,'person')]")).size();
        driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Add person')]")).click();

        driver.findElement(By.cssSelector(".w3-input[id='name']")).sendKeys("TestUser");
        driver.findElement(By.cssSelector(".w3-input[id='job']")).sendKeys("Tester");

        driver.findElement(By.id("modal_button")).click();

        List<WebElement> modifiedList = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        assertNotEquals(originalSize, modifiedList.size());
        assertEquals(11, modifiedList.size());

        driver.findElement(By.xpath("//*[@id='addPersonBtn' and contains(text(),'Reset List')]")).click();

        List<WebElement> resetList = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        assertEquals(originalSize, resetList.size());
    }
}
