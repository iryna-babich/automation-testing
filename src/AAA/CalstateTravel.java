package AAA;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalstateTravel {

  public static void main(String[] args) throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Users/irene/Drivers/chromedriver");
    WebDriver driver = new ChromeDriver();

    driver.get("https://calstate.aaa.com/travel");
    Thread.sleep(1000);
    driver.findElement(By.id("edit-zipcode")).sendKeys("84101");
    driver.findElement(By.id("edit-submit--5")).click();
    Thread.sleep(1000);

    // Select Flight tab.
    driver.findElement(By.cssSelector("a[href='#flight']")).click();
    Thread.sleep(1000);

    // Check Flight & Hotel radio button.
    driver.findElement(By.id("edit-radios-hotel-flight--2")).click();
    Thread.sleep(1000);

    // Choose origin and destination.
    WebElement depart_location = driver.findElement(By.cssSelector("#flight input[name='depart_location']"));
    WebElement arrive_location = driver.findElement(By.cssSelector("#flight input[name='arrive_location']"));
    depart_location.clear();
    depart_location.sendKeys("boi");
    Thread.sleep(1000);
    depart_location.sendKeys(Keys.ARROW_DOWN);
    depart_location.sendKeys(Keys.ENTER);

    arrive_location.clear();
    arrive_location.sendKeys("san");
    Thread.sleep(1000);
    for (int i = 0; i < 4; i++) {
      arrive_location.sendKeys(Keys.ARROW_DOWN);
    }
    arrive_location.sendKeys(Keys.ENTER);




    // Closes browser window after completing test script.
    // driver.close();

  }
}
