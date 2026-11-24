package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        // take only one button from the top
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));
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
        // expectedPeople[0][0] - ("Mike"), expectedPeople[0][1] - ("Web Designer")
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

        assertEquals(10, driver.findElements(By.cssSelector("#listOfPeople li")).size());

        for (int p = 0; p < expectedPeople.length; p++) {
            int found = 0;
            for (int i = 0; i < 10; i++) {
                WebElement row = driver.findElements(By.cssSelector("#listOfPeople li")).get(i);
                String name = row.findElement(By.className("name")).getText();
                if (name.equals(expectedPeople[p][0])) {
                    assertEquals(expectedPeople[p][1], row.findElement(By.className("job")).getText());
                    found++;
                }
            }
            assertEquals(1, found);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
        // before it we had 10
        assertEquals(10, driver.findElements(By.cssSelector("#listOfPeople li")).size());
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
        // new Name and Job
        driver.findElement(By.id("name")).sendKeys("Ilya");
        driver.findElement(By.id("job")).sendKeys("Student");
        // clicking Add ("modal_button")
        driver.findElement(By.id("modal_button")).click();

        // check that new person is added to the list with correct name and job
        // became 11 people
        assertEquals(11, driver.findElements(By.cssSelector("#listOfPeople li")).size());
        WebElement anna = driver.findElements(By.cssSelector("#listOfPeople li")).get(10);
        assertEquals("Ilya", anna.findElement(By.className("name")).getText());
        assertEquals("Student", anna.findElement(By.className("job")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO: click pencil icon for an existing person
        // Edit 'editbtn'
        WebElement mike = driver.findElements(By.cssSelector("#listOfPeople li")).get(0);
        assertEquals("Mike", mike.findElement(By.className("name")).getText());
        mike.findElement(By.className("editbtn")).click();

//          check values in "Name" and "Job" fields
        assertEquals("Mike", driver.findElement(By.id("name")).getDomProperty("value"));
        assertEquals("Web Designer", driver.findElement(By.id("job")).getDomProperty("value"));

//          change "Job" field
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Senior Web Designer");

//          click "Edit"
        driver.findElement(By.xpath("//button[text()='Edit']")).click();

//          check that the person is updated in the list with new job
        WebElement updatedMike = driver.findElements(By.cssSelector("#listOfPeople li")).get(0);
        assertEquals("Senior Web Designer", updatedMike.findElement(By.className("job")).getText());
        assertEquals(10, driver.findElements(By.cssSelector("#listOfPeople li")).size());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
        assertEquals(10, driver.findElements(By.cssSelector("#listOfPeople li")).size());
        WebElement mike = driver.findElements(By.cssSelector("#listOfPeople li")).get(0);
        assertEquals("Mike", mike.findElement(By.className("name")).getText());
        // x - closebtn
        mike.findElement(By.className("closebtn")).click();

//          check that the person is removed from the list
        assertEquals(9, driver.findElements(By.cssSelector("#listOfPeople li")).size());
        for (int i = 0; i < 9; i++) {
            String name = driver.findElements(By.cssSelector("#listOfPeople li")).get(i).findElement(By.className("name")).getText();
            assertTrue(!name.equals("Mike"));
        }
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
        WebElement mike = driver.findElements(By.cssSelector("#listOfPeople li")).get(0);
        assertEquals("Mike", mike.findElement(By.className("name")).getText());
        mike.findElement(By.className("closebtn")).click();

//          check that the list is modified
        assertEquals(9, driver.findElements(By.cssSelector("#listOfPeople li")).size());
        String firstName = driver.findElements(By.cssSelector("#listOfPeople li")).get(0).findElement(By.className("name")).getText();
        assertTrue(!firstName.equals("Mike"));

//          click "Reset List"
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        assertEquals(10, driver.findElements(By.cssSelector("#listOfPeople li")).size());
        WebElement restoredMike = driver.findElements(By.cssSelector("#listOfPeople li")).get(0);
    }
}