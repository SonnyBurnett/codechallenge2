package test.local.util;

import test.local.CodingChallenge;
import test.local.model.Product;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListManipulator {

    public List<String> mergeHeaderWithProducts(List<Product> products) {
        return Stream.of(
                List.of(CodingChallenge.HEADER_ROW),
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
