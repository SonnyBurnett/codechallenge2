import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductProcessorTest {
    ProductProcessor processor;

    @BeforeEach
    void setup() {
        this.processor = new ProductProcessor("test.csv");
    }

    @DisplayName("should parse lines to products when instantiated")
    @Test
    void construct() {
        assertEquals(3, this.processor.getProducts().size());
    }

    @DisplayName("should return parse it's lines to list of products")
    @Test
    void parseLinesToProducts() {
        List<Product> products =  this.processor.parseLinesToProducts();
        assertAll(() -> assertEquals(3, products.size()),
                () -> assertTrue(products.get(0) instanceof Product),
                () -> assertTrue(products.get(1) instanceof Product));
    }

    @DisplayName("should be able to filter products below 5")
    @Test
    void filterBelowPrice() {
        this.processor.filterBelowPrice(5);
        List<Product> above5 = this.processor.getProducts();
        assertAll(
                () -> assertEquals(1, above5.size()),
                () -> assertEquals("purple shirt", above5.get(0).getName())
        );
    }
}
