package com.ing.challenge.model;

import com.ing.challenge.parser.Parsable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record Product(
        int productId,
        String name,
        String description,
        double price,
        String category) implements Parsable {

    public static Product fromCsv(CSVRecord record) throws IllegalArgumentException {
        Pair<Boolean, List<String>> isComplete = containsValues(record, "productId", "name",
                "description", "price", "category");
        if (isComplete.getOne()) {
            int productId = 0;
            double price = 0;
            try {
                productId = Integer.parseInt(record.get("productId"));
                price = Double.parseDouble(record.get("price"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("value(s) could not be parsed to an Integer");
            }
            String name = Objects.requireNonNull(record.get("name"));
            String description = Objects.requireNonNull(record.get("description"));
            String category = Objects.requireNonNull(record.get("category"));
            return new Product(productId, name, description, price, category);
        } else {
            throw new IllegalArgumentException("Not all records could be found. Missing: "
                    + Arrays.toString(isComplete.getTwo().toArray()));
        }
    }

    @Override
    public void toCsv(File file) throws IOException {
        try (CSVPrinter csvPrinter = CSVFormat.DEFAULT
                .withHeader("productId", "name", "description", "price", "category")
                .print(file, StandardCharsets.UTF_8)) {
            csvPrinter.printRecord(this.productId, this.name, this.description, this.price, this.category);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static Pair<Boolean, List<String>> containsValues(CSVRecord record, String... fields) {
        var absent = Arrays.stream(fields)
                .filter(rec -> !record.isMapped(rec))
                .collect(Collectors.toList());
        if (absent.isEmpty()) {
            return Tuples.pair(true, Collections.emptyList());
        } else {
            return Tuples.pair(false, absent);
        }
    }
}