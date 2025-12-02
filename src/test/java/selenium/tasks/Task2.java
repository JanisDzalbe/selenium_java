package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
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
        WebElement addPersonBtn = driver.findElement(By.xpath("//button[text()='Add person']"));    //search by text, buttons share everything otherwise
        assertTrue(addPersonBtn.isDisplayed(), "Add person button is not displayed");
        assertTrue(addPersonBtn.isEnabled(), "Add person button is not enabled");

        WebElement resetListBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));
        assertTrue(resetListBtn.isDisplayed(), "Reset List button is not displayed");
        assertTrue(resetListBtn.isEnabled(), "Reset List button is not enabled");

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li")); //checking how many people we got
        assertEquals(10, people.size(), "There should be 10 people");

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

        for (int i = 0; i < expectedPeople.length; i++) {       //check if we got every expected person with expected names and jobs
            String expectedName = expectedPeople[i][0];         //expected name and job
            String expectedJob = expectedPeople[i][1];

            WebElement person = people.get(i);              //read person from the page
            String personText = person.getText();           //get name and job

            assertTrue(personText.contains(expectedName),           //check if name and job correspond
                    "Person " + (i+1) + " should have name: " + expectedName);
            assertTrue(personText.contains(expectedJob),
                    "Person " + (i+1) + " should have job: " + expectedJob);
        }       //this works just because our expected people are in right order, person1 person2 etc.
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        WebElement addPersonBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        addPersonBtn.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        String newName = "Beate";
        String newJob = "Test Engineer";

        nameField.sendKeys(newName);
        jobField.sendKeys(newJob);

        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add']"));
        addBtn.click();

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));      //count how many people we got
        assertEquals(11, people.size(), "List should now contain 11 people");   //we should have 11

        WebElement lastPerson = people.get(people.size() - 1);      //first element shows at index 0, so we need -1 to get info about last one
        String lastPersonText = lastPerson.getText();

        assertTrue(lastPersonText.contains(newName), "New employee's name is " + newName);
        assertTrue(lastPersonText.contains(newJob), "New employee's job is " + newJob);
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement editIcon = driver.findElement(By.cssSelector("#listOfPeople li:first-child .editbtn i"));    //first is Mike, person0
        editIcon.click();

        WebElement nameField = driver.findElement(By.id("name")); //checking the name and job values
        WebElement jobField = driver.findElement(By.id("job"));

        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']")));

        assertEquals("Mike", nameField.getAttribute("value"), "Expected 'Mike'");   //I hope it's okay to hardcode here
        assertEquals("Web Designer", jobField.getAttribute("value"), "Expected 'Web Designer'");

        String newJob = "QA Engineer"; //entering new job
        jobField.clear();
        jobField.sendKeys(newJob);

        editBtn.click();

        // checking if it's updated
        WebElement firstPerson = driver.findElement(By.cssSelector("#listOfPeople li:first-child"));
        String firstPersonText = firstPerson.getText();

        assertTrue(firstPersonText.contains("Mike"), "Person should still be Mike");
        assertTrue(firstPersonText.contains(newJob), "New job should've been: " + newJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        List<WebElement> initialPeople = driver.findElements(By.cssSelector("#listOfPeople li"));   //list of everyone
        int initialCount = initialPeople.size();            //amount of people

        String firstPersonText = initialPeople.get(0).getText(); //getting first person

        WebElement removeIcon = driver.findElement(By.cssSelector("#listOfPeople li:first-child .closebtn"));   //x button clicked on the first item in the list
        removeIcon.click();

        List<WebElement> updatedPeople = driver.findElements(By.cssSelector("#listOfPeople li")); //Check participants again, .size() should show a number -1
        assertEquals(initialCount - 1, updatedPeople.size(), "List should include -1 person");

        WebElement newFirstPerson = updatedPeople.get(0);       // Checking if first person is different - it means previous one was deleted.
        assertNotEquals(firstPersonText, newFirstPerson.getText(), "Person has not been deleted");
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries

        //I just took code from add person, not gonna add the rest, hope that's fine
        WebElement addPersonBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        addPersonBtn.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        String newName = "Beate";
        String newJob = "Test Engineer";

        nameField.sendKeys(newName);
        jobField.sendKeys(newJob);

        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add']"));
        addBtn.click();

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));      //count how many people we got
        assertEquals(11, people.size(), "List should now contain 11 people");   //we should have 11

        WebElement lastPerson = people.get(people.size() - 1);      //first element shows at index 0, so we need -1 to get info about last one
        String lastPersonText = lastPerson.getText();

        assertTrue(lastPersonText.contains(newName), "New employee's name is " + newName);
        assertTrue(lastPersonText.contains(newJob), "New employee's job is " + newJob);

        //this is the new part, reset button
        WebElement ResetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));
        ResetBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#listOfPeople li"), 10));

        List<WebElement> resetPeople = driver.findElements(By.cssSelector("#listOfPeople li"));     //reset list
        assertEquals(10, resetPeople.size(), "List should be back to 10 people after reset");   // Check if it's back to 10

        lastPersonText = resetPeople.get(9).getText();                      // Checking if last person is no longer the added one
        assertTrue(lastPersonText.contains("Alex"), "Last person should be Alex");
        assertTrue(lastPersonText.contains("DevOps Engineer"), "Alex should be DevOps Engineer");

    }
}
