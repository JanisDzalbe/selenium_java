package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        List<WebElement> addPersonButtons = driver.findElements(By.xpath("//button[normalize-space()='Add person']"));
        List<WebElement> resetListButtons = driver.findElements(By.xpath("//button[normalize-space()='Reset List']"));

        assertEquals(2, addPersonButtons.size());
        assertEquals(2, resetListButtons.size());

        for (WebElement button : addPersonButtons) {
            assertTrue(button.isDisplayed());
            assertTrue(button.isEnabled());
        }
        for (WebElement button : resetListButtons) {
            assertTrue(button.isDisplayed());
            assertTrue(button.isEnabled());
        }

        String[][] expected = {
                {"Mike", "Web Designer"},
                {"Jill", "Support"},
                {"Jane", "Accountant"},
                {"John", "Software Engineer"},
                {"Sarah", "Product Manager"},
                {"Carlos", "Data Analyst"},
                {"Emily", "UX Designer"},
                {"David", "Project Manager"},
                {"Maria", "QA Engineer"},
                {"Alex", "DevOps Engineer"}

        };
        List<WebElement> rows = driver.findElements(By.cssSelector("#listOfPeople li"));
        assertEquals(expected.length, rows.size());

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i][0], rows.get(i).findElement(By.cssSelector(".name")).getText());
            assertEquals(expected[i][1], rows.get(i).findElement(By.cssSelector(".job")).getText());

        }


    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        driver.findElement(By.xpath("//button[normalize-space()='Add person']")).click();

        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Peter");
        driver.findElement(By.xpath("//input[@id='job']")).sendKeys("Tester");
        driver.findElement(By.xpath("//button[@id='modal_button']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul[@id='listOfPeople']//li"), 11));

        String name = driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[11]//span[contains(@class,'name')]")).getText();
        String job = driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[11]//span[@class='job']")).getText();

        assertEquals("Peter", name);
        assertEquals("Tester", job);

    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[1]//span[contains(@class,'editbtn')]")).click();

        wait.until(ExpectedConditions.attributeToBe(By.xpath("//input[@id='name']"), "value", "Mike"));
        assertEquals("Mike", driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value"));
        assertEquals("Web Designer", driver.findElement(By.xpath("//input[@id='job']")).getAttribute("value"));

        driver.findElement(By.xpath("//input[@id='job']")).clear();
        driver.findElement(By.xpath("//input[@id='job']")).sendKeys("Senior Web Designer");

        assertEquals("Edit", driver.findElement(By.xpath("//button[@id='modal_button']")).getText());
        driver.findElement(By.xpath("//button[@id='modal_button']")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul[@id='listOfPeople']//li"), 10));

        String name = driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[1]//span[contains(@class,'name')]")).getText();
        String job = driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[1]//span[@class='job']")).getText();

        assertEquals("Mike", name);
        assertEquals("Senior Web Designer", job);

    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        assertEquals(1, driver.findElements(By.xpath("//ul[@id='listOfPeople']//span[@class='w3-xlarge name' and text()='Jill']")).size());
        assertEquals(10, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());

        driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[2]//span[contains(@class,'closebtn')]")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul[@id='listOfPeople']//li"), 9));

        assertEquals(0, driver.findElements(By.xpath("//ul[@id='listOfPeople']//span[@class='w3-xlarge name' and text()='Jill']")).size());
        assertEquals(9, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());


    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[3]//span[contains(@class,'closebtn')]")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul[@id='listOfPeople']//li"), 9));
        assertEquals(9, driver.findElements(By.xpath("//ul[@id='listOfPeople']//li")).size());
        assertEquals(0, driver.findElements(By.xpath("//ul[@id='listOfPeople']//span[@class='w3-xlarge name' and text()='Jane']")).size());

        driver.findElement(By.xpath("(//button[normalize-space()='Reset List'])[1]")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//ul[@id='listOfPeople']//li"), 10));

        String[][] expected = {
                {"Mike", "Web Designer"},
                {"Jill", "Support"},
                {"Jane", "Accountant"},
                {"John", "Software Engineer"},
                {"Sarah", "Product Manager"},
                {"Carlos", "Data Analyst"},
                {"Emily", "UX Designer"},
                {"David", "Project Manager"},
                {"Maria", "QA Engineer"},
                {"Alex", "DevOps Engineer"}
        };

        for (int i = 0; i < expected.length; i++) {
            String name = driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[" + (i + 1) + "]//span[contains(@class,'name')]")).getText();
            String job = driver.findElement(By.xpath("(//ul[@id='listOfPeople']//li)[" + (i + 1) + "]//span[@class='job']")).getText();
            assertEquals(expected[i][0], name);
            assertEquals(expected[i][1], job);
        }
    }

}
