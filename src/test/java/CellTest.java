import org.example.cell.Cell;
import org.example.cell.CellType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    public void validCell() {
        assertDoesNotThrow(() -> new Cell(CellType.ALIVE));
    }

    @Test
    public void checkCellIsAlve() {
        Cell cell = new Cell(CellType.ALIVE);

        assertTrue(cell.isAlive());
    }

    @Test
    public void evolveCellWithZeroNeighboursWhenCellIsDead() {
        Cell cell = new Cell(CellType.DEAD);

        boolean actual = cell.evolve(0).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithZeroNeighboursWhenCellIsAlive() {
        Cell cell = new Cell(CellType.ALIVE);

        boolean actual = cell.evolve(0).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithOneNeighboursWhenCellIsDead() {
        Cell cell = new Cell(CellType.DEAD);

        boolean actual = cell.evolve(1).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithOneNeighboursWhenCellIsAlive() {
        Cell cell = new Cell(CellType.ALIVE);

        boolean actual = cell.evolve(1).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithTwoNeighboursWhenCellIsDead() {
        Cell cell = new Cell(CellType.DEAD);

        boolean actual = cell.evolve(2).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithTwoNeighboursWhenCellIsAlive() {
        Cell cell = new Cell(CellType.ALIVE);

        boolean actual = cell.evolve(2).isAlive();
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithThreeNeighboursWhenCellIsDead() {
        Cell cell = new Cell(CellType.DEAD);

        boolean actual = cell.evolve(3).isAlive();
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithThreeNeighboursWhenCellIsAlive() {
        Cell cell = new Cell(CellType.ALIVE);

        boolean actual = cell.evolve(3).isAlive();
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithFourNeighboursWhenCellIsDead() {
        Cell cell = new Cell(CellType.DEAD);

        boolean actual = cell.evolve(4).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithFourNeighboursWhenCellIsAlive() {
        Cell cell = new Cell(CellType.ALIVE);

        boolean actual = cell.evolve(4).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithGreaterThanFourNeighboursWhenCellIsDead() {
        Cell cell = new Cell(CellType.DEAD);

        boolean actual = cell.evolve(10).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithGreaterThanFourNeighboursWhenCellIsAlive() {
        Cell cell = new Cell(CellType.ALIVE);

        boolean actual = cell.evolve(10).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithLessThanZeroNeighboursWhenCellIsDead() {
        Cell cell = new Cell(CellType.DEAD);

        boolean actual = cell.evolve(-1).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void evolveCellWithLessThanZeroNeighboursWhenCellIsAlive() {
        Cell cell = new Cell(CellType.ALIVE);

        boolean actual = cell.evolve(-1).isAlive();
        boolean expected = false;
        assertEquals(expected, actual);
    }
}
