package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Note;
import model.Notebook;

public class NoteController {

    @FXML private TextField titleField;
    @FXML private TextArea  contentArea;
    @FXML private TextArea  outputArea;

    private final Notebook notebook = new Notebook();

    @FXML
    private void initialize() {
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        contentArea.setWrapText(true);
    }

    @FXML
    private void handleAdd() {
        String title   = titleField.getText().trim();
        String content = contentArea.getText().trim();
        if (title.isEmpty() && content.isEmpty()) return;

        notebook.add(new Note(title, content));
        refreshOutput();

        titleField.clear();
        contentArea.clear();
        titleField.requestFocus();
    }

    private void refreshOutput() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Note n : notebook.getNotes()) {
            sb.append(i++).append(". ")
                    .append(n.getTitle().isBlank() ? "(untitled)" : n.getTitle())
                    .append('\n').append(n.getContent()).append("\n\n");
        }
        outputArea.setText(sb.toString());
    }
}
