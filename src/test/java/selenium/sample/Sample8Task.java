package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
        WebElement resetButton = driver.findElement(By.id("resetListBtn"));

        resetButton.click();

        WebElement xpathJohn = driver.findElement(By.xpath("//*[contains(@id, 'person') and ./*[contains(@class,'name') and text()='John']]"));

        assertEquals("Software Engineer", xpathJohn.findElement(By.className("job")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
        WebElement shuffleButton = driver.findElement(By.id("shuffleBtn"));

        shuffleButton.click();

        List<WebElement> allElements = driver.findElements(By.cssSelector("[id*='person']"));
        for (WebElement element : allElements) {
            if (element.findElement(By.className("name")).getText().equals("Jane")) {
                assertEquals("Accountant", element.findElement(By.className("job")).getText());
                break;
            }
        }
    }

    @Test
    public void findEmployeesBonus() throws Exception {
        WebElement resetButton = driver.findElement(By.id("resetListBtn"));
        resetButton.click();

        List<WebElement> employees = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id,'person')]"));

        for (WebElement element : elements) {
            WebElement inputElement = element.findElement(By.xpath(".//input[@value = 'employee']"));
            if (inputElement.isSelected()) {
                employees.add(inputElement);
            }
        }

        assertEquals(5, employees.size());

        WebElement shuffleButton = driver.findElement(By.id("shuffleBtn"));

        shuffleButton.click();

        List<WebElement> employees1 = new ArrayList<>();
        List<WebElement> elements1 = driver.findElements(By.xpath("//li[contains(@id,'person')]"));

        for (WebElement element : elements1) {
            WebElement inputElement = element.findElement(By.xpath(".//input[@value = 'employee']"));
            if (inputElement.isSelected()) {
                employees1.add(inputElement);
            }
        }

        assertEquals(5, employees1.size());
    }
}
