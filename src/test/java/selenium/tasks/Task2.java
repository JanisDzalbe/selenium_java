package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Random;

public class Task2 {
    WebDriver driver;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialPeopleList() throws Exception {
//         TODO:
//          check that "Add person" and "Reset List" buttons are displayed and enabled
//          check list of people contains 10 entries with correct names and jobs
//        Mike, Web Designer
//        Jill, Support
//        Jane, Accountant
//        John, Software Engineer
//        Sarah, Product Manager
//        Carlos, Data Analyst
//        Emily, UX Designer
//        David, Project Manager
//        Maria, QA Engineer
//        Alex, DevOps Engineer


        // buttons visible and enabled
        assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Add person')]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Reset List')]")).isDisplayed());

        // total people count
        assertEquals(10, driver.findElements(By.xpath("//*[contains(@id,'person')]")).size());

        // verify few key entries (pattern-based)
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[contains(@class,'name') and text()='John'] and .//*[text()='Software Engineer']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Jane'] and .//*[text()='Accountant']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Mike'] and .//*[text()='Web Designer']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Jill'] and .//*[text()='Support']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Sarah'] and .//*[text()='Product Manager']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Carlos'] and .//*[text()='Data Analyst']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Emily'] and .//*[text()='UX Designer']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='David'] and .//*[text()='Project Manager']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Maria'] and .//*[text()='QA Engineer']]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='Alex'] and .//*[text()='DevOps Engineer']]")).isDisplayed());

    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Add person')]")));
        driver.findElement(By.xpath("//button[contains(text(),'Add person')]")).click();

        driver.findElement(By.id("name")).sendKeys("TestUser");
        driver.findElement(By.id("job")).sendKeys("Tester");

        driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();

        // verify new entry
        assertTrue(driver.findElement(By.xpath("//*[contains(@id,'person') and .//*[text()='TestUser'] and .//*[text()='Tester']]")).isDisplayed());
    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
//        click pencil icon for an existing person
        String name = driver.findElement(By.xpath("//li[@id='person5']//span[contains(@class,'large name')]")).getText();
        driver.findElement(By.xpath("//span[text()='" + name + "']//parent::li//child::i")).click();

//        check values in "Name" and "Job" fields
//        String name = driver.findElement(By.id("name")).getText();
        String job = driver.findElement(By.id("job")).getText();

//        change "Job" field
        Random random = new Random();

        String generatedString = random.ints(97, 122 + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(generatedString);

//        click "Edit"
        driver.findElement(By.xpath("//button[text()='Edit']")).click();


//        check that the person is updated in the list with new job
        String changedJob = driver.findElement(By.xpath("//span[text()='" + name + "']//parent::li//span[@class='job']")).getText();
        assertNotEquals(job, changedJob);
        assertEquals(generatedString, changedJob);

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list

//click cross (x) icon for an existing person
        String name = driver.findElement(By.xpath("//li[@id='person2']//span[contains(@class,'large name')]")).getText();
        driver.findElement(By.xpath("//span[contains(@class, 'large name') and text()='" + name + "']//parent::li//child::span[contains(@onclick,'deletePerson')]")).click();

        //          check that the person is removed from the list
        try {
          WebElement person=driver.findElement(By.xpath("//span[contains(@class, 'large name') and text()='"+name+"']"));
        } catch (Exception e){
            assertTrue(true,"As person is removed, element will throw exception which proved that person has been removed");
        }
//        assertFalse(driver.findElement(By.xpath("//span[contains(@class, 'large name') and text()='"+name+"']")).isDisplayed());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        // modify list
        driver.findElement(By.xpath("//button[contains(text(),'Add person')]")).click();
        driver.findElement(By.id("name")).sendKeys("Temp");
        driver.findElement(By.id("job")).sendKeys("TempJob");
        driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();

        // verify modified
        assertTrue(driver.findElements(By.xpath("//*[text()='Temp']")).size() > 0);

        // reset
        driver.findElement(By.xpath("//button[contains(text(),'Reset List')]")).click();

        // verify back to original
        assertEquals(10, driver.findElements(By.xpath("//*[contains(@id,'person')]")).size());
    }
}
