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
        WebElement addPersonButton = driver.findElement(By.xpath("//button[contains(text(),'Add person')]"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[contains(text(),'Reset List')]"));
        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());
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
        List<WebElement> listofPeople = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(10, listofPeople.size());

        String[] listofNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] listofJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager",
                "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

        for (int i = 0; i < listofPeople.size(); i++) {
            assertEquals(listofNames[i], listofPeople.get(i).findElement(By.className("name")).getText());
            assertEquals(listofJobs[i], listofPeople.get(i).findElement(By.className("job")).getText());
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
        driver.findElement(By.id("name")).sendKeys("Krishna Prabhu");
        driver.findElement(By.id("job")).sendKeys("Test Specialist");
        driver.findElement(By.id("modal_button")).click();
        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(pageText.contains("Krishna Prabhu"));
        assertTrue(pageText.contains("Test Specialist"));
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job

        //driver.findElement(By.cssSelector("#person1 .editbtn")).click();

        driver.findElement(By.xpath("//*[@id=\"person1\"]/span[2]")).click();
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        Thread.sleep(1000);
        assertEquals("Jill", nameInput.getDomProperty("value"));
        assertEquals("Support", jobInput.getDomProperty("value"));

        String updatedJob = "Test Specialist";
        jobInput.clear();
        jobInput.sendKeys(updatedJob);

        driver.findElement(By.id("modal_button")).click();

        WebElement editedPerson = driver.findElement(By.id("person1"));
        assertEquals("Jill", editedPerson.findElement(By.className("name")).getText());
        assertEquals(updatedJob, editedPerson.findElement(By.className("job")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        WebElement alex = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]"));
        alex.findElement(By.className("closebtn")).click();
        assertTrue(driver.findElements(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]")).isEmpty());

        assertEquals(9, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        WebElement alex = driver.findElement(By.xpath("//li[.//span[contains(@class,'name') and text()='Sarah']]"));
        alex.findElement(By.className("closebtn")).click();

        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(9, personList.size());
        driver.findElements(By.xpath("//button[text()='Reset List']")).get(0).click();
        personList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(10, personList.size());
        List<WebElement> listofPeople = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(10, listofPeople.size());

        String[] listofNames = {"Mike", "Jill", "Jane", "John", "Sarah", "Carlos", "Emily", "David", "Maria", "Alex"};
        String[] listofJobs = {"Web Designer", "Support", "Accountant", "Software Engineer", "Product Manager",
                "Data Analyst", "UX Designer", "Project Manager", "QA Engineer", "DevOps Engineer"};

        for (int i = 0; i < listofPeople.size(); i++) {
            assertEquals(listofNames[i], listofPeople.get(i).findElement(By.className("name")).getText());
            assertEquals(listofJobs[i], listofPeople.get(i).findElement(By.className("job")).getText());
        }
    }
}
