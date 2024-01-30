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

        // while there should not be any alive cell.
        while(getEmptyCells() != row * column) {
            printBoard(iteration);

            // if next state is same as current state,
            // then it would lead to the infinite state generation.
            // for that we are checking next state and current state
            // and if both are same then throw an exception.
            Cell[][] nextBoardState = calculateNextBoardState();
            if (this.board == nextBoardState) {
                throw new Exception("Next Generation is not possible.");
            }
            this.board = nextBoardState;

            iteration++;
        }

        printBoard(iteration);
    }

    // neighbors 1: Make cell dead because of solitude.
    // neighbors 4: Make cell dead because of overpopulation.
    // neighbors 3: Make cell alive.
    // neighbors 2: Cell will survive.
    private Cell[][] calculateNextBoardState() {
        Cell[][] newBoard = new Cell[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int neighbors = board[i][j].neighborsCount(board);
                if (neighbors <= 1 || neighbors >= 4) {
                    newBoard[i][j] = new Cell(i, j, CellType.DEAD);
                }
                else if (neighbors == 3 || board[i][j].isAlive()) {
                    newBoard[i][j] = new Cell(i, j, CellType.ALIVE);
                }
                else {
                    newBoard[i][j] = new Cell(i, j, CellType.DEAD);
                }
            }
        }

        return newBoard;
    }

//    private int neighborsCount(int x, int y) {
//        int count = 0;
//
//        for (int i = -1; i <= 1; i++) {
//            for (int j = -1; j <= 1; j++) {
//                if (i == 0 && j == 0) {
//                    continue;
//                }
//
//                int neighborRow = x + i;
//                int neighborColumn = y + j;
//
//                if (isValidPosition(neighborRow, neighborColumn) && board[neighborRow][neighborColumn].isAlive()) {
//                    count++;
//                }
//            }
//        }
//
//        return count;
//    }

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
