package org.example;

import org.example.cell.Cell;
import org.example.cell.CellType;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private Cell[][] cellGrid;
    private final int row;
    private final int column;
    public Board(int row, int column, int seeds) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("Board should have positive numbers of cells.");
        }
        if (seeds < 0) {
            throw new IllegalArgumentException("Seeds should be positive.");
        }
        if (row * column < seeds) {
            throw new IllegalArgumentException("Seeds should not exceed the total cells.");
        }
        this.cellGrid = new Cell[row][column];
        this.row = row;
        this.column = column;
        initializeDefaultCells();
        initialAliveCells(seeds);
    }

    private void initializeDefaultCells() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.cellGrid[i][j] = new Cell(CellType.DEAD);
            }
        }
    }

    private void initialAliveCells(int seeds) {
        int populatedSeed = 0;
        Random random = new Random();

        while (populatedSeed != seeds) {
            int rowIndex = random.nextInt(0, this.row);
            int columnIndex = random.nextInt(0, this.column);

            if (!cellGrid[rowIndex][columnIndex].isAlive()) {
                cellGrid[rowIndex][columnIndex] = new Cell(CellType.ALIVE);
                populatedSeed++;
            }
        }
    }

    public int getDeadCells() {
        int count = 0;

        for (Cell[] cells : this.cellGrid) {
            for (Cell cell: cells) {
                if (!cell.isAlive()) {
                    count++;
                }
            }
        }

        return count;
    }

    private int neighborsCount(int row, int column) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int x = row + i;
                int y = column + j;
                if (i == 0 && j == 0) {
                    continue;
                }
                if (x >= 0 && x < this.row && y >= 0 && y < this.column && cellGrid[x][y].isAlive()) {
                    count++;
                }
            }
        }

        return count;
    }

    public void calculateNextCellGridState() throws GenerationNotPossible {
        Cell[][] newCellGrid = new Cell[this.row][this.column];

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                int liveNeighbours = neighborsCount(i, j);
                newCellGrid[i][j] = cellGrid[i][j].evolve(liveNeighbours);
            }
        }

        if (Arrays.deepEquals(this.cellGrid, newCellGrid)) {
            throw new GenerationNotPossible("Next Generation is not possible.");
        }
        this.cellGrid = newCellGrid;
    }

    public void play() throws GenerationNotPossible {
        int iteration = 0;

        while(getDeadCells() != row * column) {
            System.out.println("iteration count: " + iteration);
            this.printBoard();

            this.calculateNextCellGridState();

            iteration++;
        }

        System.out.println("iteration count: " + iteration);
        this.printBoard();
    }

    public void printBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(cellGrid[i][j].isAlive() ? CellType.ALIVE.getValue() + " " : CellType.DEAD.getValue() + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
}
