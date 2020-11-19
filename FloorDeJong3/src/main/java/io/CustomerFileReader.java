package io;

import model.Customer;
import model.CustomerDatabase;
import model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CustomerFileReader {

    public void read(String fileName, CustomerDatabase customerDatabase) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Check the information of the header
            reader.readLine();

            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                String[] data = nextLine.split(",");
                System.out.println(Arrays.toString(data));

                if (data.length != 6) {
                    System.out.println("Error: Incorrect number of input fields. Expected 6, received " + data.length +
                            "\nData:" + Arrays.toString(data));
                    continue;
                }

                Customer customer = customerDatabase.getCustomer(Long.parseLong(data[0]));
                Product product = new Product(data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                if (customer == null) {
                    Customer newCustomer = new Customer(Long.parseLong(data[0]), data[1], data[5]);
                    newCustomer.addProduct(product);
                    customerDatabase.addCustomer(newCustomer);
                } else {
                    customer.addProduct(product);
                }
            }
        }
    }
}
