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
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Activity 8: Practicing dynamic, conditional, and chained locators,
 * as well as looping through randomized list elements.
 */
public class Sample8Task {
    WebDriver driver;

    /**
     * Initializes ChromeDriver and opens the randomized people list task page before each test.
     *
     * @throws Exception if driver initialization fails
     */
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // open page:
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_random");
    }

    /**
     * Closes the browser after each test.
     *
     * @throws Exception if browser teardown fails
     */
    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    /**
     * Resets the list, locates John using a conditional XPath expression,
     * and asserts that his job title is "Software Engineer".
     *
     * @throws Exception if the button or person element cannot be found
     */
    @Test
    public void findPersonByXPath() throws Exception {
//  TODO:
//   Click “Reset List”
        driver.findElement(By.id("resetListBtn")).click();

//   Using xPath, find “John” and asset that he is “Software Engineer”
        WebElement john = driver.findElement(
                By.xpath("//li[contains(@id, 'person') and .//span[contains(@class, 'name') and text()='John']]")
        );
        assertEquals("Software Engineer", john.findElement(By.className("job")).getText());
    }

    /**
     * Shuffles the list order, loops through all person entries to find Jane,
     * and asserts that her job title is "Accountant".
     *
     * @throws Exception if the button or person elements cannot be found
     */
    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
        driver.findElement(By.id("shuffleBtn")).click();

//   Using a loop, Find “Jane” and assert that she is “Accountant”
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        WebElement jane = null;
        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            if ("Jane".equals(name)) {
                jane = person;
                break;
            }
        }
        assertNotNull(jane);
        assertEquals("Accountant", jane.findElement(By.className("job")).getText());
    }

    /**
     * Resets the list, verifies there are 5 employees, shuffles the order,
     * and verifies there are still 5 employees.
     *
     * @throws Exception if buttons or radio inputs cannot be found
     */
    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
        driver.findElement(By.id("resetListBtn")).click();

//   Assert that there are 5 employees
        assertEquals(5, countEmployees());

//   Click “Shuffle Order”
        driver.findElement(By.id("shuffleBtn")).click();

//   Assert that there still are 5 employees
        assertEquals(5, countEmployees());
    }

    /**
     * Counts how many people in the list currently have the "Employee" status radio selected.
     *
     * @return the number of selected employee radio buttons
     */
    private int countEmployees() {
        List<WebElement> employeeRadios = driver.findElements(By.cssSelector("input.status-radio[value='employee']"));
        int employeeCount = 0;
        for (WebElement radio : employeeRadios) {
            if (radio.isSelected()) {
                employeeCount++;
            }
        }
        return employeeCount;
    }
}