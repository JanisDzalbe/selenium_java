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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2 {
    WebDriver driver;

    private final By addPersonButton = By.xpath("//button[contains(.,'Add person')]");
    private final By resetListButton = By.xpath("//button[contains(.,'Reset List')]");
    private final By addButton = By.xpath("//button[normalize-space(.)='Add']");

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

    private WebElement findPerson(String name, String job) {
        return driver.findElement(
                By.xpath("//*[contains(.,'" + name + "') and contains(.,'" + job + "')]")
        );
    }

    private boolean isPersonPresent(String name, String job) {
        List<WebElement> found = driver.findElements(
                By.xpath("//*[contains(.,'" + name + "') and contains(.,'" + job + "')]")
        );
        return !found.isEmpty();
    }

    private WebElement nameInput() {
        return driver.findElement(By.xpath("//label[contains(.,'Name')]/following-sibling::input"));
    }

    private WebElement jobInput() {
        return driver.findElement(By.xpath("//label[contains(.,'Job')]/following-sibling::input"));
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

        WebElement addBtn = driver.findElement(addPersonButton);
        WebElement resetBtn = driver.findElement(resetListButton);

        assertTrue(addBtn.isDisplayed());
        assertTrue(addBtn.isEnabled());
        assertTrue(resetBtn.isDisplayed());
        assertTrue(resetBtn.isEnabled());

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

        for (String[] person : expected) {
            String name = person[0];
            String job = person[1];
            WebElement row = findPerson(name, job);
            assertTrue(row.isDisplayed(), "Row for " + name + " not found");
        }
    }

    @Test
    public void addNewPerson() throws Exception {
//         TODO:
//          click "Add person"
//          fill "Name" and "Job" fields
//          click "Add"
//          check that new person is added to the list with correct name and job
        String newName = "Ravindu";
        String newJob = "Test Engineer";

        driver.findElement(addPersonButton).click();

        nameInput().clear();
        nameInput().sendKeys(newName);

        jobInput().clear();
        jobInput().sendKeys(newJob);

        driver.findElement(addButton).click();

        assertTrue(isPersonPresent(newName, newJob));

    }

    @Test
    public void editExistingPerson() throws Exception {
//         TODO:
//          click pencil icon for an existing person
//          check values in "Name" and "Job" fields
//          change "Job" field
//          click "Edit"
//          check that the person is updated in the list with new job
        String originalName = "Mike";
        String originalJob = "Web Designer";
        String newJob = "Senior Web Designer";

        WebElement personRow = driver.findElement(
                By.xpath("//*[contains(.,'" + originalName + "') and contains(.,'" + originalJob + "')]")
        );

        WebElement pencil = personRow.findElement(By.xpath(".//button[1]"));
        pencil.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement jobField  = driver.findElement(By.id("job"));

        nameField.clear();
        nameField.sendKeys(originalName);

        jobField.clear();
        jobField.sendKeys(newJob);

        driver.findElement(By.id("modal_button")).click();

        WebElement updatedRow = driver.findElement(
                By.xpath("//*[contains(.,'" + originalName + "') and contains(.,'" + newJob + "')]")
        );
        assertTrue(updatedRow.isDisplayed());
    }

    @Test
    public void removeExistingPerson() throws Exception {
//         TODO:
//          click cross (x) icon for an existing person
//          check that the person is removed from the list
        String name = "Carlos";
        String job  = "Data Analyst";

        WebElement row = driver.findElement(
                By.xpath("//li[.//span[text()='" + name + "'] and .//span[text()='" + job + "']]")
        );

        WebElement deleteBtn = row.findElement(By.xpath(".//span[contains(@onclick,'deletePerson')]"));
        deleteBtn.click();

        boolean stillPresent = driver.findElements(
                By.xpath("//li[.//span[text()='" + name + "'] and .//span[text()='" + job + "']]")
        ).size() > 0;

        assertTrue(!stillPresent, "Person should be removed but still exists!");

    }

    @Test
    public void resetList() throws Exception {
//         TODO:
//          modify the list in any way (add, edit or remove a person)
//          check that the list is modified
//          click "Reset List"
//          check that the list is back to initial state with 10 original entries
        String newName = "Temp Person";
        String newJob = "Temp Job";

        driver.findElement(addPersonButton).click();
        nameInput().sendKeys(newName);
        jobInput().sendKeys(newJob);
        driver.findElement(addButton).click();

        assertTrue(isPersonPresent(newName, newJob));

        driver.findElement(resetListButton).click();

        assertTrue(!isPersonPresent(newName, newJob));

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

        for (String[] person : expected) {
            assertTrue(isPersonPresent(person[0], person[1]),
                    "Row for " + person[0] + " not found after reset");
        }
    }
}
