package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeEdgeDriver();

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
        driver.findElement(By.id("resetListBtn")).click();

        WebElement job = driver.findElement(
                By.xpath("//li[.//span[text()='John']]//span[@class='job']")
        );

        assertEquals("Software Engineer", job.getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        driver.findElement(By.id("shuffleBtn")).click();

        var people = driver.findElements(By.cssSelector("li[id^='person']"));

        String job = null;

        for (WebElement person : people) {
            String name = person.findElement(By.cssSelector(".name")).getText();

            if (name.equals("Jane")) {
                job = person.findElement(By.cssSelector(".job")).getText();
                break;
            }
        }

        assertEquals("Accountant", job);
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        driver.findElement(By.id("resetListBtn")).click();

        int employees = driver.findElements(By.cssSelector("input.status-radio[value='employee']:checked")).size();

        assertEquals(5, employees);

        driver.findElement(By.id("shuffleBtn")).click();

        int employeesAfterShuffle = driver.findElements(
                By.cssSelector("input.status-radio[value='employee']:checked")
        ).size();

        assertEquals(5, employeesAfterShuffle);
    }
}
