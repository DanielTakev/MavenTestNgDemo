package org.dani.TestNG;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class ParallelDemo {

    /**
     * EXAMPLE 1: Parallel Methods
     * These tests will run at the same time if configured in the XML.
     * We print the Thread ID to prove they are running on different threads.
     */
    @Test
    public void testMethod1() throws InterruptedException {
        System.out.println("Test Method 1 - Thread ID: " + Thread.currentThread().getId());
        Thread.sleep(2000); // Simulate work
    }

    @Test
    public void testMethod2() throws InterruptedException {
        System.out.println("Test Method 2 - Thread ID: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }

    /**
     * EXAMPLE 2: Parallel DataProvider
     * By setting 'parallel = true', TestNG will run each row of data
     * in its own separate thread simultaneously.
     */
    @DataProvider(name = "parallelData", parallel = true)
    public Object[][] getParallelData() {
        return new Object[][] {
                {"Dataset A"},
                {"Dataset B"},
                {"Dataset C"}
        };
    }

    @Test(dataProvider = "parallelData")
    public void testDataProviderParallel(String data) throws InterruptedException {
        System.out.println("Testing " + data + " - Thread ID: " + Thread.currentThread().getId());
        Thread.sleep(3000);
    }
}