package model;

public class Product {
    private String productName;
    private double price;
    private double weight;

    public Product(String productName, double price, double weight) {
        this.productName = productName;
        this.price = price;
        this.weight = weight;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
}
