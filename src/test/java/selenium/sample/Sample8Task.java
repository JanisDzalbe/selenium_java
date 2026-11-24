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
//   Using xPath, find “John” and assert that he is “Software Engineer”
        driver.findElement(By.id("resetListBtn")).click();
        WebElement job = driver.findElement(By.xpath("//li[span='John']/span[4]"));
        assertEquals("Software Engineer",job.getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        driver.findElement(By.id("shuffleBtn")).click();
        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        WebElement jane = null;
        for (WebElement person : personList){
            if(person.findElement(By.xpath(".//*[@class='w3-x3-xlargw name']")).getText().equals("Jane")){
              jane = person;
            }
            assertEquals("Software Engineer",jane.findElement(By.className("job")).getText());
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
        // locating each element with id person and creating in list
        List<WebElement> personList = driver.findElements(By.cssSelector("[id*=person]"));
        // creating an empty list
        List<WebElement> employee = new ArrayList<>();
        // at first the employee count is zero
        int employeeCount = 0;

        // using for loop iterating each person list
        for (WebElement person : personList){
            // if this person list element has value employee is selected
            if(person.findElement(By.cssSelector("[value ='employee']")).isSelected()){
                // add to employee array
              employee.add(person);
              employeeCount++;
            }
        }
        assertEquals(5,employee.size());
        assertEquals(5,employeeCount);

        driver.findElement(By.id("shuffleBtn")).click();
        personList = driver.findElements(By.cssSelector("[id*=person]"));
        employee = new ArrayList<>();

        for (WebElement person : personList){
            if(person.findElement(By.cssSelector("[value ='employee']")).isSelected()){
                employee.add(person);

            }
        }
        assertEquals(5,employee.size());
    }

}
