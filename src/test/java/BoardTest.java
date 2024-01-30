import org.example.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void validBoard() {
        assertDoesNotThrow(() -> new Board(0, 0, 0));
    }

    @Test
    public void getFreeCells() {
        Board board = new Board(3, 3, 0);

        int actual = board.getEmptyCells();
        int expected = 9;

        assertEquals(expected, actual);
    }

    @Test
    public void getFreeCellsWithSeeds() {
        Board board = new Board(3, 3, 2);

        int actual = board.getEmptyCells();
        int expected = 7;

        assertEquals(expected, actual);
    }

    @Test
    public void playGame() throws Exception {
        Board board = new Board(3, 3, 2);
        board.play();

        int actual = board.getEmptyCells();
        int expected = 9;

        assertEquals(expected, actual);
    }

//    @Test
//    public void nextGenerationNotPossible() throws Exception {
//        Board board = new Board(2, 2, 4);
//        Exception exception = assertThrows(Exception.class, board::play);
//
//        String actual = exception.getMessage();
//        String expected = "Next Generation is not possible.";
//
//        assertEquals(expected, actual);
//    }

    @Test
    public void NegativeRow() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Board(-1, 0, 0));

        String actual = exception.getMessage();
        String expected = "Board should have positive numbers of cells.";

        assertEquals(expected, actual);
    }

    @Test
    public void negativeColumn() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Board(0, -1, 0));

        String actual = exception.getMessage();
        String expected = "Board should have positive numbers of cells.";

        assertEquals(expected, actual);
    }

    @Test
    public void negativeSeeds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Board(0, 0, -1));

        String actual = exception.getMessage();
        String expected = "Seeds should be positive.";

        assertEquals(expected, actual);
    }

    @Test
    public void seedsExceedTotalCells() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Board(3, 3, 10));

        String actual = exception.getMessage();
        String expected = "Seeds should not exceed the total cells.";

        assertEquals(expected, actual);
    }
}
