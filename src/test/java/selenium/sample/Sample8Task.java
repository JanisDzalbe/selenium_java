package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeDriver();

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

        WebElement johnJob = driver.findElement(
                By.xpath("//span[@class='w3-xlarge name' and normalize-space()='John']" +
                        "/following-sibling::span[@class='job'][1]")
        );

        String actualRole = johnJob.getText();
        assertEquals("Software Engineer", actualRole);
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        driver.findElement(By.xpath("//button[normalize-space()='Shuffle Order']")).click();

        // получаем всех сотрудников по их имени
        List<WebElement> names = driver.findElements(By.xpath("//span[@class='w3-xlarge name']"));

        boolean foundJane = false;

        for (WebElement nameElement : names) {
            String name = nameElement.getText().trim();

            if (name.equals("Jane")) {
                WebElement jobElement = nameElement.findElement(
                        By.xpath("following-sibling::span[@class='job'][1]")
                );

                String job = jobElement.getText().trim();
                assertEquals("Accountant", job);

                foundJane = true;
                break;
            }
        }

        assertTrue(foundJane, "Jane was not found in the list!");
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees
        // Reset List
        driver.findElement(By.xpath("//button[normalize-space()='Reset List']")).click();


        int countAfterReset = driver.findElements(
                By.xpath("//span[@class='w3-xlarge name']")
        ).size();

        assertEquals(10, countAfterReset);

        // Shuffle Order
        driver.findElement(By.xpath("//button[normalize-space()='Shuffle Order']")).click();

        int countAfterShuffle = driver.findElements(
                By.xpath("//span[@class='w3-xlarge name']")
        ).size();

        assertEquals(10, countAfterShuffle);
    }
}
