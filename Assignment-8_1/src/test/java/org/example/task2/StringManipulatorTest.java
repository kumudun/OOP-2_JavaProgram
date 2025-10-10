package org.example.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringManipulatorTest {

    private final StringManipulator sm = new StringManipulator();

    @Test
    void concatenate_joinsStrings() {
        assertEquals("HelloWorld", sm.concatenate("Hello", "World"));
        assertEquals("Hello", sm.concatenate("Hello", ""));
        assertEquals("World", sm.concatenate("", "World"));
        assertEquals("", sm.concatenate("", ""));
    }

    @Test
    void findLength_countsChars() {
        assertEquals(0, sm.findLength(""));
        assertEquals(5, sm.findLength("Hello"));
        assertEquals(1, sm.findLength(" "));
        assertEquals(6, sm.findLength("He llo"));
    }

    @Test
    void convertToUpperCase_converts() {
        assertEquals("HELLO", sm.convertToUpperCase("hello"));
        assertEquals("123!", sm.convertToUpperCase("123!"));
        assertEquals("ÄÖ", sm.convertToUpperCase("äö"));
    }

    @Test
    void convertToLowerCase_converts() {
        assertEquals("hello", sm.convertToLowerCase("HELLO"));
        assertEquals("123!", sm.convertToLowerCase("123!"));
        assertEquals("äö", sm.convertToLowerCase("ÄÖ"));
    }

    @Test
    void containsSubstring_checksMembership() {
        assertTrue(sm.containsSubstring("hello world", "world"));
        assertTrue(sm.containsSubstring("hello", "he"));
        assertFalse(sm.containsSubstring("hello", "HEL")); // case-sensitive
        assertFalse(sm.containsSubstring("hello", "xyz"));
        assertTrue(sm.containsSubstring("", "")); // String.contains("") is true
    }
}
