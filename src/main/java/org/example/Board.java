package org.example;

import org.example.cell.Cell;
import org.example.cell.CellType;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    private Cell[][] board;
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
        this.board = new Cell[row][column];
        this.row = row;
        this.column = column;
        initializeDefaultCells();
        initialAliveCells(seeds);
    }

    private void initializeDefaultCells() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.board[i][j] = new Cell(CellType.DEAD);
            }
        }
    }

    private void initialAliveCells(int seeds) {
        int populatedSeed = 0;
        Random random = new Random();

        while (populatedSeed != seeds) {
            int rowIndex = random.nextInt(0, this.row);
            int columnIndex = random.nextInt(0, this.column);

            if (!board[rowIndex][columnIndex].isAlive()) {
                board[rowIndex][columnIndex] = new Cell(CellType.ALIVE);
                populatedSeed++;
            }
        }
    }

    public int getDeadCells() {
        int count = 0;

        for (Cell[] cells : this.board) {
            for (Cell cell: cells) {
                if (!cell.isAlive()) {
                    count++;
                }
            }
        }

        return count;
    }

    public int totalCells() {
        return this.row * this.column;
    }

    public void generateNextBoardState() throws NextGenerationNotPossibleException {
        Cell[][] newBoard = new Cell[this.row][this.column];

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                List<Point2D> neighbours = new Neighbours(i, j, this.row, this.column).neighboursCoordinates;
                int liveNeighbours = (int) neighbours.stream()
                        .map(coordinates -> board[(int) coordinates.getX()][(int) coordinates.getY()])
                        .filter(Cell::isAlive).count();
                newBoard[i][j] = board[i][j].evolve(liveNeighbours);
            }
        }

        if (Arrays.deepEquals(this.board, newBoard)) {
            throw new NextGenerationNotPossibleException("Next Generation is not possible.");
        }
        this.board = newBoard;
    }

    public void print() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j].isAlive() ? CellType.ALIVE.getValue() + " " : CellType.DEAD.getValue() + " ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
}
