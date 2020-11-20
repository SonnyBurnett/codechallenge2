package nl.codechallenge.io;

import nl.codechallenge.model.OrderedProduct;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface OrderedProductsReader {
    List<OrderedProduct> read(Path file) throws IOException;
}
