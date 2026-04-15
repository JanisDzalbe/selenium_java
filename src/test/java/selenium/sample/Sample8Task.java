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
//   Click “Reset List”
//   Using xPath, find “John” and asset that he is “Software Engineer”
        WebElement resetListButton = driver.findElements(By.id("resetListBtn")).getFirst();
        resetListButton.click();
        String name = "John";

        WebElement person = driver.findElement(By.xpath("//*[contains(@id,'person') and ./span[contains(@class,'name') " +
                "and text()='" + name + "']]"));

        String job = person.findElement(By.xpath(".//span[contains(@class,'job')]")).getText();
        assertEquals("Software Engineer", job);
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        WebElement shuffleOrderButton = driver.findElements(By.id("shuffleBtn")).getFirst();
        shuffleOrderButton.click();

        List<WebElement> people = driver.findElements(By.xpath("//*[contains(@id,'person') and ./span[contains(@class,'name')]]"));
        for(WebElement person : people) {
            String name = person.findElement(By.xpath("./span[contains(@class,'name')]")).getText();
            if (name.equals("Jane")) {
                String job = person.findElement(By.xpath(".//span[contains(@class, 'job')]")).getText();
                assertEquals("Accountant", job);
                break;
            }
        }
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        WebElement resetListButton = driver.findElements(By.id("resetListBtn")).getFirst();
        resetListButton.click();

        List<WebElement> people = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        List<WebElement> employees = new ArrayList<>();
        for (WebElement person:people) {
            WebElement radio = person.findElement(By.xpath(".//input[@value='employee']"));
            if (radio.isSelected()) {
                employees.add(person);
            }
        }
        assertEquals(5, employees.size());

        WebElement shuffleOrderButton = driver.findElements(By.id("shuffleBtn")).getFirst();
        shuffleOrderButton.click();

        List<WebElement> peopleAfter = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        List<WebElement> employeesAfter = new ArrayList<>();
        for (WebElement personAfter:peopleAfter) {
            WebElement radioAfter = personAfter.findElement(By.xpath(".//input[@value='employee']"));
            if (radioAfter.isSelected()) {
                employeesAfter.add(personAfter);
            }
        }
        assertEquals(5, employeesAfter.size());
    }
}
