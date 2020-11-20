import io.CustomerFileReader;
import io.ShippingInfoWriter;
import model.customer.Customer;
import model.shippers.ShippingInfoDeterminer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Assignment3 {

    private Map<Long, Customer> customerDatabase = new HashMap<>();

    public void run() throws IOException {
        String inputFile = "FloorDeJong3/src/main/resources/input.csv";
        String outputFile = "FloorDeJong3/src/main/resources/output.csv";

        new CustomerFileReader().read(inputFile, customerDatabase);

        new ShippingInfoDeterminer().determineInfo(customerDatabase);

        new ShippingInfoWriter().write(outputFile, customerDatabase);
    }
}
