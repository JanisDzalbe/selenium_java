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
        Map<String, String> peopleList = Map.of(
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
        WebElement addButton = driver.findElement(By.cssSelector("button[onclick*='openModalForAddPersonWithJob']"));
        WebElement resetButton = driver.findElement(By.cssSelector("button[onclick*='resetListOfPeople']"));
        assertTrue(addButton.isDisplayed() && addButton.isEnabled());
        assertTrue(resetButton.isDisplayed() && resetButton.isEnabled());


        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : allPeople) {
            String personName = person.findElement(By.className("name")).getText();
            String personJob = person.findElement(By.className("job")).getText();

            assertTrue(peopleList.containsKey(personName));
            assertEquals(peopleList.get(personName), personJob);
        }
        assertEquals(10, allPeople.size());
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        WebElement addButton = driver.findElement(By.cssSelector("button[onclick*='openModalForAddPersonWithJob']"));
        assertTrue(addButton.isDisplayed() && addButton.isEnabled());
        addButton.click();

        WebElement nameField = driver.findElement(By.xpath("//div[@id='addEditPerson']//input[@id='name']"));
        WebElement jobField = driver.findElement(By.xpath("//div[@id='addEditPerson']//input[@id='job']"));
        nameField.sendKeys("Artis");
        jobField.sendKeys("Tester");
        assertEquals("Artis", nameField.getDomProperty("value"));
        assertEquals("Tester", jobField.getDomProperty("value"));

        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Add']")).click();
        WebElement newPerson = driver.findElement(By.xpath(
                "//*[@class=\"w3-padding-16\" and .//*[text()='Artis']]")); // Finds and checks name
        assertEquals("Tester", newPerson.findElement(By.xpath(".//*[@class=\"job\"]")).getText());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        WebElement editPerson = driver.findElement(By.xpath("//*[@id='person1']"));
        String personName = driver.findElement(By.xpath(".//*[@class='w3-xlarge name']")).getText();
        String personJob = driver.findElement(By.xpath(".//*[@class='job']")).getText();
        editPerson.findElement(By.xpath("//*[@class='fa fa-pencil']")).click();

        WebElement nameField = driver.findElement(By.xpath("//div[@id='addEditPerson']//input[@id='name']"));
        WebElement jobField = driver.findElement(By.xpath("//div[@id='addEditPerson']//input[@id='job']"));
        assertEquals(nameField.getDomProperty("value"), personName);
        assertEquals(jobField.getDomProperty("value"), personJob);

        jobField.clear();
        jobField.sendKeys("Tester");
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']")).click();

        personJob = driver.findElement(By.xpath(".//*[@class='job']")).getText();
        assertEquals("Tester", personJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        WebElement delPerson = driver.findElement(By.xpath("//*[@id='person1']"));
        String delPersonName = driver.findElement(By.xpath(".//*[@class='w3-xlarge name']")).getText();
        delPerson.findElement(By.xpath("//*[contains(@class, 'closebtn')]")).click();

        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : allPeople) {
            String personName = person.findElement(By.className("name")).getText();
            if (delPersonName.equals(personName)){
                fail("Deleted person still appears in the list: " + personName);
            }
        }

    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        // add a new person
        driver.findElement(By.cssSelector("button[onclick*='openModalForAddPersonWithJob']")).click();
        WebElement nameField = driver.findElement(By.xpath("//div[@id='addEditPerson']//input[@id='name']"));
        WebElement jobField = driver.findElement(By.xpath("//div[@id='addEditPerson']//input[@id='job']"));
        String addedPerson = "Artis";
        nameField.sendKeys(addedPerson);
        jobField.sendKeys("Tester");
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Add']")).click();

        // check that new person is added, and there are 11 people now
        WebElement newPerson = driver.findElement(By.xpath(
                "//*[@class=\"w3-padding-16\" and .//*[text()='" + addedPerson + "']]")); // Finds and checks name
        assertEquals("Tester", newPerson.findElement(By.xpath(".//*[@class=\"job\"]")).getText());
        List<WebElement> allPeople = driver.findElements(By.className("w3-padding-16"));
        assertEquals(11, allPeople.size());

        // reset
        WebElement resetButton = driver.findElement(By.cssSelector("button[onclick*='resetListOfPeople']"));
        assertTrue(resetButton.isDisplayed() && resetButton.isEnabled());
        resetButton.click();

        // check that added person is not in the list, and there are 10 people
        allPeople = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : allPeople) {
            String personName = person.findElement(By.className("name")).getText();
            if (addedPerson.equals(personName)){
                fail(personName);
            }
        }
        assertEquals(10, allPeople.size());
    }
}
