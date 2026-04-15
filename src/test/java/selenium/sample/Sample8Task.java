package selenium.sample;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;
import org.junit.jupiter.api.Assertions.*;
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
       WebElement john = driver.findElement(By.xpath("//*[contains(@id,'person') and ./*[contains(@class,'name') and text()='John']]"));
       assertEquals("Software Engineer", john.findElement(By.className("job")).getText());

    }

    @Test
    public void findPersonWithLoop() throws Exception {
        driver.findElement(By.id("shuffleBtn")).getText();//click shuffle
        List<WebElement> persons = driver.findElements(By.cssSelector("[id*=\"person\"]"));//create a list
        for(WebElement person : persons){
            if (person.findElement(By.className("name")).getText().equals("Jane")){
                assertEquals("Accountant",person.findElement(By.className("job")).getText());
                break;
            }

        }
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        driver.findElement(By.id("resetListBtn")).click();
        List<WebElement> persons = driver.findElements(By.cssSelector("[id*=\"person\"]"));
        int employees = 0;

        for(WebElement persson: persons){
            if(persson.findElement(By.cssSelector("[value=\"employee\"]")).isSelected()){
                employees += 1;
            }
        }
        driver.findElement(By.id("resetListBtn")).click();
        employees = 0;
        persons = driver.findElements(By.cssSelector("[id*=\"person\"]"));
        for(WebElement person: persons){
            if(person.findElement(By.cssSelector("[value=\"employee\"]")).isSelected()){
                employees += 1;
            }
        }
        assertEquals(5, employees);

    }
}
