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
    protected static final String ERROR_MESSAGE = "Failed to write to file";

    @Override
    public void write(File file, Iterable<?> obj) throws IOException {
        try (CSVPrinter csvPrinter = CSVFormat.DEFAULT
                .withHeader("msg", "name", "type", "attributes")
                .print(file, StandardCharsets.UTF_8)) {
            csvPrinter.printRecord(obj);
        } catch (IOException e) {
            logger.error(() -> ERROR_MESSAGE, e);
            throw new IOException(ERROR_MESSAGE, e);
        }
    }
}
