package selenium.sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import selenium.utility.BootcampUtils;

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

        driver.findElement(By.id("resetListBtn")).click();

        WebElement johnElement = driver.findElement(By.xpath("//span[contains(@class,'name') and text()='John']"));

        WebElement jobElement = johnElement.findElement(By.xpath("./ancestor::li//span[@class='job']"));

        Assertions.assertEquals("Software Engineer", jobElement.getText());

    }


    @Test
    public void findPersonWithLoop() throws Exception {

        driver.findElement(By.id("shuffleBtn")).click();

        List<WebElement> people = driver.findElements(By.xpath("//li[starts-with(@id,'person')]"));

        boolean foundJane = false;

        for (WebElement person : people) {

            WebElement nameEl = person.findElement(By.xpath(".//span[@class='w3-xlarge name']"));
            String name = nameEl.getText().trim();

            if (name.equals("Jane")) {

                WebElement jobEl = person.findElement(By.xpath(".//span[@class='job']"));
                String job = jobEl.getText().trim();

                Assertions.assertEquals("Accountant", job);
                foundJane = true;
                break;
            }
        }
        Assertions.assertTrue(foundJane, "Jane was not found in the list!");
    }



    @Test
    public void findEmployeesBonus() throws Exception {

        driver.findElement(By.xpath("//button[contains(text(), 'Reset List')]")).click();

        List<WebElement> employeeRadiosBefore = driver.findElements(By.xpath("//input[@type='radio' and @value='employee']"));

        int employeesBefore = 0;
        for (WebElement radio : employeeRadiosBefore) {
            if (radio.isSelected()) {
                employeesBefore++;
            }
        }

        Assertions.assertEquals(5, employeesBefore, "There should be 5 employees after Reset List");

        driver.findElement(By.xpath("//button[contains(text(), 'Shuffle Order')]")).click();

        List<WebElement> employeeRadiosAfter = driver.findElements(
                By.xpath("//input[@type='radio' and @value='employee']")
        );

        int employeesAfter = 0;
        for (WebElement radio : employeeRadiosAfter) {
            if (radio.isSelected()) {
                employeesAfter++;
            }
        }

        Assertions.assertEquals(5, employeesAfter, "There should still be 5 employees after Shuffle Order");
    }


}
