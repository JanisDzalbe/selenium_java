package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import selenium.pages.FormPage;
import selenium.pages.ListPage;
import selenium.pages.Person;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Bonus {
    WebDriver driver;
//	ListPage listPage = PageFactory.initElements(driver, ListPage.class);
//     should contain what you see when you just open the page (the table with names/jobs)
//	FormPage formPage = PageFactory.initElements(driver, FormPage.class);
//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)

//    Bonus:
//    try storing people via an Object/separate class

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
    public void addPerson() {
        /* TODO:
         * implement adding new person using page object
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */
        ListPage listPage = new ListPage(driver);

        List<Person> before = listPage.getPeople();

        Person newPerson = new Person("Ravindu", "QA Engineer");

        FormPage formPage = listPage.clickAddPerson();
        ListPage afterAddPage = formPage.submitAdd(newPerson);

        List<Person> after = afterAddPage.getPeople();

        assertEquals(before.size() + 1, after.size());

        assertTrue(after.containsAll(before));

        assertTrue(after.contains(newPerson));
    }

    @Test
    public void editPerson() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link
         * check the list again and that 2 people stayed the same and the one used was changed
         */
        ListPage listPage = new ListPage(driver);
        List<Person> before = listPage.getPeople();

        Person original = before.get(0);
        Person updated = new Person(original.getName() + " Jr.", "Senior " + original.getJob());

        FormPage formPage = listPage.clickEditFor(original);
        ListPage afterEditPage = formPage.submitEdit(updated);

        List<Person> after = afterEditPage.getPeople();

        assertEquals(before.size(), after.size());

        List<Person> othersBefore = new ArrayList<>(before);
        othersBefore.remove(original);

        assertTrue(after.containsAll(othersBefore));

        assertTrue(after.contains(updated));
        assertFalse(after.contains(original));
    }

    @Test
    public void editPersonCancel() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link then click "Cancel" on edit form instead of "Edit"
         * check the list again and that no changes where made
         */
        ListPage listPage = new ListPage(driver);
        List<Person> before = listPage.getPeople();

        Person target = before.get(0);

        FormPage formPage = listPage.clickEditFor(target);
        formPage.cancel();

        List<Person> after = listPage.getPeople();

        assertEquals(before.size(), after.size());
        assertTrue(after.containsAll(before));
        assertTrue(before.containsAll(after));
    }


    @Test
    public void deletePerson() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */
        ListPage listPage = new ListPage(driver);
        List<Person> before = listPage.getPeople();

        Person toDelete = before.get(0);

        listPage.clickDeleteFor(toDelete);

        List<Person> after = listPage.getPeople();

        assertEquals(before.size() - 1, after.size());
        assertFalse(after.contains(toDelete));
    }


    @Test
    public void deletePersonAll() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no no table on page, but the button Add is still present and working
         */
        ListPage listPage = new ListPage(driver);
        List<Person> before = listPage.getPeople();

        for (Person p : before) {
            listPage.clickDeleteFor(p);
        }

        assertFalse(listPage.isTablePresent());

        FormPage formPage = listPage.clickAddPerson();
        ListPage afterAddPage = formPage.submitAdd(new Person("New Person", "New Job"));

        assertTrue(afterAddPage.isTablePresent());
        assertFalse(afterAddPage.getPeople().isEmpty());
    }
}
