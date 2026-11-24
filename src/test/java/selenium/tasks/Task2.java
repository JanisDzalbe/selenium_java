package selenium.tasks;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

     private By personByName(String name) {
        return By.xpath("//ul[@id='listOfPeople']//li[span[.='" + name + "']]");
    }

    private void assertOriginalPeopleList() {
        Map<String, String> expectedPeople = Map.of(
                "Mike", "Web Designer",
                "Jill", "Support",
                "Jane", "Accountant",
                "John", "Software Engineer",
                "Sarah", "Product Manager",
                "Carlos", "Data Analyst",
                "Emily", "UX Designer",
                "David", "Project Manager",
                "Maria", "QA Engineer",
                "Alex", "DevOps Engineer"
        );

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, people.size());

        Map<String, String> actualPeople = new HashMap<>();
        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            String job = person.findElement(By.className("job")).getText();
            actualPeople.put(name, job);
        }

        assertEquals(expectedPeople, actualPeople);
    }


    @Test
    public void initialPeopleList() throws Exception {
//         TODO:
//          check that "Add person" and "Reset List" buttons are displayed and enabled
            WebElement addPersonButton = driver.findElement(By.xpath("//button[.='Add person']"));
            WebElement resetListButton = driver.findElement(By.xpath("//button[.='Reset List']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

//          check list of people contains 10 entries with correct names and jobs
        assertOriginalPeopleList();
    
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
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
            driver.findElement(By.xpath("//button[.='Add person']")).click();
//          fill "Name" and "Job" fields
            driver.findElement(By.id("name")).sendKeys("Pavlo");
            driver.findElement(By.id("job")).sendKeys("Test Engineer");
//          click "Add"
            driver.findElement(By.xpath("//button[.='Add']")).click();
//          check that new person is added to the list with correct name and job
            assertEquals(11, driver.findElements(By.cssSelector("#listOfPeople li")).size());
            WebElement newPerson = driver.findElement(personByName("Pavlo"));
            assertEquals("Test Engineer", newPerson.findElement(By.className("job")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
            driver.findElement(personByName("John")).findElement(By.className("editbtn")).click();
//          check values in "Name" and "Job" fields
            WebElement nameField = driver.findElement(By.id("name"));
            WebElement jobField = driver.findElement(By.id("job"));

            assertEquals("John", nameField.getDomProperty("value"));
            assertEquals("Software Engineer", jobField.getDomProperty("value"));
//          change "Job" field
            jobField.clear();
            jobField.sendKeys("Senior Software Engineer");
//          click "Edit"
            driver.findElement(By.xpath("//*[@id='addEditPerson']//button[.='Edit']")).click();
//          check that the person is updated in the list with new job
            WebElement updatedPerson = driver.findElement(personByName("John"));
            assertEquals("Senior Software Engineer", updatedPerson.findElement(By.className("job")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
            driver.findElement(personByName("John")).findElement(By.className("closebtn")).click();
//          check that the person is removed from the list
            assertTrue(driver.findElements(personByName("John")).isEmpty());
        assertEquals(9, driver.findElements(By.cssSelector("#listOfPeople li")).size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
             driver.findElement(personByName("Mike")).findElement(By.className("closebtn")).click();
//          check that the list is modified
            assertTrue(driver.findElements(personByName("Mike")).isEmpty());
            assertEquals(9, driver.findElements(By.cssSelector("#listOfPeople li")).size());
//          click "Reset List"
            driver.findElement(By.xpath("//button[.='Reset List']")).click();
//          check that the list is back to initial state with 10 original entries
            assertOriginalPeopleList();
    }
}
