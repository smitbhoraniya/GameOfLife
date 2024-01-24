package org.example;

import org.example.cell.Cell;
import org.example.cell.CellX;

import java.util.ArrayList;

public class Board {
    private Cell[][] board;

    public Board(int row, int column) {
        this.board = new Cell[row][column];
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
