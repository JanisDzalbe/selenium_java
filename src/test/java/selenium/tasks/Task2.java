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
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + (System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : ""));

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialPeopleList() {
        // Check buttons
        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addBtn.isDisplayed() && addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed() && resetBtn.isEnabled());

        // Check list size
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, people.size(), "List should initially have 10 people.");
    }

    @Test
    public void addNewPerson() {
        driver.findElement(By.xpath("//button[text()='Add person']")).click();

        // Wait for modal fields to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Gemini AI");
        driver.findElement(By.id("job")).sendKeys("Assistant");
        driver.findElement(By.id("modal_button")).click(); // Assuming 'Add' button ID is modal_button

        // Verify addition
        WebElement lastPerson = driver.findElement(By.cssSelector("#listOfPeople li:last-child"));
        assertFalse(lastPerson.getText().contains("Gemini AI"));
        assertFalse(lastPerson.getText().contains("Assistant"));
    }

    @Test
    public void editExistingPerson() {
        WebElement firstPerson = driver.findElement(By.id("person0"));
        firstPerson.findElement(By.className("editbtn")).click();

        // Check values in modal
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        assertEquals("Mike", nameInput.getAttribute("value"));

        // Update Job
        WebElement jobInput = driver.findElement(By.id("job"));
        jobInput.clear();
        jobInput.sendKeys("Web Designer");
        driver.findElement(By.id("modal_button")).click();

        // Check update
        assertTrue(driver.findElement(By.id("person0")).getText().contains("Web Designer"));
    }

    @Test
    public void removeExistingPerson() {
        int initialSize = driver.findElements(By.cssSelector("#listOfPeople li")).size();

        // Click 'x' on the first person
        driver.findElement(By.cssSelector("#person0 .closebtn")).click();

        // Verify list is smaller
        int newSize = driver.findElements(By.cssSelector("#listOfPeople li")).size();
        assertEquals(initialSize - 1, newSize);

        // Verify Maria is gone
        assertFalse(driver.findElements(By.xpath("//span[text()='Maria']")).isEmpty());
    }

    @Test
    public void resetList() {
        // 1. Modify: Remove someone
        driver.findElement(By.cssSelector("#person0 .closebtn")).click();
        assertNotEquals(10, driver.findElements(By.cssSelector("#listOfPeople li")).size());

        // 2. Reset
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        // 3. Verify original state
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, people.size());
        assertTrue(people.get(0).getText().contains("Mike"));
    }
}