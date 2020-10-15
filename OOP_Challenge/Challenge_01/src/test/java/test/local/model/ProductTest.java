package test.local.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {

    @Test
    void invalidProductTest() {
        Product expected = new Product(-1, "", "", 0, null);
        Product actual = Product.invalid();
        assertEquals(expected, actual);
    }

    @Test
    public void dollarToEuroConversionInConstructorTest() {
        // test correct dollar to euro conversion inside the Product Constructor
        Double expected = 0.85;
        Product p1 = new Product(1, "name", "desc", 1, Category.SHIRTS);
        assertEquals(expected, p1.getEuroPrice());
    }

    @Test
    public void compareTest() {
        Product p1 = new Product(1, "name", "desc", 1, Category.PANTS);
        Product p2 = new Product(1, "name", "desc", 1, Category.PANTS);
        assertTrue(p1.equals(p2));
    }

}