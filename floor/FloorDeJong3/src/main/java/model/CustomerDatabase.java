package model;

import java.util.HashMap;
import java.util.Map;

public class CustomerDatabase {
    private Map<Long, Customer> database;

    public CustomerDatabase() {
        this.database = new HashMap<>();
    }

    public void addCustomer(Customer newCustomer) {
        database.put(newCustomer.getCustomerId(), newCustomer);
    }

    public Customer getCustomer(long customerId) {
        return database.get(customerId);
    }
}
