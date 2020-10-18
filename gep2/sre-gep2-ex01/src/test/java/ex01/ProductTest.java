package ex01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private static Product test_Product;

    @BeforeEach
    void setUp() {
        Map<String, String> test_properties = new HashMap<>();

        test_properties.put("Id", "007");
        test_properties.put("name", "James Bond");

        test_Product = new Product(new ProductDetail(test_properties));
    }

    @Test
    void getProductProperty() {
        assertEquals("007", test_Product.getProductProperty("Id"));
    }

    @Test
    void getDetails() {
        assertEquals("007", test_Product.getDetails().getProperty("Id"));
    }
}