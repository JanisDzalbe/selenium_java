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

    @Test
    public void initialPeopleList() throws Exception {
//         TODO:
//          check that "Add person" and "Reset List" buttons are displayed and enabled
        WebElement addPersonButton = driver.findElement(By.xpath("//button[contains(text(),'Add person')]"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[contains(text(),'Reset List')]"));
        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());
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
        // check list of people contains 10 entries with correct names and jobs
        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, rows.size());

        String[][] expectedPeople = {{"Mike", "Web Designer"}, {"Jill", "Support"}, {"Jane", "Accountant"}, {"John", "Software Engineer"}, {"Sarah", "Product Manager"}, {"Carlos", "Data Analyst"}, {"Emily", "UX Designer"}, {"David", "Project Manager"}, {"Maria", "QA Engineer"}, {"Alex", "DevOps Engineer"}};

        for (String[] person : expectedPeople) {
            boolean found = false;
            for (WebElement row : rows) {
                String name = row.findElement(By.cssSelector(".name")).getText().trim();
                String job = row.findElement(By.cssSelector(".job")).getText().trim();
                if (name.equals(person[0]) && job.equals(person[1])) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "Expected entry not found: " + person[0] + ", " + person[1]);
        }
    }


    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
        driver.findElement(By.xpath("//button[contains(text(),'Add person')]")).click();
//          fill "Name" and "Job" fields
        driver.findElement(By.id("name")).sendKeys("Smrithy");
        driver.findElement(By.id("job")).sendKeys("QA Engineer");
//          click "Add"

        driver.findElement(By.id("modal_button")).click();
//          check that new person is added to the list with correct name and job
        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(pageText.contains("Smrithy"));
        assertTrue(pageText.contains("QA Engineer"));
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person

        driver.findElement(By.xpath("//*[@id=\"person5\"]/span[2]")).click();
//          check values in "Name" and "Job" fields
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        assertEquals("Carlos", nameField.getAttribute("value"));
        assertEquals("Data Analyst", jobField.getAttribute("value"));
//          change "Job" field
        jobField.clear();
//          click "Edit"
        jobField.sendKeys("Senior Web Designer");
        driver.findElement(By.id("modal_button")).click();
//          check that the person is updated in the list with new job
        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(pageText.contains("Mike"));
        assertTrue(pageText.contains("Senior Web Designer"));
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person


        WebElement sarah = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]"));

        sarah.findElement(By.className("closebtn")).click();
        //  check that the person is removed from the list
        assertTrue(driver.findElements(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]")).isEmpty());

        assertEquals(9, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());

    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)


        WebElement sarah = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]"));
        sarah.findElement(By.className("closebtn")).click();
//     check that the list is modified
        assertEquals(9, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());
        //     click "Reset List"
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
        //  driver.findElement(By.id("addPersonBtn")).click();
//   check that the list is back to initial state with 10 original entries
        assertEquals(10, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());
        assertFalse(driver.findElements(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]")).isEmpty());
    }
}


