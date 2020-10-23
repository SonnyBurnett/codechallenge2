import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {

    private final double radius = 2;

    @Test
    public void testGetArea() {
        // Assign
        double expected = Math.PI*Math.pow(radius, 2);

        // Act + assert
        assertEquals(expected, Circle.calculateArea(radius));
    }

    @Test
    public void testGetCircumference() {
        // Assign
        double expected = 2*Math.PI*radius;

        // Act + assert
        assertEquals(expected, Circle.calculateCircumference(radius));
    }
}
