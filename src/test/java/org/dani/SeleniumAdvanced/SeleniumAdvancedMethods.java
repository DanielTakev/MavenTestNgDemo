package org.dani.SeleniumAdvanced;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SeleniumAdvancedMethods {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- LIFECYCLE MANAGEMENT ---

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Initializing wait here ensures it's linked to the active driver session
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println(">>> Browser session initialized.");
    }

    @AfterMethod
    public void afterTestMethod() {
        System.out.println(">>> Finished executing test method.");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("<<< Browser session closed.");
        }
    }

    // --- REUSABLE WAIT WRAPPERS ---
    private WebElement waitAndClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitAndVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // --- ELEMENT HELPER METHODS ---
    public void selectCheckbox(By locator, boolean shouldBeSelected) {
        WebElement element = waitAndClickable(locator);
        if (element.isSelected() != shouldBeSelected) {
            element.click();
        }
    }

    public void selectCheckboxByText(String text, boolean shouldBeSelected) {
        By checkboxElement = By.xpath("//div[@id=\"checkboxes\"]//label[text()=\"" + text + "\"]/input");
        selectCheckbox(checkboxElement, shouldBeSelected);
    }

    public boolean isCheckboxByTextSelected(String text) {
        By locator = By.xpath("//div[@id=\"checkboxes\"]//label[text()=\"" + text + "\"]/input");
        return waitAndClickable(locator).isSelected();
    }

    public void selectFromDropdown(By locator, int index) {
        Select dropdown = new Select(waitAndVisible(locator));
        dropdown.selectByIndex(index);
    }

    public void scrollToElement(By locator) {
        new Actions(driver)
                .scrollToElement(waitAndVisible(locator))
                .perform();
    }

    public void hoverOverElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitAndVisible(locator)).perform();
    }

    public String getTableCellValue(String tableId, int rowNum, int colNum) {
        waitAndVisible(By.xpath("//table[@id=\"" + tableId + "\"]"));
        WebElement cellLocator = driver.findElement(By.xpath("//table[@id=\"" + tableId + "\"]//tr[" + rowNum + "]/td[" + colNum + "]"));
        return cellLocator.getText();
    }

    public void handleAlert(boolean accept) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert says: " + alert.getText());

        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    public void switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        wait.until(d -> d.getWindowHandles().size() > 1);
        System.out.println("originalWindow: " + originalWindow);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void switchToFrame(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public void switchToMainContent() {
        driver.switchTo().defaultContent();
    }

    // --- TEST METHODS ---

    @Test
    public void selectFromMenuTest() throws InterruptedException {
        driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
//        Thread.sleep(2345);
        selectFromDropdown(By.cssSelector("#dropdowm-menu-1"), 2);
//        Thread.sleep(4222);
    }

    @Test
    public void selectCheckboxesTest() throws InterruptedException {
        driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
//        Thread.sleep(3345);
        selectCheckboxByText("Option 2", true);
//        Thread.sleep(2222);
        Assert.assertTrue(isCheckboxByTextSelected("Option 2"));
    }

    @Test
    public void mouseHoverTest() throws InterruptedException {
        driver.get("https://webdriveruniversity.com/Actions/index.html");

        By hoverButton = By.xpath("(//div[@id=\"div-hover\"]//button)[1]");
        hoverOverElement(hoverButton);

        // Wait for dropdown link to appear and click it
        By link = By.linkText("Link 1");
        waitAndClickable(link).click();
//        Thread.sleep(2234);
        handleAlert(true); // Accept alert after clicking
//        Thread.sleep(2334);
    }







    @Test
    public void confirmAlertDismissTest() throws InterruptedException {
        driver.get("https://webdriveruniversity.com/Popup-Alerts/index.html");

        By confirmButton = By.id("button4");
        waitAndClickable(confirmButton).click();

        handleAlert(false); // Dismiss alert
        Thread.sleep(2233);
    }

    @Test
    public void getTableCellTextByIndexes() throws InterruptedException {
//        Thread.sleep(2345);
        driver.get("https://webdriveruniversity.com/Data-Table/index.html");
        String actualResult = getTableCellValue("t01", 2, 2);
//        Thread.sleep(2222);
        Assert.assertEquals(actualResult, "Smith");
    }

    @Test
    public void switchToNewWindowUsingHelperTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/windows");

        String originalWindow = driver.getWindowHandle();

        // Open new window
        By clickHereLink = By.linkText("Click Here");
        waitAndClickable(clickHereLink).click();

        // Use YOUR helper method
        switchToNewWindow();

        // Validate we are in the new window
        WebElement heading = waitAndVisible(By.tagName("h3"));
        Assert.assertEquals(heading.getText(), "New Window");

        // Close child window
        driver.close();

        // Switch back to main window
        driver.switchTo().window(originalWindow);

        // Validate we are back
        Assert.assertEquals(driver.getTitle(), "The Internet");
    }

    @Test
    public void scrollToElementTest() throws InterruptedException {
        driver.get("https://webdriveruniversity.com/Data-Table/index.html");
//        Thread.sleep(4444);
        scrollToElement(By.cssSelector(".traversal-job-list"));
//        Thread.sleep(3333);
    }

}