package org.example.cell;

public class Cell {
    private final int x;
    private final int y;
    private CellType type;
    public Cell(int x, int y, CellType type) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates should be positive.");
        }
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean isAlive() {
        return type == CellType.ALIVE;
    }
}
