package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
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
        if (driver != null) {
            driver.quit();
        }
    }

    private List<WebElement> getPeopleCards() {
        return driver.findElements(By.xpath("//li[starts-with(@id,'person')]"));
    }

    private WebElement findPersonByName(String name) {
        for (WebElement person : getPeopleCards()) {
            if (person.getText().contains(name)) {
                return person;
            }
        }
        return null;
    }


    @Test
    public void initialPeopleList() {
        WebElement addPersonButton = driver.findElement(By.id("addPersonBtn"));
        WebElement resetListButton = driver.findElement(By.xpath("//button[text()='Reset List']"));

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        List<WebElement> persons = getPeopleCards();
        assertEquals(10, persons.size());

        assertEquals("Web Designer", findPersonByName("Mike").findElement(By.className("job")).getText());
        assertEquals("Support", findPersonByName("Jill").findElement(By.className("job")).getText());
        assertEquals("Accountant", findPersonByName("Jane").findElement(By.className("job")).getText());
        assertEquals("Software Engineer", findPersonByName("John").findElement(By.className("job")).getText());
        assertEquals("Product Manager", findPersonByName("Sarah").findElement(By.className("job")).getText());
        assertEquals("Data Analyst", findPersonByName("Carlos").findElement(By.className("job")).getText());
        assertEquals("UX Designer", findPersonByName("Emily").findElement(By.className("job")).getText());
        assertEquals("Project Manager", findPersonByName("David").findElement(By.className("job")).getText());
        assertEquals("QA Engineer", findPersonByName("Maria").findElement(By.className("job")).getText());
        assertEquals("DevOps Engineer", findPersonByName("Alex").findElement(By.className("job")).getText());
    }

    @Test
    public void addNewPerson() {
        driver.findElement(By.id("addPersonBtn")).click();

        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Anna");

        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys("Business Analyst");

        driver.findElement(By.xpath("//button[text()='Add']")).click();

        WebElement newPerson = findPersonByName("Anna");
        assertNotNull(newPerson);
        assertEquals("Business Analyst", newPerson.findElement(By.className("job")).getText());
    }

    @Test
    public void editExistingPerson() {
        driver.findElement(By.xpath("//span[contains(@onclick,'openModalForEditPersonWithJob(0)')]")).click();

        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement jobInput = driver.findElement(By.id("job"));

        assertEquals("Mike", nameInput.getDomProperty("value"));
        assertEquals("Web Designer", jobInput.getDomProperty("value"));

        jobInput.clear();
        jobInput.sendKeys("Senior Software Engineer");

        driver.findElement(By.xpath("//button[text()='Edit']")).click();

        WebElement updatedJohn = driver.findElement(By.id("person0"));
        assertEquals("Senior Software Engineer", updatedJohn.findElement(By.className("job")).getText());
    }

    @Test
    public void removeExistingPerson() {
        int before = getPeopleCards().size();
        driver.findElement(By.xpath("//span[contains(@onclick,'deletePerson(0)')]")).click();

        int after = getPeopleCards().size();
        assertEquals(before - 1, after);
    }

    @Test
    public void resetList() {
        driver.findElement(By.xpath("//span[contains(@onclick,'deletePerson(2)')]")).click();

        assertNotEquals(10, getPeopleCards().size());

        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        assertEquals(10, getPeopleCards().size());
        assertEquals("Web Designer",
                driver.findElement(By.id("person0")).findElement(By.className("job")).getText());
    }
}
