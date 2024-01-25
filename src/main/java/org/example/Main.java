package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter row: ");
        int row = myObj.nextInt();
        System.out.print("Enter column: ");
        int column = myObj.nextInt();
        System.out.print("Enter seeds: ");
        int seeds = myObj.nextInt();
        System.out.println();

        try {
            Board board = new Board(row, column, seeds);
            board.play();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}