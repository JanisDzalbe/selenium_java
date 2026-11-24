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


        // Click Reset List.
        //button - searching html elements button
        // [text()='Reset List'] - selecting button with such a text
     //  driver.findElement(By.xpath("(//button[text()='Reset List'])[1]")).click();
        driver.findElement(By.id("resetListBtn")).click();
        // Find John and check his job.
        assertEquals("Software Engineer", driver.findElement(By.xpath("//span[@class='w3-xlarge name' and text()='John']/following-sibling::span[@class='job']")).getText());

        /* TEACHERS WAY:
         driver.findElements(By.id("resetListBtn")).get(0).click();

    WebElement john = driver.findElement(By.xpath(
        "//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='John']]"
    ));

    assertEquals("Software Engineer", john.findElement(By.className("job")).getText());
}
         */
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        // Click Shuffle Order.
        driver.findElement(By.id("shuffleBtn")).click();
      //  driver.findElement(By.xpath("(//button[text()='Shuffle Order'])[1]")).click();

        // Find all people.
        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));

        // Go through every person.
        for (WebElement person : people) {

            // Check if this person is Jane.
            if (person.findElement(By.className("name")).getText().equals("Jane")) {

                // Check Jane's job.
                assertEquals("Accountant",
                        person.findElement(By.className("job")).getText());

                /* TEACHERS WAY
               driver.findElements(By.id("shuffleBtn")).get(0).click();

                List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));

                WebElement jane = null;

                for (WebElement person : personList) {
                 if (person.findElement(By.xpath(".//*[class='w3-xlarge name']")).getText().equals("Jane")) {
                 jane = person;
    }
}

assertEquals("Accountant", jane.findElement(By.className("job")).getText()););
    }
}
                 */
            }
        }
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        driver.findElement(By.id("resetListBtn")).click();
        List<WebElement> employees = driver.findElements(By.xpath("//li"));
        assertEquals(5, employees.size());
        driver.findElement(By.id("shuffleBtn")).click();
        employees = driver.findElements(By.xpath("//li"));
        assertEquals(5, employees.size());
        // for, hm

        /* TEACHERS WAY:

         */

    }
}
