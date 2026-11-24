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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Sample8Task {
    static final By PERSON = By.cssSelector("#listOfPeople li");
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
        // Click "Reset List"
        clickAndWaitForReload(By.id("resetListBtn"));

        // Using xPath, find "John" and assert that he is "Software Engineer"
        String name = "John";
        WebElement person = driver.findElement(By.xpath("//li[.//*[contains(@class, 'name') and text()='" + name + "']]"));
        assertEquals("Software Engineer", person.findElement(By.className("job")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
        // Click "Shuffle Order"
        clickAndWaitForReload(By.id("shuffleBtn"));

        // Using a loop, find "Jane" and assert that she is "Accountant"
        WebElement jane = null;
        for (WebElement person : driver.findElements(PERSON)) {
            if (person.findElement(By.className("name")).getText().equals("Jane")) {
                jane = person;
                break;
            }
        }
        assertNotNull(jane, "Jane was not found in the list");
        assertEquals("Accountant", jane.findElement(By.className("job")).getText());
    }

    @Test
    public void findEmployeesBonus() throws Exception {
        // Click "Reset List"
        clickAndWaitForReload(By.id("resetListBtn"));

        // Assert that there are 5 employees
        assertEquals(5, countEmployees());

        // Click "Shuffle Order"
        clickAndWaitForReload(By.id("shuffleBtn"));

        // Assert that there still are 5 employees
        assertEquals(5, countEmployees());
    }

    // both buttons reload the page, so wait until the old list is gone and the new one is filled
    private void clickAndWaitForReload(By button) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement oldList = driver.findElement(By.id("listOfPeople"));
        driver.findElement(button).click();
        wait.until(ExpectedConditions.stalenessOf(oldList));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(PERSON, 0));
    }

    // "checked" is set by JS (a property, not an attribute), so every radio button has to be checked in a loop
    private int countEmployees() {
        int employees = 0;
        for (WebElement radio : driver.findElements(By.cssSelector(".status-radio[value='employee']"))) {
            if (radio.isSelected()) {
                employees++;
            }
        }
        return employees;
    }
}
