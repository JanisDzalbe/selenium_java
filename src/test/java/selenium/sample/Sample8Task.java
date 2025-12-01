package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.utility.BootcampUtils;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_random");
    }

    // method which is being run after each test
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findPersonByXPath() throws Exception {
//  TODO:
//   Click “Reset List”
//   Using xPath, find “John” and asset that he is “Software Engineer”
        // Click Reset List
        driver.findElement(By.id("resetListBtn")).click();

        // Wait until list is updated and John appears
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement john = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[.//span[contains(@class,'name') and contains(text(),'John')]]")
        ));

        // Get John's job
        WebElement johnJob = john.findElement(By.xpath(".//span[@class='job']"));

        assertEquals("Software Engineer", johnJob.getText().trim());

    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”

        driver.findElement(By.id("shuffleBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#listOfPeople li")));

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        String foundJob = null;

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText().trim();
            if (name.equals("Jane")) {
                foundJob = person.findElement(By.className("job")).getText().trim();
                break;
            }
        }

        assertEquals("Accountant", foundJob);
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("resetListBtn")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("listOfPeople")));

        List<WebElement> peopleAfterReset = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, peopleAfterReset.size(), "After reset there should be 10 employees");

        driver.findElement(By.id("shuffleBtn")).click();

        Thread.sleep(500);

        List<WebElement> peopleAfterShuffle = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(10, peopleAfterShuffle.size(), "After shuffle there should still be 10 employees");


    }
}
