package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import selenium.customDataTable.PersonData;
import selenium.pages.FormPage;
import selenium.pages.ListPage;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Bonus {
    WebDriver driver;
	ListPage listPage;
//     should contain what you see when you just open the page (the table with names/jobs)
	FormPage formPage;
//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)

//    Bonus:
//    try storing people via an Object/separate class

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
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
        /* TODO:
         * implement adding new person using page object
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */
        List<PersonData> listOfOldPeople = listPage.getPeople();
        listPage.clickAddPerson();
        formPage.enterNameJobAndClickAdd("Jusus", "Our lord and savior");
        List<PersonData> listOfNewPeople = listPage.getPeople();

        assertEquals(listOfOldPeople.size(), (listOfNewPeople.size() - 1));
        for (int i = 0; i < listOfOldPeople.size(); i++) {
            assertEquals(listOfOldPeople.get(i).getName(), listOfNewPeople.get(i).getName());
            assertEquals(listOfOldPeople.get(i).getJob(), listOfNewPeople.get(i).getJob());
        }
        assertEquals("Jusus", listOfNewPeople.get(listOfNewPeople.size()-1).getName());
        assertEquals("Our lord and savior", listOfNewPeople.get(listOfNewPeople.size()-1).getJob());
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
        List<PersonData> listOfOldPeople = listPage.getPeople();
        listPage.clickEditPerson("John");
        formPage.enterJobAndClickEdit("CEO");
        List<PersonData> listOfNewPeople = listPage.getPeople();

        assertTrue(listOfNewPeople.contains(new PersonData("John", "CEO")));
        for (int i = 0; i < 2; i++) {
            if (listOfNewPeople.get(i).equals(new PersonData("John", "CEO"))) {i--; continue;}
            assertEquals(listOfOldPeople.get(i).getName(), listOfNewPeople.get(i).getName());
            assertEquals(listOfOldPeople.get(i).getJob(), listOfNewPeople.get(i).getJob());
        }
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
        List<PersonData> listOfOldPeople = listPage.getPeople();
        listPage.clickEditPerson("Alex");
        formPage.enterJob("Nuclear bomb");
        formPage.clickBackButton();
        List<PersonData> listOfNewPeople = listPage.getPeople();

        assertEquals(listOfNewPeople.size(), listOfOldPeople.size());
        for (int i = 0; i < listOfOldPeople.size(); i++) {
            if (listOfNewPeople.get(i).getName().equals("Alex")) {
                assertNotEquals("Nuclear bomb", listOfNewPeople.get(i).getJob());
                break;
            }
            assertEquals(listOfOldPeople.get(i).getName(), listOfNewPeople.get(i).getName());
            assertEquals(listOfOldPeople.get(i).getJob(), listOfNewPeople.get(i).getJob());
        }
    }


    @Test
    public void deletePerson() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */
        List<PersonData> listOfOldPeople = listPage.getPeople();
        listPage.clickRemovePerson("David");
        List<PersonData> listOfNewPeople = listPage.getPeople();

        assertEquals(listOfNewPeople.size()+1, listOfOldPeople.size());
        assertFalse(listOfNewPeople.contains(new PersonData("David", "Project Manager")));
        for (int i = 0; i < 2; i++) {
            assertEquals(listOfOldPeople.get(i).getName(), listOfNewPeople.get(i).getName());
            assertEquals(listOfOldPeople.get(i).getJob(), listOfNewPeople.get(i).getJob());
        }
    }


    @Test
    public void deletePersonAll() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no no table on page, but the button Add is still present and working
         */
        List<PersonData> listOfOldPeople = listPage.getPeople();
        for (PersonData person : listOfOldPeople) {
            listPage.clickRemovePerson(person.getName());
        }
        List<PersonData> listOfNewPeople = listPage.getPeople();

        assertEquals(0, listOfNewPeople.size());

        listPage.clickAddPerson();
        formPage.enterNameJobAndClickAdd("Bob", "Builder");

        listOfNewPeople = listPage.getPeople();
        assertEquals(1, listOfNewPeople.size());
        assertEquals("Bob",  listOfNewPeople.get(0).getName());
        assertEquals("Builder", listOfNewPeople.get(0).getJob());
    }
}
