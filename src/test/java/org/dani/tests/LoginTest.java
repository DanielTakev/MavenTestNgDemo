package org.dani.tests;

import org.dani.pages.HomePage;
import org.dani.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testLoginFlow() {
        // 1. Page Object Initialization
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // 2. Navigation
        loginPage.navigateToPage();

        // 3. Business Logic (Action)
        loginPage.login("daniel11", "1qaz!QAZ");
        homePage.verifyPageLoaded();

        // 4. Assertion (The "What")
//        String expectedUrl = homePage.getPageExpectedUrl();
//        Assert.assertEquals(expectedUrl, homePage.getActualCurrentUrl(), "Login failed!");

        // Second approach
        Assert.assertTrue(homePage.isUrlLoaded(), "User was not redirected to the Home Page!");
    }

    @Test(priority = 2)
    public void homePagePostsCountIsCorrect() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getPostsCount(), 3);
    }
}
