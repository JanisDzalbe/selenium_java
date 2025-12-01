package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {

//    @Test
//    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
//         go to https://janisdzalbe.github.io/example-site/index2.html
//         get title of page
//         get URL of current page
//         close browser
//    }
    @Test
    public void goToHomepage() throws Exception {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://janisdzalbe.github.io/example-site/index2.html");
            String title = driver.getTitle();
            System.out.println("Page title: " + title);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);


        } finally {

            driver.quit();
        }
    }

}
