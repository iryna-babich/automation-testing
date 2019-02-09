package AAA;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;

public class CalstateTravel {

  public static void main(String[] args) throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "/Users/irene/Drivers/chromedriver");
    WebDriver driver = new ChromeDriver();

    driver.get("https://calstate.aaa.com/travel");

    if (driver.findElements(By.className("acsClassicInner")).size() > 0) {
      driver.findElement(By.cssSelector("[aria-label='Close Modal Box']")).click();
    }

    driver.findElement(By.id("edit-zipcode")).sendKeys("84101");
    driver.findElement(By.id("edit-submit--5")).click();
    WebDriverWait modalPopup = new WebDriverWait(driver, 5);

    // Wait until modal is invisible to be able to click on Flight tab.
    modalPopup.until(ExpectedConditions.invisibilityOfElementLocated(By.id("alert-box-tqs1")));

    // Select Flight tab.
    driver.findElement(By.cssSelector("a[href='#flight']")).click();

    // Check Flight & Hotel radio button.
    driver.findElement(By.id("edit-radios-hotel-flight--2")).click();
    Thread.sleep(1000);

    // Choose origin and destination - Autosuggestion.
    WebElement departLocation = driver.findElement(By.cssSelector("input.tst-air-autocomplete[name='depart_location']"));
    departLocation.clear();
    departLocation.sendKeys("boi");
    Thread.sleep(1000);
    departLocation.sendKeys(Keys.ARROW_DOWN);
    departLocation.sendKeys(Keys.ENTER);

    WebElement arriveLocation = driver.findElement(By.cssSelector("input.tst-air-autocomplete[name='arrive_location']"));
    arriveLocation.clear();
    arriveLocation.sendKeys("san");
    Thread.sleep(1000);

    for (int i = 0; i < 4; i++) {
      arriveLocation.sendKeys(Keys.ARROW_DOWN);
    }
    arriveLocation.sendKeys(Keys.ENTER);
    Thread.sleep(1000);

    // Chose depart date and return dates.
    driver.findElement(By.cssSelector("input[name='depart_date']")).click();
    Thread.sleep(1000);
    WebElement datePickerOpened = driver.findElement(By.cssSelector("div.picker--opened.picker--focused"));
    datePickerOpened.findElement(By.cssSelector(".picker__day--today.picker__day--highlighted")).click();
    Thread.sleep(1000);

    // Allow flexible dates - check the element and make sure it's selected.
    WebElement flexibleDates = driver.findElement(By.cssSelector("input[name='flexible_dates']"));
    flexibleDates.click();
    Assert.assertTrue(flexibleDates.isSelected());
    Thread.sleep(1000);

    driver.findElement(By.id("edit-submit--6")).click();
  }
}
