package model.shippers;

import model.customer.Customer;

import java.util.Map;

public class ShippingInfoDeterminer {

    public void determineInfo(Map<Long, Customer> customerDatabase){
        for (Customer customer: customerDatabase.values()) {
            determineInfo(customer);
        }
    }

    void determineInfo(Customer customer) {
        if (customer.getCountry().equalsIgnoreCase("Belgium")) {
            customer.setShipper(Shipper.BELGIOPOSTO);
        } else if (customer.getCountry().equalsIgnoreCase("Netherlands") && customer.getTotalWeight() <10) {
            customer.setShipper(Shipper.POSTNL);
        } else {
            customer.setShipper(Shipper.DHL);
        }
    }
}
