package local.test.util.impl.csv;

import local.test.model.Category;
import local.test.model.Product;

import java.util.Arrays;

public final class CSV2ProductParser {
    private CSV2ProductParser() {
        // no creation of CSV 2 Product parser class
    }

    public static Product getProductFromCSVLine(String csvLine) {
        if (csvLine.isEmpty() || csvLine.isBlank()) {
            return Product.invalid();
        }

        String[] lines = csvLine.split(",");

        if (lines.length != 5) {
            System.out.println("not all information for this product is filled correctly");
            return Product.invalid();
        }

        if (!allLinesValid(lines)){
            return Product.invalid();
        }
        try {
            long id = Long.parseLong(lines[0].trim());
            int price = Integer.parseInt(lines[3].trim());

            Category category = Category.valueOf(lines[4].trim().toUpperCase());

            return new Product(id, lines[1].trim(), lines[2].trim(), price, category);

        } catch (Exception ex){
            System.out.println("cannot parse input to valid product");
            return Product.invalid();
        }

    }

    protected static boolean allLinesValid(String[] lines){
        return Arrays.stream(lines).allMatch(CSV2ProductParser::isLineValid);
    }
    protected static boolean isLineValid(String line){
        if (line == null || line.isEmpty() || line.isBlank()){
            return false;
        }
        return true;
    }

}
