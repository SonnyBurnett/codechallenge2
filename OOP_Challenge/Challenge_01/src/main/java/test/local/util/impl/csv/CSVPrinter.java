package test.local.util.impl.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.local.util.FilePrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CSVPrinter implements FilePrinter {
    private final Logger logger = LoggerFactory.getLogger(CSVPrinter.class);

    @Override
    public void printFile(String filename, List<String> content) {
        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            logger.info("OUTPUT: {}", filename);
            content.forEach(line -> {
                logger.info(line);
                pw.println(line);
            });
        } catch (FileNotFoundException e) {
            logger.error("Cannot write output file", e);
        }
    }
}
