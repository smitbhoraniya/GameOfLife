import org.example.Board;
import org.example.NextGenerationNotPossibleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void validBoard() {
        assertDoesNotThrow(() -> new Board(0, 0, 0));
    }

    @Test
    public void boardShouldHaveAllDeadCellWithoutSeeds() {
        Board board = new Board(3, 3, 0);

        int actual = board.getDeadCells();
        int expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void getDeadCellsWithSeeds() {
        Board board = new Board(3, 3, 2);

        int actual = board.getDeadCells();
        int expected = 7;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveBoardWithOneSeed() throws NextGenerationNotPossibleException {
        Board board = new Board(2, 2, 1);

        board.generateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveBoardWithTwoSeed() throws NextGenerationNotPossibleException {
        Board board = new Board(2, 2, 2);

        board.generateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveBoardWithThreeSeed() throws NextGenerationNotPossibleException {
        Board board = new Board(2, 2, 3);

        board.generateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void nextBoardGenerationIsNotPossible() {
        Board board = new Board(2, 2, 4);

        assertThrows(NextGenerationNotPossibleException.class, board::generateNextBoardState);
    }

    @Test
    public void negativeRowInBoard() {
        assertThrows(IllegalArgumentException.class, () -> new Board(-1, 0, 0));
    }

    @Test
    public void negativeColumnInBoard() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0, -1, 0));
    }

    @Test
    public void negativeSeedsInBoard() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0, 0, -1));
    }

    @Test
    public void seedsExceedTotalCells() {
        assertThrows(IllegalArgumentException.class, () -> new Board(3, 3, 10));
    }
}
