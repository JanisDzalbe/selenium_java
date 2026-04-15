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
import java.util.HashSet;
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
//
//  verify buttons are visible and enabled
        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addBtn.isDisplayed() && addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed() && resetBtn.isEnabled());

        // fetch all people from the list
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, people.size());

        // expected data (name | job)
        List<String> expected = List.of(
                "Mike|Web Designer",
                "Jill|Support",
                "Jane|Accountant",
                "John|Software Engineer",
                "Sarah|Product Manager",
                "Carlos|Data Analyst",
                "Emily|UX Designer",
                "David|Project Manager",
                "Maria|QA Engineer",
                "Alex|DevOps Engineer"
        );

        // build actual list from UI
        List<String> actual = new ArrayList<>();
        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();
            actual.add(name + "|" + job);
        }

        // verify list matches exactly (order + content)
        assertEquals(expected, actual);

        // verify no duplicate entries
        assertEquals(actual.size(), new HashSet<>(actual).size());
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job

        // open add person form
        driver.findElement(By.id("addPersonBtn")).click();

        // fill form using stable id locators
        driver.findElement(By.id("name")).sendKeys("Surendar Rajendran");
        driver.findElement(By.id("job")).sendKeys("Intern");

        // submit form
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // get updated list
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        // verify new entry exists
        boolean found = false;

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();

            if (name.equals("Surendar Rajendran") && job.equals("Intern")) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job

        // click edit (pencil) icon for the first person in the list
        driver.findElement(By.cssSelector("#listOfPeople li .editbtn")).click();

        // capture current values from input fields
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        String originalName = nameInput.getAttribute("value");
        String updatedJob = "Updated Job";

        // clear and update job field
        jobInput.clear();
        jobInput.sendKeys(updatedJob);

        // submit edit
        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        // locate updated person in list
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        boolean updated = false;

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();

            if (name.equals(originalName) && job.equals(updatedJob)) {
                updated = true;
                break;
            }
        }

        // verify update is reflected in list
        assertTrue(updated);
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list

        // get initial list of people
        List<WebElement> peopleBefore = driver.findElements(By.cssSelector("#listOfPeople li"));
        int initialSize = peopleBefore.size();

        // capture name of first person (to verify removal)
        String removedName = peopleBefore.get(0)
                .findElement(By.className("name"))
                .getText();

        // click delete (cross) icon for first person
        driver.findElement(By.cssSelector("#listOfPeople li .closebtn")).click();

        // get updated list
        List<WebElement> peopleAfter = driver.findElements(By.cssSelector("#listOfPeople li"));

        // verify list size decreased by 1
        assertEquals(initialSize - 1, peopleAfter.size());

        // verify removed person no longer exists
        boolean stillExists = false;

        for (WebElement person : peopleAfter) {
            String name = person.findElement(By.className("name")).getText();
            if (name.equals(removedName)) {
                stillExists = true;
                break;
            }
        }

        assertFalse(stillExists);
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries

        // initial expected data
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

        // verify initial state
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, people.size());

        // --- MODIFY LIST (add new person) ---

        driver.findElement(By.id("addPersonBtn")).click();

        driver.findElement(By.id("name")).sendKeys("Surendar Rajendran");
        driver.findElement(By.id("job")).sendKeys("Intern");

        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // verify list modified
        List<WebElement> modifiedList = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(11, modifiedList.size());

        // --- RESET LIST ---

        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        // get reset list
        List<WebElement> resetList = driver.findElements(By.cssSelector("#listOfPeople li"));

        // verify size restored
        assertEquals(10, resetList.size());

        // verify exact original content
        for (int i = 0; i < resetList.size(); i++) {

            String actualName = resetList.get(i)
                    .findElement(By.className("name"))
                    .getText();

            String actualJob = resetList.get(i)
                    .findElement(By.className("job"))
                    .getText();

            assertEquals(expectedPeople[i][0], actualName);
            assertEquals(expectedPeople[i][1], actualJob);
        }
    }
}
