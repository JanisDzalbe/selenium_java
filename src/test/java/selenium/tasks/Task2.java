
package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.utility.BootcampUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {

    WebDriver driver;

    @BeforeEach
    public void openPage() {
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialPeopleList() {

        By addButton = By.xpath("//button[normalize-space()='Add person']");
        By resetButton = By.xpath("//button[normalize-space()='Reset List']");

        assertTrue(driver.findElement(addButton).isDisplayed());
        assertTrue(driver.findElement(addButton).isEnabled());

        assertTrue(driver.findElement(resetButton).isDisplayed());
        assertTrue(driver.findElement(resetButton).isEnabled());

        String[] names = {
                "Mike", "Jill", "Jane", "John", "Sarah",
                "Carlos", "Emily", "David", "Maria", "Alex"
        };

        String[] jobs = {
                "Web Designer", "Support", "Accountant",
                "Software Engineer", "Product Manager",
                "Data Analyst", "UX Designer", "Project Manager",
                "QA Engineer", "DevOps Engineer"
        };

        By namesLocator = By.xpath(
                "//*[not(*) and (normalize-space()='Mike' " +
                        "or normalize-space()='Jill' " +
                        "or normalize-space()='Jane' " +
                        "or normalize-space()='John' " +
                        "or normalize-space()='Sarah' " +
                        "or normalize-space()='Carlos' " +
                        "or normalize-space()='Emily' " +
                        "or normalize-space()='David' " +
                        "or normalize-space()='Maria' " +
                        "or normalize-space()='Alex')]"
        );

        List<WebElement> people = driver.findElements(namesLocator);

        assertEquals(10, people.size());

        for (int i = 0; i < names.length; i++) {

            By nameLocator = By.xpath(
                    "//*[not(*) and normalize-space()='" + names[i] + "']"
            );

            By jobLocator = By.xpath(
                    "//*[not(*) and normalize-space()='" + jobs[i] + "']"
            );

            assertTrue(driver.findElement(nameLocator).isDisplayed());
            assertTrue(driver.findElement(jobLocator).isDisplayed());
        }
    }

    @Test
    public void addNewPerson() {

        driver.findElement(
                By.xpath("//button[normalize-space()='Add person']")
        ).click();

        driver.findElement(By.id("name")).sendKeys("Oliver");
        driver.findElement(By.id("job")).sendKeys("QA Engineer Intern");

        driver.findElement(
                By.xpath("//button[normalize-space()='Add']")
        ).click();

        assertTrue(driver.findElement(
                By.xpath("//*[not(*) and normalize-space()='Oliver']")
        ).isDisplayed());

        assertTrue(driver.findElement(
                By.xpath("//*[not(*) and normalize-space()='QA Engineer Intern']")
        ).isDisplayed());
    }

    @Test
    public void editExistingPerson() {

        WebElement janeCard = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and normalize-space()='Jane']]")
        );

        janeCard.findElement(By.cssSelector("span.editbtn")).click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField = driver.findElement(By.id("job"));

        assertEquals("Jane", nameField.getAttribute("value"));
        assertEquals("Accountant", jobField.getAttribute("value"));

        jobField.clear();
        jobField.sendKeys("Senior Accountant");

        driver.findElement(
                By.xpath("//button[normalize-space()='Edit']")
        ).click();

        WebElement updatedJaneCard = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and normalize-space()='Jane']]")
        );

        assertEquals(
                "Senior Accountant",
                updatedJaneCard.findElement(By.cssSelector("span.job")).getText()
        );
    }

    @Test
    public void removeExistingPerson() {

        WebElement carlosCard = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and normalize-space()='Carlos']]")
        );

        carlosCard.findElement(By.cssSelector("span.closebtn")).click();

        assertTrue(driver.findElements(
                By.xpath("//li[.//span[contains(@class,'name') and normalize-space()='Carlos']]")
        ).isEmpty());

        List<WebElement> remainingPeople = driver.findElements(
                By.cssSelector("#listOfPeople li")
        );

        assertEquals(9, remainingPeople.size());
    }

    @Test
    public void resetList() {

        // 1. Find Carlos's employee card
        WebElement carlosCard = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and normalize-space()='Carlos']]")
        );

        // 2. Remove Carlos
        carlosCard.findElement(By.cssSelector("span.closebtn")).click();

        // 3. Verify that the list is modified
        assertTrue(driver.findElements(
                By.xpath("//li[.//span[contains(@class,'name') and normalize-space()='Carlos']]")
        ).isEmpty());

        assertEquals(
                9,
                driver.findElements(By.cssSelector("#listOfPeople li")).size()
        );

        // 4. Click Reset List
        driver.findElement(
                By.xpath("//button[normalize-space()='Reset List']")
        ).click();

        // 5. Verify that all 10 original people are restored
        assertEquals(
                10,
                driver.findElements(By.cssSelector("#listOfPeople li")).size()
        );

        // 6. Verify Carlos is back with his original job
        WebElement restoredCarlos = driver.findElement(
                By.xpath("//li[.//span[contains(@class,'name') and normalize-space()='Carlos']]")
        );

        assertEquals(
                "Data Analyst",
                restoredCarlos.findElement(By.cssSelector("span.job")).getText()
        );
    }
}
