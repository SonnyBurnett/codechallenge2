package com.ing.challenge.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CsvWriter implements GameWriter<Iterable<?>> {
    private static final Logger logger = LogManager.getLogger(CsvWriter.class);

    @Override
    public void write(File file, Iterable<?> obj) {
        try (CSVPrinter csvPrinter = CSVFormat.DEFAULT
                .withHeader("msg", "name", "type", "attributes")
                .print(file, StandardCharsets.UTF_8)) {
            csvPrinter.printRecord(obj);
        } catch (IOException e) {
            logger.error(() -> "Failed to ");
        }
    }
}
