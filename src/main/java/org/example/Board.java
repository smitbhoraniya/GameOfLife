package org.example;

import org.example.cell.Cell;
import org.example.cell.CellType;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private Cell[][] board;
    private final int row;
    private final int column;
    private final int seeds;

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

        this.row = row;
        this.column = column;
        this.board = new Cell[row][column];
        this.seeds = seeds;
        initializeDefaultCells();
        initialAliveCells();
    }

    private void initializeDefaultCells() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.board[i][j] = new Cell(i, j, CellType.DEAD);
            }
        }
    }

    private void initialAliveCells() {
        int populatedSeed = 0;
        Random random = new Random();

        while (populatedSeed != this.seeds) {
            int rowIndex = random.nextInt(0, this.row);
            int columnIndex = random.nextInt(0, this.column);

            if (!board[rowIndex][columnIndex].isAlive()) {
                board[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex, CellType.ALIVE);
                populatedSeed++;
            }
        }
    }

    public int getEmptyCells() {
        int count = 0;

        for (Cell[] cells : board) {
            for (Cell cell: cells) {
                if (!cell.isAlive()) {
                    count++;
                }
            }
        }

        return count;
    }

    public void play() throws Exception {
        int iteration = 0;

        while(getEmptyCells() != row * column) {
            printBoard(iteration);

            Cell[][] nextBoardState = calculateNextBoardState();
            if (Arrays.deepEquals(this.board, nextBoardState)) {
                throw new Exception("Next Generation is not possible.");
            }
            this.board = nextBoardState;

            iteration++;
        }

        printBoard(iteration);
    }

    private Cell[][] calculateNextBoardState() {
        Cell[][] newBoard = new Cell[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                newBoard[i][j] = board[i][j].evolve(board);
            }
        }

        return newBoard;
    }

    private void printBoard(int iteration) {
        System.out.println("iteration count: " + iteration);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j].isAlive() ? "⦿ " : "○ ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
}
