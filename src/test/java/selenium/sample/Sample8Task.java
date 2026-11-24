package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        driver.findElement(By.id("resetListBtn")).click();
//   Using xPath, find “John” and asset that he is “Software Engineer”
        WebElement john = driver.findElement(
                By.xpath("//ul[@id='listOfPeople']//li[contains(., 'John')]")
        );
        assertTrue(john.getText().contains("Software Engineer"));
    }


    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”

        driver.findElement(By.id("shuffleBtn")).click();
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        List<WebElement> allPeople = driver.findElements(By.cssSelector("[id*=person]"));
        WebElement janeItem = null;

        for (WebElement person : allPeople) {
            if (person.getText().contains("Jane")) {
                janeItem = person;
                break;
            }
        }
        assertEquals("Accountant", janeItem.findElement(By.className("job")).getText());
        // assertTrue(janeItem.getText().contains("Accountant"));
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:

        //   Click “Reset List”
        driver.findElements(By.id("resetListBtn")).get(0).click();
        // Assert that there are 5 employees after Reset List
        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        List<WebElement> employees = new ArrayList<>();

        for (WebElement person : personList) {
            if (person.findElement(By.cssSelector("[value*=\"employee\"]")).isSelected()) {
                employees.add(person);
            }
        }

        assertEquals(5, employees.size());

        //   Click “Shuffle”
        driver.findElements(By.id("shuffleBtn")).get(0).click();
        // Assert that there still are 5 employees after Shuffle Order
        personList = driver.findElements(By.cssSelector("[id*=person]"));
        employees = new ArrayList<>();

        for (WebElement person : personList) {
            if (person.findElement(By.cssSelector("[value*=\"employee\"]")).isSelected()) {
                employees.add(person);
            }
        }

        assertEquals(5, employees.size());
    }
}

