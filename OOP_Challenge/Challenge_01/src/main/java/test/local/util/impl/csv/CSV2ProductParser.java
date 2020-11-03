package test.local.util.impl.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.local.model.Category;
import test.local.model.Product;
import test.local.model.impl.Pants;
import test.local.model.impl.Shirts;

import java.util.Arrays;

public final class CSV2ProductParser {
    private static final Logger logger = LoggerFactory.getLogger(CSV2ProductParser.class);
    private CSV2ProductParser() {
        // no creation of CSV 2 Product parser class
    }

    public static Product getProductFromCSVLine(String csvLine) {
        if (csvLine.isEmpty() || csvLine.isBlank()) {
            return Product.invalid();
        }

        String[] lines = csvLine.split(",");

        if (lines.length != 5) {
            logger.warn("not all information for this product is filled correctly, returning invalid product");
            return Product.invalid();
        }

        if (!allLinesValid(lines)) {
            return Product.invalid();
        }
        try {
            long id = Long.parseLong(lines[0].trim());
            int price = Integer.parseInt(lines[3].trim());

            Category category = Category.valueOf(lines[4].trim().toUpperCase());
            if (category == Category.PANTS) {
                return new Pants(id, lines[1].trim(), lines[2].trim(), price);
            } else if (category == Category.SHIRTS) {
                return new Shirts(id, lines[1].trim(), lines[2].trim(), price);
            } else {
                return Product.invalid();
            }

        } catch (Exception ex) {
            logger.error("cannot parse input to valid product, {}", ex.getLocalizedMessage());
            return Product.invalid();
        }

    }

    protected static boolean allLinesValid(String[] lines) {
        return Arrays.stream(lines).allMatch(CSV2ProductParser::isLineValid);
    }

    protected static boolean isLineValid(String line) {
        if (line == null || line.isEmpty() || line.isBlank()) {
            return false;
        }
        return true;
    }

}
