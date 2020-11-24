package io;

import model.customer.Customer;
import model.customer.CustomerFactory;
import model.product.Product;
import model.product.ProductFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class CustomerFileReader {

    private ProductFactory productFactory;
    private CustomerFactory customerFactory;

    public CustomerFileReader(ProductFactory productFactory, CustomerFactory customerFactory) {
        this.productFactory = productFactory;
        this.customerFactory = customerFactory;
    }

    public CustomerFileReader() {
        this(new ProductFactory(), new CustomerFactory());
    }

    public void read(String fileName, Map<Long, Customer> customerDatabase) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            if (checkHeaderInfo(reader.readLine())) {
                String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    processDate(customerDatabase, nextLine);
                }
            }
        }
    }

    void processDate(Map<Long, Customer> customerDatabase, String line) {
        String[] data = line.split(",");
        if (!checkData(data)) {
            return;
        }

        Customer customer = customerDatabase.get(Long.parseLong(data[0]));
        Product product = productFactory.create(data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
        if (customer == null) {
            customer = customerFactory.create(Long.parseLong(data[0]), data[1], data[5]);
            customerDatabase.put(Long.parseLong(data[0]), customer);
        }

        customer.addProduct(product);

    }

    boolean checkHeaderInfo(String header) {
        String[] expectedHeaders = new String[]{"CustomerId", "Name", "Product", "Price", "Weight", "Country"};
        String[] columns = header.split(",");

        if (columns.length != 6) {
            System.out.println("Error: Incorrect number of columns. Expected 6 fields, received " + columns.length +
                    "\nData: " + Arrays.toString(columns));
            return false;
        }

        int nrIncorrectHeaders = 0;
        for (int i=0; i<columns.length; i++) {
            nrIncorrectHeaders += checkFieldName(expectedHeaders[i], columns[i]);
        }

        return nrIncorrectHeaders == 0;
    }

    int checkFieldName(String expected, String received) {

        if (!received.equalsIgnoreCase(expected)) {
            System.out.println(String.format("Error: Incorrect column type. Expected: %s, received: %s", expected
                    , received));
            return 1;
        }
        return 0;
    }

    boolean checkData(String[] data) {
        if (data.length != 6) {
            System.out.println("Error: Incorrect number of input fields. Expected 6 fields, received " + data.length +
                    "\nData: " + Arrays.toString(data));
            return false;
        }

        int nrIncorrectFields = 0;
        nrIncorrectFields += checkFieldData("customerId", "long", data[0]);
        nrIncorrectFields += checkFieldData("price", "double", data[3]);
        nrIncorrectFields += checkFieldData("weight", "double", data[4]);

        return nrIncorrectFields == 0;
    }

    int checkFieldData(String fieldName, String expectedType, String receivedData) {
        try {
            if (expectedType.equals("long")) {
                Long.parseLong(receivedData);
            } else if(expectedType.equals("double")) {
                Double.parseDouble(receivedData);
            }
        } catch (NumberFormatException e) {
            System.out.println(String.format("Error: Incorrect format of %s. Expected a %s, received %s", fieldName
                    , expectedType, receivedData));
            return 1;
        }

        return 0;
    }
}
