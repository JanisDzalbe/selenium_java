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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String[][] peopleMap = {{"Mike", "Web Designer"}, {"Jill", "Support"}, {"Jane", "Accountant"}, {"John", "Software Engineer"},
                {"Sarah", "Product Manager"}, {"Carlos", "Data Analyst"}, {"Emily", "UX Designer"}, {"David", "Project Manager"},
                {"Maria", "QA Engineer"}, {"Alex", "DevOps Engineer"}};

        WebElement addPersonBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetListBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));
        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));

        assertTrue(addPersonBtn.isDisplayed());
        assertTrue(addPersonBtn.isEnabled());
        assertTrue(resetListBtn.isDisplayed());
        assertTrue(resetListBtn.isEnabled());

        assertTrue(people.size() == 10);

        for (int i=0; i<peopleMap[2].length; i++) {
            assertEquals(peopleMap[i][0], people.get(i).findElement(By.className("name")).getText());
            assertEquals(peopleMap[i][1], people.get(i).findElement(By.className("job")).getText());
            i++;
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addPersonBtn = driver.findElement(By.xpath("//button[text()='Add person']"));

        addPersonBtn.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));

        String name = "John Pork";
        String job = "CEO";

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add']"));


        nameField.sendKeys(name);
        jobField.sendKeys(job);
        addBtn.click();

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(name)) {
                assertEquals(job, person.findElement(By.className("job")).getText());
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("#person0 .fa-pencil")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement editBtn = driver.findElement(By.xpath("//button[text()='Edit']"));

        assertEquals("Mike", nameField.getAttribute("value"));
        assertEquals("Web Designer", jobField.getAttribute("value"));

        String newJob = "Our lord saviour";
        String name = "Mike";

        jobField.clear();
        jobField.sendKeys(newJob);
        editBtn.click();

        WebElement mike = driver.findElement(By.id("person0"));
        assertEquals(newJob, mike.findElement(By.className("job")).getText());
        assertEquals(name, mike.findElement(By.className("name")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        String name = "Jill";
        WebElement deleteJillBtn =  driver.findElement(By.cssSelector("#person1 .closebtn"));
        deleteJillBtn.click();

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals(name)) {
                fail();
            }
        }
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        String[][] peopleMap = {{"Mike", "Web Designer"}, {"Jill", "Support"}, {"Jane", "Accountant"}, {"John", "Software Engineer"},
                {"Sarah", "Product Manager"}, {"Carlos", "Data Analyst"}, {"Emily", "UX Designer"}, {"David", "Project Manager"},
                {"Maria", "QA Engineer"}, {"Alex", "DevOps Engineer"}};

        WebElement deleteEmilyBtn =  driver.findElement(By.cssSelector("#person1 .closebtn"));
        deleteEmilyBtn.click();

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : people) {
            if (person.findElement(By.className("name")).getText().equals("Jill")) {
                fail();
            }
        }

        WebElement resetListBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));
        resetListBtn.click();

        people = driver.findElements(By.className("w3-padding-16"));
        assertTrue(people.size() == 10);
        for (int i=0; i<peopleMap[2].length; i++) {
            assertEquals(peopleMap[i][0], people.get(i).findElement(By.className("name")).getText());
            assertEquals(peopleMap[i][1], people.get(i).findElement(By.className("job")).getText());
            i++;
        }
    }
}
