import org.example.cell.Cell;
import org.example.cell.CellType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CellTest {
    @Test
    public void validCell() {
        assertDoesNotThrow(() -> new Cell(0, 0 , CellType.ALIVE));
    }

    @Test
    public void inValidCell() {
        assertThrows(IllegalArgumentException.class, () -> new Cell(-1, 0, CellType.ALIVE));
    }
}
