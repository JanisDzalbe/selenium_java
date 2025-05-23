package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
        WebDriver driver = new ChromeDriver();
//         go to https://acctabootcamp.github.io/site/index2.html
        driver.get("https://acctabootcamp.github.io/site/index2.html");
//         get title of page
        System.out.println(driver.getTitle());
//         get URL of current page
        System.out.println(driver.getCurrentUrl());
        //Sleep for 10 seconds
        Thread.sleep(10000);
//         close browser
        driver.quit();
    }
}
