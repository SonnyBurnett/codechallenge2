package test.local.util.impl.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.local.util.FileReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements FileReader {
    private final Logger logger = LoggerFactory.getLogger(CSVReader.class);

    public List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
            if (inputStream == null) {
                throw new NullPointerException("inputStream was null");
            }
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            streamReader.close();

        } catch (Exception ex) {
            logger.error("cannot open or read input file: {}", ex.getLocalizedMessage());
        }
        return lines;
    }
}
