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
        String name = "John";
        String jobTitle = "Software Engineer";
        String xpath = "//*[contains(@id, 'person') and .//*[text()='" + name + "']]";
        String personText = driver.findElement(By.xpath(xpath)).getText();
        assertTrue(personText.contains(jobTitle), "John should be " + jobTitle + ", but he is: " + personText);
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
        driver.findElement(By.id("shuffleBtn")).click();
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        String name = "Jane";
        String jobTitle = "Accountant";
        List<WebElement> people = driver.findElements(By.xpath("//*[contains(@id,'person')]"));
        boolean found = false;
      for(int i = 0; i < people.size(); i++) {
          String xpath = "//*[contains(@id, 'person') and .//*[text()='" + name + "']]";
          String personText = driver.findElement(By.xpath(xpath)).getText();
          if (personText.contains(jobTitle)) {
              found = true;
              break;
          }
      }
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
        driver.findElement(By.id("resetListBtn")).click();
//   Assert that there are 5 employees
        List<WebElement> people = driver.findElements(By.xpath("//*[contains(@id,'person')]"));
        List<WebElement> employees = new ArrayList<>();
        int count = 0;
        for (WebElement person : people) {
            if(person.findElement(By.cssSelector("[value='employee']")).isSelected()) {
                employees.add(person);
                count++;
            }
        }
        assertEquals(5, employees.size());
        assertEquals(5, count);


//   Click “Shuffle Order”
        driver.findElement(By.id("shuffleBtn")).click();
//   Assert that there still are 5 employees

    }
}
