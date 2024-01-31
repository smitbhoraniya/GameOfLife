import org.example.cell.Cell;
import org.example.cell.CellType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    public void validCell() {
        assertDoesNotThrow(() -> new Cell(0, 0 , CellType.ALIVE));
    }

    @Test
    public void inValidCell() {
        assertThrows(IllegalArgumentException.class, () -> new Cell(-1, 0, CellType.ALIVE));
    }

    @Test
    public void evolveCellWithOneNeighbor() {
        Cell[][] board = new Cell[2][2];
        board[0][0] = new Cell(0, 0, CellType.ALIVE);
        board[0][1] = new Cell(0, 1, CellType.ALIVE);
        board[1][0] = new Cell(1, 0, CellType.DEAD);
        board[1][1] = new Cell(1, 1, CellType.DEAD);

        Cell newCell = board[0][0].evolve(board);

        assertFalse(newCell.isAlive());
    }

    @Test
    public void evolveCellWithTwoNeighborsAndCellShouldBeAlive() {
        Cell[][] board = new Cell[2][2];
        board[0][0] = new Cell(0, 0, CellType.ALIVE);
        board[0][1] = new Cell(0, 1, CellType.ALIVE);
        board[1][0] = new Cell(1, 0, CellType.ALIVE);
        board[1][1] = new Cell(1, 1, CellType.DEAD);

        Cell newCell = board[0][0].evolve(board);

        assertTrue(newCell.isAlive());
    }

    @Test
    public void evolveCellWithTwoNeighborsAndCellShouldBeDead() {
        Cell[][] board = new Cell[2][2];
        board[0][0] = new Cell(0, 0, CellType.DEAD);
        board[0][1] = new Cell(0, 1, CellType.ALIVE);
        board[1][0] = new Cell(1, 0, CellType.ALIVE);
        board[1][1] = new Cell(1, 1, CellType.DEAD);

        Cell newCell = board[0][0].evolve(board);

        assertFalse(newCell.isAlive());
    }

    @Test
    public void evolveCellWithThreeNeighborAndCellShouldBeAlive() {
        Cell[][] board = new Cell[2][2];
        board[0][0] = new Cell(0, 0, CellType.DEAD);
        board[0][1] = new Cell(0, 1, CellType.ALIVE);
        board[1][0] = new Cell(1, 0, CellType.ALIVE);
        board[1][1] = new Cell(1, 1, CellType.ALIVE);

        Cell newCell = board[0][0].evolve(board);

        assertTrue(newCell.isAlive());
    }

    @Test
    public void evolveCellWithFourNeighbor() {
        Cell[][] board = new Cell[2][3];
        board[0][0] = new Cell(0, 0, CellType.ALIVE);
        board[0][1] = new Cell(0, 1, CellType.ALIVE);
        board[1][0] = new Cell(1, 0, CellType.ALIVE);
        board[1][1] = new Cell(1, 1, CellType.ALIVE);
        board[1][2] = new Cell(1, 2, CellType.ALIVE);
        board[0][2] = new Cell(0, 2, CellType.DEAD);

        Cell newCell = board[1][1].evolve(board);

        assertFalse(newCell.isAlive());
    }
}
