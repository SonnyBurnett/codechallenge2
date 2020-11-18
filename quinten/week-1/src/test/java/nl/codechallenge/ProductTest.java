package nl.codechallenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest {

    @Test
    public void productPriceIsEvaluated() {

        Product cheap = new Product(5, "", "", "", "");
        Product tenner = new Product(10, "", "", "", "");
        Product expensive = new Product(15, "", "", "", "");

        assertFalse(cheap.isTenPlusDollars());
        assertTrue(tenner.isTenPlusDollars());
        assertTrue(expensive.isTenPlusDollars());

    }

    @Test
    public void productPriceConvertedToEuros() {
        assertEquals(0, new Product(0, "", "", "", "").priceInEuro());
        assertEquals(0.85, new Product(1, "", "", "", "").priceInEuro());
        assertEquals(0.85 * 123, new Product(123, "", "", "", "").priceInEuro());

        // and negative prices are possible as far as the code is concerned:
        assertEquals(-0.85, new Product(-1, "", "", "", "").priceInEuro());

    }

    @Test
    public void productHasDataFields() {
        Product p = new Product(0, "productId", "name", "description", "category");

        assertEquals("productId", p.productId());
        assertEquals("name", p.name());
        assertEquals("description", p.description());
        assertEquals("category", p.category());
    }

    @Test
    public void productDataFieldsAreRequired() {

        assertThrows(NullPointerException.class, () -> {
            new Product(0, null, "", "", "");
        });

        assertThrows(NullPointerException.class, () -> {
            new Product(0, "", null, "", "");
        });

        assertThrows(NullPointerException.class, () -> {
            new Product(0, "", "", null, "");
        });

        assertThrows(NullPointerException.class, () -> {
            new Product(0, "", "", "", null);
        });
    }

}
