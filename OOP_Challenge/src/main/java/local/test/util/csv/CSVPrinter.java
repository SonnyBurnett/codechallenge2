package local.test.util.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CSVPrinter {
    private final Logger logger = LoggerFactory.getLogger(CSVPrinter.class);
    public void printFile(String filename, List<String> dataLines) {
        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            logger.info("OUTPUT: {}", filename);
            dataLines.forEach(line -> {
                logger.info(line);
                pw.println(line);
            });
        } catch (FileNotFoundException e) {
            logger.error("Cannot write output file", e);
        }
    }
}
