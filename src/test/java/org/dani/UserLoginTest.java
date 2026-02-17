package org.dani;

import org.testng.annotations.*;

public class UserLoginTest extends BaseEnvironment {

    @BeforeClass
    public void initializePageObjects() {
        System.out.println("> @BeforeClass: Initializing LoginPage and Dashboard objects.");
    }

    @BeforeMethod
    public void openBrowser() {
        System.out.println("- @BeforeMethod: Launching Chrome and navigating to Login URL.");
    }

    @Test
    public void testValidLogin() {
        System.out.println("  [Test] Running: Login with valid credentials.");
    }

    @Test
    public void testInvalidLogin() {
        System.out.println("  [Test] Running: Login with incorrect password.");
    }

    @AfterMethod
    public void closeBrowser() {
        System.out.println("- @AfterMethod: Closing Chrome browser (cleaning session).");
    }

    @AfterClass
    public void clearMemory() {
        System.out.println("> @AfterClass: Nullifying Page Objects.");
    }
}