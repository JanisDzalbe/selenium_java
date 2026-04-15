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
        WebElement johnsJob = driver.findElement(By.xpath("//li[.//span[text()='John']]//span[@class='job']"));

        assertEquals("Software Engineer", johnsJob.getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
        driver.findElement(By.id("shuffleBtn")).click();

        List<WebElement> peopleList = driver.findElements(By.cssSelector("#listOfPeople li"));
        String foundJob = "";

        for (WebElement person : peopleList) {
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
        List<WebElement> initialEmployees = driver.findElements(By.xpath("//input[@value='employee']"))
                .stream()
                .filter(WebElement::isSelected)
                .toList();

        assertEquals(5, initialEmployees.size(), "Should have 5 employees initially");

        driver.findElement(By.id("shuffleBtn")).click();

        List<WebElement> shuffledEmployees = driver.findElements(By.xpath("//input[@value='employee']"))
                .stream()
                .filter(WebElement::isSelected)
                .toList();

        assertEquals(5, shuffledEmployees.size(), "Should still have 5 employees after shuffle");
    }
}