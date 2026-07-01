package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testBasicAssertions() {
        
        assertEquals(5, 2 + 3);
        assertEquals("Hello", "He" + "llo");
        
        assertNotEquals(5, 2 + 2);
        assertNotEquals("Hello", "World");
    }

    @Test
    public void testBooleanAssertions() {
       
        assertTrue(5 > 3);
        assertTrue("Hello".startsWith("H"));
        
        assertFalse(5 < 3);
        assertFalse("Hello".endsWith("z"));
    }

    @Test
    public void testNullAssertions() {
   
        String nullString = null;
        assertNull(nullString);
        assertNull(null);
        
        String nonNullString = "Hello";
        assertNotNull(nonNullString);
        assertNotNull(new Object());
    }

    @Test
    public void testArrayAssertions() {
   
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
        
        int[] notExpected = {1, 2, 4};
        assertNotEquals(expected, notExpected);
    }

    @Test
    public void testObjectAssertions() {
       
        String str1 = "Hello";
        String str2 = str1;
        assertSame(str1, str2);
        
        String str3 = new String("Hello");
        assertNotSame(str1, str3);
        
        assertEquals(str1, str3);
    }

    @Test
    public void testCalculatorAssertions() {
        Calculator calc = new Calculator();
        
        assertEquals(5, calc.add(2, 3));
        assertEquals(0, calc.add(-1, 1));
        assertTrue(calc.add(2, 3) > 0);
        assertFalse(calc.add(-5, 3) > 0);
        
        assertTrue(calc.isEven(4));
        assertFalse(calc.isEven(5));
        assertTrue(calc.isEven(0));
        assertTrue(calc.isEven(-2));
    }

    @Test
    public void testStringUtilsAssertions() {
        StringUtils utils = new StringUtils();
        
        assertEquals("olleH", utils.reverse("Hello"));
        assertNull(utils.reverse(null));
        assertEquals("", utils.reverse(""));
        
        assertTrue(utils.isPalindrome("madam"));
        assertTrue(utils.isPalindrome("racecar"));
        assertFalse(utils.isPalindrome("hello"));
        assertFalse(utils.isPalindrome(null));
        
        assertEquals(2, utils.countVowels("Hello"));
        assertEquals(5, utils.countVowels("AEIOU"));
        assertEquals(0, utils.countVowels(""));
        assertEquals(0, utils.countVowels(null));
    }

    @Test
    public void testStringUtilsAssertionsWithMessages() {
        StringUtils utils = new StringUtils();
        
        assertEquals("Reverse should work correctly", "olleH", utils.reverse("Hello"));
        assertTrue("'madam' should be a palindrome", utils.isPalindrome("madam"));
        assertFalse("'hello' should not be a palindrome", utils.isPalindrome("hello"));
        assertNotNull("StringUtils should not be null", utils);
    }
}