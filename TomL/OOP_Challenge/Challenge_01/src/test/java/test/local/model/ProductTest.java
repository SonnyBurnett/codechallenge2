package test.local.model;

import org.junit.jupiter.api.Test;
import test.local.model.impl.InvalidProduct;
import test.local.model.impl.Pants;
import test.local.model.impl.Shirts;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void invalidProductTest() {
        Product expected = new InvalidProduct();
        Product actual = Product.invalid();
        assertEquals(expected, actual);
    }

    @Test
    public void dollarToEuroConversionInConstructorTest() {
        // test correct dollar to euro conversion inside the Product Constructor
        Double expected = 0.85;
        Product p1 = new Shirts(1, "name", "desc", 1);
        assertEquals(expected, p1.getEuroPrice());
    }

    @Test
    public void compareTest() {
        Product p1 = new Pants(1, "name", "desc", 1);
        Product p2 = new Pants(1, "name", "desc", 1);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void setCorrectCategoriesTest(){
        Product a = new Pants(1, "name", "desc", 1);
        Product b = new Shirts(1, "name", "desc", 1);
        assertNotEquals(a, b);
        assertEquals(Category.PANTS, a.getCategory());
        assertEquals(Category.SHIRTS, b.getCategory());
    }

}