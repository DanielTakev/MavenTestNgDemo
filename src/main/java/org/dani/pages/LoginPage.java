package org.dani.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // 1. Locators (Private so tests can't mess with them)
    private final By usernameField = By.cssSelector("#defaultLoginFormUsername");
    private final By passwordField = By.cssSelector("#defaultLoginFormPassword");
    private final By signInButton = By.cssSelector("#sign-in-button");
    // Const
    private final String loginPageUrl = "/users/login";


    public LoginPage(WebDriver driver) {
        super(driver); // Calls the BasePage constructor
    }

    // 2. Actions (Methods that the tests will call)
    public String getPageExpectedUrl() {
        return super.getExpectedUrl(loginPageUrl);
    }

    public void verifyPageLoaded() {
        waitForUrl(getExpectedUrl(loginPageUrl));
    }

    public void navigateToPage() {
        navigateTo(loginPageUrl);
    }

    public void enterUsername(String username) {
        typeText(usernameField, username);
    }

    public void enterPassword(String password) {
        typeText(passwordField, password);
    }

    public void clickSignIn() {
        click(signInButton);
    }

    // High-level "Flow" method
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickSignIn();
    }
}