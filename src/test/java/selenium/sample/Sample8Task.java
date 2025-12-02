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
//   Click “Reset List”
        driver.findElement(By.id("resetListBtn")).click();
//   Using xPath, find “John” and asset that he is “Software Engineer”
        WebElement johnElem = driver.findElement(By.xpath("//li[*/text()='John']"));
//        WebElement johnElem = driver.findElement(By.xpath("//*[@class=\"w3-padding-16\" and .//*[text()='John']]"));
        assertEquals("Software Engineer", johnElem.findElement(By.xpath(".//*[@class='job']")).getText());
//        assertEquals("Software Engineer", johnElem.findElement(By.className("job")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//   Click “Shuffle Order”
        driver.findElement(By.id("shuffleBtn")).click();
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        List<WebElement> allPersons = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : allPersons) {
            if(person.findElement(By.className("name")).getText().equals("Jane")){
                assertEquals("Accountant", person.findElement(By.className("job")).getText());
            }
        }

    }

    @Test
    public void findEmployeesBonus() throws Exception {
//   Click “Reset List”
        driver.findElement(By.id("resetListBtn")).click();
//   Assert that there are 5 employees
        int employeeCount = 0;
        List<WebElement> allPersons = driver.findElements(By.className("w3-padding-16"));
        for (WebElement person : allPersons) {
            if(person.findElement(By.cssSelector("input[value='employee']")).isSelected()){
                employeeCount++;
            }
        }
//   Click “Shuffle Order”
        driver.findElement(By.id("shuffleBtn")).click();
//   Assert that there still are 5 employees
        allPersons = driver.findElements(By.className("w3-padding-16"));
        int newEmployeeCount = 0;
        for (WebElement person : allPersons) {
            if(person.findElement(By.cssSelector("input[value='employee']")).isSelected()){
                newEmployeeCount++;
            }
        }
        assertEquals(employeeCount, newEmployeeCount);
    }
}
