package org.example;

public class Simulator {
    private final Board board;
    public Simulator(Board board) {
        this.board = board;
    }

    public void play() throws NextGenerationNotPossibleException {
        int iteration = 0;

        while(this.board.getDeadCells() != this.board.totalCells()) {
            System.out.println("iteration count: " + iteration);
            this.board.print();

            this.board.generateNextBoardState();

            iteration++;
        }

        System.out.println("iteration count: " + iteration);
        this.board.print();
    }
}
