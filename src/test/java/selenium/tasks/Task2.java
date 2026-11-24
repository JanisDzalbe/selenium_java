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

        // FindElement(): asks: Does the element exist in the DOM?
        // isDisplayed(): asks: Is the element displayed
        // isEnabled() asks: Is the element enabled for interaction?
        // click()) asks: Perform a click on the element
        // Assert(Assertion) - checks the Boolean result. It does not change webpage.

        // Create a List (список) that stores WebElement objects (элементы веб-страницы).
        // 'buttons' is the name of our variable.
        // driver.findElements() searches the webpage and returns ALL matching elements.
        // By.cssSelector() tells Selenium to use a CSS selector to find elements.
        // ".w3-btn.w3-white" finds elements that have BOTH CSS classes.
        // We expect to find two buttons: "Add person" and "Reset List".

        // Find Add person button using its onclick attribute.
        WebElement addPersonButton = driver.findElement(
                By.cssSelector("button[onclick='openModalForAddPersonWithJob()']")
        );

// Check that Add person button is displayed.
        assertTrue(addPersonButton.isDisplayed());

// Check that Add person button is enabled.
        assertTrue(addPersonButton.isEnabled());


// Find Reset List button using its onclick attribute.
        WebElement resetListButton = driver.findElement(
                By.cssSelector("button[onclick='resetListOfPeople()']")
        );

// Check that Reset List button is displayed.
        assertTrue(resetListButton.isDisplayed());

// Check that Reset List button is enabled.
        assertTrue(resetListButton.isEnabled());


    /*
        List — a collection (list).
        WebElement — web elements.
        people — the name of our variable.
        findElements() — finds all elements that match the locator.
        By.cssSelector() — using a CSS selector.
     */
        List<WebElement> people = driver.findElements(
                By.cssSelector("YOUR_SELECTOR")
        );
        // Check that the list contains exactly 10 people.
        assertEquals(10, people.size());

        // Expected names.
        String[] expectedNames = {
                "Mike", "Jill", "Jane", "John", "Sarah",
                "Carlos", "Emily", "David", "Maria", "Alex"
        };

        // Expected jobs.
        String[] expectedJobs = {
                "Web Designer", "Support", "Accountant",
                "Software Engineer", "Product Manager",
                "Data Analyst", "UX Designer", "Project Manager",
                "QA Engineer", "DevOps Engineer"
        };
    }
    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        /*
        // Find Add person button using its onclick attribute.
        WebElement addPersonButton = driver.findElement(
                By.cssSelector("button[onclick='openModalForAddPersonWithJob()']"));
         */
        // Click on add person button
        driver.findElement(By.id("addPersonBtn")).click();

        // Enter person's name
        driver.findElement(By.id("name")).sendKeys("Naruto");

        // Enter person's job
        driver.findElement(By.id("job")).sendKeys("Ninja");

        // Click on Add modal_buton
        driver.findElement(By.id("modal_button")).click();

        // Check that Naruto is displayed
        assertTrue(driver.findElement(By.xpath("//*[text()='Naruto']")).isDisplayed());
        // Check that Ninja(profession) is displayed
        assertTrue(driver.findElement(By.xpath("//*[text()='Ninja']")).isDisplayed());

    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        // Find Mike in the list
        WebElement mike = driver.findElement(By.xpath("//div[.//span[text()='Mike']]"));
        // Click on the pencil icon in front of the Mike
        mike.findElement(By.className("fa-pencil")).click();
        // Check person's name
        assertEquals("Mike", driver.findElement(By.id("name")).getAttribute("value"));

        // Check person's job
        assertEquals("Web Designer", driver.findElement(By.id("job")).getAttribute("value"));

        // Changing job. Clear job field
        driver.findElement(By.id("job")).clear();

        // Enter a new job
        driver.findElement(By.id("job")).sendKeys("Ninja");

        // Click on edit button(which is modal)
        driver.findElement(By.id("modal_button")).click();

        // Find Mike in the list
        WebElement person = driver.findElement(By.xpath("//span[text()='Mike']/.."));

        // Check that Naruto's job as a Ninja is updated
        assertEquals("Ninja", person.findElement(By.className("job")).getText());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        driver.findElement(By.className("w3-closebtn")).click();
        // Check that Mike is removed from the list.
        assertTrue(driver.findElements(By.xpath("//span[text()='Mike']")).isEmpty());
       // assertFalse(driver.findElement(By.xpath("//span[text()='Mike']")).isDisplayed());
    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries

        // Find Mike in the list.
        WebElement mike = driver.findElement(By.xpath("//span[text()='Mike']/.."));

        // Click on the delete icon.
        mike.findElement(By.className("closebtn")).click();

        // Check that Mike is removed.
        assertTrue(driver.findElements(By.xpath("//span[text()='Mike']")).isEmpty());

        // Click on Reset List button.
        driver.findElement(By.xpath("//button[text()='Reset List']")).click();

        // Find the people list by ID.
        WebElement list = driver.findElement(By.id("listOfPeople"));

        // Find all people inside the list.
        List<WebElement> people = list.findElements(By.className("w3-padding-16"));

        // Check that the list contains 10 original entries.
        assertEquals(10, people.size());


        /*
        driver.findElement(By.className("w3-closebtn")).click();
        assertTrue(driver.findElements(By.xpath("//span[text()='Mike']")).isEmpty());
        driver.findElement(By.id("addPersonBtn")).click();
        // Find all people in the list.
        // Find all people in the list.
        // Find the people list by ID.
        WebElement list = driver.findElement(By.id("listOfPeople"));
        // Find all people inside the list.
        List<WebElement> people = list.findElements(By.className("w3-padding-16"));
        // Check that the list contains 10 original entries.
        assertEquals(10, people.size());
         */
    }
}

