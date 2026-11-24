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
        driver.findElement(By.id("resetListBtn")).click();
//   Using xPath, find “John” and asset that he is “Software Engineer”
        assertEquals("Software Engineer", driver.findElement(By.xpath("//span[text()='John']/following-sibling::span[@class='job']")).getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
//  TODO:
//   Click “Shuffle Order”
        driver.findElement(By.id("shuffleBtn")).click();
//   Using a loop, Find “Jane” and assert that she is “Accountant”
        List<WebElement> people = driver.findElements(By.cssSelector("li[id^='person']"));


        for (WebElement person : people) {
            String name = person.findElement(By.className("name")).getText();
            if (name.equals("Jane")) {
                String job = person.findElement(By.className("job")).getText();
                assertEquals("Accountant", job);
    }}}

    @Test
    public void findEmployeesBonus() throws Exception {
//  TODO:
//   Click “Reset List”
        driver.findElement(By.id("resetListBtn")).click();
        assertEquals(5, driver.findElements(By.cssSelector("input[value='employee']:checked")).size());

        driver.findElement(By.id("shuffleBtn")).click();
        assertEquals(5, driver.findElements(By.cssSelector("input[value='employee']:checked")).size());
    }
}
