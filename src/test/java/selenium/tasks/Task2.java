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

        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addBtn.isDisplayed());
        assertTrue(addBtn.isEnabled());

        assertTrue(resetBtn.isDisplayed());
        assertTrue(resetBtn.isEnabled());


        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, people.size());

        List<String> expectedNames = List.of(
                "Mike", "Jill", "Jane", "John", "Sarah",
                "Carlos", "Emily", "David", "Maria", "Alex"
        );

        List<String> expectedJobs = List.of(
                "Web Designer", "Support", "Accountant", "Software Engineer",
                "Product Manager", "Data Analyst", "UX Designer",
                "Project Manager", "QA Engineer", "DevOps Engineer"
        );

        for (int i = 0; i < people.size(); i++) {

            String actualName = people.get(i).findElement(By.className("name")).getText();

            String actualJob = people.get(i).findElement(By.className("job")).getText();



            assertEquals(expectedNames.get(i), actualName);
            assertEquals(expectedJobs.get(i), actualJob);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job

        driver.findElement(By.id("addPersonBtn")).click();
        String newName = "Sonakshi";
        String newJob = "Tester";

        driver.findElement(By.id("name")).sendKeys(newName);
        driver.findElement(By.id("job")).sendKeys(newJob);

        driver.findElement(By.cssSelector("button[onclick='addPersonWithJobToList()']")).click();

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        boolean found = false;

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();

            if (name.equals(newName) && job.equals(newJob)) {
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

        String updatedJob = "Automation Engineer";
        driver.findElements( By.cssSelector("span[onclick^='openModalForEditPersonWithJob']") ).get(0).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        String existingName = nameInput.getAttribute("value");
        String existingJob = jobInput.getAttribute("value");

        assertTrue(existingName.length() > 0);
        assertTrue(existingJob.length() > 0);

        jobInput.clear();
        jobInput.sendKeys(updatedJob);

        driver.findElement(By.cssSelector("button[onclick^='editPersonWithJob']") ).click();

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        boolean updated = false;

        for (WebElement person : people) {
            String name = person.findElement(By.cssSelector(".name")).getText();
            String job = person.findElement(By.className("job")).getText();

            if (name.equals(existingName) && job.equals(updatedJob)) {
                updated = true;
                break;
            }
        }

        assertTrue(updated);

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list

        List<WebElement> peopleBefore = driver.findElements(By.cssSelector("#listOfPeople li"));
        int initialSize = peopleBefore.size();

        driver.findElements( By.cssSelector("span[onclick^='deletePerson']")).get(0).click();

        List<WebElement> peopleAfter = driver.findElements(By.cssSelector("#listOfPeople li"));
        int newSize = peopleAfter.size();

        assertEquals(initialSize - 1, newSize);
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries

        List<WebElement> initialPeople = driver.findElements(By.cssSelector("#listOfPeople li"));
        int initialSize = initialPeople.size();

        driver.findElements(By.cssSelector("span[onclick^='deletePerson']")).get(0).click();

        List<WebElement> modifiedPeople = driver.findElements(By.cssSelector("#listOfPeople li"));
        int modifiedSize = modifiedPeople.size();
        assertTrue(modifiedSize < initialSize);

        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        List<WebElement> resetPeople = driver.findElements(By.cssSelector("#listOfPeople li"));

        assertEquals(10, resetPeople.size());

    }
}
