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
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();
//   Using xPath, find “John” and asset that he is “Software Engineer”
        WebElement johnCard = driver.findElement(By.xpath("//ul[@id='listOfPeople']//li[.//span[text()='John']]"));
        String johnRole = johnCard.findElement(By.className("job")).getText();
        assertEquals("Software Engineer", johnRole);

    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
        driver.findElement(By.xpath("//button[text()='Shuffle Order']")).click();
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        List<WebElement> allPeople = driver.findElements(By.xpath("//ul[@id='listOfPeople']//li"));
        WebElement jane = null;
        for (WebElement person : allPeople) {
            String name = person.findElement(By.className("name")).getText();
            if (name.equals("Jane")) {
                jane = person;
                break;
            }
        }
        assertEquals("Accountant", jane.findElement(By.className("job")).getText());
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
        driver.findElements(By.id("resetListBtn")).get(0).click();
        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        List<WebElement> employees = new ArrayList<>();

        for (WebElement person : personList) {
            if (person.findElement(By.cssSelector("[value=\"employee\"]")).isSelected()) {
                employees.add(person);
            }
        }
//   Assert that there are 5 employees
        assertEquals(5, employees.size());

//   Click “Shuffle Order”
        driver.findElement(By.xpath("//button[text()='Shuffle Order']")).click();

        personList = driver.findElements(By.cssSelector("[id*=person]"));
        employees = new ArrayList<>();

        for (WebElement person : personList) {
            if (person.findElement(By.cssSelector("[value=\"employee\"]")).isSelected()) {
                employees.add(person);
            }
        }

//   Assert that there still are 5 employees
        assertEquals(5, employees.size());
    }
}
