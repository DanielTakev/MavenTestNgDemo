package org.dani.SeleniumWaits;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    WebDriver driver = new ChromeDriver();

    public void waitForPageToLoad(int seconds) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }

    public void setImplicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public WebElement waitForElementVisible(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement fluentWait(By locator, int timeoutSeconds, int pollingMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(d -> d.findElement(locator));
    }
}
