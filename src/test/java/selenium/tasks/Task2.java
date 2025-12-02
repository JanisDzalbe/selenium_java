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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        WebElement addPersonButton = driver.findElement(By.xpath("//*[@id='addPersonBtn' and text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(resetListButton.isDisplayed());

        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isEnabled());


        List<WebElement> allPersons = driver.findElements(By.className("w3-padding-16"));
        assertEquals(10, allPersons.size());

        String personName1 = "Mike";
        String personName2 = "Jill";
        String personName3 = "Jane";
        String personName4 = "John";
        String personName5 = "Sarah";
        String personName6 = "Carlos";
        String personName7 = "Emily";
        String personName8 = "David";
        String personName9 = "Maria";
        String personName10 = "Alex";

        String personJob1 = "Web Designer";
        String personJob2 = "Support";
        String personJob3 = "Accountant";
        String personJob4 = "Software Engineer";
        String personJob5 = "Product Manager";
        String personJob6 = "Data Analyst";
        String personJob7 = "UX Designer";
        String personJob8 = "Project Manager";
        String personJob9 = "QA Engineer";
        String personJob10 = "DevOps Engineer";

        assertEquals(personName1, driver.findElement(By.cssSelector("#person0 .name")).getText());
        assertEquals(personName2, driver.findElement(By.cssSelector("#person1 .name")).getText());
        assertEquals(personName3, driver.findElement(By.cssSelector("#person2 .name")).getText());
        assertEquals(personName4, driver.findElement(By.cssSelector("#person3 .name")).getText());
        assertEquals(personName5, driver.findElement(By.cssSelector("#person4 .name")).getText());
        assertEquals(personName6, driver.findElement(By.cssSelector("#person5 .name")).getText());
        assertEquals(personName7, driver.findElement(By.cssSelector("#person6 .name")).getText());
        assertEquals(personName8, driver.findElement(By.cssSelector("#person7 .name")).getText());
        assertEquals(personName9, driver.findElement(By.cssSelector("#person8 .name")).getText());
        assertEquals(personName10, driver.findElement(By.cssSelector("#person9 .name")).getText());

        assertEquals(personJob1, driver.findElement(By.cssSelector("#person0 .job")).getText());
        assertEquals(personJob2, driver.findElement(By.cssSelector("#person1 .job")).getText());
        assertEquals(personJob3, driver.findElement(By.cssSelector("#person2 .job")).getText());
        assertEquals(personJob4, driver.findElement(By.cssSelector("#person3 .job")).getText());
        assertEquals(personJob5, driver.findElement(By.cssSelector("#person4 .job")).getText());
        assertEquals(personJob6, driver.findElement(By.cssSelector("#person5 .job")).getText());
        assertEquals(personJob7, driver.findElement(By.cssSelector("#person6 .job")).getText());
        assertEquals(personJob8, driver.findElement(By.cssSelector("#person7 .job")).getText());
        assertEquals(personJob9, driver.findElement(By.cssSelector("#person8 .job")).getText());
        assertEquals(personJob10, driver.findElement(By.cssSelector("#person9 .job")).getText());
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        driver.findElement(By.xpath("//*[@id='addPersonBtn' and text()='Add person']")).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        String addedName = "Kristiana";
        String addedJob = "Tester";

        nameInput.sendKeys(addedName);
        jobInput.sendKeys(addedJob);
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Add']")).click();

        WebElement addedPerson = driver.findElement(By.id("person10"));
        assertTrue(addedPerson.isDisplayed());

        assertEquals(addedName, driver.findElement(By.cssSelector("#person10 .name")).getText());
        assertEquals(addedJob, driver.findElement(By.cssSelector("#person10 .job")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        driver.findElement(By.cssSelector("#person0 .editbtn")).click();

        String originalName = "Mike";
        String originalJob = "Web Designer";

        assertEquals(originalName, driver.findElement(By.id("name")).getAttribute("value"));
        assertEquals(originalJob, driver.findElement(By.id("job")).getAttribute("value"));

        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        String newJob = "Cook";
        jobInput.sendKeys(newJob);
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']")).click();

        assertEquals(newJob, driver.findElement(By.cssSelector("#person0 .job")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        driver.findElement(By.cssSelector("#person0 .closebtn")).click();

        List<WebElement> person1 = driver.findElements(By.cssSelector("#person0"));

        assertTrue(person1.isEmpty());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        driver.findElement(By.xpath("//*[@id='addPersonBtn' and text()='Add person']")).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        String addedName = "Anna";
        String addedJob = "Manager";

        nameInput.sendKeys(addedName);
        jobInput.sendKeys(addedJob);
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Add']")).click();

        WebElement addedPerson = driver.findElement(By.id("person10"));
        assertTrue(addedPerson.isDisplayed());

        assertEquals(addedName, driver.findElement(By.cssSelector("#person10 .name")).getText());
        assertEquals(addedJob, driver.findElement(By.cssSelector("#person10 .job")).getText());

        driver.findElement(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']")).click();

        List<WebElement> allPersons = driver.findElements(By.className("w3-padding-16"));
        assertEquals(10, allPersons.size());

        String personName1 = "Mike";
        String personName2 = "Jill";
        String personName3 = "Jane";
        String personName4 = "John";
        String personName5 = "Sarah";
        String personName6 = "Carlos";
        String personName7 = "Emily";
        String personName8 = "David";
        String personName9 = "Maria";
        String personName10 = "Alex";

        String personJob1 = "Web Designer";
        String personJob2 = "Support";
        String personJob3 = "Accountant";
        String personJob4 = "Software Engineer";
        String personJob5 = "Product Manager";
        String personJob6 = "Data Analyst";
        String personJob7 = "UX Designer";
        String personJob8 = "Project Manager";
        String personJob9 = "QA Engineer";
        String personJob10 = "DevOps Engineer";

        assertEquals(personName1, driver.findElement(By.cssSelector("#person0 .name")).getText());
        assertEquals(personName2, driver.findElement(By.cssSelector("#person1 .name")).getText());
        assertEquals(personName3, driver.findElement(By.cssSelector("#person2 .name")).getText());
        assertEquals(personName4, driver.findElement(By.cssSelector("#person3 .name")).getText());
        assertEquals(personName5, driver.findElement(By.cssSelector("#person4 .name")).getText());
        assertEquals(personName6, driver.findElement(By.cssSelector("#person5 .name")).getText());
        assertEquals(personName7, driver.findElement(By.cssSelector("#person6 .name")).getText());
        assertEquals(personName8, driver.findElement(By.cssSelector("#person7 .name")).getText());
        assertEquals(personName9, driver.findElement(By.cssSelector("#person8 .name")).getText());
        assertEquals(personName10, driver.findElement(By.cssSelector("#person9 .name")).getText());

        assertEquals(personJob1, driver.findElement(By.cssSelector("#person0 .job")).getText());
        assertEquals(personJob2, driver.findElement(By.cssSelector("#person1 .job")).getText());
        assertEquals(personJob3, driver.findElement(By.cssSelector("#person2 .job")).getText());
        assertEquals(personJob4, driver.findElement(By.cssSelector("#person3 .job")).getText());
        assertEquals(personJob5, driver.findElement(By.cssSelector("#person4 .job")).getText());
        assertEquals(personJob6, driver.findElement(By.cssSelector("#person5 .job")).getText());
        assertEquals(personJob7, driver.findElement(By.cssSelector("#person6 .job")).getText());
        assertEquals(personJob8, driver.findElement(By.cssSelector("#person7 .job")).getText());
        assertEquals(personJob9, driver.findElement(By.cssSelector("#person8 .job")).getText());
        assertEquals(personJob10, driver.findElement(By.cssSelector("#person9 .job")).getText());
    }
}
