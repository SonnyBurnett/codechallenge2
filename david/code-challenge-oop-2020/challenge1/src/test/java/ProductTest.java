import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    Product product;

    @BeforeEach
    void setup () {
        product = new Shirt("a", "b", "c", new BigDecimal(3));
    }

    @Test
    void getProductId() {
        assertEquals("a",product.getProductId());
    }

    @Test
    void getName() {
        assertEquals("b", product.getName());
    }

    @Test
    void getDescription() {
        assertEquals("c", product.getDescription());
    }

    @Test
    void getPrice() {
        assertEquals(new BigDecimal(3), product.getPrice());
    }

    @Test
    @DisplayName("should be able to adjust the price with a CurrencyConverter")
    void changePrice() {
        product.convertPrice(new CurrencyConverter(2f));
        assertEquals(new BigDecimal(6), product.getPrice());
    }
}
