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
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @BeforeEach
    public void startingTests() throws Exception {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // open page:
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

        // click "Reset List"
        driver.findElement(By.id("resetListBtn")).click();

        // find John's name using xpath
        WebElement johnName = driver.findElement(By.xpath("//span[@class='w3-xlarge name' and text()='John']"));

        // go to parent person block
        WebElement johnBlock = johnName.findElement(By.xpath("./ancestor::li[1]"));

        // get John's profession from the same block
        String profession = johnBlock.findElement(By.className("job")).getText();

        // check profession
        assertEquals("Software Engineer", profession);
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
//   Using a loop, Find “Jane” and assert that she is “Accountant”

        // click "Shuffle Order"
        driver.findElement(By.id("shuffleBtn")).click();

        // get all person blocks
        List<WebElement> people = driver.findElements(By.xpath("//li[starts-with(@id,'person')]"));

        // variable for found Jane
        WebElement janeBlock = null;

        // loop through all people
        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();

            // find Jane
            if (name.equals("Jane")) {
                janeBlock = person;
                break;
            }
        }

        // check that Jane was found
        assertNotNull(janeBlock);

        // check Jane profession
        assertEquals("Accountant", janeBlock.findElement(By.className("job")).getText());
    }

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
//   Assert that there are 5 employees
//   Click “Shuffle Order”
//   Assert that there still are 5 employees

        // click "Reset List"
        driver.findElement(By.id("resetListBtn")).click();

        // get all person blocks after reset
        List<WebElement> people = driver.findElements(By.xpath("//li[starts-with(@id,'person')]"));

        int employeeCount = 0;

        // count employees
        for (WebElement person : people) {
            List<WebElement> radios = person.findElements(By.cssSelector("input.status-radio[value='employee']"));
            if (!radios.isEmpty() && radios.get(0).isSelected()) {
                employeeCount++;
            }
        }

        // check there are 5 employees
        assertEquals(5, employeeCount);

        // click "Shuffle Order"
        driver.findElement(By.id("shuffleBtn")).click();

        // get all person blocks after shuffle
        people = driver.findElements(By.xpath("//li[starts-with(@id,'person')]"));

        employeeCount = 0;

        // count employees again
        for (WebElement person : people) {
            List<WebElement> radios = person.findElements(By.cssSelector("input.status-radio[value='employee']"));
            if (!radios.isEmpty() && radios.get(0).isSelected()) {
                employeeCount++;
            }
        }

        // check there still are 5 employees
        assertEquals(5, employeeCount);
    }
}