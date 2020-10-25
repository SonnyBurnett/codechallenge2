import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleTest {
    private final double length = 2;
    private final double width = 3;

    @Test
    public void testGetArea() {
        // assign
        double expected = length*width;
        // Act + assert
        assertEquals(expected, Rectangle.calculateArea(length, width));
    }

    @Test
    public void testGetCircumference() {
        // assign
        double expected = 2*length + 2*width;

        // Act + assert
        assertEquals(expected, Rectangle.calculateCircumference(length, width));
    }

    @Test
    public void testToString() {
        // assign
        double length = 2, width = 3;
        String expected = "Rectangle: " + length + "x" + width;

        Rectangle rectangle = new Rectangle(length, width);

        // act + assert
        assertEquals(expected, rectangle.toString());

    }
}
