package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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

        driver.findElements(By.id("resetListBtn")).getFirst().click();
        WebElement john = driver.findElement(By.xpath("//*[contains(@id,'person') and ./*[contains(@class,'name') and text()='John']]"));
        assertEquals("Software Engineer", john.findElement(By.className("job")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        driver.findElement(By.id("shuffleBtn")).click();
        // loop through all names
        for (int i = 1; i <= driver.findElements(By.xpath("//div[@class='name']")).size(); i++) {

            String name = driver.findElement(By.xpath("(//div[@class='name'])[" + i + "]")).getText();

            if (name.equals("Jane")) {

                // get corresponding role using dynamic xpath
                String role = driver.findElement(
                        By.xpath("(//div[@class='name'])[" + i + "]/following-sibling::div")
                ).getText();

                // assert role
                assertEquals("Accountant", role);
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
        driver.findElements(By.id("resetListBtn")).getFirst().click();
        List<WebElement> persons = driver.findElements(By.cssSelector("[id*=\"person\"]"));
        int employees = 0;

        for(WebElement person: persons){
            if(person.findElement(By.cssSelector("[value=\"employee\"]")).isSelected()){
                employees +=1;
            }
        }
        assertEquals(5, employees);

        driver.findElements(By.id("shuffleBtn")).getFirst().click();
        persons = driver.findElements(By.cssSelector("[id*=\"person\"]"));

        List<WebElement> employeesList = new ArrayList<>();
        for(WebElement person: persons){
            if(person.findElement(By.cssSelector("[value=\"employee\"]")).isSelected()){
                employeesList.add(person);
            }
        }

    }
}
