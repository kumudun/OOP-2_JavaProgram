package org.example;

import java.util.Arrays;
import java.util.List;

public class AssignmentsCombined {

    // Task 1: mean (no loops)
    private static double mean(int[] data) {
        return Arrays.stream(data).average().orElse(0.0);
    }

    // Task 2: filter odds -> double -> sum
    private static int filterDoubleSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> (n & 1) == 1)
                .map(n -> n * 2)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};  // mean = 30.0
        List<Integer> nums = Arrays.asList(11, 22, 33, 44, 55, 66, 77, 88, 99, 100); // sum = 550

        System.out.println("Task 1 - Mean of the array = " + mean(arr));
        System.out.println("Task 2 - Sum of doubled odd numbers = " + filterDoubleSum(nums));
    }
}
