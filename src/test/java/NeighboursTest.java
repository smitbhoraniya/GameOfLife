import org.example.Neighbours;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NeighboursTest {
    @Test
    public void findValidNeighbours() {
        List<Point2D> neighbours = new Neighbours(5, 5, 10, 10).neighboursCoordinates;

        List<Point2D> expectedNeighbours = new ArrayList<>() {{
            add(new Point2D.Double(4, 4));
            add(new Point2D.Double(4, 5));
            add(new Point2D.Double(4, 6));
            add(new Point2D.Double(5, 4));
            add(new Point2D.Double(5, 6));
            add(new Point2D.Double(6, 4));
            add(new Point2D.Double(6, 5));
            add(new Point2D.Double(6, 6));
        }};
        assertEquals(expectedNeighbours, neighbours);
    }

    @Test
    public void findStartPointNeighbours() {
        List<Point2D> neighbours = new Neighbours(0, 0, 10, 10).neighboursCoordinates;

        List<Point2D> expectedNeighbours = new ArrayList<>() {{
            add(new Point2D.Double(0, 1));
            add(new Point2D.Double(1, 0));
            add(new Point2D.Double(1, 1));
        }};
        assertEquals(expectedNeighbours, neighbours);
    }
}
