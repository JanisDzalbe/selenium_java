package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;
    List<Map<String, String>> expectedPeople;
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
        WebElement addPersonBtn = driver.findElement(By.xpath("//h2[text()='People with jobs']/following-sibling::Button[@id='addPersonBtn']"));
        //or  WebElement addPersonBtn = driver.findElement(By.xpath("//h2[text()='People with jobs']/following-sibling::Button[text()='Add person']"));
        WebElement ResetBtn = driver.findElement(By.xpath("//h2[text()='People with jobs']/following-sibling::Button[text()='Reset List']"));
        WebElement listls = driver.findElement(By.id("listOfPeople"));
        List<WebElement> listOfPeoples = driver.findElements(By.className("w3-padding-16"));
        expectedPeople = new ArrayList<>();
        fillTestData();

        int listCount = listOfPeoples.size();

        int expectedListCount = expectedPeople.size();

//         TODO:
//          check that "Add person" and "Reset List" buttons are displayed and enabled
        assertTrue(addPersonBtn.isDisplayed());
        assertTrue(addPersonBtn.isEnabled());
        assertTrue(ResetBtn.isDisplayed());
        assertTrue(ResetBtn.isEnabled());
        //check list of people contains 10 entries with correct names and jobs
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
        assertEquals(listCount,10);
        assertEquals(listCount, expectedListCount);
        int i = 0;
        for (WebElement person : listOfPeoples) {
          if (listCount==expectedListCount)
          {
              assertEquals(expectedPeople.get(i).get("name"), person.findElement(By.className("name")).getText());
              assertEquals(expectedPeople.get(i).get("job"), person.findElement(By.className("job")).getText());
              i++;
          }
        }
    }

    @Test
    public void addNewPerson() throws Exception {
            WebElement addPersonBtn = driver.findElement(By.xpath("//h2[text()='People with jobs']/following-sibling::Button[@id='addPersonBtn' and text()='Add person']"));
            //TODO:
//          click "Add person"
            addPersonBtn.click();
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement addBtn = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Add']"));
//          fill "Name" and "Job" fields
            nameField.sendKeys("Ahmed");
            jobField.sendKeys("Test Automation Engineer");
//          click "Add"
            addBtn.click();
//          check that new person is added to the list with correct name and job
            List<WebElement> listOfPeoples = driver.findElements(By.className("w3-padding-16"));
            assertEquals(listOfPeoples.size(), 11);
            WebElement lastElement = listOfPeoples.get(listOfPeoples.size()-1);
            assertEquals("Ahmed", lastElement.findElement(By.className("name")).getText());
            assertEquals("Test Automation Engineer", lastElement.findElement(By.className("job")).getText());
    }
//
    @Test
    public void editExistingPerson() throws Exception {
        WebElement pencilIcon = driver.findElement(By.xpath("//i[@class='fa fa-pencil'][1]"));
        List<WebElement> listOfPeoples = driver.findElements(By.className("w3-padding-16"));
        WebElement firstElement = listOfPeoples.get(0);
        String existingName=firstElement.findElement(By.className("name")).getText();
        String existingJob=firstElement.findElement(By.className("job")).getText();

//         TODO:
//          click pencil icon for an existing person
        pencilIcon.click();
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement editBtn = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Edit']"));
//          check values in "Name" and "Job" fields

        assertEquals(existingName, nameField.getAttribute("value"));
        assertEquals(existingJob, jobField.getAttribute("value"));
//          change "Job" field
        jobField.clear();
         jobField.sendKeys("Teacher");
//          click "Edit"
        editBtn.click();
//          check that the person is updated in the list with new job
        WebElement firstElementAfterEditing = driver.findElements(By.className("w3-padding-16")).get(0);
        assertEquals(existingName, firstElementAfterEditing.findElement(By.className("name")).getText());
        assertEquals("Teacher", firstElementAfterEditing.findElement(By.className("job")).getText());
    }
//
    @Test
    public void removeExistingPerson() throws Exception {
        //get First Person name and job
        List<WebElement> listOfPeoples = driver.findElements(By.className("w3-padding-16"));
        WebElement firstElement = listOfPeoples.get(0);
        String firstPersonName = firstElement.findElement(By.className("name")).getText();
        String firstPersonJob = firstElement.findElement(By.className("job")).getText();

//         TODO:
//          click cross (x) icon for an existing person
            driver.findElement(By.xpath("//*[@id='person0']/span[1]")).click();
//          check that the person is removed from the list
        List<WebElement> listOfPeoplesAfterRemoving = driver.findElements(By.className("w3-padding-16"));
            int listCountBeforeRemovingPerson = listOfPeoplesAfterRemoving.size();
             assertEquals(listOfPeoples.size()-1, listCountBeforeRemovingPerson);

        for (WebElement person : listOfPeoplesAfterRemoving) {

            assertFalse(person.findElement(By.className("name")).getText().equals(firstPersonName));
            assertFalse(person.findElement(By.className("job")).getText().equals(firstPersonJob));
        }
    }

    @Test
    public void resetList() throws Exception {
        WebElement pencilIcon = driver.findElement(By.xpath("//i[@class='fa fa-pencil'][1]"));
        List<WebElement> listOfPeoples = driver.findElements(By.className("w3-padding-16"));
        WebElement firstElement = listOfPeoples.get(0);
        String existingName=firstElement.findElement(By.className("name")).getText();
        String existingJob=firstElement.findElement(By.className("job")).getText();
//         TODO:
//          modify the list in any way (add, edit or remove a person)
        //edit
        pencilIcon.click();
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement editBtn = driver.findElement(By.xpath("//button[@id='modal_button' and text()='Edit']"));
        jobField.clear();
        jobField.sendKeys("Driver");
        editBtn.click();
//          check that the list is modified
        WebElement firstElementAfterEditing = driver.findElements(By.className("w3-padding-16")).get(0);
        assertEquals(existingName, firstElementAfterEditing.findElement(By.className("name")).getText());
        assertEquals("Driver", firstElementAfterEditing.findElement(By.className("job")).getText());
//          click "Reset List"
        driver.findElement(By.xpath("//h2[text()='People with jobs']/following-sibling::Button[@id='addPersonBtn' and text()='Reset List']")).click();
//          check that the list is back to initial state with 10 original entries
        List<WebElement> listOfPeoplesAfterEditing = driver.findElements(By.className("w3-padding-16"));
        expectedPeople = new ArrayList<>();
        fillTestData();
        assertEquals(listOfPeoples.size(),10);
        int i = 0;
        for (WebElement person : listOfPeoplesAfterEditing) {
                assertEquals(expectedPeople.get(i).get("name"), person.findElement(By.className("name")).getText());
                assertEquals(expectedPeople.get(i).get("job"), person.findElement(By.className("job")).getText());
                i++;
        }
    }

private Map<String, String> createPerson(String name, String job) {
    Map<String, String> person = new HashMap<>();
    person.put("name", name);
    person.put("job", job);
    return person;
}

private void fillTestData()
{
    expectedPeople.add(createPerson("Mike", "Web Designer"));
    expectedPeople.add(createPerson("Jill", "Support"));
    expectedPeople.add(createPerson("Jane", "Accountant"));
    expectedPeople.add(createPerson("John", "Software Engineer"));
    expectedPeople.add(createPerson("Sarah", "Product Manager"));
    expectedPeople.add(createPerson("Carlos", "Data Analyst"));
    expectedPeople.add(createPerson("Emily", "UX Designer"));
    expectedPeople.add(createPerson("David", "Project Manager"));
    expectedPeople.add(createPerson("Maria", "QA Engineer"));
    expectedPeople.add(createPerson("Alex", "DevOps Engineer"));
}
}
