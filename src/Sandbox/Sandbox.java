package Sandbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sandbox {
  public static void main(String[] args) {
    // Adding Chrome driver to the test.
    System.setProperty("webdriver.chrome.driver", "/Users/irene/Drivers/chromedriver");

    // Create driver object for Chrome browser.
    WebDriver driver = new ChromeDriver();

    // Setting the URL where the driver should land.
    driver.get("https://www.autodesk.com/autodesk-university/");

    // Printing page's title.
    System.out.println(driver.getTitle());

    // Selecting element(logo) by class and clicking - which will lead us to autodesk.com.
    driver.findElement(By.className("global-header__logo")).click();

    // Returning back (clicking Back button in browser).
    driver.navigate().back();

    // Setting new String variable.
    String webUrl = "https://www.autodesk.com/autodesk-university/";

    if (webUrl.equals(driver.getCurrentUrl())) {
      System.out.println("The URL is correct.");
    }

    // Webdriver's methods.
    System.out.println(driver.getCurrentUrl());
    System.out.println(driver.getPageSource());

    // Closes browser window after completing test script.
    driver.close();

    // Closes ALL browser windows after the test script is completed.
    // driver.quit();
  }
}
