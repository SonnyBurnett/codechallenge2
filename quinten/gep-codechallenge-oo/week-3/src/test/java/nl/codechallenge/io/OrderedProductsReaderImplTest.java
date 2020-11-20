package nl.codechallenge.io;

import nl.codechallenge.model.Country;
import nl.codechallenge.model.OrderedProduct;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OrderedProductsReaderImplTest {

    @Test
    void readExampleFile() throws IOException {
        OrderedProductsReader orderedProductsReader = new OrderedProductsReaderImpl();
        List<OrderedProduct> products = orderedProductsReader.read(Paths.get("src/test/resources/inputs.csv"));

        List<OrderedProduct> expected = Arrays.asList(
                new OrderedProduct(16, "Henry Been", "Pepernoten", 3.23, 0.5, Country.Netherlands),
                new OrderedProduct(21, "Pietje de Boer", "Monitor", 466.19, 2.5, Country.Belgium),
                new OrderedProduct(16, "Henry Been", "Jas", 128.12, 2.2, Country.Netherlands)
        );
        assertThat(products, hasSize(3));
        assertThat(products, containsInAnyOrder(expected.toArray()));
    }

}
