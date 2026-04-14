package selenium.sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;



public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {

//        TODO:
//         define driver
//         go to https://janisdzalbe.github.io/example-site/index2.html
//         get title of page
//         get URL of current page
//         close browser

        // set driver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\seva2\\IdeaProjects\\selenium_java\\lib\\chromedriver.exe");

        // define driver
        WebDriver driver = new ChromeDriver();

        // open required page
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");

        // print title
        System.out.println(driver.getTitle());

        // print current URL
        System.out.println(driver.getCurrentUrl());

        // wait a bit
        Thread.sleep(5000);

        // close browser
        driver.quit();
    }
}
