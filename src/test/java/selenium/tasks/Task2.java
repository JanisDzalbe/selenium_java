package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;
    WebDriverWait wait;

    private final String[][] initialPeople = {
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

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.ignoreAll(List.of(StaleElementReferenceException.class));
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialPeopleList() throws Exception {
        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        assertEquals(10, people.size());

        for (int i = 0; i < initialPeople.length; i++) {
            WebElement person = people.get(i);
            String actualName = person.findElement(By.className("name")).getText();
            String actualJob = person.findElement(By.className("job")).getText();
            assertEquals(initialPeople[i][0], actualName);
            assertEquals(initialPeople[i][1], actualJob);
        }
    }

    @Test
    public void addNewPerson() throws Exception {
        String newName = "Julija";
        String newJob = "Functional Tester";

        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        addPersonButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));

        nameField.sendKeys(newName);
        jobField.sendKeys(newJob);
        addButton.click();

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        boolean found = false;

        for (WebElement person : people) {
            String actualName = person.findElement(By.className("name")).getText();
            String actualJob = person.findElement(By.className("job")).getText();
            if (actualName.equals(newName)) {
                assertEquals(newJob, actualJob);
                found = true;
                break;
            }
        }

        assertTrue(found, "Newly added person should be present in the list");
    }

    @Test
    public void editExistingPerson() throws Exception {
        driver.findElement(By.cssSelector("#person0 .fa-pencil")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement editButton = driver.findElement(By.xpath("//button[text()='Edit']"));

        assertEquals("Mike", nameField.getAttribute("value"));
        assertEquals("Web Designer", jobField.getAttribute("value"));

        String updatedJob = "Senior Web Designer";

        jobField.clear();
        jobField.sendKeys(updatedJob);
        editButton.click();

        WebElement updatedPerson = driver.findElement(By.id("person0"));
        String actualName = updatedPerson.findElement(By.className("name")).getText();
        String actualJob = updatedPerson.findElement(By.className("job")).getText();

        assertEquals("Mike", actualName);
        assertEquals(updatedJob, actualJob);
    }

    @Test
    public void removeExistingPerson() throws Exception {
        List<WebElement> peopleBefore = driver.findElements(By.className("w3-padding-16"));
        int sizeBefore = peopleBefore.size();
        String nameToRemove = peopleBefore.get(1).findElement(By.className("name")).getText();

        WebElement deleteButton = driver.findElement(By.cssSelector("#person1 .closebtn"));
        deleteButton.click();

        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                By.className("w3-padding-16"), sizeBefore));

        List<WebElement> peopleAfter = driver.findElements(By.className("w3-padding-16"));
        assertEquals(sizeBefore - 1, peopleAfter.size());

        boolean stillPresent = peopleAfter.stream()
                .map(p -> p.findElement(By.className("name")).getText())
                .anyMatch(name -> name.equals(nameToRemove));

        assertFalse(stillPresent, "Removed person should not be present in the list");
    }

    @Test
    public void resetList() throws Exception {
        String newName = "Julija";
        String newJob = "Functional Tester";

        WebElement addPersonButton = driver.findElement(By.xpath("//button[text()='Add person']"));
        addPersonButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));

        nameField.sendKeys(newName);
        jobField.sendKeys(newJob);
        addButton.click();

        List<WebElement> modifiedPeople = driver.findElements(By.className("w3-padding-16"));
        assertEquals(11, modifiedPeople.size());

        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));
        resetListButton.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("w3-padding-16"), 10));

        List<WebElement> people = driver.findElements(By.className("w3-padding-16"));
        assertEquals(10, people.size());

        for (int i = 0; i < initialPeople.length; i++) {
            WebElement person = people.get(i);
            String actualName = person.findElement(By.className("name")).getText();
            String actualJob = person.findElement(By.className("job")).getText();
            assertEquals(initialPeople[i][0], actualName);
            assertEquals(initialPeople[i][1], actualJob);
        }
    }
}
