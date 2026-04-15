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
        driver.findElement(By.id("resetListBtn")).click();
        driver.findElement(By.xpath("//span[text()='John']"));
        assertEquals("Software Engineer",driver.findElement(By.xpath("//span[text()='John']/ancestor::li//span[@class='job']")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        driver.findElement(By.id("shuffleBtn")).click();
        List<WebElement> names = driver.findElements(By.className("name"));
        for (WebElement n : names){
            if (n.getText().equals("Jane")){
                String job = driver.findElement(By.xpath("//span[text()='Jane']/ancestor::li//span[@class='job']")).getText();
                assertEquals("Accountant",job);
                break;
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
        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));
        int employeeCount = 0;
        for (WebElement p : people){
            WebElement employeeRadio = p.findElement(By.cssSelector("input[value='employee']"));
            if (employeeRadio.isSelected()){
                employeeCount++;
            }
        }
        assertEquals(5, employeeCount);
        driver.findElement(By.id("shuffleBtn")).click();
        people = driver.findElements(By.cssSelector("li[id^='person']"));
        employeeCount = 0;
        for (WebElement p : people){
            WebElement employeeRadio = p.findElement(By.cssSelector("input[value='employee']"));
            if (employeeRadio.isSelected()){
                employeeCount++;
            }
        }
        assertEquals(5, employeeCount);
    }
}
