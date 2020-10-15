package test.local.model;

import test.local.util.CashConverter;

import java.util.Objects;

public class Product {
    private long productId;
    private String name;
    private String description;
    private int dollarPrice;
    private double euroPrice;
    private Category category;

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDollarPrice() {
        return dollarPrice;
    }

    public Category getCategory() {
        return category;
    }

    public double getEuroPrice() {
        return euroPrice;
    }

    public Product(long productId, String name, String description, int dollarPrice, Category category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.dollarPrice = dollarPrice;
        this.euroPrice = CashConverter.ConvertDollarToEuro(dollarPrice);
        this.category = category;
    }

    public static Product invalid() {
        return new Product(-1, "", "", 0, null);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                Double.compare(product.dollarPrice, dollarPrice) == 0 &&
                name.equals(product.name) &&
                Objects.equals(description, product.description) &&
                category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, description, dollarPrice, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + dollarPrice +
                ", category=" + category +
                '}';
    }

    public String toCSVString() {
        return "" + productId + ", " + name + ", " + description + ", " + euroPrice + ", " + category.toString().toLowerCase();
    }
}
