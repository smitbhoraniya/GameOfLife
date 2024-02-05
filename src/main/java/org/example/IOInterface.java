package org.example;

import java.util.Scanner;

public class IOInterface {
    public void playGame() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter number of row: ");
        int row = myObj.nextInt();
        System.out.print("Enter number of column: ");
        int column = myObj.nextInt();
        System.out.print("Enter number of seeds: ");
        int seeds = myObj.nextInt();
        System.out.print("Type of playing(Manual(0) & Automatic(1)): ");
        int typeOfPlaying = myObj.nextInt();
        System.out.println();

        try {
            Board board = new Board(row, column, seeds);
            if (typeOfPlaying == 1) {
                board.play();
            }
            else if (typeOfPlaying == 0) {
                while (board.getDeadCells() != row * column) {
                    board.printBoard();
                    board.calculateNextCellGridState();
                    System.out.print("Enter any number for get next state: ");
                    myObj.nextInt();
                }
            }
            else {
                throw new UnsupportedOperationException("Playing type is not supported.");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
