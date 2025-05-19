package selenium.sample;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.utility.BootcampUtils;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {


        WebDriver driver = new ChromeDriver();
        driver = BootcampUtils.initializeChromeDriver();

        driver.get("https://acctabootcamp.github.io/site/index2.html");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        // messing around w/ assertions, they probably shouldn't be here
        Assertions.assertEquals("Home Page for this site version 2", driver.getTitle());
        Assertions.assertEquals("https://acctabootcamp.github.io/site/index2.html", driver.getCurrentUrl());

        driver.quit();
//        TODO:
//         define driver
//         go to https://acctabootcamp.github.io/site/index2.html
//         get title of page
//         get URL of current page
//         close browser
    }
}
