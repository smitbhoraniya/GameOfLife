package org.example;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private String[][] board;
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
        this.board = new String[row][column];
        this.seeds = seeds;
        initBoard();
    }

    private void initBoard() {
        int populatedSeed = 0;

        Random random = new Random();
        while (populatedSeed != this.seeds) {
            int rowIndex = random.nextInt(0, this.row);
            int columnIndex = random.nextInt(0, this.column);

            if (board[rowIndex][columnIndex] == null) {
                board[rowIndex][columnIndex] = "X";
                populatedSeed++;
            }
        }
    }

    public int getEmptyCells() {
        int count = 0;
        for (String[] cells : board) {
            for (String cell: cells) {
                if (cell == null) {
                    count++;
                }
            }
        }

        return count;
    }

    public void play() {
        int iteration = 0;
        while(getEmptyCells() != row * column) {
            iteration++;
            System.out.println("Iteration count: " + iteration);
            printBoard();
            nextPlay();
            System.out.println();
            System.out.println();
        }
        iteration++;
        System.out.println("Iteration count: " + iteration);
        printBoard();
    }

    private void nextPlay() {
        List<Point2D> populatedCells = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int neighbors = neighborsCount(i, j);
                if (neighbors <= 1 || neighbors >= 4) {
                    continue;
                }
                if (neighbors == 3 || (neighbors == 2 && board[i][j] != null)) {
                    populatedCells.add(new Point2D.Double(i, j));
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = null;
            }
        }

        for (Point2D point: populatedCells) {
            board[(int) point.getX()][(int) point.getY()] = "X";
        }
    }

    private int neighborsCount(int i, int j) {
        int count = 0;

        if (i - 1 >= 0 && i - 1 < this.row && j - 1 >= 0 && j - 1 < this.column && this.board[i - 1][j - 1] != null) {
            count++;
        }
        if (i - 1 >= 0 && i - 1 < this.row && j >= 0 && j < this.column && this.board[i - 1][j] != null) {
            count++;
        }
        if (i - 1 >= 0 && i - 1 < this.row && j + 1 >= 0 && j + 1 < this.column && this.board[i - 1][j + 1] != null) {
            count++;
        }
        if (i >= 0 && i < this.row && j - 1 >= 0 && j - 1 < this.column && this.board[i][j - 1] != null) {
            count++;
        }
        if (i >= 0 && i < this.row && j + 1 >= 0 && j + 1 < this.column && this.board[i][j + 1] != null) {
            count++;
        }
        if (i + 1 >= 0 && i + 1 < this.row && j - 1 >= 0 && j - 1 < this.column && this.board[i + 1][j - 1] != null) {
            count++;
        }
        if (i + 1 >= 0 && i + 1 < this.row && j >= 0 && j < this.column && this.board[i + 1][j] != null) {
            count++;
        }
        if (i + 1 >= 0 && i + 1 < this.row && j + 1 >= 0 && j + 1 < this.column && this.board[i + 1][j + 1] != null) {
            count++;
        }

        return count;
    }

    private void printBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j]);
                }
                else {
                    System.out.print(" ");
                }

                if (j != column - 1) {
                    System.out.print("|");
                }
            }
            if (i != row - 1) {
                System.out.println();
                for (int j = 0; j < column; j++) {
                    System.out.print("__");
                }
            }
            System.out.println();
        }
    }
}
