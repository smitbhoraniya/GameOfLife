package org.example.cell;

import java.util.Objects;

public class Cell {
    private final CellType type;
    public Cell(CellType type) {
        this.type = type;
    }

    public boolean isAlive() {
        return type == CellType.ALIVE;
    }

    public Cell evolve(int liveNeighbours) {
        if (liveNeighbours == 3 || (liveNeighbours == 2 && this.type == CellType.ALIVE)) {
            return new Cell(CellType.ALIVE);
        }
        return new Cell(CellType.DEAD);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cell c)) {
            return false;
        }
        return c.type == this.type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.type);
    }
}
