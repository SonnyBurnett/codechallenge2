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

    public void addProduct(Product product) {
        productList.add(product);
    }

    public long getCustomerId() {
        return customerId;
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

    public Shipper getShipper() {
        return shipper;
    }

    public int getDuration() {
        return duration;
    }

    public double getShippingCosts() {
        return shippingCosts;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setShippingCosts(double shippingCosts) {
        this.shippingCosts = shippingCosts;
    }
}
