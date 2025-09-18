package controller;



import model.Dictionary;

public class DictionaryController {
    private final Dictionary dictionary;

    public DictionaryController(Dictionary dictionary) {
        this.dictionary = dictionary;
        // temporary seed data
        dictionary.addWord("java", "A high-level, object-oriented programming language.");
        dictionary.addWord("javafx", "A Java toolkit for building GUIs.");
        dictionary.addWord("mvc", "Model-View-Controller architectural pattern.");
        dictionary.addWord("Inheritance", "The mechanism in OOP by which one class can inherit properties and methods from another class.");
        dictionary.addWord("Encapsulation", "The bundling of data and methods that operate on that data within a single unit or class, restricting access to some of the object's components.");
        dictionary.addWord("Polymorphism", "The ability of different classes to be treated as instances of the same class through a common interface, typically by overriding methods.");
        dictionary.addWord("interface", "A reference type in Java that defines a contract of methods that implementing classes must fulfill.");
        dictionary.addWord("Abstract class", "A class that cannot be instantiated on its own and may contain abstract methods that must be implemented by subclasses.");
        dictionary.addWord("Association", "A relationship between two classes where one class uses or interacts with another class.");
        dictionary.addWord("Collections", "A framework in Java that provides data structures and algorithms for storing and manipulating groups of objects, such as lists, sets, and maps.");

    }

    public String search(String word) {
        try {
            return dictionary.getMeaning(word);
        } catch (IllegalArgumentException e) {
            return "Please enter a word.";
        } catch (java.util.NoSuchElementException e) {
            return "Word not found in dictionary.";
        }
    }

    public void add(String word, String meaning) {
        dictionary.addWord(word, meaning);
    }
}

