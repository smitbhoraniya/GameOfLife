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
        assertDoesNotThrow(() -> new Board(0, 0));
    }

    @Test
    public void getFreeCells() {
        Board board = new Board(3, 3);

        int actual = board.getEmptyCells();
        int expected = 9;

        assertEquals(expected, actual);
    }
}
