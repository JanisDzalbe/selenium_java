package selenium.sample;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.utility.BootcampUtils;


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
    WebElement john = driver.findElement(By.xpath("//ul[@id='listOfPeople']//li[.//span[normalize-space(.)='John']]"));

    assertEquals("Software Engineer", john.findElement(By.className("job")).getText());
    }

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
            if (name.equals("Jane")) {
                jane = person;
                break;
            }
        }

    assertNotNull(jane, "Jane was not found in the list");
    assertEquals("Accountant", jane.findElement(By.className("job")).getText());
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:

    

//   Click “Reset List”
     driver.findElement(By.id("resetListBtn")).click();

//   Assert that there are 5 employees
     assertEquals(5, driver.findElements(By.cssSelector("#listOfPeople input[value='employee']:checked")).size());

//   Click “Shuffle Order”
     driver.findElement(By.id("shuffleBtn")).click();
//   Assert that there still are 5 employees
    assertEquals(5, driver.findElements(By.cssSelector("#listOfPeople input[value='employee']:checked")).size());
    }
}
