package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.FormPage;
import selenium.pages.ListPage;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//import pages.FormPage;
//import pages.ListPage;

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
        driver.get("https://acctabootcamp.github.io/site/tasks/list_of_people_with_jobs");
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
        FormPage formPage = new FormPage(driver);

        List<String[]> oldList = listPage.getPeopleList();

        listPage.clickAddButton();
        formPage.waitForm();
        formPage.fillForm("John", "Teacher");
        formPage.submitForm();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementLocated(By.id("listOfPeople"), "John"));

        List<String[]> newList = listPage.getPeopleList();

        assertEquals(oldList.size() + 1, newList.size());

        boolean found = newList.stream()
                .anyMatch(p -> p[0].equals("John") && p[1].equals("Teacher"));
        assertTrue(found, "'John - Teacher' not found in list");
        for (String[] person : oldList) {
            assertTrue(newList.stream().anyMatch(p -> p[0].equals(person[0]) && p[1].equals(person[1])));
        }

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
        List<String[]> oldList = listPage.getPeopleList();
        listPage.clickEditButton(0);

        FormPage formPage = new FormPage(driver);
        formPage.waitForm();
        formPage.fillForm("Solvita", "Farmer");
        formPage.submitForm();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementLocated(By.id("listOfPeople"), "Solvita"));

        List<String[]> newList = listPage.getPeopleList();
        assertTrue(newList.stream().anyMatch(p -> p[0].equals("Solvita") && p[1].equals("Farmer")));


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
        List<String[]> oldList = listPage.getPeopleList();
        listPage.clickEditButton(0);
        FormPage formPage = new FormPage(driver);
        formPage.waitForm();
        formPage.clickCancel();

        List<String[]> newList = listPage.getPeopleList();


        assertEquals(oldList.size(), newList.size(), "List size should not change");
        for (int i = 0; i < oldList.size(); i++) {
            assertArrayEquals(oldList.get(i), newList.get(i), "Person at index " + i + " changed!");
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

        ListPage listPage = new ListPage(driver);
        List<String[]> oldList = listPage.getPeopleList();
        String[] personToDelete = oldList.get(0);
        listPage.clickDeleteButton(0);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.numberOfElementsToBeLessThan(
                        By.cssSelector("ul#listOfPeople > li"), oldList.size()
                ));

        List<String[]> newList = listPage.getPeopleList();

        assertEquals(oldList.size() - 1, newList.size());
        boolean stillExists = newList.stream().anyMatch(p ->
                p[0].equals(personToDelete[0]) && p[1].equals(personToDelete[1])
        );
        assertFalse(stillExists);
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
        List<String[]> oldList = listPage.getPeopleList();
        int originalCount = oldList.size();

        for (int i = 0; i < originalCount; i++) {
            listPage.clickDeleteButton(0);
            listPage.waitUntilListSizeChanges(originalCount - (i + 1));
        }

        assertFalse(listPage.isPeopleListPresent());
        assertTrue(listPage.isAddButtonPresent());

    }
}
