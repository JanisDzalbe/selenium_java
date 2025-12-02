package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Sample8Task {
    WebDriver driver;

    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        //open page:
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_random");
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findPersonByXPath() throws Exception {
//  TODO:
//   Click “Reset List”
//   Using xPath, find “John” and asset that he is “Software Engineer”
        WebElement resetButton = driver.findElement(
                By.xpath("//button[normalize-space()='Reset List']")
        );
        resetButton.click();

        WebElement johnItem = driver.findElement(
                By.xpath("//li[contains(., 'John')]")
        );

        String johnText = johnItem.getText();
        assertTrue(johnText.contains("John"));
        assertTrue(johnText.contains("Software Engineer"));
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        WebElement shuffleButton = driver.findElement(
                By.xpath("//button[normalize-space()='Shuffle Order']")
        );
        shuffleButton.click();

        List<WebElement> people = driver.findElements(By.tagName("li"));

        WebElement janeItem = null;
        for (WebElement person : people) {
            String text = person.getText();
            if (text.contains("Jane")) {
                janeItem = person;
                break;
            }
        }

        assertNotNull(janeItem, "Jane should be present in the list");
        assertTrue(janeItem.getText().contains("Accountant"));
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        WebElement resetButton = driver.findElement(
                By.xpath("//button[normalize-space()='Reset List']")
        );
        resetButton.click();

        List<WebElement> employees = driver.findElements(By.tagName("li"));
        assertEquals(5, employees.size());

        WebElement shuffleButton = driver.findElement(
                By.xpath("//button[normalize-space()='Shuffle Order']")
        );
        shuffleButton.click();

        List<WebElement> employeesAfterShuffle = driver.findElements(By.tagName("li"));
        assertEquals(5, employeesAfterShuffle.size());
    }
}
