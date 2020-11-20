package model.customer;

import model.product.Product;
import model.shippers.Shipper;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final long customerId;
    private String name;
    private String country;
    private List<Product> productList;
    private Shipper shipper;
    private int duration;
    private double shippingCosts;

    public Customer(long customerId, String name, String country) {
        this.customerId = customerId;
        this.name = name;
        this.country = country;
        this.productList = new ArrayList<>();
    }

    public double getTotalWeight() {
        double weight =  0;
        for (Product product: productList){
            weight += product.getWeight();
        }

        return weight;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;

        double totalWeight = getTotalWeight();
        setShippingCosts(shipper.determineCosts(totalWeight));
        setDuration(shipper.determineDuration(totalWeight));
    }

    public String getShippingInfo() {
        return String.format("%s,%s,%s,%s,%s", customerId, name, shipper, duration, shippingCosts);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public List<Product> getProductList() {
        return productList;
    }

    void setDuration(int duration) {
        this.duration = duration;
    }

    void setShippingCosts(double shippingCosts) {
        this.shippingCosts = shippingCosts;
    }
}
