package selenium.sample;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
        // define driver
        WebDriver driver = new ChromeDriver();

        // go to page
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        // get title of page
        String title = driver.getTitle();
        System.out.println("Title: " + title);

        // get URL of current page
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        // close browser
        driver.quit();
    }
}