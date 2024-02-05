import org.example.Board;
import org.example.GenerationNotPossible;
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
    public void evolveCellGridWithOneSeed() throws GenerationNotPossible {
        Board board = new Board(2, 2, 1);

        board.calculateNextCellGridState();

        int actual = board.getDeadCells();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellGridWithTwoSeed() throws GenerationNotPossible {
        Board board = new Board(2, 2, 2);

        board.calculateNextCellGridState();

        int actual = board.getDeadCells();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellGridWithThreeSeed() throws GenerationNotPossible {
        Board board = new Board(2, 2, 3);

        board.calculateNextCellGridState();

        int actual = board.getDeadCells();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void endOfGameAllCellShouldBeDead() throws Exception {
        Board board = new Board(3, 3, 2);
        board.play();

        int actual = board.getDeadCells();
        int expected = 9;

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

        assertThrows(GenerationNotPossible.class, board::play);
    }
}
