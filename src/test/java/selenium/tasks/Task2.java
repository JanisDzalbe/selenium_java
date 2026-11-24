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
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));
        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

//        check list of people contains 10 entries with correct names and jobs
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

        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        assertEquals(10, rows.size());
        for (int i = 0; i < expectedPeople.length; i++) {
            String rowText = rows.get(i).getText();
            assertTrue(rowText.contains(expectedPeople[i][0]));
            assertTrue(rowText.contains(expectedPeople[i][1]));
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
        driver.findElement(By.xpath("//button[text()='Add person']")).click();
//          fill "Name" and "Job" fields
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        nameInput.clear();
        nameInput.sendKeys("Sruthy");
        jobInput.clear();
        jobInput.sendKeys("QA Automation Engineer");
//          click "Add"
        driver.findElement(By.xpath("//button[text()='Add']")).click();
//          check that new person is added to the list with correct name and job
        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        assertEquals(11, rows.size());
        String lastRowText = rows.get(rows.size() - 1).getText();
        assertTrue(lastRowText.contains("Sruthy"));
        assertTrue(lastRowText.contains("QA Automation Engineer"));
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        rows.get(0).findElement(By.cssSelector(".fa-pencil")).click();
//          check values in "Name" and "Job" fields
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        assertEquals("Mike", nameInput.getAttribute("value"));
        assertEquals("Web Designer", jobInput.getAttribute("value"));
//          change "Job" field
        jobInput.clear();
        jobInput.sendKeys("Developer");
//          click "Edit"
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
//          check that the person is updated in the list with new job
        List<WebElement> updatedRows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        assertTrue(updatedRows.get(0).getText().contains("Mike"));
        assertTrue(updatedRows.get(0).getText().contains("Developer"));
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        assertEquals(10, rows.size());
        rows.get(0).findElement(By.xpath(".//*[text()='×' or text()='x' or text()='X']")).click();
//          check that the person is removed from the list
        List<WebElement> updatedRows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        assertEquals(9, updatedRows.size());
        for (WebElement row : updatedRows) {
            assertFalse(row.getText().contains("Mike"));
        }
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
        //Edit Jane

        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        rows.get(2).findElement(By.cssSelector(".fa-pencil")).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));
        nameInput.clear();
        nameInput.sendKeys("Jane Updated");
        jobInput.clear();
        jobInput.sendKeys("Senior Accountant");
        driver.findElement(By.xpath("//button[text()='Edit']")).click();
//          check that the list is modified
        List<WebElement> modifiedRows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        assertTrue(modifiedRows.get(2).getText().contains("Jane Updated"));
        assertTrue(modifiedRows.get(2).getText().contains("Senior Accountant"));
//          click "Reset List"
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
//          check that the list is back to initial state with 10 original entries
        List<WebElement> resetRows = driver.findElements(By.cssSelector("#listOfPeople > div"));
        assertEquals(10, resetRows.size());
        assertTrue(resetRows.get(2).getText().contains("Jane"));
        assertTrue(resetRows.get(2).getText().contains("Accountant"));
        assertFalse(resetRows.get(2).getText().contains("Jane Updated"));
    }
}
