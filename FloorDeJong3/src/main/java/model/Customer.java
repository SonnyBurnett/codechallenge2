package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final long customerId;
    private String name;
    private String country;
    private List<Product> productList;

    public Customer(long customerId, String name, String country) {
        this.customerId = customerId;
        this.name = name;
        this.country = country;
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public long getCustomerId() {
        return customerId;
    }
}
