package org.dani.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final String baseUrl = "http://training.skillo-bg.com:4200";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected String getExpectedUrl(String urlSuffix) {
        return baseUrl + urlSuffix;
    }

    public String getActualCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // --- REUSABLE WAIT WRAPPERS ---
    protected WebElement waitAndClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitAndVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void navigateTo(String urlSuffix) {
        driver.get(baseUrl + urlSuffix);
    }

    protected void click(By locator) {
        waitAndClickable(locator).click();
    }

    protected void typeText(By locator, String text) {
        waitAndVisible(locator).clear();
        waitAndVisible(locator).sendKeys(text);
    }

    protected void typeTextWithoutClear(By locator, String text) {
        waitAndVisible(locator).sendKeys(text);
    }

    protected void waitForUrl(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    protected void waitForUrlToContain(String urlPart) {
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    protected int getElementsCount(By locator) {
        waitAndVisible(locator);
        return driver.findElements(locator).size();
    }
}