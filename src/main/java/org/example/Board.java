package org.example;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private int[][] board;
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
        this.board = new int[row][column];
        this.seeds = seeds;
        initialAliveCells();
    }

    private void initialAliveCells() {
        int populatedSeed = 0;
        Random random = new Random();

        while (populatedSeed != this.seeds) {
            int rowIndex = random.nextInt(0, this.row);
            int columnIndex = random.nextInt(0, this.column);

            // Cell should be null.
            if (board[rowIndex][columnIndex] == 0) {
                board[rowIndex][columnIndex] = 1;
                populatedSeed++;
            }
        }
    }

    public int getEmptyCells() {
        int count = 0;

        for (int[] cells : board) {
            for (int cell: cells) {
                if (cell == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public void play() throws Exception {
        int iteration = 0;

        // while there should not be any alive cell.
        while(getEmptyCells() != row * column) {
            printBoard(iteration);

            int[][] nextBoard = calculateNextBoardState();
            if (Arrays.deepEquals(this.board, nextBoard)) {
                throw new Exception("Next Generation is not possible.");
            }
            this.board = nextBoard;

            iteration++;
        }

        printBoard(iteration);
    }

    // neighbors 1: Make cell dead because of solitude.
    // neighbors 4: Make cell dead because of overpopulation.
    // neighbors 3: Make cell alive.
    // neighbors 2: Cell will survive.
    private int[][] calculateNextBoardState() {
        int[][] newBoard = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int neighbors = neighborsCount(i, j);
                if (neighbors <= 1 || neighbors >= 4) {
                    continue;
                }
                if (neighbors == 3 || board[i][j] == 1) {
                    newBoard[i][j] = 1;
                }
            }
        }

        return newBoard;
    }

    private int neighborsCount(int x, int y) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int neighborRow = x + i;
                int neighborColumn = y + j;

                if (isValidPosition(neighborRow, neighborColumn) && board[neighborRow][neighborColumn] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void printBoard(int iteration) {
        System.out.println("iteration count: " + iteration);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j] == 1 ? "⦿ " : "○ ");
            }
            System.out.println();
        }

        System.out.println("\n\n");
    }

    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < this.row && column >= 0 && column < this.column;
    }
}
