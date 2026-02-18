package org.dani.SeleniumDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumDemo {

    @Test
    public void myFirstSeleniumTest() throws InterruptedException {
        WebDriver myChromeDriver = new ChromeDriver();
        Thread.sleep(3000);

        myChromeDriver.get("http://training.skillo-bg.com:4200/posts/all");
        Thread.sleep(3000);
        myChromeDriver.manage().window().fullscreen();
        Thread.sleep(3000);

        myChromeDriver.quit();
    }
}
