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
//  TODO:
//   Click “Reset List”
//   Using xPath, find “John” and asset that he is “Software Engineer”
        driver.findElement(By.xpath("//button[contains(.,'Reset List')]")).click();

        WebElement john = driver.findElement(By.xpath("//li[span[contains(@class,'name') and text()='John']]"));
        assertEquals("Software Engineer", john.findElement(By.className("job")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        driver.findElement(By.xpath("//button[contains(.,'Shuffle Order')]")).click();

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        WebElement jane = null;
        for (WebElement person : people){
            if (!person.findElements(By.xpath(".//span[contains(@class,'name') and text()='Jane']")).isEmpty()){
                jane = person;
            }
        }
        assertEquals("Accountant", jane.findElement(By.xpath(".//span[@class='job']")).getText());

    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        driver.findElement(By.xpath("//button[contains(.,'Reset List')]")).click();
        assertEquals(5, countEmployees());

        driver.findElement(By.xpath("//button[contains(.,'Shuffle Order')]")).click();
        assertEquals(5, countEmployees());
    }
        private int countEmployees(){
            List<WebElement> employeeRadios = driver.findElements(By.xpath("//input[@type='radio' and @value='employee']"));
            int employees = 0;
            for(WebElement radio : employeeRadios){
                if (radio.isSelected()){
                    employees++;

                }
            }
            return employees;

        }
    }

