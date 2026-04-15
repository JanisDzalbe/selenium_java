package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

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
//
    // Click Reset List
        driver.findElement(By.id("resetListBtn")).click();

    // Find John using correct tag (span, not h3)
        WebElement john = driver.findElement(
                By.xpath("//li[.//span[@class='w3-xlarge name' and text()='John']]")
        );

    // Get job
        String job = john.findElement(By.className("job")).getText();

    // Assert
        assertEquals("Software Engineer", job);
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
//
//  Click Shuffle Order
        driver.findElement(By.id("shuffleBtn")).click();

//  Get all people
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople > div"));

        WebElement jane = null;

//  Loop through each person
        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();

            if (name.equals("Jane")) {
                jane = person;
                break;
            }
        }

//  Get job and assert
        String job = jane.findElement(By.className("job")).getText();
        assertEquals("Accountant", job);
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees

// Here is my attempt to solve this, but couldn't acheive needed solution yet

// Click Reset List
        driver.findElement(By.id("resetListBtn")).click();

// Get ONLY valid people (li with id=personX)
        List<WebElement> people = driver.findElements(
                By.xpath("//li[starts-with(@id,'person')]")
        );

// Assert count = 5
        assertEquals(5, people.size());

// Click Shuffle
        driver.findElement(By.id("shuffleBtn")).click();

// Get again
        List<WebElement> shuffledPeople = driver.findElements(
                By.xpath("//li[starts-with(@id,'person')]")
        );

// Assert still 5
        assertEquals(5, shuffledPeople.size());
    }
}
