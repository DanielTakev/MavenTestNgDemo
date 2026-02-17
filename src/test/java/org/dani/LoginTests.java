package org.dani;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests {

    @DataProvider(name = "loginCredentials")
    public Object[][] getData() {
        return new Object[][] {
                {"admin_user", "pass123", true},  // Valid user
                {"locked_user", "pass456", false}, // Locked user
                {"invalid_user", "wrong!", false}  // Non-existent user
        };
    }

    @Test(dataProvider = "loginCredentials")
    public void verifyLogin(String username, String password, boolean expectedResult) {
        System.out.println("Checking login for: " + username);

        // Simulating a login method
        LoginService loginService = new LoginService();
        boolean actualResult = loginService.login(username, password);

        Assert.assertEquals(actualResult, expectedResult, "Login result mismatch for user: " + username);
    }
}

