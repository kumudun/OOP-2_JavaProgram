package org.example.task1;



public class Pen {
    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");
        private final String color;
        Color(String color) { this.color = color; }
        @Override public String toString() { return color; }
    }

    private Color currentColor;
    private boolean capped = true; // pens start capped

    public Pen() { this(Color.RED); }
    public Pen(Color color) { this.currentColor = color; }

    public void capOn()  { this.capped = true; }
    public void capOff() { this.capped = false; }

    /** Color change takes effect only when capped (as required by tests). */
    public void changeColor(Color newColor) {
        if (capped) this.currentColor = newColor;
    }

    /** Draws only when uncapped; otherwise returns empty string. */
    public String draw() {
        return capped ? "" : ("Drawing " + currentColor);
    }
}
