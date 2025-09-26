package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Notebook {
    private final List<Note> notes = new ArrayList<>();

    public void add(Note note) {
        if (note != null) notes.add(note);
    }
    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }
}
