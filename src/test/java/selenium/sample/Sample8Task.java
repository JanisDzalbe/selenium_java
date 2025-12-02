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
//  TODO:
//   Click “Reset List”
//   Using xPath, find “John” and asset that he is “Software Engineer”
        driver.findElement(By.xpath("//*[normalize-space(text())='Reset List']")).click();

        WebElement johnPerson = driver.findElement(
                By.xpath("//li[contains(@id,'person')][.//*[text()='John']]")
        );

        String johnBlockText = johnPerson.getText();

        assertTrue(johnBlockText.contains("John"));

        assertTrue(johnBlockText.contains("Software Engineer"));
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        driver.findElement(By.xpath("//*[normalize-space(text())='Shuffle Order']")).click();

        List<WebElement> allPeople = driver.findElements(By.cssSelector("li[id*='person']"));

        String janeJob = null;

        for (WebElement person : allPeople) {
            String name = person.findElement(By.className("name")).getText();
            if (name.equals("Jane")) {
                janeJob = person.findElement(By.className("job")).getText();
                break;
            }
        }

        assertEquals("Accountant", janeJob);
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees

        driver.findElement(By.xpath("//*[normalize-space(text())='Reset List']")).click();

        List<WebElement> peopleAfterReset = driver.findElements(By.cssSelector("li[id*='person']"));

        java.util.Set<String> namesAfterReset = new java.util.HashSet<>();
        for (WebElement person : peopleAfterReset) {
            String name = person.findElement(By.className("name")).getText();
            namesAfterReset.add(name);
        }

        assertEquals(10, namesAfterReset.size());

        driver.findElement(By.xpath("//*[normalize-space(text())='Shuffle Order']")).click();

        List<WebElement> peopleAfterShuffle = driver.findElements(By.cssSelector("li[id*='person']"));

        java.util.Set<String> namesAfterShuffle = new java.util.HashSet<>();
        for (WebElement person : peopleAfterShuffle) {
            String name = person.findElement(By.className("name")).getText();
            namesAfterShuffle.add(name);
        }

        assertEquals(10, namesAfterShuffle.size());
    }
}
