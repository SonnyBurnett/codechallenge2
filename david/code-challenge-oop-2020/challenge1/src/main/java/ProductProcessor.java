import util.CSVReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductProcessor extends CSVReader {
    private List<Product> products;

    public ProductProcessor(String path) {
        super(path);
        this.products = this.parseLinesToProducts();
    }

    public List<Product> parseLinesToProducts() {
        return lines.stream().map(line -> parseStringToProduct(line)).collect(Collectors.toList());
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product parseStringToProduct(String[] input) {
        String type = input[4];

        switch(type) {
            case "shirts":
                return new Shirt(input[0], input[1], input[2], Integer.parseInt(input[3]));
            case "pants":
                return new Pants(input[0], input[1], input[2], Integer.parseInt(input[3]));
            default:
                throw new IllegalArgumentException();
        }
    }

    private void setProductsAsLines() {
        this.lines = products.stream().map(product -> parseProductToString(product)).collect(Collectors.toList());
    }

    private String[] parseProductToString(Product product) {
        return new String[]{product.getProductId(), product.getName(), product.getDescription(), Integer.toString(product.getPrice()), product.getCategory()};
    }

    public ProductProcessor filterBelowPrice(int priceThreshold) {
        this.products = this.products.stream().filter(product -> product.getPrice() > priceThreshold).collect(Collectors.toList());
        return this;
    }

    public void save(String filePath) {
        this.setProductsAsLines();
        super.save(filePath);
    }
}
