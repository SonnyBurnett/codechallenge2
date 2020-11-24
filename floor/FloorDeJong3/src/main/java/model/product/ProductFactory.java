package model.product;

public class ProductFactory {
    public Product create(String productName, double price, double weight) {
        return new Product(productName, price, weight);
    }
}
