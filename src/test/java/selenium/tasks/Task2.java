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
import java.util.Map;

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
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));
        assertTrue(addPersonButton.isDisplayed());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
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

        List<WebElement> names = driver.findElements(By.cssSelector(".name"));
        List<WebElement> jobs = driver.findElements(By.cssSelector(".job"));
        assertEquals(expectedPeople.size(), names.size());
        assertEquals(expectedPeople.size(), jobs.size());

        for(int i = 0; i < names.size(); i++){
            String name = names.get(i).getText();
            String job = jobs.get(i).getText();
            System.out.println(name + " - " + job);
            assertTrue(expectedPeople.containsKey(name));
            assertEquals(expectedPeople.get(name), job);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        addPersonButton.click();
//          fill "Name" and "Job" fields
        String name = "Mark";
        String jobTitle = "QA engineer";
        WebElement nameField = driver.findElement(By.cssSelector("input[id='name']"));
        WebElement jobField = driver.findElement(By.cssSelector("input[id='job']"));
        nameField.sendKeys(name);
        jobField.sendKeys(jobTitle);
//          click "Add"
        driver.findElement(By.cssSelector("button[onclick='addPersonWithJobToList()']")).click();
// check that new person is added to the list with correct name and job
        WebElement newPerson = driver.findElement(
                By.xpath("//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='" + name + "']]"));
        assertEquals(jobTitle, newPerson.findElement(By.className("job")).getText());

    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
        addNewPerson();
        String name = "Mark";
        String jobTitle = "QA engineer";
        String newJobTitle = "Senior QA engineer";
        WebElement markNameLocator = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]"));
        markNameLocator.findElement(By.cssSelector(".fa-pencil")).click();
//          check values in "Name" and "Job" fields

        WebElement nameValue = driver.findElement(By.id("name"));
        WebElement jobValue = driver.findElement(By.id("job"));
        assertEquals(name, nameValue.getAttribute("value"));
        assertEquals(jobTitle, jobValue.getAttribute("value"));
//          change "Job" field
        jobValue.clear();
        jobValue.sendKeys(newJobTitle);
//          click "Edit"
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
//          check that the person is updated in the list with new job
        WebElement newPerson = driver.findElement(
                By.xpath("//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='" + name + "']]"));
        assertEquals(newJobTitle, newPerson.findElement(By.className("job")).getText());

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
        String name = "Mark";
        addNewPerson();
//          click cross (x) icon for an existing person
        WebElement existingPerson = driver.findElement(
                By.xpath("//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='" + name + "']]"));
        existingPerson.findElement(By.cssSelector(".w3-closebtn")).click();
//          check that the person is removed from the list
        // check that the person is removed from the list
        List<WebElement> found = driver.findElements(
                By.xpath("//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='" + name + "']]"));
        assertEquals(0, found.size());

    }

    @Test
    public void resetList() throws Exception {
//         TODO:
        String name = "David";
        String newName = "David_S";
//          modify the list in any way (add, edit or remove a person)
        WebElement davidNameLocator = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + name + "']]"));
        davidNameLocator.findElement(By.cssSelector(".fa-pencil")).click();
        WebElement nameValue = driver.findElement(By.id("name"));
        nameValue.clear();
        nameValue.sendKeys(newName);
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
//          check that the list is modified
        WebElement editedPerson = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='" + newName + "']]"));
        assertEquals(newName, editedPerson.findElement(By.className("name")).getText());
//          click "Reset List"
        driver.findElement(By.cssSelector("button[onclick='resetListOfPeople()']")).click();;
//          check that the list is back to initial state with 10 original entries
        initialPeopleList();

    }
}
