package org.dani.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // WebElements below
    private final By postElements = By.cssSelector(".post-feed-container");

    private final String homePageUrl = "/posts/all";

    public HomePage(WebDriver driver) {
        super(driver); // Calls the BasePage constructor
    }

    public String getPageExpectedUrl() {
        return super.getExpectedUrl(homePageUrl);
    }

    public void navigateToPage() {
        navigateTo(homePageUrl);
    }

    public void verifyPageLoaded() {
        waitForUrl(getExpectedUrl(homePageUrl));
    }

    public boolean isUrlLoaded() {
        verifyPageLoaded();
        return getActualCurrentUrl().equals(getPageExpectedUrl());
    }

    public int getPostsCount() {
        return getElementsCount(postElements);
    }
}
