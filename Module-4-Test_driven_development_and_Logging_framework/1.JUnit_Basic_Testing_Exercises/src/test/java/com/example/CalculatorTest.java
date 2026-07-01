package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
        assertEquals(0, calc.add(-1, 1));
        assertEquals(-5, calc.add(-2, -3));
        assertEquals(10, calc.add(0, 10));
    }

    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.subtract(5, 3));
        assertEquals(-2, calc.subtract(3, 5));
        assertEquals(0, calc.subtract(5, 5));
        assertEquals(-5, calc.subtract(0, 5));
    }

    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.multiply(2, 3));
        assertEquals(-6, calc.multiply(-2, 3));
        assertEquals(6, calc.multiply(-2, -3));
        assertEquals(0, calc.multiply(5, 0));
    }

    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.divide(6, 3));
        assertEquals(-2, calc.divide(-6, 3));
        assertEquals(2, calc.divide(-6, -3));
        assertEquals(0, calc.divide(0, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        Calculator calc = new Calculator();
        calc.divide(5, 0);
    }

    @Test
    public void testIsEven() {
        Calculator calc = new Calculator();
        assertTrue(calc.isEven(2));
        assertTrue(calc.isEven(0));
        assertTrue(calc.isEven(-4));
        assertFalse(calc.isEven(3));
        assertFalse(calc.isEven(-5));
    }

    @Test
    public void testFactorial() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.factorial(0));
        assertEquals(1, calc.factorial(1));
        assertEquals(2, calc.factorial(2));
        assertEquals(6, calc.factorial(3));
        assertEquals(120, calc.factorial(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        Calculator calc = new Calculator();
        calc.factorial(-1);
    }
}