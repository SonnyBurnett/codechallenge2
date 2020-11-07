import directions.Directions2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Directions2DTest {

    Directions2D directions = new Directions2D();

    @Test
    public void testGetOppositeDirection() {
        // Act + assert
        assertEquals("LD", directions.getOppositeDirection("RU"));
        assertEquals("RD", directions.getOppositeDirection("LU"));
        assertEquals("U", directions.getOppositeDirection("D"));
        assertEquals("L", directions.getOppositeDirection("R"));
    }

    @Test
    public void testGetOppositeDirectionIncorrectLength() {
        // Assign
        String direction = "LUP";

        // Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> directions.getOppositeDirection(direction));

        String expectedMessage = "Direction string longer then 2: " + direction;
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testGetOppositeDirectionIncorrectLCharacter() {
        // Assign
        String direction = "LP";

        // Act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> directions.getOppositeDirection(direction));

        String expectedMessage = "Incorrect direction: " + direction.charAt(1) + ". Needs to be one of " + directions.getDirections().keySet();
        assertEquals(expectedMessage, exception.getMessage());
    }
}
