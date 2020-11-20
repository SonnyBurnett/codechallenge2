package model.customer;

public class CustomerFactory {
    public Customer create(long customerId, String name, String country) {
        return new Customer(customerId, name, country);
    }
}
