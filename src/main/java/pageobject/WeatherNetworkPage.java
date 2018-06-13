package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WeatherNetworkPage{

    private static WebDriver driver;
    @FindBy(className = "current-location-current-temp-c")
    private WebElement currentTemperature;
    @FindBy(linkText = "See All News")
    private WebElement seeAllNewsButton;
    @FindBy(linkText = "Latest")
    private WebElement latestNews;
    @FindBy(linkText = "World")
    private WebElement worldNews;
    @FindBy(xpath = "//li[@class=\"active\"]")
    private WebElement activeLabel;

    public WeatherNetworkPage(WebDriver driver) {
        WeatherNetworkPage.driver = driver;
    }

    public int findTemperature() {
        waitUtilVisible(By.className("current-location-current-temp-c"));
        String currentTemp = currentTemperature.getText();
        int currentTempNumber = Integer.parseInt(currentTemp.substring(0, currentTemp.length()-3));
        return currentTempNumber;
    }

    public String seeAllNews() {
        waitUtilClickable(By.linkText("See All News"));
        seeAllNewsButton.click();
        waitUtilVisible(By.xpath("//a[@href=\"#category-subnav\"]"));
        return driver.getCurrentUrl();
    }

    public WeatherNetworkPage goToNewsPage() {
        driver.get("https://www.theweathernetwork.com/news/category/latest/");
        return this;
    }

    public WeatherNetworkPage seeLatestNews(){
        waitUtilClickable(By.linkText("Latest"));
        latestNews.click();
        return this;
    }

    public WeatherNetworkPage seeWorldNews() {
        waitUtilClickable(By.linkText("World"));
        worldNews.click();
        return this;
    }

    public boolean isUnderlineAppear(String linkText) {
        waitUtilClickable(By.tagName("a"));
        return activeLabel.findElement(By.tagName("a")).getText().equals(linkText);
    }

    private void waitUtilClickable(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitUtilVisible(By element) {
        (new WebDriverWait(driver, 30))
                .until(presenceOfElementLocated(element));
    }

  }

