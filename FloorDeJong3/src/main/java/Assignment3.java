import io.CustomerFileReader;
import model.Customer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Assignment3 {

    private Map<Long, Customer> customerDatabase = new HashMap<>();

    public void run() throws IOException {
        String fileName = "FloorDeJong3/src/main/resources/input.csv";

        // Read file
        new CustomerFileReader().read(fileName, customerDatabase);

        // Do logic


        // Write file
//        new ShippingInfoWriter().write();
    }
}
