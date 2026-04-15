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
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        driver.findElement(By.xpath("//button[contains(text(),'Reset')]")).click();

        WebElement johnElement = driver.findElement(
                By.xpath("//li[contains(@id,'person') and .//span[contains(@class,'name') and text()='John']]")
        );

        String profession = johnElement.findElement(By.className("job")).getText();

        assertEquals("Software Engineer", profession);
    }

    @Test
    public void findPersonWithLoop() throws Exception {

        driver.findElement(By.xpath("//button[contains(text(),'Shuffle')]")).click();

        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));

        WebElement janeElement = null;

        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();

            if (name.equals("Jane")) {
                janeElement = person;
                break;
            }
        }

        assertNotNull(janeElement);

        String profession = janeElement.findElement(By.className("job")).getText();

        assertEquals("Accountant", profession);
    }

    @Test
    public void findEmployeesBonus() throws Exception {

        // Click Reset List
        driver.findElement(By.id("resetListBtn")).click();

        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        List<WebElement> employees = new ArrayList<>();

        for (WebElement elem : elements) {
            WebElement inputElement = elem.findElement(By.xpath(".//input[@value='employee']"));
            if (inputElement.isSelected()) {
                employees.add(elem);
            }
        }

        assertEquals(5, employees.size());

        // Click Shuffle Order
        driver.findElement(By.id("shuffleBtn")).click();

        elements = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        employees = new ArrayList<>();

        for (WebElement elem : elements) {
            WebElement inputElement = elem.findElement(By.xpath(".//input[@value='employee']"));
            if (inputElement.isSelected()) {
                employees.add(elem);
            }
        }

        assertEquals(5, employees.size());
    }
}