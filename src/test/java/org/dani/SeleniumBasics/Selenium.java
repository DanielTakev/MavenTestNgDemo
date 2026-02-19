package org.dani.SeleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Selenium {

    private static final By usernameField = By.cssSelector("#defaultLoginFormUsername");
    private static final By passwordField = By.cssSelector("#defaultLoginFormPassword");
    private static final By signInButton = By.cssSelector("form .btn-primary");
    private static final By signOutIcon = By.cssSelector(".fa-sign-out-alt");

    @Test
    public void myFirstTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://training.skillo-bg.com:4300/users/login");
//        Thread.sleep(1333);

        WebElement usernameFieldElement = driver.findElement(usernameField);
        usernameFieldElement.sendKeys("daniel11");

//        Thread.sleep(1333);
        System.out.println("The text text is: " + usernameFieldElement.getText());
        System.out.println("The value attribute is: " + usernameFieldElement.getAttribute("value"));
        System.out.println("The placeholder attribute is: " + usernameFieldElement.getAttribute("placeholder"));

        WebElement passwordFieldElement = driver.findElement(passwordField);
        passwordFieldElement.sendKeys("1qaz!QAZ");
//        Thread.sleep(1333);

        WebElement signInButtonElement = driver.findElement(signInButton);
        signInButtonElement.click();
//        Thread.sleep(5333);

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://training.skillo-bg.com:4300/posts/all", actualUrl);
        driver.findElement(signOutIcon);

        driver.quit();
    }
}
