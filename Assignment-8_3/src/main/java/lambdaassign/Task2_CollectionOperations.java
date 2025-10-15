package lambdaassign;

import java.util.*;

public class Task2_CollectionOperations {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 5, 8, 20, 15, 3, 12));

        System.out.println("=== Original List ===");
        System.out.println(numbers);

        // Remove even numbers
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("\n=== After Removing Even Numbers ===");
        System.out.println(numbers);

        // Double odd numbers
        numbers.replaceAll(n -> n * 2);
        System.out.println("\n=== After Doubling Odd Numbers ===");
        System.out.println(numbers);

        // Sum all numbers
        final int[] sum = {0};
        numbers.forEach(n -> sum[0] += n);
        System.out.println("\n=== Sum of Numbers ===");
        System.out.println(sum[0]);
    }
}
