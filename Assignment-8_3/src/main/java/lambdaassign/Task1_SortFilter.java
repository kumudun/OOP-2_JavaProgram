package lambdaassign;

import java.util.*;

public class Task1_SortFilter {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30, "New York"));
        people.add(new Person("Bob", 25, "Los Angeles"));
        people.add(new Person("Charlie", 35, "New York"));
        people.add(new Person("Diana", 20, "Chicago"));
        people.add(new Person("Ethan", 22, "New York"));

        System.out.println("=== Original List ===");
        people.forEach(System.out::println);

        // Sort by age (ascending)
        people.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        System.out.println("\n=== Sorted by Age (Ascending) ===");
        people.forEach(System.out::println);

        // Keep only New York
        people.removeIf(p -> !p.getCity().equalsIgnoreCase("Doha"));

        System.out.println("\n=== Filtered (Only Doha) ===");
        people.forEach(System.out::println);
    }
}
