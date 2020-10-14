package com.ing.challenge.parser;

import com.ing.challenge.model.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CsvProductParser implements Parser<Product> {
    /**
     * {@inheritDoc}
     *
     * @param fileName name of the file
     * @return A {@link List} of Object of type T
     * @see Parser#read(File)
     */
    @Override
    public List<Product> read(String fileName) {
        try (Reader in = new FileReader(fileName)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .parse(in);
            return StreamSupport.stream(records.spliterator(), false)
                    .map(Product::fromCsv)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param file A {@link File} object relating to A file on the system
     * @return A {@link List} of Object of type T
     * @see Parser#read(String)
     */
    @Override
    public List<Product> read(File file) {
        return read(file.getAbsolutePath());
    }

    @Override
    public boolean write(File file, Product product) {
        return write(file, List.of(product));
    }

    @Override
    public boolean write(File file, List<Product> products) {
        boolean hasFailed = false;
        try (CSVPrinter csvPrinter = CSVFormat.DEFAULT
                .withHeader("productId", "name", "description", "price", "category")
                .print(file, StandardCharsets.UTF_8)) {
            String[][] data = new String[products.size()][];
            int x = 0;
            for (var p : products) {
                data[x++] = new String[]{
                        String.valueOf(p.productId()),
                        p.name(),
                        p.description(),
                        String.valueOf(p.price()),
                        p.category()
                };
            }
            csvPrinter.printRecords((Object[]) data);
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            hasFailed = true;
            //throw e;
        }
        return !hasFailed;
    }
}
