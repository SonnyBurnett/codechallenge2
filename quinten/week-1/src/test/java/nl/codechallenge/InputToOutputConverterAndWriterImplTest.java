package nl.codechallenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputToOutputConverterAndWriterImplTest {

    @Test
    public void trimArray() {
        // for stupid CSV libraries that do not properly support mappings to the read
        // data...
        String[] untrimmed = { " a", "    b c " };
        String[] wanted = { "a", "b c" };
        assertTrue(Arrays.equals(wanted, InputToOutputConverterAndWriterImpl.trim(untrimmed)));
    }

    @Test
    public void parseCsvRowToProduct() {
        // given
        String[] row = { "45848", " shorts", " short pants", " 8", " pants" };

        // when
        Product product = InputToOutputConverterAndWriterImpl.parseCsvRowToProduct(row);

        // then
        Product expected = new Product(8, "45848", "shorts", "short pants", "pants");
        assertEquals(expected, product);
    }

    @Test
    public void productToEuroRow() {
        // given
        Product product = new Product(10, "45848", "shorts", "short pants", "pants");

        // when
        String[] converted = InputToOutputConverterAndWriterImpl.productToEuroRow(product);

        // then
        String[] expected = { "45848", "shorts", "short pants", "8.5", "pants" };
        assertTrue(Arrays.equals(expected, converted));
    }

    @Test
    public void assertCorrectHeaderLine() {
        // given
        String[] headerWithSpaces = { "productId", " name", " description", " price", " category" };
        String[] headerWithWrongOrder = { "productId", " description", " name", " price", " category" };

        // THEN

        // no expection = OK
        InputToOutputConverterAndWriterImpl.assertCorrectHeaderLine(headerWithSpaces);

        assertThrows(RuntimeException.class, () -> {
            InputToOutputConverterAndWriterImpl.assertCorrectHeaderLine(headerWithWrongOrder);
        });

    }

}
