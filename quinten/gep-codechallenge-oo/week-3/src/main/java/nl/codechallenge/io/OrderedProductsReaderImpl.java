package nl.codechallenge.io;

import com.opencsv.bean.CsvToBeanBuilder;
import nl.codechallenge.model.OrderedProduct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class OrderedProductsReaderImpl implements OrderedProductsReader {
    @Override
    public List<OrderedProduct> read(Path file) throws IOException {
        try (Reader reader = Files.newBufferedReader(file)) {
            return new CsvToBeanBuilder<OrderedProduct>(reader).withType(OrderedProduct.class).build().parse();
        }
    }
}
