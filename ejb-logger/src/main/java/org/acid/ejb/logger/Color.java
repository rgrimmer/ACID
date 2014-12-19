package org.acid.ejb.logger;

public enum Color {

    NORMAL(-1),
    BLACK(0),
    RED(1),
    GREEN(2),
    YELLOW(3),
    BLUE(4),
    MAGENTA(5),
    CYAN(6),
    WHITE(7);

    private final int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return (this == Color.NORMAL) ? "\u001B[m" : "\u001B[3" + value + "m";
    }
}
