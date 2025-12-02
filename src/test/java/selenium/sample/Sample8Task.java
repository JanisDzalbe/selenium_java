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
        WebElement jill = driver.findElement(By.xpath("//*[@class ='w3-padding-16' and .//*[text()='Jill']]"));
//   Using xPath, find “John” and asset that he is “Software Engineer”
        assertEquals("Support", jill.findElement(By.xpath(".//*[@class='job']")).getText());
        assertEquals("Support", jill.findElement(By.className("job")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
        driver.findElement(By.id("shuffleBtn")).click();
        //   Using a loop, Find “Jane” and assert that she is “Accountant”
        List<WebElement> listOfPersons = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : listOfPersons) {
            if (person.findElement(By.className("name")).getText().equals("Jane"))
            {
                assertEquals("Accountant", person.findElement(By.className("job")).getText());
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
    }
}
