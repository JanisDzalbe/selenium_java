package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
//         go to https://acctabootcamp.github.io/site/index2.html
//         get title of page
//         get URL of current page
//         close browser
        WebDriver driver = BootcampUtils.initializeChromeDriver();
        driver.get("https://acctabootcamp.github.io/site/index2.html");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.quit();
    }
}
