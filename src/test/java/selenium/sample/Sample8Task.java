
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
        driver.findElement(By.xpath("//button[normalize-space()='Reset List']")).click();

        WebElement john = driver.findElement(
                By.xpath("//*[normalize-space()='John']"));

        WebElement jobTitle = driver.findElement(
                By.xpath("//*[normalize-space()='John']/following::*[normalize-space()='Software Engineer'][1]"));

        assertEquals("John", john.getText());
        assertEquals("Software Engineer", jobTitle.getText());
    }

    @Test
    public void findPersonWithLoop() throws Exception {
        driver.findElement(By.xpath("//button[normalize-space()='Shuffle Order']")).click();

        List<WebElement> names = driver.findElements(
                By.xpath("//*[normalize-space()='Jane']"));

        WebElement jane = null;

        for (WebElement name : names) {
            if (name.getText().equals("Jane")) {
                jane = name;
                break;
            }
        }

        assertNotNull(jane, "Jane was not found");

        WebElement jobTitle = driver.findElement(
                By.xpath("//*[normalize-space()='Jane']/following::*[normalize-space()='Accountant'][1]"));

        assertEquals("Accountant", jobTitle.getText());
    }

    @Test
    public void findEmployeesBonus() throws Exception {
        driver.findElement(By.xpath("//button[normalize-space()='Reset List']")).click();

        List<WebElement> employeesBeforeShuffle = driver.findElements(
                By.xpath("//*[normalize-space()='John' or normalize-space()='Mike' or normalize-space()='David' or normalize-space()='Maria' or normalize-space()='Sarah']"));

        assertEquals(5, employeesBeforeShuffle.size());

        driver.findElement(By.xpath("//button[normalize-space()='Shuffle Order']")).click();

        List<WebElement> employeesAfterShuffle = driver.findElements(
                By.xpath("//*[normalize-space()='John' or normalize-space()='Mike' or normalize-space()='David' or normalize-space()='Maria' or normalize-space()='Sarah']"));

        assertEquals(5, employeesAfterShuffle.size());
    }
}
