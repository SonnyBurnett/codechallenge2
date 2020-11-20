import io.CustomerFileReader;
import model.customer.Customer;
import model.shippers.ShippingInfoDeterminer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Assignment3 {

    private Map<Long, Customer> customerDatabase = new HashMap<>();

    public void run() throws IOException {
        String fileName = "FloorDeJong3/src/main/resources/input.csv";

        new CustomerFileReader().read(fileName, customerDatabase);

        new ShippingInfoDeterminer().determineInfo(customerDatabase);

        // Write file
//        new ShippingInfoWriter().write();
    }
}
