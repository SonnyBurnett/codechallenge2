import util.CSVReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductProcessor {
    private CSVReader reader;
    private List<Product> products;

    public ProductProcessor(String path) {
        reader = new CSVReader(path);
        this.products = this.parseLinesToProducts();
    }

    public List<Product> parseLinesToProducts() {
        return reader.getLines().stream().map(line -> parseStringToProduct(line)).collect(Collectors.toList());
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product parseStringToProduct(String[] input) {
        String type = input[4];

        switch(type) {
            case "shirts":
                return new Shirt(input[0], input[1], input[2], new BigDecimal(input[3]));
            case "pants":
                return new Pants(input[0], input[1], input[2], new BigDecimal(input[3]));
            default:
                throw new IllegalArgumentException();
        }
    }

    private void setProductsAsLines() {
        List<String[]> mutatedList = products.stream().map(product -> parseProductToString(product)).collect(Collectors.toList());
        reader.setLines(mutatedList);
    }

    private String[] parseProductToString(Product product) {
        return new String[]{product.getProductId(), product.getName(), product.getDescription(), product.getPrice().setScale(1, RoundingMode.HALF_UP).toString(), product.getCategory()};
    }

    public ProductProcessor filterBelowPrice(int priceThreshold) {
        Predicate<Product> isLargerThanThreshold = p -> p.getPrice().compareTo(new BigDecimal(priceThreshold)) > 0;

        this.products = this.products.stream()
                .filter(isLargerThanThreshold)
                .collect(Collectors.toList());
        return this;
    }

    public ProductProcessor convertPrices(CurrencyConverter converter) {
        this.products = this.products.stream().map(product -> {
            product.convertPrice(converter);
            return product;
        }).collect(Collectors.toList());
        return this;
    }

    public void save(String filePath) {
        this.setProductsAsLines();
        reader.save(filePath);
    }
}
