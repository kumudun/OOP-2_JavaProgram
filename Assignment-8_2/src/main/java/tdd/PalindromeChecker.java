// src/main/java/tdd/PalindromeChecker.java
package tdd;

public class PalindromeChecker {

    /**
     * Returns true if the input reads the same forward and backward when we ignore
     * spaces, punctuation, and case. Returns false for null.
     */
    public boolean isPalindrome(String str) {
        if (str == null) return false;
        int i = 0, j = str.length() - 1;
        while (i < j) {
            char left = str.charAt(i);
            char right = str.charAt(j);

            // skip non-alphanumeric on both ends
            if (!Character.isLetterOrDigit(left)) { i++; continue; }
            if (!Character.isLetterOrDigit(right)) { j--; continue; }

            // compare case-insensitively
            if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                return false;
            }
            i++; j--;
        }
        return true;
    }
}
