package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.security.SecureRandom;
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

        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add person']"));

        WebElement resetButton = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addButton.isDisplayed());
        assertTrue(addButton.isEnabled());

        assertTrue(resetButton.isDisplayed());
        assertTrue(resetButton.isEnabled());

        List<WebElement> people = driver.findElements(By.cssSelector("[id*='person']"));

        assertEquals(10, people.size());

        List<String> expectedPeople = List.of(        "Mike - Web Designer",
                "Jill - Support",
                "Jane - Accountant",
                "John - Software Engineer",
                "Sarah - Product Manager",
                "Carlos - Data Analyst",
                "Emily - UX Designer",
                "David - Project Manager",
                "Maria - QA Engineer",
                "Alex - DevOps Engineer"
        );

        int i = 0;

        for (WebElement person: people){
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();

            assertEquals(expectedPeople.get(i), name + " - " + job);
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
        driver.findElement(By.xpath("//button[text()='Add person']")).click();

        driver.findElement(By.id("name")).sendKeys("Vika");
        driver.findElement(By.id("job")).sendKeys("QA");

        driver.findElement(By.xpath("//button[text()='Add']")).click();

        WebElement vika = driver.findElement(By.xpath("//li[.//span[text()='Vika']]"));

        assertEquals("QA", vika.findElement(By.className("job")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        driver.findElement(By.xpath("//li[.//span[text()='Mike']]//span[contains(@class, 'editbtn')]")).click();

        assertEquals("Mike", driver.findElement(By.id("name")).getDomProperty("value"));
        assertEquals("Web Designer", driver.findElement(By.id("job")).getDomProperty("value"));

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("QA");
        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        WebElement mike = driver.findElement(By.xpath("//li[.//span[text()='Mike']]"));

        assertEquals("QA", mike.findElement(By.className("job")).getText());

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        WebElement alex = driver.findElement(By.xpath("//li[.//span[text()='Alex']]"));

        alex.findElement(By.className("closebtn")).click();

        assertEquals(9, driver.findElements(By.cssSelector("[id*='person']")).size());

        assertEquals(0, driver.findElements(By.xpath("//li[.//span[text()='Alex']]")).size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        WebElement emily = driver.findElement(By.xpath("//li[.//span[text()='Emily']]"));

        emily.findElement(By.className("closebtn")).click();

        assertEquals(9, driver.findElements(By.cssSelector("[id*='person']")).size());

        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        assertEquals(10, driver.findElements(By.cssSelector("[id*='person']")).size());

        List<String> expectedPeople = List.of(        "Mike - Web Designer",
                "Jill - Support",
                "Jane - Accountant",
                "John - Software Engineer",
                "Sarah - Product Manager",
                "Carlos - Data Analyst",
                "Emily - UX Designer",
                "David - Project Manager",
                "Maria - QA Engineer",
                "Alex - DevOps Engineer"
        );

        List<WebElement> people = driver.findElements(By.cssSelector("[id*='person']"));

        int i = 0;

        for (WebElement person: people){
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();

            assertEquals(expectedPeople.get(i), name + " - " + job);
            i++;
        }
    }
}
