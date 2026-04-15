package selenium.tasks;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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
        String libWithDriversLocation =
                System.getProperty("user.dir") + File.separator + "lib" + File.separator;

        System.setProperty("webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");

        wait.until(d -> !d.findElements(By.cssSelector("#listOfPeople li")).isEmpty());
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    private List<WebElement> people() {
        return driver.findElements(By.cssSelector("#listOfPeople li"));
    }

    //  TEST 1
    @Test
    public void initialPeopleList() {

        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add person']"));
        WebElement resetBtn = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addBtn.isDisplayed() && addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed() && resetBtn.isEnabled());

        List<WebElement> list = people();
        assertEquals(10, list.size());

        assertTrue(list.get(0).getText().contains("Mike"));
        assertTrue(list.get(9).getText().contains("Alex"));
    }

    // TEST 2
    @Test
    public void addNewPerson() {

        int before = people().size();

        // Open modal
        driver.findElement(By.xpath("//button[text()='Add person']")).click();

        // Wait for modal inputs
        wait.until(d -> d.findElement(By.id("name")).isDisplayed());

        // Fill ONLY existing fields
        driver.findElement(By.id("name")).sendKeys("Test User");
        driver.findElement(By.id("job")).sendKeys("Tester");

        // Click Add
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        // Wait for list update
        wait.until(d -> people().size() == before + 1);

        WebElement last = people().get(people().size() - 1);

        assertTrue(last.getText().contains("Test User"));
        assertTrue(last.getText().contains("Tester"));
    }

    //  TEST 3
    @Test
    public void editExistingPerson() {

        WebElement first = people().get(0);

        first.findElement(By.className("editbtn")).click();

        wait.until(d -> d.findElement(By.id("job")).isDisplayed());

        WebElement job = driver.findElement(By.id("job"));
        job.clear();
        job.sendKeys("Updated Job");

        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        wait.until(d -> people().get(0).getText().contains("Updated Job"));

        assertTrue(people().get(0).getText().contains("Updated Job"));
    }

    //  TEST 4
    @Test
    public void removeExistingPerson() {

        int before = people().size();

        people().get(0).findElement(By.className("closebtn")).click();

        wait.until(d -> people().size() == before - 1);

        assertEquals(before - 1, people().size());
    }

    //  TEST 5 (FIXED)
    @Test
    public void resetList() {

        int originalSize = people().size();

        // modify list
        people().get(0).findElement(By.className("closebtn")).click();

        wait.until(d -> people().size() < originalSize);

        assertNotEquals(originalSize, people().size());

        
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        wait.until(d -> people().size() == 10);

        assertEquals(10, people().size());
    }
}
