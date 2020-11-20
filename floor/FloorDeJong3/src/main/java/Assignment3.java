import io.CustomerFileReader;
import model.CustomerDatabase;

import java.io.IOException;

public class Assignment3 {

    private CustomerDatabase customerDatabase = new CustomerDatabase();

    public void run() throws IOException {
        String fileName = "FloorDeJong3/src/main/resources/input.csv";

        // Read file
        new CustomerFileReader().read(fileName, customerDatabase);

        // Do logic


        // Write file
//        new ShippingInfoWriter().write();
    }
}
