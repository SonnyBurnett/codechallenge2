package ex03;

import java.util.HashMap;
import java.util.Set;

public class Customers {
    private final HashMap<Integer, Customer> customers;

    public Customers() {
        customers = new HashMap<>();
    }

    public void addCustomer(String sId, String name) {
        if (!this.customers.containsKey(Integer.parseInt(sId))) {
            this.customers.put(Integer.parseInt(sId), new Customer(name));
        }
    }

    public void addCustomerOrder(String sId, Order order) {
        if (this.customers.containsKey(Integer.parseInt(sId))) {
            Customer customer = new Customer(this.customers.get(Integer.parseInt(sId)).getName());
            customer.setOrdersList(this.customers.get(Integer.parseInt(sId)).getOrdersList());
            customer.setPackagesList(this.customers.get(Integer.parseInt(sId)).getPackagesList());
            this.customers.remove(Integer.parseInt(sId));
            customer.addOrderToCustomer(order);
            this.customers.put(Integer.parseInt(sId), customer);
        }
    }

    public void addCustomerPackage(String sId, Package aPackage) {
        if (this.customers.containsKey(Integer.parseInt(sId))) {
            Customer customer = new Customer(this.customers.get(Integer.parseInt(sId)).getName());
            customer.setOrdersList(this.customers.get(Integer.parseInt(sId)).getOrdersList());
            customer.setPackagesList(this.customers.get(Integer.parseInt(sId)).getPackagesList());
            this.customers.remove(Integer.parseInt(sId));
            customer.addPackageToCustomer(aPackage);
            this.customers.put(Integer.parseInt(sId), customer);
        }
    }

    public void generateCustomersPackage() {
        ShippingInformation sInfo = new ShippingInformation();
        Set<Integer> keys = this.customers.keySet();
        Integer[] array = keys.toArray(new Integer[0]);

        for (Integer a : array) {
            if (this.customers.containsKey(a)) {
                addCustomerPackage(a.toString(), sInfo.calcShipping(this.customers.get(a).getOrdersList()));
            }
        }
    }

    public String generatePrintingLines() {
        StringBuilder toPrint = new StringBuilder();
        Set<Integer> keys = this.customers.keySet();
        Integer[] array = keys.toArray(new Integer[0]);

        for (Integer a : array) {
            if (this.customers.containsKey(a)) {
                toPrint.append(a).append(", ").append(this.customers.get(a).generatePrintingLine()).append("\n");
            }
        }

        return toPrint.toString();
    }
}