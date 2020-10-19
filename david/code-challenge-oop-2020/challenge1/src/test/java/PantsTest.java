import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PantsTest {
    Pants pants;

    @BeforeEach
    void setup() {
        this.pants = new Pants("2", "pants", "some pants", 9);
    }

    @DisplayName("should be able to instantiate pants class")
    @Test
    void shirt() {
        assertTrue(this.pants instanceof Pants);
    }

    @DisplayName("should return pants category")
    @Test
    void getCategory() {
        assertEquals(this.pants.getCategory(), "pants");
    }
}
