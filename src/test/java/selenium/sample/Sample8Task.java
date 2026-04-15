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

    @BeforeEach
    public void startingTests() throws Exception {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_random");
    }

    @AfterEach
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findPersonByXPath() throws Exception {
        driver.findElement(By.id("resetListBtn")).click();

        WebElement john = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and text()='John']]")
        );
        String job = john.findElement(By.className("job")).getText();

        assertEquals("Software Engineer", job);
    }

    @Test
    public void findPersonWithLoop() throws Exception {
        driver.findElement(By.id("shuffleBtn")).click();

        List<WebElement> people = driver.findElements(By.cssSelector("#listOfPeople li"));
        String foundJob = null;

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            if (name.equals("Jane")) {
                foundJob = person.findElement(By.className("job")).getText();
                break;
            }
        }

        assertEquals("Accountant", foundJob);
    }

    @Test
    public void findEmployeesBonus() throws Exception {
        driver.findElement(By.id("resetListBtn")).click();
        assertEquals(5, countEmployees());

        driver.findElement(By.id("shuffleBtn")).click();
        assertEquals(5, countEmployees());
    }

    private int countEmployees() {
        List<WebElement> persons = driver.findElements(By.cssSelector("[id^='person']"));
        int employees = 0;
        for (WebElement person : persons) {
            if (person.findElement(By.cssSelector("input[value='employee']")).isSelected()) {
                employees++;
            }
        }
        return employees;
    }
}