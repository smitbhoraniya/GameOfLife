package org.example.cell;

public enum CellType {
    DEAD("0"),
    ALIVE("1");

    private final String value;
    private CellType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
