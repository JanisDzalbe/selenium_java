package selenium.tasks;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.tasks.pages.FormPage;
import selenium.tasks.pages.ListPage;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Task3Bonus {
    WebDriver driver;
    ListPage listPage;
    FormPage formPage;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + (System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : ""));

        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");

        listPage = new ListPage(driver);
        formPage = new FormPage(driver);
    }

    @AfterEach
    public void closeBrowser() { driver.quit(); }

    @Test
    public void addPerson() {
        List<Person> before = listPage.getPeopleList();

        listPage.clickAddPerson();
        formPage.fillForm("New Guy", "Testing");
        formPage.submit();

        List<Person> after = listPage.getPeopleList();
        assertEquals(before.size() + 1, after.size());
        assertTrue(after.contains(new Person("New Guy", "Testing")));
    }

    @Test
    public void editPerson() {
        List<Person> before = listPage.getPeopleList();

        listPage.clickEditForPerson(0); // Edit first person
        formPage.fillForm("Updated Name", "Updated Job");
        formPage.submit();

        List<Person> after = listPage.getPeopleList();
        assertEquals(before.size(), after.size());
        assertEquals("Updated Name", after.get(0).getName());
    }

    @Test
    public void editPersonCancel() {
        List<Person> before = listPage.getPeopleList();

        listPage.clickEditForPerson(0);
        formPage.fillForm("I should not exist", "Nope");
        formPage.cancel();

        List<Person> after = listPage.getPeopleList();
        assertEquals(before, after, "List should be identical after cancel");
    }

    @Test
    public void deletePersonAll() {
        List<Person> current = listPage.getPeopleList();
        while(!current.isEmpty()) {
            listPage.clickDeleteForPerson(0);
            current = listPage.getPeopleList();
        }

        assertTrue(true);
        // Verify Add button is still there
        listPage.clickAddPerson();
        assertTrue(driver.findElement(By.id("name")).isDisplayed());
    }
}