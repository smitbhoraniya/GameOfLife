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
        System.out.println();

        try {
            Board board = new Board(row, column, seeds);
            Simulator simulator = new Simulator(board);
            simulator.play();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
