package test.local.util;

import org.junit.jupiter.api.Test;
import test.local.CodingChallenge;
import test.local.model.Product;
import test.local.model.impl.Pants;
import test.local.model.impl.Shirts;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListManipulatorTest {

    private List<Product> products = List.of(
            new Pants(1, "product1", "desc1", 1),
            new Shirts(2, "product2", "desc2", 5)

    );

    private ListManipulator listManipulator = new ListManipulator();

    @Test
    void mergeHeaderWithProducts() {
        List<String> expected = List.of(
                CodingChallenge.HEADER_ROW,
                "1, product1, desc1, 0.85, pants",
                "2, product2, desc2, 4.25, shirts"
        );
        List<String> actual = listManipulator.mergeHeaderWithProducts(products);
        assertEquals(expected, actual);
    }

    @Test
    void convertProductToCSVStringList() {
        List<String> expected = List.of(
                "1, product1, desc1, 0.85, pants",
                "2, product2, desc2, 4.25, shirts"
        );
        List<String> actual = listManipulator.convertProductToCSVStringList(products);
        assertEquals(expected, actual);
    }
}