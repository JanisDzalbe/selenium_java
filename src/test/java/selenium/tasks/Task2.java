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
        assertTrue(driver.findElement(By.id("addPersonBtn")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//button[text()='Reset List'] ")).isDisplayed());

        // Get all people
        List<WebElement> peopleList = driver.findElements(By.cssSelector("[id*='person']")
        );

        //checking list contains exactly 10 entries
        assertEquals(10, peopleList.size());

        // Expected names and jobs
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

        // Verify each person and their job
        for (String[] expected : expectedPeople) {
            boolean personFound = false;
            for (WebElement person : peopleList) {
                String personDetails = person.getText();

                if (personDetails.contains(expected[0]) && personDetails.contains(expected[1])) {
                    personFound = true;
                    break;
                }
            }
            assertTrue(personFound, "Person or job not found: " + expected[0] + " - " + expected[1]);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        // Click Add person
        driver.findElement(By.id("addPersonBtn")).click();

        // Enter Name and job
        driver.findElement(By.cssSelector("#addEditPerson #name")).sendKeys("Pete");
        driver.findElement(By.cssSelector("#addEditPerson #job")).sendKeys("QA Tester123");
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // Get updated people list
        List<WebElement> people = driver.findElements(By.cssSelector("[id*='person']")
        );

        // Check that list contains 11 people
        assertEquals(11, people.size());
        // Verify Pete is added with the correct job
        boolean personFound = false;
        for (WebElement person : people) {
            String details = person.getText();

            if (details.contains("Pete") && details.contains("QA Tester123")) {
                personFound = true;
                break;
            }
        }
        assertTrue(personFound, "New person Pete was not added correctly");
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        List<WebElement> peopleList = driver.findElements(By.cssSelector("[id*='person']"));
        for (WebElement person : peopleList) {
            if (person.getText().contains("Mike")) {
                person.findElement(By.xpath("//li[.//span[text()='Mike']]//span[starts-with(@onclick, 'deletePerson')]")).click();
                break;
            }
        }
        WebElement nameField = driver.findElement(By.cssSelector("#addEditPerson #name"));
        WebElement jobField = driver.findElement(By.cssSelector("#addEditPerson #job"));

        assertEquals("Mike", nameField.getAttribute("value"));
        assertEquals("Web Designer", jobField.getAttribute("value"));
        // Change Job
        jobField.clear();
        jobField.sendKeys("Senior Web Designer");

        // Click Edit
        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        // Find Mike again and verify updated job
        peopleList = driver.findElements(By.cssSelector("[id*='person']"));
        boolean updated = false;
        for (WebElement person : peopleList) {

            if (person.getText().contains("Mike") && person.getText().contains("Senior Web Designer")) {
                updated = true;
                break;
            }
        }
        assertTrue(updated, "Mike's job was not updated");
    }


    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        List<WebElement> people = driver.findElements(By.cssSelector("[id*='person']")
        );

        // Find Mike and click his cross (X) icon
        for (WebElement person : people) {
            if (person.getText().contains("Mike")) {
                person.findElement(By.cssSelector(".remove")).click();
                break;
            }
        }
        // Get the updated people list
        people = driver.findElements(By.cssSelector("[id*='person']")
        );
        assertEquals(9, people.size());
        // Check that Mike is no longer in the list
        boolean mikeFound = false;
        for (WebElement person : people) {
            if (person.getText().contains("Mike")) {
                mikeFound = true;
                break;
            }
        }
        assertFalse(mikeFound, "Mike was not removed from the list");
    }


    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        addNewPerson();
        // Check that the list is modified- 11 people
        List<WebElement> peopleList = driver.findElements(By.cssSelector("[id*='person']")
        );

        assertEquals(11, peopleList.size());
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        // Call the initial list test
        initialPeopleList();

    }
}
