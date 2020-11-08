package board;

import directions.Directions2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void testHasNeighbourAtDirection() {
        // Assign
        String direction1 = "LD";
        String direction2 = "R";
        BoardPosition position1 = new BoardPosition(1);
        BoardPosition position2 = new BoardPosition(2);
        position1.addNeighbour(direction1, position2);

        // Act + assert
        assertEquals(position2, position1.getNeighbourAtDirection(direction1));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> position1.getNeighbourAtDirection(direction2));

        String expectedMessage = "Position " + position1.getPositionId() + " has no neighbour in direction " + direction2;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testSameValue() {
        // Assign
        String value = "X";
        BoardPosition mockPosition = mock(BoardPosition.class);
        when(mockPosition.getValue()).thenReturn(value).thenReturn("O");

        BoardPosition position1 = new BoardPosition(1);
        position1.setValue(value);


        // Act + assert
        assertTrue(position1.sameValue(mockPosition));
        assertFalse(position1.sameValue(mockPosition));
    }
}
