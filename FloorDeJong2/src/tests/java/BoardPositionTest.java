import board.BoardPosition;
import directions.Directions2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardPositionTest {

    @Test
    public void testAddNeighbour() {
        // Assign
        String direction = "LD";
        String oppositeDirection = new Directions2D().getOppositeDirection(direction);
        BoardPosition position = new BoardPosition(0);
        BoardPosition neighbourPosition = new BoardPosition(1);

        // Act
        position.addNeighbour(direction, neighbourPosition);

        // Assert
        assertEquals(1, position.getNeighbours().size());
        assertTrue(position.getNeighbours().containsKey(direction));
        assertTrue(position.getNeighbours().containsValue(neighbourPosition));

        assertEquals(1, neighbourPosition.getNeighbours().size());
        assertTrue(neighbourPosition.getNeighbours().containsKey(oppositeDirection));
        assertTrue(neighbourPosition.getNeighbours().containsValue(position));
    }

    @Test
    public void testIsOccupied() {
        // Assign
        BoardPosition position1 = new BoardPosition(1);
        BoardPosition position2 = new BoardPosition(1);

        // Act
        position2.setValue("X");

        // Assert
        assertFalse(position1.isOccupied());
        assertTrue(position2.isOccupied());
    }
}
