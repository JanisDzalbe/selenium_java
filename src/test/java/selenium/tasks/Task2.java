//package selenium.tasks;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.io.File;
//
//public class Task2 {
//    WebDriver driver;
//
//    @BeforeEach
//    public void openPage() {
//        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
//        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
//        driver = new ChromeDriver();
//        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
//    }
//
//    @AfterEach
//    public void closeBrowser() {
//        driver.quit();
//    }
//
//    @Test
//    public void initialPeopleList() throws Exception {
////         TODO:
////          check that "Add person" and "Reset List" buttons are displayed and enabled
////          check list of people contains 10 entries with correct names and jobs
////        Mike, Web Designer
////        Jill, Support
////        Jane, Accountant
////        John, Software Engineer
////        Sarah, Product Manager
////        Carlos, Data Analyst
////        Emily, UX Designer
////        David, Project Manager
////        Maria, QA Engineer
////        Alex, DevOps Engineer
//    }
//
//    @Test
//    public void addNewPerson() throws Exception {
////         TODO:
////          click "Add person"
////          fill "Name" and "Job" fields
////          click "Add"
////          check that new person is added to the list with correct name and job
//    }
//
//    @Test
//    public void editExistingPerson() throws Exception {
////         TODO:
////          click pencil icon for an existing person
////          check values in "Name" and "Job" fields
////          change "Job" field
////          click "Edit"
////          check that the person is updated in the list with new job
//    }
//
//    @Test
//    public void removeExistingPerson() throws Exception {
////         TODO:
////          click cross (x) icon for an existing person
////          check that the person is removed from the list
//    }
//
//    @Test
//    public void resetList() throws Exception {
////         TODO:
////          modify the list in any way (add, edit or remove a person)
////          check that the list is modified
////          click "Reset List"
////          check that the list is back to initial state with 10 original entries
//    }
//}

//
//package selenium.tasks;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.io.File;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class Task2 {
//    WebDriver driver;
//    WebDriverWait wait;
//
//
//    private final Map<String, String> initialPeople = new HashMap<>() {{
//        put("Mike", "Web Designer");
//        put("Jill", "Support");
//        put("Jane", "Accountant");
//        put("John", "Software Engineer");
//        put("Sarah", "Product Manager");
//        put("Carlos", "Data Analyst");
//        put("Emily", "UX Designer");
//        put("David", "Project Manager");
//        put("Maria", "QA Engineer");
//        put("Alex", "DevOps Engineer");
//    }};
//
//
//    private final By ADD_PERSON_BUTTON_LOCATOR = By.xpath("//button[text()='Add person']");
//    private final By RESET_LIST_BUTTON_LOCATOR = By.xpath("//button[text()='Reset List']");
//    private final By PEOPLE_LIST_LOCATOR = By.id("listOfPeople");
//    private final By PERSON_ENTRY_LOCATOR = By.xpath("//*[@id='listOfPeople']/div/li");
//    private final By MODAL_NAME_FIELD = By.id("nameInput");
//    private final By MODAL_JOB_FIELD = By.id("jobInput");
//    private final By MODAL_ADD_BUTTON = By.xpath("//button[text()='Add']");
//    private final By MODAL_EDIT_BUTTON = By.xpath("//button[text()='Edit']");
//    private final By PERSON_BY_NAME_XPATH = By.xpath("//span[@class='w3-xlarge name' and text()='%s']/..");
//
//
//    @BeforeEach
//    public void openPage() {
//
//        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
//
//        String driverPath = libWithDriversLocation + "chromedriver";
//        if (new File(driverPath + ".exe").exists()) {
//            System.setProperty("webdriver.chrome.driver", driverPath + ".exe");
//        } else {
//            System.setProperty("webdriver.chrome.driver", driverPath);
//        }
//
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
//    }
//
//    @AfterEach
//    public void closeBrowser() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//
//    private void checkCurrentList(Map<String, String> expected) {
//
//        wait.until(ExpectedConditions.presenceOfElementLocated(PERSON_ENTRY_LOCATOR));
//
//        List<WebElement> peopleElements = driver.findElements(PERSON_ENTRY_LOCATOR);
//        assertEquals(expected.size(), peopleElements.size(), "Количество элементов в списке не соответствует ожидаемому.");
//
//        for (WebElement personElement : peopleElements) {
//            String name = personElement.findElement(By.className("name")).getText();
//            String job = personElement.findElement(By.className("job")).getText();
//
//            assertTrue(expected.containsKey(name), "Найдено неожиданное имя: " + name);
//            assertEquals(expected.get(name), job,
//                    String.format("Должность для %s не соответствует: ожидается '%s', найдено '%s'",
//                            name, expected.get(name), job));
//        }
//    }
//
//    @Test
//    public void initialPeopleList() {
//        // Check that "Add person" and "Reset List" buttons are displayed and enabled
//        WebElement addBtn = driver.findElement(ADD_PERSON_BUTTON_LOCATOR);
//        WebElement resetBtn = driver.findElement(RESET_LIST_BUTTON_LOCATOR);
//
//        assertTrue(addBtn.isDisplayed(), "'Add person' button is not displayed.");
//        assertTrue(addBtn.isEnabled(), "'Add person' button is not enabled.");
//
//        assertTrue(resetBtn.isDisplayed(), "'Reset List' button is not displayed.");
//        assertTrue(resetBtn.isEnabled(), "'Reset List' button is not enabled.");
//
//        // Check the initial list of people
//        checkCurrentList(initialPeople);
//    }
//
//    @Test
//    public void addNewPerson() {
//        System.out.println("--- Тест: Добавление нового человека ---");
//        String newName = "Test Tester";
//        String newJob = "Automation Engineer";
//
//        // 1. Клик "Add person"
//        driver.findElement(ADD_PERSON_BUTTON_LOCATOR).click();
//
//        // 2. Ожидаем модальное окно
//        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_NAME_FIELD));
//
//        // 3. Заполняем поля
//        driver.findElement(MODAL_NAME_FIELD).sendKeys(newName);
//        driver.findElement(MODAL_JOB_FIELD).sendKeys(newJob);
//
//        // 4. Клик "Add"
//        driver.findElement(MODAL_ADD_BUTTON).click();
//
//        // 5. Проверка: ожидаем, что список увеличится на 1
//        wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, 11));
//
//        // 6. Проверка: новый человек добавлен с правильными данными
//        // Используем XPath для поиска нового человека по имени
//        By newPersonNameLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH.toString().replace("By.xpath: ", ""), newName));
//        WebElement newPersonElement = driver.findElement(newPersonNameLocator);
//
//        String actualName = newPersonElement.findElement(By.className("name")).getText();
//        String actualJob = newPersonElement.findElement(By.className("job")).getText();
//
//        assertEquals(newName, actualName, "Имя нового человека не совпадает.");
//        assertEquals(newJob, actualJob, "Должность нового человека не совпадает.");
//        System.out.println("Новый человек успешно добавлен и проверен.");
//    }
//
//    @Test
//    public void editExistingPerson() {
//        System.out.println("--- Тест: Редактирование существующего человека ---");
//        String personToEdit = "John";
//        String newJob = "Senior Software Architect";
//
//        // 1. Клик по иконке карандаша (edit icon) для John
//        By editIconLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH.toString().replace("By.xpath: ", ""), personToEdit) +
//                "/span[contains(@class, 'editbtn')]");
//
//        driver.findElement(editIconLocator).click();
//
//        // 2. Ожидаем модальное окно и проверяем значения
//        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_NAME_FIELD));
//
//        WebElement nameField = driver.findElement(MODAL_NAME_FIELD);
//        WebElement jobField = driver.findElement(MODAL_JOB_FIELD);
//
//        // Проверяем, что поля заполнены правильно
//        assertEquals(personToEdit, nameField.getAttribute("value"), "Поле имени в модале заполнено неправильно.");
//        assertEquals(initialPeople.get(personToEdit), jobField.getAttribute("value"), "Поле должности в модале заполнено неправильно.");
//
//        // 3. Изменяем поле "Job"
//        jobField.clear();
//        jobField.sendKeys(newJob);
//
//        // 4. Клик "Edit"
//        driver.findElement(MODAL_EDIT_BUTTON).click();
//
//        // 5. Проверка: человек обновлен с новой должностью
//        // Ожидаем исчезновения модального окна
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_NAME_FIELD));
//
//        By updatedPersonJobLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH.toString().replace("By.xpath: ", ""), personToEdit) +
//                "/span[@class='job']");
//
//        WebElement jobElement = driver.findElement(updatedPersonJobLocator);
//        assertEquals(newJob, jobElement.getText(), "Должность не была обновлена.");
//        System.out.println("Человек успешно отредактирован и проверен.");
//    }
//
//    @Test
//    public void removeExistingPerson() {
//        String personToRemove = "Sarah";
//        int initialCount = driver.findElements(PERSON_ENTRY_LOCATOR).size();
//
//        By removeIconLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH.toString().replace("By.xpath: ", ""), personToRemove) +
//                "/span[contains(@class, 'closebtn')]");
//        driver.findElement(removeIconLocator).click();
//
//        wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, initialCount - 1));
//
//        List<WebElement> peopleElementsAfterRemoval = driver.findElements(PERSON_ENTRY_LOCATOR);
//
//        for (WebElement personElement : peopleElementsAfterRemoval) {
//            String name = personElement.findElement(By.className("name")).getText();
//            assertNotEquals(personToRemove, name, "Person was not removed from the list.");
//        }
//    }
//
//    @Test
//    public void resetList() {
//        String newName = "Temporary";
//        String newJob = "Worker";
//
//
//        driver.findElement(ADD_PERSON_BUTTON_PAGE).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_NAME_FIELD));
//        driver.findElement(MODAL_NAME_FIELD).sendKeys(newName);
//        driver.findElement(MODAL_JOB_FIELD).sendKeys(newJob);
//        driver.findElement(MODAL_ADD_BUTTON).click();
//
//
//        wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, 11));
//
//        By temporaryPersonLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH_TEMPLATE, newName));
//        assertTrue(driver.findElements(temporaryPersonLocator).size() > 0, "List modification failed: new person was not added.");
//
//
//        driver.findElement(RESET_LIST_BUTTON_PAGE).click();
//
//
//        wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, 10));
//
//        checkCurrentList(initialPeople);
//    }
//}
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {
    WebDriver driver;
    WebDriverWait wait;

    private final Map<String, String> initialPeople = new HashMap<>() {{
        put("Mike", "Web Designer");
        put("Jill", "Support");
        put("Jane", "Accountant");
        put("John", "Software Engineer");
        put("Sarah", "Product Manager");
        put("Carlos", "Data Analyst");
        put("Emily", "UX Designer");
        put("David", "Project Manager");
        put("Maria", "QA Engineer");
        put("Alex", "DevOps Engineer");
    }};

    // Locators on the main page
    private final By ADD_PERSON_BUTTON_PAGE = By.xpath("//div[@class='w3-container']/button[text()='Add person']");
    private final By RESET_LIST_BUTTON_PAGE = By.xpath("//div[@class='w3-container']/button[text()='Reset List']");
    private final By PERSON_ENTRY_LOCATOR = By.xpath("//*[@id='listOfPeople']/div/li");

    // Locators for the modal window
    private final By MODAL_NAME_FIELD = By.id("nameInput");
    private final By MODAL_JOB_FIELD = By.id("jobInput");
    private final By MODAL_ADD_BUTTON = By.xpath("//div[contains(@class, 'w3-modal-content')]//button[text()='Add']");
    private final By MODAL_EDIT_BUTTON = By.xpath("//div[contains(@class, 'w3-modal-content')]//button[text()='Edit']");

    // Parametrized XPath to find the <li> element containing a specific name
    private final String PERSON_BY_NAME_XPATH_TEMPLATE = "//span[@class='w3-xlarge name' and text()='%s']/..";


    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        String driverPath = libWithDriversLocation + "chromedriver";
        if (new File(driverPath + ".exe").exists()) {
            System.setProperty("webdriver.chrome.driver", driverPath + ".exe");
        } else {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void checkCurrentList(Map<String, String> expected) {
        wait.until(ExpectedConditions.presenceOfElementLocated(PERSON_ENTRY_LOCATOR));

        List<WebElement> peopleElements = driver.findElements(PERSON_ENTRY_LOCATOR);
        assertEquals(expected.size(), peopleElements.size(), "The number of entries in the list does not match the expected count.");

        for (WebElement personElement : peopleElements) {
            String name = personElement.findElement(By.className("name")).getText();
            String job = personElement.findElement(By.className("job")).getText();

            assertTrue(expected.containsKey(name), "Found unexpected name in the list: " + name);
            assertEquals(expected.get(name), job,
                    String.format("Job for %s does not match: expected '%s', found '%s'",
                            name, expected.get(name), job));
        }
    }

    @Test
    public void initialPeopleList() {
        // Check that "Add person" and "Reset List" buttons are displayed and enabled
        WebElement addBtn = driver.findElement(ADD_PERSON_BUTTON_PAGE);
        WebElement resetBtn = driver.findElement(RESET_LIST_BUTTON_PAGE);

        assertTrue(addBtn.isDisplayed(), "'Add person' button is not displayed.");
        assertTrue(addBtn.isEnabled(), "'Add person' button is not enabled.");

        assertTrue(resetBtn.isDisplayed(), "'Reset List' button is not displayed.");
        assertTrue(resetBtn.isEnabled(), "'Reset List' button is not enabled.");

        // Check the initial list of people
        checkCurrentList(initialPeople);
    }

    @Test
    public void addNewPerson() {
        String newName = "Test Tester";
        String newJob = "Automation Engineer";

        // Click "Add person"
        driver.findElement(ADD_PERSON_BUTTON_PAGE).click();

        // Wait for modal and fill fields
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_NAME_FIELD));
        driver.findElement(MODAL_NAME_FIELD).sendKeys(newName);
        driver.findElement(MODAL_JOB_FIELD).sendKeys(newJob);

        // Click "Add"
        driver.findElement(MODAL_ADD_BUTTON).click();

        // Check that new person is added to the list (list size should be 11)
        wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, 11));

        // Check that new person is added with correct name and job
        By newPersonNameLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH_TEMPLATE, newName));
        WebElement newPersonElement = driver.findElement(newPersonNameLocator);

        String actualName = newPersonElement.findElement(By.className("name")).getText();
        String actualJob = newPersonElement.findElement(By.className("job")).getText();

        assertEquals(newName, actualName, "New person's name does not match.");
        assertEquals(newJob, actualJob, "New person's job does not match.");
    }

    @Test
    public void editExistingPerson() {
        String personToEdit = "John";
        String newJob = "Senior Software Architect";

        // Click pencil icon for an existing person
        By editIconLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH_TEMPLATE, personToEdit) +
                "/span[contains(@class, 'editbtn')]");

        driver.findElement(editIconLocator).click();

        // Wait for modal and check initial values
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_NAME_FIELD));

        WebElement nameField = driver.findElement(MODAL_NAME_FIELD);
        WebElement jobField = driver.findElement(MODAL_JOB_FIELD);

        assertEquals(personToEdit, nameField.getAttribute("value"), "Name field in modal is incorrect.");
        assertEquals(initialPeople.get(personToEdit), jobField.getAttribute("value"), "Job field in modal is incorrect.");

        // Change "Job" field
        jobField.clear();
        jobField.sendKeys(newJob);

        // Click "Edit"
        driver.findElement(MODAL_EDIT_BUTTON).click();

        // Check that the person is updated in the list with new job
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_NAME_FIELD));

        By updatedPersonJobLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH_TEMPLATE, personToEdit) +
                "/span[@class='job']");

        WebElement jobElement = driver.findElement(updatedPersonJobLocator);
        assertEquals(newJob, jobElement.getText(), "The job was not updated.");
    }

    @Test
    public void removeExistingPerson() {
        String personToRemove = "Sarah";
        int initialCount = driver.findElements(PERSON_ENTRY_LOCATOR).size();

        // Click the cross (x) icon for an existing person
        By removeIconLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH_TEMPLATE, personToRemove) +
                "/span[contains(@class, 'closebtn')]");

        driver.findElement(removeIconLocator).click();

        // Check that the person is removed from the list
        wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, initialCount - 1));

        List<WebElement> peopleElementsAfterRemoval = driver.findElements(PERSON_ENTRY_LOCATOR);

        // Verify that the person is no longer in the list
        for (WebElement personElement : peopleElementsAfterRemoval) {
            String name = personElement.findElement(By.className("name")).getText();
            assertNotEquals(personToRemove, name, "Person was not removed from the list.");
        }
    }

    @Test
    public void resetList() {
        String newName = "Temporary";
        String newJob = "Worker";

        // Modify the list (add a person)
        driver.findElement(ADD_PERSON_BUTTON_PAGE).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_NAME_FIELD));
        driver.findElement(MODAL_NAME_FIELD).sendKeys(newName);
        driver.findElement(MODAL_JOB_FIELD).sendKeys(newJob);
        driver.findElement(MODAL_ADD_BUTTON).click();

        // Check that the list is modified (11 entries)
        wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, 11));

        By temporaryPersonLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH_TEMPLATE, newName));
        assertTrue(driver.findElements(temporaryPersonLocator).size() > 0, "List modification failed: new person was not added.");

        // Click "Reset List"
        driver.findElement(RESET_LIST_BUTTON_PAGE).click();

        // Check that the list is back to the initial state (10 original entries)
        wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, 10));

        checkCurrentList(initialPeople);
    }
}



