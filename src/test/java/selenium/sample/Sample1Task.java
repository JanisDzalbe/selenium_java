package selenium.sample;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import org.junit.jupiter.api.Test;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
//         go to https://acctabootcamp.github.io/site/index2.html
//         get title of page
//         get URL of current page
//         close browser

        WebDriver driver = new ChromeDriver();

        driver.get("https://acctabootcamp.github.io/site/index2.html");

        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        driver.quit();

    }
}
