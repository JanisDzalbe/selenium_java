package selenium.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task2 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver",
                libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());

        driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/tasks/list_of_people_with_jobs");

        // small explicit wait for dynamic content
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    // ---------- Helpers ----------

    private WebElement getAddPersonButton() {
        // first visible "Add person" button
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Add person']")));
    }

    private WebElement getResetListButton() {
        // first visible "Reset List" button
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Reset List']")));
    }

    private WebElement getAddButtonInForm() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Add']")));
    }

    private WebElement getEditButtonInForm() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Edit']")));
    }

    private WebElement getNameInput() {
        // locate input belonging to label "Name"
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(normalize-space(),'Name')]/following-sibling::input[1]")));
    }

    private WebElement getJobInput() {
        // locate input belonging to label "Job"
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(normalize-space(),'Job')]/following-sibling::input[1]")));
    }

    private WebElement findPersonRow(String name, String job) {
        String xpath = String.format(
                "//li[contains(normalize-space(.), '%s') and contains(normalize-space(.), '%s')]",
                name, job
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    private boolean isPersonPresent(String name, String job) {
        String xpath = String.format(
                "//li[contains(normalize-space(.), '%s') and contains(normalize-space(.), '%s')]",
                name, job
        );
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    private void assertBaseListPresent() {
        // ensure initial tenants are there (we don't assert "exactly 10 <li>", only content)
        List<String[]> expectedPeople = Arrays.asList(
                new String[]{"Mike", "Web Designer"},
                new String[]{"Jill", "Support"},
                new String[]{"Jane", "Accountant"},
                new String[]{"John", "Software Engineer"},
                new String[]{"Sarah", "Product Manager"},
                new String[]{"Carlos", "Data Analyst"},
                new String[]{"Emily", "UX Designer"},
                new String[]{"David", "Project Manager"},
                new String[]{"Maria", "QA Engineer"},
                new String[]{"Alex", "DevOps Engineer"}
        );

        for (String[] person : expectedPeople) {
            String name = person[0];
            String job = person[1];
            WebElement row = findPersonRow(name, job);
            assertTrue(row.isDisplayed(), "Person should be visible: " + name + ", " + job);
        }
    }

    // ---------- Tests ----------

    @Test
    public void initialPeopleList() throws Exception {
        // make sure we're in base state
        getResetListButton().click();

        // "Add person" and "Reset List" buttons
        WebElement addPersonButton = getAddPersonButton();
        WebElement resetListButton = getResetListButton();

        assertTrue(addPersonButton.isDisplayed());
        assertTrue(addPersonButton.isEnabled());
        assertTrue(resetListButton.isDisplayed());
        assertTrue(resetListButton.isEnabled());

        // base 10 people (by content, not by raw li count)
        assertBaseListPresent();
    }

    @Test
    public void addNewPerson() throws Exception {
        getResetListButton().click();

        String newName = "Test User";
        String newJob = "Automation Engineer";

        // click "Add person"
        getAddPersonButton().click();

        // fill "Name" and "Job"
        WebElement nameInput = getNameInput();
        WebElement jobInput = getJobInput();

        nameInput.clear();
        nameInput.sendKeys(newName);

        jobInput.clear();
        jobInput.sendKeys(newJob);

        // click "Add"
        getAddButtonInForm().click();

        // check that new person appears
        assertTrue(isPersonPresent(newName, newJob),
                "Newly added person should be present: " + newName + ", " + newJob);
    }

    @Test
    public void editExistingPerson() throws Exception {
        getResetListButton().click();

        String originalName = "Mike";
        String originalJob = "Web Designer";
        String updatedJob = "Senior Web Designer";

        // locate Mike's row
        WebElement mikeRow = findPersonRow(originalName, originalJob);
        List<WebElement> buttons = mikeRow.findElements(By.tagName("button"));
        assertFalse(buttons.isEmpty(), "Expected at least one button (edit) in Mike's row");

        // assume first button is "edit"
        WebElement editButton = buttons.get(0);
        editButton.click();

        // form fields
        WebElement nameInput = getNameInput();
        WebElement jobInput = getJobInput();

        // set values
        nameInput.clear();
        nameInput.sendKeys(originalName);

        jobInput.clear();
        jobInput.sendKeys(updatedJob);

        // click "Edit"
        getEditButtonInForm().click();

        // check updated entry
        assertTrue(isPersonPresent(originalName, updatedJob),
                "Updated Mike entry should be present with new job");
    }

    @Test
    public void removeExistingPerson() throws Exception {
        getResetListButton().click();

        String nameToRemove = "Alex";
        String jobToRemove = "DevOps Engineer";

        assertTrue(isPersonPresent(nameToRemove, jobToRemove),
                "Alex should be present before removal");

        WebElement alexRow = findPersonRow(nameToRemove, jobToRemove);
        List<WebElement> buttons = alexRow.findElements(By.tagName("button"));

        // assume second button is delete (x)
        assertTrue(buttons.size() >= 2,
                "Expected at least two buttons (edit, delete) in Alex's row");
        WebElement deleteButton = buttons.get(1);
        deleteButton.click();

        // wait until Alex disappears
        String alexXpath = "//li[contains(normalize-space(.), 'Alex') and contains(normalize-space(.), 'DevOps Engineer')]";
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(alexXpath)));

        assertFalse(isPersonPresent(nameToRemove, jobToRemove),
                "Alex should be removed from the list");
    }

    @Test
    public void resetList() throws Exception {
        getResetListButton().click();

        String tempName = "Temp User";
        String tempJob = "Temp Job";

        // modify list: add person
        getAddPersonButton().click();

        WebElement nameInput = getNameInput();
        WebElement jobInput = getJobInput();

        nameInput.clear();
        nameInput.sendKeys(tempName);

        jobInput.clear();
        jobInput.sendKeys(tempJob);

        getAddButtonInForm().click();

        // ensure modification is visible
        assertTrue(isPersonPresent(tempName, tempJob),
                "Temporary person should be present after modification");

        // reset
        getResetListButton().click();

        // temp user should disappear
        String tempXpath = String.format(
                "//li[contains(normalize-space(.), '%s') and contains(normalize-space(.), '%s')]",
                tempName, tempJob
        );
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(tempXpath)));

        assertFalse(isPersonPresent(tempName, tempJob),
                "Temporary person should not be present after reset");

        // base list back
        assertBaseListPresent();
    }
}
