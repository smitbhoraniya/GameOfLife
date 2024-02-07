import org.example.Board;
import org.example.NextGenerationNotPossibleException;
import org.example.Simulator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimulatorTest {
    @Test
    public void atTheEndAllCellShouldBeDead() throws NextGenerationNotPossibleException {
        Board board = new Board(3, 3, 2);
        Simulator simulator = new Simulator(board);

        simulator.play();

        int actual = board.getDeadCells();
        int expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void nextGenerationIsNotPossible() {
        Board board = new Board(2, 2, 4);
        Simulator simulator = new Simulator(board);

        assertThrows(NextGenerationNotPossibleException.class, simulator::play);
    }
}
