package io;

import model.customer.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ShippingInfoWriter {

    private FileWriterFactory fileWriterFactory;

    public ShippingInfoWriter(FileWriterFactory fileWriterFactory) {
        this.fileWriterFactory = fileWriterFactory;
    }

    public ShippingInfoWriter() {
        this(new FileWriterFactory());
    }

    public void write(String fileLocation, Map<Long, Customer> customerDatabase) throws IOException {
        try (FileWriter writer = fileWriterFactory.create(fileLocation)) {
            String fieldNames = "CustomerId,Name,Shipper,Duration,ShippingCost\n";
            writer.write(fieldNames);

            for (Customer customer: customerDatabase.values()) {
                String line = customer.getShippingInfo() + "\n";
                writer.write(line);
            }
        }
    }
}
