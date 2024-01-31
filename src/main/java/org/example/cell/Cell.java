package org.example.cell;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Cell {
    private final int x;
    private final int y;
    private final CellType type;
    private final List<Point2D> neighbour;
    public Cell(int x, int y, CellType type) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates should be positive.");
        }
        this.x = x;
        this.y = y;
        this.type = type;
        this.neighbour = new ArrayList<>();
        IntStream.rangeClosed(-1, 1)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(-1, 1)
                        .mapToObj(j -> new Point2D.Double(this.x + i, this.y + j)))
                .forEach(this.neighbour::add);
    }

    public boolean isAlive() {
        return type == CellType.ALIVE;
    }

    private int neighborsCount(Cell[][] board) {
        int count = 0;
        for (Point2D point: neighbour) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            if (isValidPosition(x, y, board.length, board[0].length) && board[x][y].isAlive() && !(this.x == x && this.y == y)) {
                count++;
            }
        }

        return count;
    }

    public Cell evolve(Cell[][] board) {
        int neighborsCount = neighborsCount(board);
        if (neighborsCount == 3 || (neighborsCount == 2 && this.isAlive())) {
            return new Cell(this.x, this.y, CellType.ALIVE);
        }
        return new Cell(this.x, this.y, CellType.DEAD);
    }

    private boolean isValidPosition(int x, int y, int row, int column) {
        return x >= 0 && x < row && y >= 0 && y < column;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Cell c)) {
            return false;
        }
        return c.x == this.x && c.y == this.y && c.type == this.type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash("" + this.x + this.y + this.type);
    }
}
