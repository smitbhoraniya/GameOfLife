package org.example;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Neighbours {
    public final List<Point2D> neighboursCoordinates;
    public Neighbours(int x, int y, int totalRows, int totalColumns) {
        if (x < 0 || y < 0 || totalRows < 0 || totalColumns < 0) {
            throw new IllegalArgumentException("Board should have positive numbers of cells.");
        }
        if (totalRows < x || totalColumns < y) {
            throw new IllegalArgumentException("Coordinate could not exceed the boundary.");
        }
        this.neighboursCoordinates = new ArrayList<>();
        this.neighborsCount(x, y, totalRows, totalColumns);
    }

    private void neighborsCount(int x, int y, int totalRows, int totalColumns) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int coordinateX = x + i;
                int coordinateY = y + j;
                if (i == 0 && j == 0) {
                    continue;
                }
                if (coordinateX >= 0 && coordinateX < totalRows && coordinateY >= 0 && coordinateY < totalColumns) {
                    neighboursCoordinates.add(new Point2D.Double(coordinateX, coordinateY));
                }
            }
        }
    }
}
