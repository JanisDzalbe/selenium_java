package selenium.tasks;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pages.ListPage;
import pages.FormPage;
import pages.Person;

import java.io.File;
import java.util.List;

public class Task3Bonus {

    WebDriver driver;
    ListPage listPage;
    FormPage formPage;

    @BeforeEach
    public void openPage() {
        String libPath = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libPath + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");

        listPage = PageFactory.initElements(driver, ListPage.class);
        formPage = PageFactory.initElements(driver, FormPage.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void addPerson() {

        List<Person> before = listPage.getAllPeople();

        listPage.clickAddPerson();
        formPage.fillForm("Juris", "Cool guy");
        formPage.submit();

        List<Person> after = listPage.getAllPeople();

        assertEquals(before.size() + 1, after.size());
        assertEquals("Juris", after.get(after.size() - 1).getName());
        assertEquals("Cool guy", after.get(after.size() - 1).getJob());
    }

    @Test
    public void editPerson() {
        List<Person> before = listPage.getAllPeople();
        int index = 1;

        listPage.clickEditPerson(index);
        formPage.fillForm("Peter Parker", "Spiderman");
        formPage.submit();

        List<Person> after = listPage.getAllPeople();

        assertEquals("Peter Parker", after.get(index).getName());
        assertEquals("Spiderman",   after.get(index).getJob());
    }

    @Test
    public void editPersonCancel() {
        List<Person> before = listPage.getAllPeople();

        listPage.clickEditPerson(0);

        formPage.fillForm("SHOULD NOT SAVE", "SHOULD NOT SAVE");
        formPage.cancel();

        List<Person> after = listPage.getAllPeople();

        assertEquals(before.size(), after.size());
        assertEquals(before.get(0).getName(), after.get(0).getName());
        assertEquals(before.get(0).getJob(),  after.get(0).getJob());
    }

    @Test
    public void deletePerson() {
        List<Person> before = listPage.getAllPeople();
        assertTrue(before.size() > 0);

        listPage.deletePerson(0);

        List<Person> after = listPage.getAllPeople();
        assertEquals(before.size() - 1, after.size());
    }

    @Test
    public void deletePersonAll() {

        assertTrue(listPage.getAllPeople().size() > 0);

        while (!listPage.isListEmpty()) {
            listPage.deletePerson(0);
        }

        assertTrue(listPage.isListEmpty());

        driver.findElement(By.id("addPersonBtn")).click();
        assertTrue(driver.findElement(By.id("name")).isDisplayed());
    }
}
