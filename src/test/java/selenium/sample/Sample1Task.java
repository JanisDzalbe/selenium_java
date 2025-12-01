package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {

        System.setProperty("webdrtver.chrome.driver," , "C:\\Users\\37129\\IdeaProjects\\selenium_java\\lib\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.close();
        driver.quit();
//        TODO:
//         define driver
//         go to https://janisdzalbe.github.io/example-site/index2.html
//         get title of page
//         get URL of current page
//         close browser
    }
}
