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

import static org.junit.jupiter.api.Assertions .*;

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

       
        private final By ADD_PERSON_BUTTON_PAGE = By.xpath("//div[@class='w3-container']/button[text()='Add person']");
        private final By RESET_LIST_BUTTON_PAGE = By.xpath("//div[@class='w3-container']/button[text()='Reset List']");
        private final By PERSON_ENTRY_LOCATOR = By.xpath("//*[@id='listOfPeople']/div/li");


        private final By MODAL_NAME_FIELD = By.id("nameInput");
        private final By MODAL_JOB_FIELD = By.id("jobInput");
        private final By MODAL_ADD_BUTTON = By.xpath("//div[contains(@class, 'w3-modal-content')]//button[text()='Add']");
        private final By MODAL_EDIT_BUTTON = By.xpath("//div[contains(@class, 'w3-modal-content')]//button[text()='Edit']");


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
        public void removeExistingPerson() {
            String personToRemove = "Sarah";
            int initialCount = driver.findElements(PERSON_ENTRY_LOCATOR).size();

            By removeIconLocator = By.xpath(String.format(PERSON_BY_NAME_XPATH_TEMPLATE, personToRemove) +
                    "/span[contains(@class, 'closebtn')]");

            driver.findElement(removeIconLocator).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(PERSON_ENTRY_LOCATOR, initialCount - 1));
            List<WebElement> peopleElementsAfterRemoval = driver.findElements(PERSON_ENTRY_LOCATOR);

            for (WebElement personElement : peopleElementsAfterRemoval) {
                String name = personElement.findElement(By.className("name")).getText();
                assertNotEquals(personToRemove, name, "Person was not removed from the list.");
            }


        }


    }








