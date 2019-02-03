package Autodesk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Autodesk {
  public static void main(String[] args) throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Users/irene/Drivers/chromedriver");
    WebDriver driver = new ChromeDriver();

    driver.get("https://www.autodesk.com/autodesk-university/");
    driver.findElement(By.xpath("//div[@class='login-block']/a[2]")).click();
    driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("username");
    driver.findElement(By.cssSelector("button[id='verify_user_btn']")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("btnSubmit")).click();


    // Closes browser window after completing test script.
    // driver.close();

  }
}


