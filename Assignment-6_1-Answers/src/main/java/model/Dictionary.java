package model;



import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

public class Dictionary {
    private final Map<String, String> data = new HashMap<>();

    public void addWord(String word, String meaning) {
        String key = normalize(word);
        if (key.isEmpty()) throw new IllegalArgumentException("empty");
        data.put(key, meaning);
    }

    public String getMeaning(String word) {
        String key = normalize(word);
        if (key.isEmpty()) throw new IllegalArgumentException("empty");
        String meaning = data.get(key);
        if (meaning == null) throw new NoSuchElementException("notfound");
        return meaning;
    }

    private String normalize(String s) {
        return s == null ? "" : s.trim().toLowerCase(Locale.ROOT);
    }
}

