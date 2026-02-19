package org.dani.SeleniumWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tests {

    // Centralized wait utility using Explicit Wait
    protected WebElement waitForVisibilityAndFind(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForVisibilityAndFind(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForVisibilityAndSendKeys(WebDriver driver, By locator, String input) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(input);
    }

    // Wait until an element is actually ready to be clicked
    protected void waitToBeClickableAndClick(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Wait for the URL to change - much better than sleeping after a click
    protected void waitForUrl(WebDriver driver, String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    private static final By usernameField = By.cssSelector("#defaultLoginFormUsername");
    private static final By passwordField = By.cssSelector("#defaultLoginFormPassword");
    private static final By signInButton = By.cssSelector("form .btn-primary");
    private static final By signOutIcon = By.cssSelector(".fa-sign-out-alt");
    private static final By postFeedContainer = By.cssSelector(".post-feed-container");

    @Test
    public void myFirstSmartTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        // Важи при всеки findElement и чака елементът да е наличен в DOM
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


//        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://training.skillo-bg.com:4300/users/login");
//        waitForVisibilityAndFind(driver, usernameField, 30).sendKeys("daniel11");
        waitForVisibilityAndSendKeys(driver, usernameField, "daniel11");
//        waitForVisibilityAndFind(driver, passwordField).sendKeys("1qaz!QAZ");
        waitForVisibilityAndSendKeys(driver, passwordField, "1qaz!QAZ");
        waitToBeClickableAndClick(driver, signInButton);

        String expectedDashboardUrl = "http://training.skillo-bg.com:4300/posts/all";
        // Here we need `waitForUrl`
        waitForUrl(driver, expectedDashboardUrl);

//        Assert.assertEquals(driver.getCurrentUrl(), expectedDashboardUrl, "Login failed or redirected incorrectly.");
        Assert.assertTrue(waitForVisibilityAndFind(driver, signOutIcon).isDisplayed(), "Sign out icon not found!");
        Assert.assertTrue(waitForVisibilityAndFind(driver, postFeedContainer).isDisplayed());
        driver.quit();
    }
}
