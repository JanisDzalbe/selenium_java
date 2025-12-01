package selenium.sample;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Sample1Task {

    @Test
    public void goToHomepage() throws Exception {
        System.setProperty("webdriver.chrome","lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://janisdzalbe.github.io/example-site/index2.html");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.quit();
    }
}
