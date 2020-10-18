package ex01;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Object Oriented Programing: First Expert Assignment</h1>
 * reasoning is documented in the @see gep2/sre-gep2-ex01/README.md readme.
 */

public class Expert01 {
    /**
     * This solves the given expert01, in here I re-used mandatory main also as the control flow for the solution;
     * <b>Note that:</b> in this case it is about how the Object are structured,
     * not on how you structure your control flow.
     *
     * @param args Default input arguments of the main method, not used.
     */

    public static void main(String[] args) {
        Products prod = new Products("List of Products");

        try {
            prod.load("gep2/sre-gep2-ex01/src/main/resources/001-experts-inputs.csv");

        } catch (Exception error) {
            System.out.println("NOT able to load");
        }

        prod.migrateValue("price");

        Map<String, String> priceProperties = new HashMap<>();
        priceProperties.put("price", "10.20");
        ProductDetail whatToFilter = new ProductDetail(priceProperties);
        prod.filterGreaterThanOrEqual(whatToFilter, "price");

        try {
            prod.write("gep2/sre-gep2-ex01/src/main/resources/001-experts-outputs.csv");

        } catch (Exception error) {
            System.out.println("NOT able to write file.");
        }
    }
}
