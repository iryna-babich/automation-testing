package Expedia;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Expedia {
  public static void main(String[] args) {
//    System.setProperty("webdriver.chrome.driver", "/Users/irene/Drivers/chromedriver");
//    WebDriver driver = new ChromeDriver();

    System.setProperty("webdriver.gecko.driver", "/Users/irene/Drivers/geckodriver");
    WebDriver driver = new FirefoxDriver();

    // Get the URL.
    driver.get("https://www.expedia.com/");

    // Wait until tabs are ready to be clicked on.
    WebDriverWait tabsReady = new WebDriverWait(driver, 2);
    tabsReady.until(ExpectedConditions.elementToBeClickable(By.id("tab-package-tab-hp")));

    // Click on Bundle and Save tab and choose 'boi' as origin.
    driver.findElement(By.id("tab-package-tab-hp")).click();
    WebElement originInput = driver.findElement(By.id("package-origin-hp-package"));
    originInput.clear();
    originInput.sendKeys("boi");

    WebDriverWait originDropdown = new WebDriverWait(driver, 3);
    originDropdown.until(ExpectedConditions.presenceOfElementLocated(By.id("typeaheadDataPlain")));
    originInput.sendKeys(Keys.ENTER);

    // Chose destination and choose San Antonio, TX.
    WebElement destinationInput = driver.findElement(By.id("package-destination-hp-package"));
//    destinationInput.click();
    destinationInput.clear();
    destinationInput.sendKeys("san");
    // Wait until suggestion dropdown is available.
    WebDriverWait destinationDropdown = new WebDriverWait(driver, 3);
    destinationDropdown.until(ExpectedConditions.visibilityOfElementLocated(By.id("typeaheadDataPlain")));

    // Create list of all results in dropdown and loop through them until find the desired destination.
    List<WebElement> destOptions = driver.findElements(By.cssSelector("li.results-item"));
    for (int i = 0; i < destOptions.size(); i++) {
      String destinationText = destOptions.get(i).getText();
      System.out.println(destinationText);
      destinationInput.sendKeys(Keys.ARROW_DOWN);
      if (destOptions.get(i).getText().contains(" Antonio, TX")) {
        destinationInput.sendKeys(Keys.ENTER);
        break;
      }
    }

    // Choose departing date.
    String departMonth = "May";
    driver.findElement(By.id("package-departing-hp-package")).click();

    // Get the first calendar's months title and compare it with departDate.
    WebElement monthOfDepart = driver.findElement(By.xpath("//div[@class='datepicker-cal-month'][1]"));
    String monthOfDepartTitle = monthOfDepart.findElement(By.cssSelector("caption.datepicker-cal-month-header")).getText();

    while(!monthOfDepartTitle.contains(departMonth)) {
      driver.findElement(By.cssSelector("button.datepicker-paging.datepicker-next")).click();
      monthOfDepartTitle = driver.findElement(By.cssSelector("caption.datepicker-cal-month-header")).getText();
    }
    System.out.println(monthOfDepartTitle);

    // After we picked the proper month, create list of dates and loop through it.
    WebElement chosenMonthOfDepart = driver.findElement(By.xpath("//div[@class='datepicker-cal-month'][1]"));
    List<WebElement> dateOfDepart = chosenMonthOfDepart.findElements(By.cssSelector("button.datepicker-cal-date"));
    for (int i = 0; i < dateOfDepart.size(); i++) {
      String dateOfDepartText = dateOfDepart.get(i).getAttribute("data-day");
      if (dateOfDepartText.equals("17")) {
        System.out.println("found");
        dateOfDepart.get(i).click();
        break;
      }
    }

    // Choose returning month and compare it to departure month, the bundle package has 4 weeks max between departure and returning.
    String returnMonth = "June";
    driver.findElement(By.id("package-returning-hp-package")).click();

    WebElement chosenMonthOfReturn = driver.findElement(By.xpath("//div[@class='datepicker-cal-month'][1]"));

    if (!departMonth.equals(returnMonth)) {
      chosenMonthOfReturn = driver.findElement(By.xpath("//div[@class='datepicker-cal-month'][2]"));
    }

    // After we picked the proper month, create list of dates and loop through it.
    List<WebElement> dateOfReturn = chosenMonthOfReturn.findElements(By.cssSelector("button.datepicker-cal-date"));
    for (int i = 0; i < dateOfReturn.size(); i++) {
      String dateOfReturnText = dateOfReturn.get(i).getAttribute("data-day");
      System.out.println("date:" + dateOfReturnText);
      if (dateOfReturnText.equals("6")) {
        System.out.println("found");
        dateOfReturn.get(i).click();
        break;
      } else {
        System.out.println("The return date is beyond allowed dates.");
      }
    }

    // Choose 2 adults and 2 child and one infant in travelers dropdown.
    WebElement travelerSection = driver.findElement(By.id("traveler-selector-hp-package"));
    travelerSection.findElement(By.className("gcw-traveler-amount-select")).click();
    WebElement childrenSection = travelerSection.findElement(By.className("children-wrapper"));
    childrenSection.findElement(By.className("uitk-step-input-plus")).click();

    Select childAge = new Select(childrenSection.findElement(By.className("gcw-child-age-1-1-hc")));
    childAge.selectByValue("10");

    WebElement infantSection = travelerSection.findElement(By.className("infants-wrapper"));
    infantSection.findElement(By.className("uitk-step-input-plus")).click();

    Select infantAge = new Select(infantSection.findElement(By.className("gcw-infant-age-1-hc")));
    infantAge.selectByValue("1");

    travelerSection.findElement(By.id("package-children-in-seat-hp-package")).click();

    driver.findElement(By.id("search-button-hp-package")).click();
  }
}
