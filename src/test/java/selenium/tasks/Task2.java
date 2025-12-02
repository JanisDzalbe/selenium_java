package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;
    private static WebDriverWait wait;

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

        WebElement AddPerson = driver.findElement(By.xpath("//button[text()=\"Add person\"]"));
        WebElement ResetList = driver.findElement(By.xpath("//button[text()=\"Reset List\"]"));

        assertAll(() -> assertTrue(AddPerson.isDisplayed()), () -> assertTrue(AddPerson.isEnabled()), () -> assertTrue(ResetList.isDisplayed()), () -> assertTrue(ResetList.isEnabled()));
        Map<String, String> Emplloyeemap = new HashMap<String, String>();
        Emplloyeemap.put("Mike", "Web Designer");
        Emplloyeemap.put("Jill", "Support");
        Emplloyeemap.put("Jane", "Accountant");
        Emplloyeemap.put("John", "Software Engineer");
        Emplloyeemap.put("Sarah", "Product Manager");
        Emplloyeemap.put("Emily", "UX Designer");
        Emplloyeemap.put("David", "Project Manager");
        Emplloyeemap.put("Maria", "QA Engineer");
        Emplloyeemap.put("Alex", "DevOps Engineer");
        Emplloyeemap.put("Carlos", "Data Analyst");

        int count = 0;
        List<WebElement> allPeople = driver.findElements(By.xpath("//*[@id='listOfPeople']/*"));
        for (WebElement person : allPeople) {
            count++;
        }

        for (WebElement person : allPeople) {
            System.out.println(person.findElement(By.className("name")).getText() + " " + person.findElement(By.className("job")).getText());

            assertEquals(Emplloyeemap.get(person.findElement(By.className("name")).getText()), person.findElement(By.className("job")).getText());

        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job

        driver.findElement(By.xpath("//*[@id=\"addPersonBtn\"]")).click();
        WebElement fillName = driver.findElement(By.id("name"));
        fillName.sendKeys("Tom");
        WebElement fillJob = driver.findElement(By.id("job"));
        fillJob.sendKeys("Software Engineer");
        driver.findElement(By.id("modal_button")).click();

        List<WebElement> allPeople = driver.findElements(By.xpath("//*[@id='listOfPeople']/*"));
        WebElement addedPerson = allPeople.get(allPeople.size() - 1);

        String addedName = addedPerson.findElement(By.className("name")).getText();
        String addedJob = addedPerson.findElement(By.className("job")).getText();

        Thread.sleep(2000);
        assertEquals("Tom", addedPerson.findElement(By.className("name")).getText());
        assertEquals("Software Engineer", addedPerson.findElement(By.className("job")).getText());

    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job

        driver.findElement(By.xpath("//*[@id=\"person0\"]/span[2]")).click();
        assertEquals("Mike", driver.findElement(By.id("name")).getAttribute("value"));
        assertEquals("Web Designer", driver.findElement(By.id("job")).getAttribute("value"));

        WebElement jobField = driver.findElement(By.id("job"));
        jobField.clear();
        jobField.sendKeys("Software Engineer");
        driver.findElement(By.id("modal_button")).click();

        Thread.sleep(2000);
        WebElement updatedPerson = driver.findElement(By.id("person0"));
        String updatedJob = updatedPerson.findElement(By.className("job")).getText();
        assertEquals("Software Engineer", updatedJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"person0\"]/span[1]")).click();
        Thread.sleep(2000);

        List<WebElement> person = driver.findElements(By.id("person0"));
        assertEquals(0, person.size());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries

        driver.findElement(By.xpath("//*[@id=\"person0\"]/span[1]")).click();
        Thread.sleep(2000);
        List<WebElement> modifiedList = driver.findElements(By.xpath("//*[@id='listOfPeople']/*"));
        assertEquals(9, modifiedList.size());
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
        Thread.sleep(2000);
        List<WebElement> resetList = driver.findElements(By.xpath("//*[@id='listOfPeople']/*"));
        assertEquals(10, resetList.size());

    }
}
