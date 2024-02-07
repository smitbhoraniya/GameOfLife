import org.example.Board;
import org.example.NextGenerationNotPossible;
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
    public void evolveBoardWithOneSeed() throws NextGenerationNotPossible {
        Board board = new Board(2, 2, 1);

        board.calculateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveBoardWithTwoSeed() throws NextGenerationNotPossible {
        Board board = new Board(2, 2, 2);

        board.calculateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveBoardWithThreeSeed() throws NextGenerationNotPossible {
        Board board = new Board(2, 2, 3);

        board.calculateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void endOfGameAllCellsShouldBeDead() throws Exception {
        Board board = new Board(3, 3, 2);
        board.play();

        int actual = board.getDeadCells();
        int expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void nextBoardStateWithOneSeed() throws NextGenerationNotPossible {
        Board board = new Board(2, 2, 1);
        board.calculateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void nextBoardStateWithTwoSeed() throws NextGenerationNotPossible {
        Board board = new Board(2, 2, 2);
        board.calculateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void nextBoardStateWithThreeSeed() throws NextGenerationNotPossible {
        Board board = new Board(2, 2, 3);
        board.calculateNextBoardState();

        int actual = board.getDeadCells();
        int expected = 0;
        assertEquals(expected, actual);
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

    @Test
    public void nextGenerationNotPossible() {
        Board board = new Board(2, 2, 4);

        assertThrows(NextGenerationNotPossible.class, board::play);
    }
}
