package local.test.util;

import local.test.model.Product;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static local.test.CodingChallenge.HEADER_ROW;

public class ListManipulator {

    public List<String> mergeHeaderWithProducts(List<Product> products) {
        return Stream.of(
                List.of(HEADER_ROW),
                convertProductToCSVStringList(products)
        )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    protected List<String> convertProductToCSVStringList(List<Product> products) {
        return products
                .stream()
                .map(Product::toCSVString)
                .collect(Collectors.toList());
    }
}
