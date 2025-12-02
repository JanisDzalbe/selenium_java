package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        driver.findElement(By.xpath("//*[text()='Reset List']")).click();
        WebElement john = driver.findElement(By.xpath("//li[contains(.,'John')]"));
        assertTrue(john.getText().contains("Software Engineer"));
    }

    @Test
    public void findPersonWithLoop() throws Exception {
        driver.findElement(By.xpath("//*[text()='Shuffle Order']")).click();
        List<WebElement> employees = driver.findElements(By.tagName("li"));
        WebElement jane = null;

        for (WebElement employee : employees) {
            if (employee.getText().contains("Jane")) {
                jane = employee;
                break;
            }
        }

        assertNotNull(jane);
        assertTrue(jane.getText().contains("Accountant"));
    }
}
