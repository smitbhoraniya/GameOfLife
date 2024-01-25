import org.example.Board;
import org.example.cell.CellX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void validCellX() {
        assertDoesNotThrow(() -> new CellX());
    }

    @Test
    public void validBoard() {
        assertDoesNotThrow(() -> new Board(0, 0, 0));
    }

    @Test
    public void NegativeRow() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Board(-1, 0, 0));

        String actual = exception.getMessage();
        String expected = "Board should have positive numbers of cells.";
    }

    @Test
    public void negativeColumn() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Board(0, -1, 0));

        String actual = exception.getMessage();
        String expected = "Board should have positive numbers of cells.";
    }

    @Test
    public void negativeSeeds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Board(0, 0, -1));

        String actual = exception.getMessage();
        String expected = "Board should have positive numbers of cells.";
    }

    @Test
    public void seedsExceedTotalCells() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Board(3, 3, 10));

        String actual = exception.getMessage();
        String expected = "Board should have positive numbers of cells.";
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
}
