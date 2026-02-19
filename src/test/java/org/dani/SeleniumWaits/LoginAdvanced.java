package org.dani.SeleniumWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginAdvanced {

    // Class-level variable so @Before, @Test, and @After can all see it
    private WebDriver driver;

    // Locators
    private static final By usernameField = By.cssSelector("#defaultLoginFormUsername");
    private static final By passwordField = By.cssSelector("#defaultLoginFormPassword");
    private static final By signInButton = By.cssSelector("form .btn-primary");
    private static final By signOutIcon = By.cssSelector(".fa-sign-out-alt");
    private static final By postFeedContainer = By.cssSelector(".post-feed-container");

    @BeforeClass
    public void setUp() {
        // Initialize the driver once for all tests in this class
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Setting a page load timeout as a global safety net
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        System.out.println(">>> Browser started for the class.");
    }

    @BeforeMethod
    public void beforeMethod() {
        // Something before each test method (test case)
        driver.navigate().refresh();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after all tests in this class are done
        if (driver != null) {
            driver.quit();
            System.out.println("<<< Browser closed after class execution.");
        }
    }

    @Test
    public void myFirstLoginTest() {
        driver.get("http://training.skillo-bg.com:4300/users/login");

        // Wait and find the username field
        waitAndFind(usernameField).sendKeys("testtestovvv");

        // Wait and find the password field
        waitAndFind(passwordField).sendKeys("1qaz!QAZ");

        // Click the sign-in button
        waitAndClick(signInButton);

        // Wait for the URL to change to the dashboard
        String expectedDashboardUrl = "http://training.skillo-bg.com:4300/posts/all";
        waitForUrl(expectedDashboardUrl);

        // Assertions
        Assert.assertTrue(waitAndFind(signOutIcon).isDisplayed(), "Sign out icon not found!");
        Assert.assertTrue(waitForVisibilityAndFind(driver, postFeedContainer).isDisplayed());
    }

    @Test
    public void verifyPostTitleIsCorrect() throws InterruptedException {
        String expectedPostTitle = "Ant ";
        waitForVisibilityAndFindPostTitle(driver, expectedPostTitle);
    }


    // Helper methods
    protected WebElement waitAndFind(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitAndClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void waitForUrl(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    protected WebElement waitForVisibilityAndFind(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForVisibilityAndFindPostTitle(WebDriver driver, String title) {
        By postTitle = By.xpath("//div[contains(@class,'post-title') and text()='" + title + "']");
        return waitForVisibilityAndFind(driver, postTitle);
    }
}
