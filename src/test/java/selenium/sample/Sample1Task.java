package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

public class Sample1Task {
    WebDriver driver;

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
//         go to https://acctabootcamp.github.io/site/index2.html
//         get title of page
//         get URL of current page
//         close browser
        driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/index2.html");
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();

        System.out.println("Title: " + title);
        System.out.println("URL: " + url);

        driver.quit();
    }
}
