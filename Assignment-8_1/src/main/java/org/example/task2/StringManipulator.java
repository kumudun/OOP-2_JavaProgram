package org.example.task2;

public class StringManipulator {

    public String concatenate(String str1, String str2) {
        return str1 + str2;
    }

    public int findLength(String str) {
        return str.length();
    }

    public String convertToUpperCase(String str) {
        return str.toUpperCase();
    }

    public String convertToLowerCase(String str) {
        return str.toLowerCase();
    }

    public boolean containsSubstring(String str, String subStr) {
        return str.contains(subStr);
    }
}
