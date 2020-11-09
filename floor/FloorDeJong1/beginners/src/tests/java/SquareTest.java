import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTest {

    @Test
    public void testToString() {
        // assign
        double length = 2;
        String expected = "Square: " + length + "x" + length;

        Square square = new Square(length);

        // act + assert
        assertEquals(expected, square.toString());

    }
}
