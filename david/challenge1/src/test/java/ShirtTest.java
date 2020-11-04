import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShirtTest {
    Shirt shirt;

    @BeforeEach
    void setup() {
        this.shirt = new Shirt("1", "a shirt", "a nice shirt", new BigDecimal(3f));
    }

    @DisplayName("should be able to instantiate shirt class")
    @Test
    void shirt() {
        assertTrue(this.shirt instanceof Shirt);
    }

    @DisplayName("should return shirts category")
    @Test
    void getCategory() {
        assertEquals(this.shirt.getCategory(), "shirts");
    }
}
