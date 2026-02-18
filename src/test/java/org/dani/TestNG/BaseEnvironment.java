package org.dani.TestNG;

import org.testng.annotations.*;

public class BaseEnvironment {

    @BeforeSuite
    public void startGlobalServices() {
        System.out.println(">>> @BeforeSuite: Starting Selenium Browser & Database Connection...");
    }

    @BeforeTest
    public void configureModuleSettings() {
        System.out.println(">> @BeforeTest: Loading config files for this specific <test> tag...");
    }

    @AfterTest
    public void cleanModuleSettings() {
        System.out.println("<< @AfterTest: Closing module-specific resources...");
    }

    @AfterSuite
    public void stopGlobalServices() {
        System.out.println("<<< @AfterSuite: Tearing down all services and generating final HTML report.");
    }
}