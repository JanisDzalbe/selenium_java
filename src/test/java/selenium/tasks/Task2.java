package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
        WebElement addButton = driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Add person']")).get(0);
        WebElement resetButton = driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']")).get(0);

        assertTrue(addButton.isDisplayed());
        assertTrue(addButton.isEnabled());

        assertTrue(resetButton.isDisplayed());
        assertTrue(resetButton.isEnabled());

//          check list of people contains 10 entries with correct names and jobs

        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        assertEquals(10, personList.size());

        //Mike, Web Designer
//        Jill, Support
//        Jane, Accountant
//        John, Software Engineer
//        Sarah, Product Manager
//        Carlos, Data Analyst
//        Emily, UX Designer
//        David, Project Manager
//        Maria, QA Engineer
//        Alex, DevOps Engineer

        List<Map.Entry<String, String>> employeesList = List.of(
                Map.entry("Mike", "Web Designer"),
                Map.entry("Jill", "Support"),
                Map.entry("Jane", "Accountant"),
                Map.entry("John", "Software Engineer"),
                Map.entry("Sarah", "Product Manager"),
                Map.entry("Carlos", "Data Analyst"),
                Map.entry("Emily", "UX Designer"),
                Map.entry("David", "Project Manager"),
                Map.entry("Maria", "QA Engineer"),
                Map.entry("Alex", "DevOps Engineer")

        );

        for (WebElement person : personList) {
            String actualPersonName = person.findElement(By.cssSelector(".w3-xlarge.name")).getText();
            String actualPersonJob = person.findElement(By.cssSelector(".job")).getText();

            boolean found = false;

            for (Map.Entry<String, String> employee : employeesList) {

                if (employee.getKey().equals(actualPersonName)) {
                    assertEquals(employee.getValue(), actualPersonJob, "Jobs do not match");

                    found = true;
                    break;
                }

            }

            assertTrue(found, "No such name found in employeesList");
        }

    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
        driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Add person']")).get(0).click();
//          fill "Name" and "Job" fields // unique
        driver.findElement(By.id("name")).sendKeys("TestName");
        driver.findElement(By.id("job")).sendKeys("TestJob");

//          click "Add"
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Add']")).click();

//          check that new person is added to the list with correct name and job
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='TestName'] and .//*[text()='TestJob']]")).isDisplayed());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
        driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Jane']] //*[contains(@class,'editbtn')]")).click();
//          check values in "Name" and "Job" fields
        assertEquals("Jane", driver.findElement(By.id("name")).getDomProperty("value"));
        assertEquals("Accountant", driver.findElement(By.id("job")).getDomProperty("value"));

//          change "Job" field
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Senior Accountant");

//          click "Edit"
        driver.findElement(By.xpath("//*[@id='modal_button' and text()='Edit']"
        )).click();

//          check that the person is updated in the list with new job
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Jane'] and .//*[text()='Senior Accountant']]")).isDisplayed());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
        driver.findElements(By.xpath("//*[contains(@id,'person') and .//*[text()='Jane']] //*[contains(@class,'closebtn')]")).get(0).click();

//          check that the person is removed from the list
        try {
            driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Jane']]")).isDisplayed();
            fail("Jane is in the Employee list");
        } catch (NoSuchElementException e) {
            System.out.println("Employee record for Jane was deleted");
        }

    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified // call one of the above as functions
        removeExistingPerson();

//          click "Reset List"
        driver.findElements(By.xpath("//*[@id='addPersonBtn' and text()='Reset List']")).get(0).click();

//          check that the list is back to initial state with 10 original entries
        initialPeopleList();
        System.out.println("List was reset: all 10 original people are restored");
    }
}
