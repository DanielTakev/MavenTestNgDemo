package org.dani;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class TestNgDemo {

    @Test(priority = 3)
    public void validateAddition() {
        Calculator calc = new Calculator();
        int result = calc.add(10, 5);

        // Check if 10 + 5 equals 15
        Assert.assertEquals(result, 15, "The sum calculation is incorrect.");
    }

    @Test(priority = 2)
    public void validateResetFunction() {
        Calculator calc = new Calculator();
        calc.setTotal(500); // Set a value
        calc.reset();       // Clear it

        // Check that the value is no longer 500
        Assert.assertNotEquals(calc.getTotal(), 500, "The total should not be 500 after reset.");
    }

    @Test(priority = 1)
    public void validateWelcomeMessage() {
        String username = "Alex";
        String actualMessage = "Welcome, " + username + "!";

        // Exact match check
        Assert.assertEquals(actualMessage, "Welcome, Alex!", "The greeting message is wrong.");
    }

    @Test(priority = 100)
    public void validateInventoryStatus() {
        int stockCount = 0;
        boolean isAvailable = (stockCount > 0);

        // We expect this to be false because stock is 0
        Assert.assertFalse(isAvailable, "Product should not be available when stock is zero.");
    }

    @Test(priority = 3)
    public void validateCartSize() {
        List<String> cart = new ArrayList<>();
        cart.add("Laptop");
        cart.add("Mouse");

        // Check if the list size is exactly 2
        Assert.assertEquals(cart.size(), 2, "The cart count does not match the items added.");
    }

    @Test(groups = "regression")
    public void validateEmailFormat() {
        String userEmail = "testuser@gmail.com";

        // Check if the email contains the domain
        Assert.assertTrue(userEmail.contains("@gmail.com"), "The email should be a Gmail address.");
    }

    @Test
    public void validateUserProfile() {
        SoftAssert softAssert = new SoftAssert();

        String actualName = "John";
        String actualEmail = "john@example.com";
        int actualAge = 25;

        // These will all run, even if the first one fails
        softAssert.assertEquals(actualName, "John", "Name mismatch!");
        softAssert.assertEquals(actualEmail, "john@example.com", "Email mismatch!");
        softAssert.assertTrue(actualAge > 18, "User is not an adult!");

        // This collects all failures and reports them at once
        softAssert.assertAll();
    }

    @Test
    public void validateFormErrors() {
        SoftAssert softAssert = new SoftAssert();

        boolean isUserErrorVisible = true;
        boolean isPassErrorVisible = false; // Suppose this failed to appear

        softAssert.assertTrue(isUserErrorVisible, "Username error should be displayed.");
        softAssert.assertFalse(isPassErrorVisible, "Password error should be displayed.");

        System.out.println("This line will print even if the password error was missing!");

        softAssert.assertAll();
    }
}
