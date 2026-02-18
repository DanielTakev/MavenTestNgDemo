package org.dani.TestNG;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorDataTest {

    @DataProvider(name = "additionData")
    public Object[][] additionData() {
        return new Object[][] {
                { 10, 5, 15 },
                { 0, 0, 0 },
                { -1, -1, -2 },
                { 100, 200, 300 }
        };
    }

    @Test(dataProvider = "additionData")
    public void testAddMultipleValues(int input1, int input2, int expectedSum) {
        Calculator calc = new Calculator();
        int actualSum = calc.add(input1, input2);

        Assert.assertEquals(actualSum, expectedSum, "Sum failed for: " + input1 + " + " + input2);
    }
}