package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        driver.findElements(By.id("resetListBtn")).getFirst().click();
        WebElement person = driver.findElement(By.xpath(
                "//*[contains(@id,'person') and ./span[contains(@class,'name') and text()='John']]"
        ));
        String job = person.findElement(By.xpath(".//span[contains(@class,'job')]")).getText();
        assertEquals("Software Engineer", job);

//  TODO:
//   Click “Reset List”
//   Using xPath, find “John” and asset that he is “Software Engineer”
    }

    @Test
    public void findPersonWithLoop() throws Exception {
        driver.findElements(By.id("shuffleBtn")).getFirst().click();
        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        for (WebElement elem : elements) {
            String name = elem.findElement(By.xpath(".//span[contains(@class, 'name')]")).getText();
            if (name.equals("Jane")) {
                String job = elem.findElement(By.xpath(".//span[contains(@class, 'job')]")).getText();
                assertEquals("Accountant", job);
                return;
            }
        }
        fail("Didn't find person named 'Jane'");

//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
    }

    @Test
    public void findEmployeesBonus() throws Exception {

        Predicate<WebElement> isEmployee =
                (x) -> x.findElement(By.xpath(".//input[@value = 'employee']")).isSelected();

        driver.findElements(By.id("resetListBtn")).getFirst().click();
        List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        List<WebElement> employees = elements.stream().filter(isEmployee).toList();
        assertEquals(5, employees.size());

        driver.findElements(By.id("shuffleBtn")).getFirst().click();
        elements = driver.findElements(By.xpath("//li[contains(@id,'person')]"));
        employees = elements.stream().filter(isEmployee).toList();
        assertEquals(5, employees.size());

//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
    }
}
