import io.CustomerFileReader;
import io.ShippingInfoWriter;
import model.customer.Customer;
import model.shippers.ShippingInfoDeterminer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Assignment3 {

    private final Map<Long, Customer> customerDatabase = new HashMap<>();

    private final CustomerFileReader reader = new CustomerFileReader();
    private final ShippingInfoWriter writer = new ShippingInfoWriter();
    private final ShippingInfoDeterminer determiner = new ShippingInfoDeterminer();

    public void run(String inputFile, String outputFile) {
        try {
            reader.read(inputFile, customerDatabase);
            determiner.determineInfo(customerDatabase);
            writer.write(outputFile, customerDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
