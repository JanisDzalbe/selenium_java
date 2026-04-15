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

        //clicks "reset list"
        driver.findElements(By.id("resetListBtn")).getFirst().click();

        //using XPath to find "John" and assert that he is "Software Engineer"
        WebElement john = driver.findElement(
                By.xpath("//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='John']]")
        );
        assertEquals("Software Engineer",
                john.findElement(By.className("job")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {

        //shuffles order
        driver.findElements(By.id("shuffleBtn")).getFirst().click();

        //gets all persons
        List<WebElement> persons = driver.findElements(By.cssSelector("[id*='person-']"));

        for (WebElement person : persons) {
            if (person.findElement(By.className("name")).getText().equals("Jane")) {
                assertEquals("Accountant",
                        person.findElement(By.className("job")).getText());
                break;
            }
        }
    }

    @Test
    public void findEmployeesBonus() {

        //reset
        driver.findElement(By.xpath("//button[contains(text(),'Reset')]")).click();

        List<WebElement> persons = driver.findElements(By.cssSelector("[id*='person']"));

        int employeesCount1 = 0;

        for (WebElement person : persons) {
            WebElement employeeRadio = person.findElement(By.xpath(".//input[@value='employee']"));
            if (employeeRadio.isSelected()) {
                employeesCount1++;
            }
        }

        assertEquals(5, employeesCount1);
        driver.findElement(By.xpath("//button[contains(text(),'Shuffle')]")).click();
        List<WebElement> persons2 = driver.findElements(By.cssSelector("[id*='person']"));

        int employeesCount2 = 0;

        for (WebElement person : persons2) {
            WebElement employeeRadio = person.findElement(By.xpath(".//input[@value='employee']"));
            if (employeeRadio.isSelected()) {
                employeesCount2++;
            }
        }
        assertEquals(5, employeesCount2);
    }
}
