package board;

import directions.Directions;
import directions.Directions2D;

import java.util.List;
import java.util.Map;

public abstract class Board2D implements Board{
    Directions directions = new Directions2D();

    @Override
    public Map<Integer, BoardPosition> getPositions() {
        return positions;
    }

    @Override
    public List<String> getAllowedValues() {
        return allowedValues;
    }
}
