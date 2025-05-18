package selenium.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.AgeSamplePage;
import selenium.pages.AgeSubmittedSamplePage;
import selenium.utility.BootcampUtils;

import java.time.Duration;

public class Sample10 {
    static WebDriver driver;
    static AgeSamplePage agePage;
    static AgeSubmittedSamplePage ageSubmittedPage;

    @BeforeEach
    public void openPage() throws InterruptedException {
        // Initialize driver
        driver = BootcampUtils.initializeChromeDriver();

        // Set an implicity wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open page and create Page Objects via Selenium's PageFactory
        driver.get("https://acctabootcamp.github.io/site/examples/age");
        agePage = PageFactory.initElements(driver, AgeSamplePage.class);
        ageSubmittedPage = PageFactory.initElements(driver, AgeSubmittedSamplePage.class);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void checkCleanPage() throws Exception {
        agePage.checkThatFormIsClean();
    }

    @Test
    public void checkErrorMessageOnEmptyAge() throws Exception {
        agePage.clickSubmit();
        agePage.checkErrorMessage("You haven't entered anything in age field");
    }

    @Test
    public void checkErrorMessageOnEmptyName() throws Exception {
        agePage.enterAge(3);
        agePage.clickSubmit();
        agePage.checkErrorMessage("You haven't entered anything in name field");
    }

    @Test
    public void checkSuccessfulMessageForKid() throws Exception {
        agePage.enterNameAgeAndClickSubmit("Ann", 5);
        ageSubmittedPage.checkMessageText("Hello, Ann, you are a kid");
    }

    @Test
    public void checkSuccessfulMessageForAdult() throws Exception {
        agePage.enterNameAgeAndClickSubmit("Tom", 55);
        ageSubmittedPage.checkMessageText("Hello, Tom, you are an adult");
    }

    @Test
    public void checkBackButton() throws Exception {
        agePage.enterNameAgeAndClickSubmit("Tom", 55);
        ageSubmittedPage.clickBackButton();
        agePage.checkThatFormIsClean();
    }
}
