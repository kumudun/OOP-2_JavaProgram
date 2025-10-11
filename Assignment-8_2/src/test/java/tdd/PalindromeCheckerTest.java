// src/test/java/tdd/PalindromeCheckerTest.java
package tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PalindromeChecker TDD tests")
class PalindromeCheckerTest {

    @Test
    @DisplayName("Simple palindromes")
    void simplePalindromes() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("radar"));
        assertTrue(checker.isPalindrome("level"));
        assertTrue(checker.isPalindrome("1221"));
    }

    @Test
    @DisplayName("Ignore case, spaces, and punctuation")
    void ignoresCaseSpacesPunctuation() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome("A man, a plan, a canal, Panama"));
        assertTrue(checker.isPalindrome("No 'x' in Nixon"));
        assertTrue(checker.isPalindrome("Was it a car or a cat I saw?"));
    }

    @Test
    @DisplayName("Non-palindromes return false")
    void nonPalindromes() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse(checker.isPalindrome("hello"));
        assertFalse(checker.isPalindrome("openai"));
        assertFalse(checker.isPalindrome("Java"));
    }

    @Test
    @DisplayName("Empty string is a palindrome; null is not")
    void emptyAndNull() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(""));         // common convention
        assertFalse(checker.isPalindrome(null));      // safe behavior
    }

    @Test
    @DisplayName("Unicode letters/numbers basic handling")
    void unicodeBasic() {
        PalindromeChecker checker = new PalindromeChecker();
        // Uses Character.isLetterOrDigit & toLowerCase → handles many scripts
        assertTrue(checker.isPalindrome("Ää"));
        assertFalse(checker.isPalindrome("Äb"));
    }
}
