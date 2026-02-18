package org.dani.TestNG;

/**
 * A simple Calculator class for Unit Testing demonstrations.
 */
public class Calculator {
    private int total = 0;

    // Standard addition
    public int add(int a, int b) {
        return a + b;
    }

    // Standard subtraction
    public int subtract(int a, int b) {
        return a - b;
    }

    // Methods to demonstrate state management
    public void setTotal(int value) {
        this.total = value;
    }

    public int getTotal() {
        return this.total;
    }

    public void reset() {
        this.total = 0;
    }

    // Example of a method that might return null for certain inputs
    public String getOperationName(char symbol) {
        if (symbol == '+') return "Addition";
        if (symbol == '-') return "Subtraction";
        return null; // Useful for assertNull demonstrations
    }
}