package org.example;

import org.example.cell.Cell;
import org.example.cell.CellX;

import java.util.Random;

public class Board {
    private Cell[][] board;
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
        this.board = new Cell[row][column];
        this.seeds = seeds;
        initBoard();
    }

    private void initBoard() {
        int populatedSeed = 0;

        Random random = new Random();
        while (populatedSeed != this.seeds) {
            int row = random.nextInt(0, board.length);
            int column = random.nextInt(0, board[0].length);

            if (board[row][column] == null) {
                board[row][column] = new CellX();
                populatedSeed++;
            }
        }
    }

    public int getEmptyCells() {
        int count = 0;
        for (Cell[] cells : board) {
            for (Cell cell: cells) {
                if (cell == null) {
                    count++;
                }
            }
        }

        return count;
    }


}
