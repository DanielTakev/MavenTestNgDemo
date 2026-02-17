package org.dani;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

public class SimpleDataTest {

    // 1. Define the DataProvider
    @DataProvider(name = "nameProvider")
    public Object[] nameData() {
        return new Object[] { "Alice", "Bob", "Charlie" };
    }

    // 2. Link the test to the DataProvider
    @Test(dataProvider = "nameProvider")
    public void testNames(String name) {
        System.out.println("Testing name: " + name);
        Assert.assertNotNull(name);
    }
}