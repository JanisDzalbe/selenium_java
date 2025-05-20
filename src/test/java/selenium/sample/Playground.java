package selenium.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import selenium.utility.BootcampUtils;


public class Playground {
    /*
    You can use this for your testing shenanigans and everything else.
    Feel free to commit this (to not have to unselect this from Git commit window - this will not be looked at too much.
     */
    public static void main(String[] args) {
        WebDriver driver = BootcampUtils.initializeChromeDriver();
    driver.get("https://kristinek.github.io/site/tasks/locators_different");



    System.out.println(driver.findElement(By.xpath("//*[contains(@class, 'w3-pink')]")).getText());




        driver.quit();
    }
}
